package com.sirmium.departman.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirmium.departman.dto.DepartmanCreateUpdateDTO;
import com.sirmium.departman.dto.DepartmanDTO;
import com.sirmium.departman.dto.FakultetDTO;
import com.sirmium.katedra.dto.KatedraDTO;
import com.sirmium.profesor.dto.ProfesorDTO;
import com.sirmium.departman.model.Departman;
import com.sirmium.departman.model.Fakultet;
import com.sirmium.katedra.model.Katedra;
import com.sirmium.profesor.model.Profesor;
import com.sirmium.departman.repository.DepartmanRepository;
import com.sirmium.fakultet.repository.FakultetRepository;
import com.sirmium.katedra.repository.KatedraRepository;
import com.sirmium.profesor.repository.ProfesorRepository;
import com.sirmium.profesor.service.ProfesorService;

@Service
public class DepartmanServiceImpl implements DepartmanService {

    private final DepartmanRepository departmanRepository;
    private final KatedraRepository katedraRepository;
    private final FakultetRepository fakultetRepository;
    private final ProfesorRepository profesorRepository;
    
    @Autowired
    private ProfesorService profesorService;

    public DepartmanServiceImpl(DepartmanRepository departmanRepository,
                                 KatedraRepository katedraRepository,
                                 FakultetRepository fakultetRepository,
                                 ProfesorRepository profesorRepository) {
        this.departmanRepository = departmanRepository;
        this.katedraRepository = katedraRepository;
        this.fakultetRepository = fakultetRepository;
        this.profesorRepository = profesorRepository;
    }
    
    @Override
    public DepartmanDTO create(DepartmanCreateUpdateDTO dto) {
        Departman departman = new Departman();
        departman.setNaziv(dto.getNaziv());
        departman.setOpis(dto.getOpis());
        departman.setDeleted(false);

        if (dto.getFakultetId() != null) {
            Fakultet fakultet = fakultetRepository.findById(dto.getFakultetId())
                    .orElseThrow(() -> new RuntimeException("Fakultet ne postoji"));
            departman.setFakultet(fakultet);
        }

        if (dto.getSefDepartmanaId() != null) {
            Profesor profesor = profesorRepository.findById(dto.getSefDepartmanaId())
                    .orElseThrow(() -> new RuntimeException("Profesor ne postoji"));
            departman.setSefDepartmana(profesor);
        }

        departmanRepository.save(departman);
        return toDTO(departman);
    }

    @Override
    public DepartmanDTO update(Long id, DepartmanCreateUpdateDTO dto) {
        Departman departman = departmanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departman ne postoji"));

        departman.setNaziv(dto.getNaziv());
        departman.setOpis(dto.getOpis());

        if (dto.getFakultetId() != null) {
            Fakultet fakultet = fakultetRepository.findById(dto.getFakultetId())
                    .orElseThrow(() -> new RuntimeException("Fakultet ne postoji"));
            departman.setFakultet(fakultet);
        }

        if (dto.getSefDepartmanaId() != null) {
            Profesor profesor = profesorRepository.findById(dto.getSefDepartmanaId())
                    .orElseThrow(() -> new RuntimeException("Profesor ne postoji"));
            departman.setSefDepartmana(profesor);
        }

        departmanRepository.save(departman);
        return toDTO(departman);
    }

    @Override
    public List<DepartmanDTO> findAll() {
        return departmanRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }
    
    @Override
    public List<DepartmanDTO> findAllActive() {
        return departmanRepository.findByDeletedFalse()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public List<DepartmanDTO> findAllForAdmin() {
        return departmanRepository.findAllByOrderByDeletedAscFakultetNazivAsc()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public void deaktiviraj(Long id) {
        departmanRepository.findById(id).ifPresent(dep -> {
            dep.setDeleted(true);
            departmanRepository.save(dep);
        });
    }

    @Override
    public void aktiviraj(Long id) {
        departmanRepository.findById(id).ifPresent(dep -> {
            dep.setDeleted(false);
            departmanRepository.save(dep);
        });
    }

    @Override
    public DepartmanDTO findById(Long id) {
        Optional<Departman> optional = departmanRepository.findById(id);
        return optional.map(this::toDTO).orElse(null);
    }
    
    @Override
    public List<DepartmanDTO> findByFakultetId(Long fakultetId) {
        return departmanRepository.findByFakultetIdAndDeletedFalse(fakultetId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public void delete(Long id) {
        departmanRepository.deleteById(id);
    }

    @Override
    public Departman toEntity(DepartmanDTO dto) {
        Departman departman = new Departman();
        departman.setId(dto.getId());
        departman.setNaziv(dto.getNaziv());
        departman.setOpis(dto.getOpis());
        departman.setDeleted(dto.isDeleted());

        if (dto.getKatedre() != null) {
            ArrayList<Katedra> katedre = new ArrayList<>();
            for (KatedraDTO kdto : dto.getKatedre()) {
                if (kdto.getId() != null) {
                    katedraRepository.findById(kdto.getId()).ifPresent(katedre::add);
                }
            }
            departman.setKatedre(katedre);
        }
        
        if (dto.getFakultetId() != null) {
            fakultetRepository.findById(dto.getFakultetId()).ifPresent(departman::setFakultet);
        }

        if (dto.getSefDepartmanaId() != null) {
            profesorRepository.findById(dto.getSefDepartmanaId()).ifPresent(departman::setSefDepartmana);
        }

        return departman;
    }

    @Override
    public DepartmanDTO toDTO(Departman entity) {
        DepartmanDTO dto = new DepartmanDTO();
        dto.setId(entity.getId());
        dto.setNaziv(entity.getNaziv());
        dto.setOpis(entity.getOpis());
        dto.setDeleted(entity.isDeleted());

        if (entity.getKatedre() != null) {
            dto.setKatedre(entity.getKatedre()
                    .stream()
                    .map(k -> new KatedraDTO(k.getId(), k.getNaziv(), null, k.getOpis(), null, null, null))
                    .toList());
        }
        

        if (entity.getFakultet() != null) {
            dto.setFakultet(new FakultetDTO(
                    entity.getFakultet().getId(),
                    entity.getFakultet().getNaziv(),
                    entity.getFakultet().getEmail(),
                    null,
                    null,
                    null,
                    entity.getFakultet().getOpis(),
                    entity.getFakultet().getLokacija(),
                    entity.getFakultet().getBrojTelefona()
            ));
        }

        if (entity.getSefDepartmana() != null) {
            dto.setSefDepartmana(profesorService.toDTO(entity.getSefDepartmana()));
        }

        return dto;
    }
}