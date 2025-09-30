package com.sirmium.fakultet.service;

import com.sirmium.fakultet.dto.FakultetDTO;
import com.sirmium.fakultet.dto.FakultetSimpleDTO;
import com.sirmium.fakultet.model.Fakultet;
import com.sirmium.fakultet.repository.FakultetRepository;
import com.sirmium.fakultet.service.FakultetService;
import com.sirmium.profesor.model.Profesor;
import com.sirmium.profesor.repository.ProfesorRepository;
import com.sirmium.univerzitet.model.Univerzitet;
import com.sirmium.univerzitet.repository.UniverzitetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FakultetServiceImpl implements FakultetService {

    private final FakultetRepository fakultetRepository;
    private final UniverzitetRepository univerzitetRepository;
    private final ProfesorRepository profesorRepository;

    @Autowired
    public FakultetServiceImpl(FakultetRepository fakultetRepository,
                              UniverzitetRepository univerzitetRepository,
                              ProfesorRepository profesorRepository) {
        this.fakultetRepository = fakultetRepository;
        this.univerzitetRepository = univerzitetRepository;
        this.profesorRepository = profesorRepository;
    }

    @Override
    public FakultetDTO create(FakultetDTO dto) {
        Univerzitet univerzitet = univerzitetRepository.findById(dto.getUniverzitetId())
            .orElseThrow(() -> new RuntimeException("Univerzitet nije pronađen"));

        Profesor dekan = null;
        if (dto.getDekanId() != null) {
            dekan = profesorRepository.findById(dto.getDekanId())
                .orElseThrow(() -> new RuntimeException("Profesor nije pronađen"));
        }

        Fakultet fakultet = new Fakultet();
        fakultet.setNaziv(dto.getNaziv());
        fakultet.setEmail(dto.getEmail());
        fakultet.setLokacija(dto.getLokacija());
        fakultet.setBrojTelefona(dto.getBrojTelefona());
        fakultet.setOpis(dto.getOpis());
        fakultet.setUniverzitet(univerzitet);
        fakultet.setDekan(dekan);
        fakultet.setDeleted(false);

        Fakultet savedFakultet = fakultetRepository.save(fakultet);
        return toDTO(savedFakultet);
    }

    @Override
    public FakultetSimpleDTO createSimple(FakultetSimpleDTO dto) {
        Univerzitet univerzitet = univerzitetRepository.findById(dto.getUniverzitetId())
            .orElseThrow(() -> new RuntimeException("Univerzitet nije pronađen"));

        Profesor dekan = null;
        if (dto.getDekanId() != null) {
            dekan = profesorRepository.findById(dto.getDekanId())
                .orElseThrow(() -> new RuntimeException("Profesor nije pronađen"));
        }

        Fakultet fakultet = new Fakultet();
        fakultet.setNaziv(dto.getNaziv());
        fakultet.setEmail(dto.getEmail());
        fakultet.setLokacija(dto.getLokacija());
        fakultet.setBrojTelefona(dto.getBrojTelefona());
        fakultet.setOpis(dto.getOpis());
        fakultet.setUniverzitet(univerzitet);
        fakultet.setDekan(dekan);
        fakultet.setDeleted(false);

        Fakultet savedFakultet = fakultetRepository.save(fakultet);
        return toSimpleDTO(savedFakultet);
    }

    @Override
    public List<FakultetDTO> findAll() {
        return fakultetRepository.findAll().stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public FakultetDTO findById(Long id) {
        Fakultet fakultet = fakultetRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Fakultet nije pronađen"));
        return toDTO(fakultet);
    }

    @Override
    public FakultetDTO update(Long id, FakultetDTO dto) {
        Fakultet fakultet = fakultetRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Fakultet nije pronađen"));

        Univerzitet univerzitet = univerzitetRepository.findById(dto.getUniverzitetId())
            .orElseThrow(() -> new RuntimeException("Univerzitet nije pronađen"));

        Profesor dekan = null;
        if (dto.getDekanId() != null) {
            dekan = profesorRepository.findById(dto.getDekanId())
                .orElseThrow(() -> new RuntimeException("Profesor nije pronađen"));
        }

        fakultet.setNaziv(dto.getNaziv());
        fakultet.setEmail(dto.getEmail());
        fakultet.setLokacija(dto.getLokacija());
        fakultet.setBrojTelefona(dto.getBrojTelefona());
        fakultet.setOpis(dto.getOpis());
        fakultet.setUniverzitet(univerzitet);
        fakultet.setDekan(dekan);

        Fakultet updatedFakultet = fakultetRepository.save(fakultet);
        return toDTO(updatedFakultet);
    }

    @Override
    public FakultetSimpleDTO updateSimple(Long id, FakultetSimpleDTO dto) {
        Fakultet fakultet = fakultetRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Fakultet nije pronađen"));

        Univerzitet univerzitet = univerzitetRepository.findById(dto.getUniverzitetId())
            .orElseThrow(() -> new RuntimeException("Univerzitet nije pronađen"));

        Profesor dekan = null;
        if (dto.getDekanId() != null) {
            dekan = profesorRepository.findById(dto.getDekanId())
                .orElseThrow(() -> new RuntimeException("Profesor nije pronađen"));
        }

        fakultet.setNaziv(dto.getNaziv());
        fakultet.setEmail(dto.getEmail());
        fakultet.setLokacija(dto.getLokacija());
        fakultet.setBrojTelefona(dto.getBrojTelefona());
        fakultet.setOpis(dto.getOpis());
        fakultet.setUniverzitet(univerzitet);
        fakultet.setDekan(dekan);

        Fakultet updatedFakultet = fakultetRepository.save(fakultet);
        return toSimpleDTO(updatedFakultet);
    }

    @Override
    public void delete(Long id) {
        Fakultet fakultet = fakultetRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Fakultet nije pronađen"));
        fakultet.setDeleted(true);
        fakultetRepository.save(fakultet);
    }

    @Override
    public List<FakultetSimpleDTO> findAllSimple() {
        return fakultetRepository.findAll().stream()
            .filter(f -> !f.isDeleted()) // Samo aktivni
            .map(this::toSimpleDTO)
            .collect(Collectors.toList());
    }

    @Override
    public FakultetSimpleDTO findSimpleById(Long id) {
        Fakultet fakultet = fakultetRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Fakultet nije pronađen"));
        return toSimpleDTO(fakultet);
    }

    @Override
    public List<FakultetSimpleDTO> findAllSimpleAdmin() {
        return fakultetRepository.findAll().stream()
            .map(this::toSimpleDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void setDeleted(Long id, boolean deleted) {
        Fakultet fakultet = fakultetRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Fakultet nije pronađen"));
        fakultet.setDeleted(deleted);
        fakultetRepository.save(fakultet);
    }

    @Override
    public FakultetDTO toDTO(Fakultet entity) {
        FakultetDTO dto = new FakultetDTO();
        dto.setId(entity.getId());
        dto.setNaziv(entity.getNaziv());
        dto.setEmail(entity.getEmail());
        dto.setLokacija(entity.getLokacija());
        dto.setBrojTelefona(entity.getBrojTelefona());
        dto.setOpis(entity.getOpis());
        dto.setDeleted(entity.isDeleted());
        
        if (entity.getUniverzitet() != null) {
            dto.setUniverzitetId(entity.getUniverzitet().getId());
            dto.setUniverzitetNaziv(entity.getUniverzitet().getNaziv());
        }
        
        if (entity.getDekan() != null && entity.getDekan().getUser() != null) {
            dto.setDekanId(entity.getDekan().getId());
            dto.setDekanIme(entity.getDekan().getUser().getIme() + " " + 
                           entity.getDekan().getUser().getPrezime());
        }
        
        return dto;
    }

    @Override
    public Fakultet toEntity(FakultetDTO dto) {
        Fakultet fakultet = new Fakultet();
        fakultet.setId(dto.getId());
        fakultet.setNaziv(dto.getNaziv());
        fakultet.setEmail(dto.getEmail());
        fakultet.setLokacija(dto.getLokacija());
        fakultet.setBrojTelefona(dto.getBrojTelefona());
        fakultet.setOpis(dto.getOpis());
        fakultet.setDeleted(dto.isDeleted());
        return fakultet;
    }

    @Override
    public FakultetSimpleDTO toSimpleDTO(Fakultet entity) {
        FakultetSimpleDTO dto = new FakultetSimpleDTO();
        dto.setId(entity.getId());
        dto.setNaziv(entity.getNaziv());
        dto.setEmail(entity.getEmail());
        dto.setLokacija(entity.getLokacija());
        dto.setBrojTelefona(entity.getBrojTelefona());
        dto.setOpis(entity.getOpis());
        dto.setDeleted(entity.isDeleted());
        
        if (entity.getUniverzitet() != null) {
            dto.setUniverzitetId(entity.getUniverzitet().getId());
            dto.setUniverzitetNaziv(entity.getUniverzitet().getNaziv());
        }
        
        if (entity.getDekan() != null && entity.getDekan().getUser() != null) {
            dto.setDekanId(entity.getDekan().getId());
            dto.setDekanIme(entity.getDekan().getUser().getIme() + " " + 
                           entity.getDekan().getUser().getPrezime());
        }
        
        return dto;
    }
}