package com.sirmium.departman.service;

import com.sirmium.departman.dto.DepartmanCreateUpdateDTO;
import com.sirmium.departman.dto.DepartmanDTO;
import com.sirmium.departman.model.Departman;
import com.sirmium.departman.repository.DepartmanRepository;
import com.sirmium.departman.service.DepartmanService;
import com.sirmium.fakultet.model.Fakultet;
import com.sirmium.fakultet.repository.FakultetRepository;
import com.sirmium.profesor.model.Profesor;
import com.sirmium.profesor.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DepartmanServiceImpl implements DepartmanService {

    private final DepartmanRepository departmanRepository;
    private final FakultetRepository fakultetRepository;
    private final ProfesorRepository profesorRepository;

    @Autowired
    public DepartmanServiceImpl(DepartmanRepository departmanRepository,
                               FakultetRepository fakultetRepository,
                               ProfesorRepository profesorRepository) {
        this.departmanRepository = departmanRepository;
        this.fakultetRepository = fakultetRepository;
        this.profesorRepository = profesorRepository;
    }

    @Override
    public DepartmanDTO create(DepartmanCreateUpdateDTO dto) {
        Fakultet fakultet = fakultetRepository.findById(dto.getFakultetId())
            .orElseThrow(() -> new RuntimeException("Fakultet nije pronađen"));

        Profesor sefDepartmana = null;
        if (dto.getSefDepartmanaId() != null) {
            sefDepartmana = profesorRepository.findById(dto.getSefDepartmanaId())
                .orElseThrow(() -> new RuntimeException("Profesor nije pronađen"));
        }

        Departman departman = new Departman();
        departman.setNaziv(dto.getNaziv());
        departman.setOpis(dto.getOpis());
        departman.setFakultet(fakultet);
        departman.setSefDepartmana(sefDepartmana);
        departman.setDeleted(false);

        Departman savedDepartman = departmanRepository.save(departman);
        return toDTO(savedDepartman);
    }

    @Override
    public List<DepartmanDTO> findAll() {
        return departmanRepository.findAll().stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public DepartmanDTO findById(Long id) {
        Departman departman = departmanRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Departman nije pronađen"));
        return toDTO(departman);
    }

    @Override
    public DepartmanDTO update(Long id, DepartmanCreateUpdateDTO dto) {
        Departman departman = departmanRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Departman nije pronađen"));

        Fakultet fakultet = fakultetRepository.findById(dto.getFakultetId())
            .orElseThrow(() -> new RuntimeException("Fakultet nije pronađen"));

        Profesor sefDepartmana = null;
        if (dto.getSefDepartmanaId() != null) {
            sefDepartmana = profesorRepository.findById(dto.getSefDepartmanaId())
                .orElseThrow(() -> new RuntimeException("Profesor nije pronađen"));
        }

        departman.setNaziv(dto.getNaziv());
        departman.setOpis(dto.getOpis());
        departman.setFakultet(fakultet);
        departman.setSefDepartmana(sefDepartmana);

        Departman updatedDepartman = departmanRepository.save(departman);
        return toDTO(updatedDepartman);
    }

    @Override
    public void delete(Long id) {
        Departman departman = departmanRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Departman nije pronađen"));
        departman.setDeleted(true);
        departmanRepository.save(departman);
    }

    @Override
    public List<DepartmanDTO> findByFakultetId(Long fakultetId) {
        return departmanRepository.findByFakultetId(fakultetId).stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<DepartmanDTO> findAllActive() {
        return departmanRepository.findByDeletedFalse().stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<DepartmanDTO> findAllForAdmin() {
        return departmanRepository.findAllByOrderByDeletedAscFakultetNazivAsc().stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void deaktiviraj(Long id) {
        Departman departman = departmanRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Departman nije pronađen"));
        departman.setDeleted(true);
        departmanRepository.save(departman);
    }

    @Override
    public void aktiviraj(Long id) {
        Departman departman = departmanRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Departman nije pronađen"));
        departman.setDeleted(false);
        departmanRepository.save(departman);
    }

    @Override
    public DepartmanDTO toDTO(Departman entity) {
        DepartmanDTO dto = new DepartmanDTO();
        dto.setId(entity.getId());
        dto.setNaziv(entity.getNaziv());
        dto.setOpis(entity.getOpis());
        dto.setDeleted(entity.isDeleted());
        
        if (entity.getFakultet() != null) {
            dto.setFakultetId(entity.getFakultet().getId());
            dto.setFakultetNaziv(entity.getFakultet().getNaziv());
        }
        
        if (entity.getSefDepartmana() != null && entity.getSefDepartmana().getUser() != null) {
            dto.setSefDepartmanaId(entity.getSefDepartmana().getId());
            dto.setSefDepartmanaIme(entity.getSefDepartmana().getUser().getIme() + " " + 
                                   entity.getSefDepartmana().getUser().getPrezime());
        }
        
        return dto;
    }

    @Override
    public Departman toEntity(DepartmanDTO dto) {
        Departman departman = new Departman();
        departman.setId(dto.getId());
        departman.setNaziv(dto.getNaziv());
        departman.setOpis(dto.getOpis());
        departman.setDeleted(dto.isDeleted());
        return departman;
    }
}