package com.sirmium.profesorPredmet.service;

import com.sirmium.profesorPredmet.model.Ishod;
import com.sirmium.profesorPredmet.repository.IshodRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IshodService {
    
    private final IshodRepository ishodRepository;
    
    public IshodService(IshodRepository ishodRepository) {
        this.ishodRepository = ishodRepository;
    }
    
    public List<Ishod> getIshodiZaPredmet(Long predmetId) {
        return ishodRepository.findByPredmetIdOrderByRedniBrojAsc(predmetId);
    }
    
    public Ishod kreirajIshod(Ishod ishod) {
        return ishodRepository.save(ishod);
    }
    
    public Ishod azurirajIshod(Long ishodId, Ishod updatedIshod) {
        Ishod ishod = ishodRepository.findById(ishodId)
            .orElseThrow(() -> new RuntimeException("Ishod nije pronađen"));
        
        ishod.setOpis(updatedIshod.getOpis());
        ishod.setRedniBroj(updatedIshod.getRedniBroj());
        ishod.setEspbBodovi(updatedIshod.getEspbBodovi());
        
        return ishodRepository.save(ishod);
    }
    
    public void obrisiIshod(Long ishodId) {
        ishodRepository.deleteById(ishodId);
    }
    
    public List<Ishod> getIshodiSaMinBodovima(Long predmetId, int minBodovi) {
        return ishodRepository.findByPredmetIdAndMinBodovi(predmetId, minBodovi);
    }
}