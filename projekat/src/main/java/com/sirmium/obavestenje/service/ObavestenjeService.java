package com.sirmium.obavestenje.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sirmium.obavestenje.dto.ObavestenjeDTO;
import com.sirmium.obavestenje.dto.ObavestenjeResponseDTO;
import com.sirmium.obavestenje.model.Obavestenje;
import com.sirmium.obavestenje.repository.ObavestenjeRepository;

import com.sirmium.predmet.model.PohadjanjePredmeta;
import com.sirmium.predmet.repository.PohadjanjePredmetaRepository;
import com.sirmium.predmet.repository.PredmetRepository;
import com.sirmium.predmet.model.Predmet;

import com.sirmium.user.model.User;
import com.sirmium.user.repository.UserRepository;

@Service
@Transactional
public class ObavestenjeService {

	private final ObavestenjeRepository obavestenjeRepo;
	private final PredmetRepository predmetRepo;
	private final UserRepository userRepo;
	private final PohadjanjePredmetaRepository pohadjanjePredmetaRepo;

	public ObavestenjeService(ObavestenjeRepository obavestenjeRepo, PredmetRepository predmetRepo,
			UserRepository userRepo, PohadjanjePredmetaRepository pohadjanjePredmetaRepo) {
		this.obavestenjeRepo = obavestenjeRepo;
		this.predmetRepo = predmetRepo;
		this.userRepo = userRepo;
		this.pohadjanjePredmetaRepo = pohadjanjePredmetaRepo;
	}

	public ObavestenjeResponseDTO kreirajObavestenje(ObavestenjeDTO dto) {
		// Dobavljanje ulogovanog korisnika
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User autor = userRepo.findByEmail(username)
				.orElseThrow(() -> new RuntimeException("Ulogovani korisnik nije pronađen"));

		// Pronalaženje predmeta
		Predmet predmet = predmetRepo.findById(dto.getPredmetId())
				.orElseThrow(() -> new RuntimeException("Predmet nije pronađen"));

		// Ako datum nije prosleđen – automatski ga postavljamo
		LocalDate datumZaUpis = dto.getDatum() != null ? dto.getDatum() : LocalDate.now();

		// Kreiranje i čuvanje obaveštenja
		Obavestenje obavestenje = new Obavestenje();
		obavestenje.setNaslov(dto.getNaslov());
		obavestenje.setTekst(dto.getTekst());
		obavestenje.setDatum(datumZaUpis);
		obavestenje.setPredmet(predmet);
		obavestenje.setAutor(autor);
		obavestenje.setAktivan(true);

		Obavestenje sacuvano = obavestenjeRepo.save(obavestenje);

		// Mapiranje u ResponseDTO
		return new ObavestenjeResponseDTO(sacuvano.getId(), sacuvano.getNaslov(), sacuvano.getTekst(),
				sacuvano.getDatum(), predmet.getId(), predmet.getNaziv(), autor.getId(),
				autor.getIme() + " " + autor.getPrezime());
	}

	public ObavestenjeResponseDTO izmeniObavestenje(Long id, ObavestenjeDTO dto) {
		Obavestenje obavestenje = obavestenjeRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Obaveštenje nije pronađeno"));

		// Provera autora (ulogovanog korisnika)
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User ulogovani = userRepo.findByEmail(username)
				.orElseThrow(() -> new RuntimeException("Ulogovani korisnik nije pronađen"));

		if (!obavestenje.getAutor().getId().equals(ulogovani.getId())) {
			throw new RuntimeException("Nemate dozvolu da izmenite ovo obaveštenje");
		}

		obavestenje.setNaslov(dto.getNaslov());
		obavestenje.setTekst(dto.getTekst());
		obavestenje.setDatum(dto.getDatum() != null ? dto.getDatum() : LocalDate.now());

		// Promena predmeta ako je potrebno
		if (!obavestenje.getPredmet().getId().equals(dto.getPredmetId())) {
			Predmet noviPredmet = predmetRepo.findById(dto.getPredmetId())
					.orElseThrow(() -> new RuntimeException("Predmet nije pronađen"));
			obavestenje.setPredmet(noviPredmet);
		}

		Obavestenje sacuvano = obavestenjeRepo.save(obavestenje);

		return new ObavestenjeResponseDTO(sacuvano.getId(), sacuvano.getNaslov(), sacuvano.getTekst(),
				sacuvano.getDatum(), sacuvano.getPredmet().getId(), sacuvano.getPredmet().getNaziv(),
				sacuvano.getAutor().getId(), sacuvano.getAutor().getIme() + " " + sacuvano.getAutor().getPrezime());
	}

