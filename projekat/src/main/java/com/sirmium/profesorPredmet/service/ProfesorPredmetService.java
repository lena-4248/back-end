package com.sirmium.profesorPredmet.service;

import com.sirmium.profesor.model.Profesor;
import com.sirmium.profesorPredmet.model.ProfesorPredmet;
import com.sirmium.profesorPredmet.repository.ProfesorPredmetRepository;
import com.sirmium.predmet.model.Predmet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProfesorPredmetService {
    
    private final ProfesorPredmetRepository profesorPredmetRepository;
    
    public ProfesorPredmetService(ProfesorPredmetRepository profesorPredmetRepository) {
        this.profesorPredmetRepository = profesorPredmetRepository;
    }
    
    public List<ProfesorPredmet> getAngazmaniByProfesor(Long profesorId) {
        return profesorPredmetRepository.findByProfesorId(profesorId);
    }
    
    public List<ProfesorPredmet> getAngazmaniByPredmet(Long predmetId) {
        return profesorPredmetRepository.findByPredmetId(predmetId);
    }
    
    public ProfesorPredmet dodajAngazman(Profesor profesor, Predmet predmet, String tipAngazmana, int brojCasova) {
        ProfesorPredmet angazman = new ProfesorPredmet();
        angazman.setProfesor(profesor);
        angazman.setPredmet(predmet);
        angazman.setTipAngazmana(tipAngazmana);
        angazman.setBrojCasova(brojCasova);
        
        return profesorPredmetRepository.save(angazman);
    }
    
    public void ukloniAngazman(Long angazmanId) {
    	profesorPredmetRepository.deleteById(angazmanId);
    }
    
    public ProfesorPredmet azurirajAngazman(Long angazmanId, String tipAngazmana, Integer brojCasova) {
        ProfesorPredmet angazman = profesorPredmetRepository.findById(angazmanId)
            .orElseThrow(() -> new RuntimeException("Angažman nije pronađen"));
        
        if (tipAngazmana != null) {
            angazman.setTipAngazmana(tipAngazmana);
        }
        
        if (brojCasova != null) {
            angazman.setBrojCasova(brojCasova);
        }
        
        return profesorPredmetRepository.save(angazman);
    }
    
    public boolean profesorJeAngazovanNaPredmetu(Long profesorId, Long predmetId) {
        return profesorPredmetRepository.findByProfesorId(profesorId).stream()
            .anyMatch(angazman -> angazman.getPredmet().getId().equals(predmetId));
    }
}