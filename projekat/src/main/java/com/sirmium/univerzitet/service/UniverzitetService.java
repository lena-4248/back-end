package com.sirmium.univerzitet.service;

import com.sirmium.univerzitet.model.Univerzitet;
import com.sirmium.univerzitet.repository.UniverzitetRepository;
import com.sirmium.profesor.model.Profesor;
import com.sirmium.profesor.repository.ProfesorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UniverzitetService {

    private final UniverzitetRepository univerzitetRepository;
    private final ProfesorRepository profesorRepository;

    public UniverzitetService(UniverzitetRepository univerzitetRepository,
                            ProfesorRepository profesorRepository) {
        this.univerzitetRepository = univerzitetRepository;
        this.profesorRepository = profesorRepository;
    }

    public List<Univerzitet> findAll() {
        return univerzitetRepository.findAll();
    }

    public Optional<Univerzitet> findById(Long id) {
        return univerzitetRepository.findById(id);
    }

    public Univerzitet save(Univerzitet univerzitet) {
        return univerzitetRepository.save(univerzitet);
    }

    public Univerzitet update(Long id, Univerzitet updatedUniverzitet) {
        Univerzitet existing = univerzitetRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Univerzitet nije pronađen"));
        
        existing.setNaziv(updatedUniverzitet.getNaziv());
        existing.setOpis(updatedUniverzitet.getOpis());
        existing.setAdresa(updatedUniverzitet.getAdresa());
        existing.setKontaktEmail(updatedUniverzitet.getKontaktEmail());
        existing.setKontaktTelefon(updatedUniverzitet.getKontaktTelefon());
        existing.setIstorijat(updatedUniverzitet.getIstorijat());
        existing.setLogoPath(updatedUniverzitet.getLogoPath());
        existing.setWebsiteUrl(updatedUniverzitet.getWebsiteUrl());
        
        return univerzitetRepository.save(existing);
    }

    public void delete(Long id) {
        univerzitetRepository.deleteById(id);
    }

    public Univerzitet postaviRektora(Long univerzitetId, Long profesorId) {
        Univerzitet univerzitet = univerzitetRepository.findById(univerzitetId)
            .orElseThrow(() -> new RuntimeException("Univerzitet nije pronađen"));
        
        Profesor profesor = profesorRepository.findById(profesorId)
            .orElseThrow(() -> new RuntimeException("Profesor nije pronađen"));
        
        univerzitet.setRektor(profesor);
        return univerzitetRepository.save(univerzitet);
    }

    public List<Univerzitet> pretraziPoNazivu(String naziv) {
        return univerzitetRepository.findByNazivContaining(naziv);
    }

    public Long getBrojFakulteta(Long univerzitetId) {
        return univerzitetRepository.countFakultetiByUniverzitetId(univerzitetId);
    }
}