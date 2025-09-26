package com.sirmium.profesorPredmet.service;

import com.sirmium.profesorPredmet.model.TerminNastave;
import com.sirmium.profesorPredmet.repository.TerminNastaveRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TerminNastaveService {
    
    private final TerminNastaveRepository terminNastaveRepository;
    
    public TerminNastaveService(TerminNastaveRepository terminNastaveRepository) {
        this.terminNastaveRepository = terminNastaveRepository;
    }
    
    public List<TerminNastave> getTerminiZaPredmet(Long predmetId) {
        return terminNastaveRepository.findByPredmetIdOrderByDatumVremeAsc(predmetId);
    }
    
    public List<TerminNastave> getTerminiZaPredmetUPeriodu(Long predmetId, LocalDateTime start, LocalDateTime end) {
        return terminNastaveRepository.findByPredmetIdAndDatumVremeBetween(predmetId, start, end);
    }
    
    public List<TerminNastave> getBuduciTerminiZaPredmete(List<Long> predmetIds) {
        return terminNastaveRepository.findByPredmetIdsAndDatumVremeAfter(predmetIds, LocalDateTime.now());
    }
    
    public TerminNastave zakaziTermin(TerminNastave termin) {
        return terminNastaveRepository.save(termin);
    }
    
    public TerminNastave azurirajTermin(Long terminId, TerminNastave updatedTermin) {
        TerminNastave termin = terminNastaveRepository.findById(terminId)
            .orElseThrow(() -> new RuntimeException("Termin nije pronađen"));
        
        termin.setTema(updatedTermin.getTema());
        termin.setOpis(updatedTermin.getOpis());
        termin.setUcionica(updatedTermin.getUcionica());
        termin.setDatumVreme(updatedTermin.getDatumVreme());
        termin.setTipTermina(updatedTermin.getTipTermina());
        termin.setIshod(updatedTermin.getIshod());
        
        return terminNastaveRepository.save(termin);
    }
    
    public void otkaziTermin(Long terminId) {
        terminNastaveRepository.deleteById(terminId);
    }
    
    public boolean terminSePreklapa(Long predmetId, LocalDateTime datumVreme, Long ignorisiTerminId) {
        LocalDateTime start = datumVreme.minusMinutes(30);
        LocalDateTime end = datumVreme.plusMinutes(30);
        
        List<TerminNastave> preklapajuciTermini = terminNastaveRepository
            .findByPredmetIdAndDatumVremeBetween(predmetId, start, end);
        
        return preklapajuciTermini.stream()
            .anyMatch(termin -> !termin.getId().equals(ignorisiTerminId));
    }
}