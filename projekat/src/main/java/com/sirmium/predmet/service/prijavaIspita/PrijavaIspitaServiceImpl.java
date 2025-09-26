package com.sirmium.predmet.service.prijavaIspita;

import com.sirmium.predmet.dto.PrijavaIspitaDTO;
import com.sirmium.predmet.model.PohadjanjePredmeta;
import com.sirmium.predmet.model.PrijavaIspita;
import com.sirmium.predmet.repository.PohadjanjePredmetaRepository;
import com.sirmium.predmet.repository.PrijavaIspitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrijavaIspitaServiceImpl implements PrijavaIspitaService {

    @Autowired
    private PrijavaIspitaRepository prijavaRepo;

    @Autowired
    private PohadjanjePredmetaRepository pohadjanjeRepo;

    @Override
    public PrijavaIspitaDTO create(PrijavaIspitaDTO dto) {
        PrijavaIspita entity = new PrijavaIspita();
        entity.setDatumPrijave(LocalDateTime.now());
        entity.setDatumIspita(dto.getDatumIspita());
        entity.setStatus(dto.isStatus());

        PohadjanjePredmeta pohadjanje = pohadjanjeRepo.findById(dto.getPohadjanjeId())
                .orElseThrow(() -> new RuntimeException("Pohadjanje predmeta nije pronađeno"));

        entity.setPohadjanje(pohadjanje);
        prijavaRepo.save(entity);

        return toDto(entity);
    }

    @Override
    public List<PrijavaIspitaDTO> findAll() {
        return prijavaRepo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public PrijavaIspitaDTO findById(Long id) {
        PrijavaIspita entity = prijavaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Prijava ne postoji"));
        return toDto(entity);
    }

    @Override
    public PrijavaIspitaDTO update(Long id, PrijavaIspitaDTO dto) {
        PrijavaIspita entity = prijavaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Prijava ne postoji"));

        entity.setDatumIspita(dto.getDatumIspita());
        entity.setStatus(dto.isStatus());

        return toDto(prijavaRepo.save(entity));
    }

    @Override
    public void delete(Long id) {
        prijavaRepo.deleteById(id);
    }

    @Override
    public List<PrijavaIspitaDTO> getDostupnePrijave(Long studentId) {
        List<PrijavaIspita> prijave = prijavaRepo.findDostupnePrijave(studentId);
        return prijave.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public PrijavaIspitaDTO prijaviIspit(Long prijavaId) {
        PrijavaIspita prijava = prijavaRepo.findById(prijavaId)
            .orElseThrow(() -> new RuntimeException("Prijava ne postoji."));

        if (prijava.isStatus()) {
            throw new RuntimeException("Ispit je već prijavljen.");
        }

        prijava.setStatus(true);
        prijava.setDatumPrijave(LocalDateTime.now());

        prijava = prijavaRepo.save(prijava);
        return toDto(prijava);
    }

    @Override
    public void kreirajPrijaveZaPredmet(Long predmetId, LocalDateTime datumIspita) {
        List<PohadjanjePredmeta> pohadjanja = pohadjanjeRepo.findByPredmetId(predmetId);

        for (PohadjanjePredmeta pohadjanje : pohadjanja) {
            PrijavaIspita prijava = new PrijavaIspita();
            prijava.setDatumIspita(datumIspita);
            prijava.setDatumPrijave(null);
            prijava.setStatus(false);
            prijava.setPohadjanje(pohadjanje);
            prijavaRepo.save(prijava);
        }
    }

    private PrijavaIspitaDTO toDto(PrijavaIspita entity) {
        PrijavaIspitaDTO dto = new PrijavaIspitaDTO();
        dto.setId(entity.getId());
        dto.setDatumPrijave(entity.getDatumPrijave());
        dto.setDatumIspita(entity.getDatumIspita());
        dto.setStatus(entity.isStatus());
        dto.setPohadjanjeId(entity.getPohadjanje().getId());
        dto.setPredmetNaziv(entity.getPohadjanje().getPredmet().getNaziv());
        return dto;
    }
}