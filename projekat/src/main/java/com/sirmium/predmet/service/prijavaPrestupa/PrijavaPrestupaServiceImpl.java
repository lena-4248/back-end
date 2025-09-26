package com.sirmium.predmet.service.prijavaPrestupa;

import com.sirmium.predmet.dto.PrijavaPrestupaDTO;
import com.sirmium.predmet.model.PohadjanjePredmeta;
import com.sirmium.predmet.model.PrijavaPrestupa;
import com.sirmium.predmet.repository.PohadjanjePredmetaRepository;
import com.sirmium.predmet.repository.PrijavaPrestupaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrijavaPrestupaServiceImpl implements PrijavaPrestupaService {

    @Autowired
    private PrijavaPrestupaRepository repository;

    @Autowired
    private PohadjanjePredmetaRepository pohadjanjeRepo;

    private PrijavaPrestupaDTO toDTO(PrijavaPrestupa prijava) {
        PrijavaPrestupaDTO dto = new PrijavaPrestupaDTO();
        dto.setId(prijava.getId());
        dto.setOpis(prijava.getOpis());
        dto.setDatum(prijava.getDatum());
        dto.setPohadjanjeId(prijava.getPohadjanje().getId());
        return dto;
    }

    private PrijavaPrestupa toEntity(PrijavaPrestupaDTO dto) {
        PohadjanjePredmeta pohadjanje = pohadjanjeRepo.findById(dto.getPohadjanjeId())
                .orElseThrow(() -> new RuntimeException("Pohadjanje predmeta nije pronađeno"));
        
        PrijavaPrestupa prijava = new PrijavaPrestupa();
        prijava.setId(dto.getId());
        prijava.setOpis(dto.getOpis());
        prijava.setDatum(dto.getDatum());
        prijava.setPohadjanje(pohadjanje);
        return prijava;
    }

    @Override
    public PrijavaPrestupaDTO create(PrijavaPrestupaDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    @Override
    public List<PrijavaPrestupaDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public PrijavaPrestupaDTO findById(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public PrijavaPrestupaDTO update(Long id, PrijavaPrestupaDTO dto) {
        return repository.findById(id).map(existing -> {
            existing.setOpis(dto.getOpis());
            existing.setDatum(dto.getDatum());
            existing.setPohadjanje(pohadjanjeRepo.findById(dto.getPohadjanjeId())
                    .orElseThrow(() -> new RuntimeException("Pohadjanje predmeta nije pronađeno")));
            return toDTO(repository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}