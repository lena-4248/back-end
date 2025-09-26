package com.sirmium.profesor.service;

import com.sirmium.profesor.model.Profesor;
import com.sirmium.profesor.repository.ProfesorRepository;
import com.sirmium.profesorPredmet.model.ProfesorPredmet;
import com.sirmium.profesorPredmet.repository.ProfesorPredmetRepository;
import com.sirmium.predmet.model.Predmet;
import com.sirmium.user.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProfesorService {
    
    private final ProfesorRepository profesorRepository;
    private final ProfesorPredmetRepository profesorPredmetRepository;
    
    public ProfesorService(ProfesorRepository profesorRepository,
                         ProfesorPredmetRepository profesorPredmetRepository) {
        this.profesorRepository = profesorRepository;
        this.profesorPredmetRepository = profesorPredmetRepository;
    }
    
    public Optional<Profesor> findByUser(User user) {
        return profesorRepository.findByUser(user);
    }
    
    public Optional<Profesor> findByUserId(Long userId) {
        return profesorRepository.findByUserId(userId);
    }
    
    public Optional<Profesor> findByUserEmail(String email) {
        return profesorRepository.findByUserEmail(email);
    }
    
    public Profesor save(Profesor profesor) {
        return profesorRepository.save(profesor);
    }
    
    public List<Profesor> findByZvanje(String zvanje) {
        return profesorRepository.findByZvanje(zvanje);
    }
    
    public List<Profesor> findByKatedra(String katedra) {
        return profesorRepository.findByKatedra(katedra);
    }
    
    public List<Profesor> findByImeOrPrezime(String ime, String prezime) {
        return profesorRepository.findByImeOrPrezime(ime, prezime);
    }
    
    // Metode za rad sa predmetima profesora
    public List<ProfesorPredmet> getPredmetiProfesora(Long profesorId) {
        return profesorPredmetRepository.findByProfesorId(profesorId);
    }
    
    public List<ProfesorPredmet> getPredmetiProfesoraByTip(Long profesorId, String tipAngazmana) {
        Profesor profesor = profesorRepository.findById(profesorId)
            .orElseThrow(() -> new RuntimeException("Profesor nije pronađen"));
        return profesorPredmetRepository.findByProfesorAndTipAngazmana(profesor, tipAngazmana);
    }
    
    public List<ProfesorPredmet> getPredmetiProfesoraBySemestar(Long profesorId, int semestar) {
        return profesorPredmetRepository.findByProfesorIdAndSemestar(profesorId, semestar);
    }
    
    public boolean jeAngazovanNaPredmetu(Long profesorId, Long predmetId) {
        Profesor profesor = profesorRepository.findById(profesorId)
            .orElseThrow(() -> new RuntimeException("Profesor nije pronađen"));
        Predmet predmet = new Predmet();
        predmet.setId(predmetId);
        return profesorPredmetRepository.existsByProfesorAndPredmet(profesor, predmet);
    }
}