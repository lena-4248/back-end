package com.sirmium.obavestenje.service;

import com.sirmium.obavestenje.dto.OpsteObavestenjeDTO;
import com.sirmium.obavestenje.model.OpsteObavestenje;
import com.sirmium.obavestenje.repository.OpsteObavestenjeRepository;
import com.sirmium.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OpsteObavestenjeService {

    @Autowired
    private OpsteObavestenjeRepository repository;

    public List<OpsteObavestenje> findAll() {
        return repository.findByAktivanTrue();
    }
    
    public List<OpsteObavestenje> findAllIncludingInactive() {
        return repository.findAll();
    }
    
    public List<OpsteObavestenje> findByAutorId(Long autorId) {
        return repository.findByAutorId(autorId);
    }
    
    public List<OpsteObavestenje> findAktivnaByAutorId(Long autorId) {
        return repository.findByAutorIdAndAktivanTrue(autorId);
    }

    public OpsteObavestenje save(OpsteObavestenje obavestenje) {
        return repository.save(obavestenje);
    }

    public void softDelete(Long id) {
        repository.findById(id).ifPresent(o -> {
            o.setAktivan(false);
            repository.save(o);
        });
    }
    
    public void hardDelete(Long id) {
        repository.deleteById(id);
    }
    
    public void activate(Long id) {
        repository.findById(id).ifPresent(o -> {
            o.setAktivan(true);
            repository.save(o);
        });
    }

    public OpsteObavestenje findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Opšte obaveštenje nije pronađeno"));
    }
    
    public OpsteObavestenje create(OpsteObavestenjeDTO dto, User autor) {
        OpsteObavestenje o = new OpsteObavestenje();
        o.setNaslov(dto.getNaslov());
        o.setTekst(dto.getTekst());
        o.setDatum(dto.getDatum() != null ? dto.getDatum() : LocalDate.now());
        o.setAutor(autor);
        o.setAktivan(true);
        return repository.save(o);
    }
    
    public OpsteObavestenje update(Long id, OpsteObavestenjeDTO dto) {
        OpsteObavestenje existing = findById(id);
        
        existing.setNaslov(dto.getNaslov());
        existing.setTekst(dto.getTekst());
        if (dto.getDatum() != null) {
            existing.setDatum(dto.getDatum());
        }
        
        return repository.save(existing);
    }
    
    public List<OpsteObavestenje> findNeaktivna() {
        return repository.findByAktivanFalse();
    }
}