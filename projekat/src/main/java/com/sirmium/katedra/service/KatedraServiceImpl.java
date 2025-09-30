package com.sirmium.katedra.service;

import com.sirmium.katedra.dto.KatedraCreateUpdateDTO;
import com.sirmium.katedra.dto.KatedraDTO;
import com.sirmium.katedra.model.Katedra;
import com.sirmium.katedra.repository.KatedraRepository;
import com.sirmium.katedra.service.KatedraService;
import com.sirmium.departman.model.Departman;
import com.sirmium.departman.repository.DepartmanRepository;
import com.sirmium.profesor.model.Profesor;
import com.sirmium.profesor.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class KatedraServiceImpl implements KatedraService {

    private final KatedraRepository katedraRepository;
    private final DepartmanRepository departmanRepository;
    private final ProfesorRepository profesorRepository;

    @Autowired
    public KatedraServiceImpl(KatedraRepository katedraRepository,
                             DepartmanRepository departmanRepository,
                             ProfesorRepository profesorRepository) {
        this.katedraRepository = katedraRepository;
        this.departmanRepository = departmanRepository;
        this.profesorRepository = profesorRepository;
    }

    @Override
    public KatedraDTO create(KatedraDTO dto) {
        Departman departman = departmanRepository.findById(dto.getDepartman().getId())
            .orElseThrow(() -> new RuntimeException("Departman nije pronađen"));

        Profesor sefKatedre = null;
        if (dto.getSefKatedre() != null && dto.getSefKatedre().getId() != null) {
            sefKatedre = profesorRepository.findById(dto.getSefKatedre().getId())
                .orElseThrow(() -> new RuntimeException("Profesor nije pronađen"));
        }

        Katedra katedra = new Katedra();
        katedra.setNaziv(dto.getNaziv());
        katedra.setOpis(dto.getOpis());
        katedra.setDepartman(departman);
        katedra.setSefKatedre(sefKatedre);
        katedra.setDeleted(false);

        Katedra savedKatedra = katedraRepository.save(katedra);
        return toDTO(savedKatedra);
    }

    @Override
    public KatedraDTO create(KatedraCreateUpdateDTO dto) {
        Departman departman = departmanRepository.findById(dto.getDepartmanId())
            .orElseThrow(() -> new RuntimeException("Departman nije pronađen"));

        Profesor sefKatedre = null;
        if (dto.getSefKatedreId() != null) {
            sefKatedre = profesorRepository.findById(dto.getSefKatedreId())
                .orElseThrow(() -> new RuntimeException("Profesor nije pronađen"));
        }

        Katedra katedra = new Katedra();
        katedra.setNaziv(dto.getNaziv());
        katedra.setOpis(dto.getOpis());
        katedra.setDepartman(departman);
        katedra.setSefKatedre(sefKatedre);
        katedra.setDeleted(false);

        Katedra savedKatedra = katedraRepository.save(katedra);
        return toSimpleDTO(savedKatedra);
    }

    @Override
    public List<KatedraDTO> findAll() {
        return katedraRepository.findAll().stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public KatedraDTO findById(Long id) {
        Katedra katedra = katedraRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Katedra nije pronađena"));
        return toDTO(katedra);
    }

    @Override
    public KatedraDTO update(Long id, KatedraDTO dto) {
        Katedra katedra = katedraRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Katedra nije pronađena"));

        Departman departman = departmanRepository.findById(dto.getDepartman().getId())
            .orElseThrow(() -> new RuntimeException("Departman nije pronađen"));

        Profesor sefKatedre = null;
        if (dto.getSefKatedre() != null && dto.getSefKatedre().getId() != null) {
            sefKatedre = profesorRepository.findById(dto.getSefKatedre().getId())
                .orElseThrow(() -> new RuntimeException("Profesor nije pronađen"));
        }

        katedra.setNaziv(dto.getNaziv());
        katedra.setOpis(dto.getOpis());
        katedra.setDepartman(departman);
        katedra.setSefKatedre(sefKatedre);

        Katedra updatedKatedra = katedraRepository.save(katedra);
        return toDTO(updatedKatedra);
    }

    @Override
    public KatedraDTO update(Long id, KatedraCreateUpdateDTO dto) {
        Katedra katedra = katedraRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Katedra nije pronađena"));

        Departman departman = departmanRepository.findById(dto.getDepartmanId())
            .orElseThrow(() -> new RuntimeException("Departman nije pronađen"));

        Profesor sefKatedre = null;
        if (dto.getSefKatedreId() != null) {
            sefKatedre = profesorRepository.findById(dto.getSefKatedreId())
                .orElseThrow(() -> new RuntimeException("Profesor nije pronađen"));
        }

        katedra.setNaziv(dto.getNaziv());
        katedra.setOpis(dto.getOpis());
        katedra.setDepartman(departman);
        katedra.setSefKatedre(sefKatedre);

        Katedra updatedKatedra = katedraRepository.save(katedra);
        return toSimpleDTO(updatedKatedra);
    }

    @Override
    public void delete(Long id) {
        Katedra katedra = katedraRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Katedra nije pronađena"));
        katedra.setDeleted(true);
        katedraRepository.save(katedra);
    }

    @Override
    public List<KatedraDTO> findByDepartmanId(Long departmanId) {
        return katedraRepository.findByDepartmanId(departmanId).stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<KatedraDTO> findAllAktivne() {
        return katedraRepository.findByDeletedFalse().stream()
            .map(this::toSimpleDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<KatedraDTO> findAllAdmin() {
        return katedraRepository.findAllSortiranoPoAktivnostiIFakultetu().stream()
            .map(this::toSimpleDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<KatedraDTO> findAktivneByDepartmanId(Long departmanId) {
        return katedraRepository.findByDepartmanIdAndDeletedFalse(departmanId).stream()
            .map(this::toSimpleDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void setDeleted(Long id, boolean deleted) {
        Katedra katedra = katedraRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Katedra nije pronađena"));
        katedra.setDeleted(deleted);
        katedraRepository.save(katedra);
    }

    // Privatna metoda za mapiranje u kompleksni DTO
    private KatedraDTO toDTO(Katedra entity) {
        KatedraDTO dto = new KatedraDTO();
        dto.setId(entity.getId());
        dto.setNaziv(entity.getNaziv());
        dto.setOpis(entity.getOpis());
        dto.setDeleted(entity.isDeleted());
        
        // Ostala mapiranja (predmeti, tipoviStudija, itd.) možete dodati kasnije
        // kada implementirate odgovarajuće servise
        
        return dto;
    }

    // Privatna metoda za mapiranje u jednostavni DTO
    private KatedraDTO toSimpleDTO(Katedra entity) {
        KatedraDTO dto = new KatedraDTO();
        dto.setId(entity.getId());
        dto.setNaziv(entity.getNaziv());
        dto.setOpis(entity.getOpis());
        dto.setDeleted(entity.isDeleted());
        
        // Dodajte osnovne informacije o departmanu i šefu katedre
        if (entity.getDepartman() != null) {
            // Možete dodati DepartmanDTO ako želite
        }
        
        if (entity.getSefKatedre() != null && entity.getSefKatedre().getUser() != null) {
            // Možete dodati osnovne informacije o šefu katedre
        }
        
        return dto;
    }
}