	public void obrisiObavestenje(Long id) {
		Obavestenje obavestenje = obavestenjeRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Obaveštenje nije pronađeno"));

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User ulogovani = userRepo.findByEmail(username)
				.orElseThrow(() -> new RuntimeException("Ulogovani korisnik nije pronađen"));

		if (!obavestenje.getAutor().getId().equals(ulogovani.getId())) {
			throw new RuntimeException("Nemate dozvolu da obrišete ovo obaveštenje");
		}

		obavestenjeRepo.delete(obavestenje);
	}

	public List<ObavestenjeResponseDTO> findObavestenjaZaProfesora() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User profesor = userRepo.findByEmail(username)
				.orElseThrow(() -> new RuntimeException("Ulogovani profesor nije pronađen"));

		return obavestenjeRepo.findByAutorId(profesor.getId()).stream()
				.map(obavestenje -> new ObavestenjeResponseDTO(obavestenje.getId(), obavestenje.getNaslov(),
						obavestenje.getTekst(), obavestenje.getDatum(), obavestenje.getPredmet().getId(),
						obavestenje.getPredmet().getNaziv(), obavestenje.getAutor().getId(),
						obavestenje.getAutor().getIme() + " " + obavestenje.getAutor().getPrezime()))
				.collect(Collectors.toList());
	}

	public List<ObavestenjeResponseDTO> findObavestenjaZaStudenta(Long studentId) {
		
		List<PohadjanjePredmeta> pohadjanja = pohadjanjePredmetaRepo.findByStudentId(studentId);

		return pohadjanja.stream().filter(PohadjanjePredmeta::isAktivan)
				.flatMap(p -> p.getPredmet().getObavestenja().stream()).filter(Obavestenje::isAktivan) // Samo aktivna
																										// obaveštenja
				.map(obavestenje -> new ObavestenjeResponseDTO(obavestenje.getId(), obavestenje.getNaslov(),
						obavestenje.getTekst(), obavestenje.getDatum(), obavestenje.getPredmet().getId(),
						obavestenje.getPredmet().getNaziv(), obavestenje.getAutor().getId(),
						obavestenje.getAutor().getIme() + " " + obavestenje.getAutor().getPrezime()))
				.collect(Collectors.toList());
	}

	public List<ObavestenjeResponseDTO> svaObavestenjaDTO() {
		return obavestenjeRepo.findAll().stream()
				.map(obavestenje -> new ObavestenjeResponseDTO(obavestenje.getId(), obavestenje.getNaslov(),
						obavestenje.getTekst(), obavestenje.getDatum(), obavestenje.getPredmet().getId(),
						obavestenje.getPredmet().getNaziv(), obavestenje.getAutor().getId(),
						obavestenje.getAutor().getIme() + " " + obavestenje.getAutor().getPrezime()))
				.collect(Collectors.toList());
	}

	public List<Obavestenje> svaObavestenja() {
		return obavestenjeRepo.findAll();
	}

	public Obavestenje nadjiPoId(Long id) {
		return obavestenjeRepo.findById(id).orElseThrow(() -> new RuntimeException("Obaveštenje nije pronađeno"));
	}

	// Dodatna metoda za soft delete
	public void deaktivirajObavestenje(Long id) {
		Obavestenje obavestenje = obavestenjeRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Obaveštenje nije pronađeno"));

		obavestenje.setAktivan(false);
		obavestenjeRepo.save(obavestenje);
	}

	// Metoda za pronalaženje obaveštenja po predmetu
	public List<ObavestenjeResponseDTO> findObavestenjaByPredmetId(Long predmetId) {
		return obavestenjeRepo.findByPredmetId(predmetId).stream()
				.map(obavestenje -> new ObavestenjeResponseDTO(obavestenje.getId(), obavestenje.getNaslov(),
						obavestenje.getTekst(), obavestenje.getDatum(), obavestenje.getPredmet().getId(),
						obavestenje.getPredmet().getNaziv(), obavestenje.getAutor().getId(),
						obavestenje.getAutor().getIme() + " " + obavestenje.getAutor().getPrezime()))
				.collect(Collectors.toList());
	}
}