package com.sirmium.predmet.service;

import com.sirmium.predmet.dto.PredmetDTO;
import com.sirmium.predmet.model.Predmet;
import com.sirmium.predmet.repository.PredmetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PredmetServiceImpl implements PredmetService {
    
    private final PredmetRepository predmetRepository;
    
    public PredmetServiceImpl(PredmetRepository predmetRepository) {
        this.predmetRepository = predmetRepository;
    }
    
    @Override
    public PredmetDTO create(PredmetDTO dto) {
        Predmet predmet = new Predmet();
        // Mapiranje iz DTO u entitet
        predmet.setNaziv(dto.getNaziv());
        predmet.setOpis(dto.getOpis());
        predmet.setSifra(dto.getSifra());
        predmet.setEspb(dto.getEspb());
        predmet.setSemestar(dto.getSemestar());
        predmet.setAktivan(true); // podrazumevano aktiviran
        
        Predmet saved = predmetRepository.save(predmet);
        return mapToDTO(saved);
    }
    
    @Override
    public List<PredmetDTO> findAll() {
        return predmetRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<PredmetDTO> findAllAktivni() {
        return predmetRepository.findAll().stream()
                .filter(Predmet::isAktivan) // pretpostavka da ima boolean isAktivan()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public PredmetDTO findById(Long id) {
        Predmet predmet = predmetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Predmet nije pronađen"));
        return mapToDTO(predmet);
    }
    
    @Override
    public PredmetDTO update(Long id, PredmetDTO dto) {
        Predmet predmet = predmetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Predmet nije pronađen"));
        
        // Ažuriranje polja
        predmet.setNaziv(dto.getNaziv());
        predmet.setOpis(dto.getOpis());
        predmet.setSifra(dto.getSifra());
        predmet.setEspb(dto.getEspb());
        predmet.setSemestar(dto.getSemestar());
        
        Predmet updated = predmetRepository.save(predmet);
        return mapToDTO(updated);
    }
    
    @Override
    public void delete(Long id) {
        predmetRepository.deleteById(id);
    }
    
    @Override
    public PredmetDTO aktiviraj(Long id) {
        Predmet predmet = predmetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Predmet nije pronađen"));
        predmet.setAktivan(true);
        Predmet updated = predmetRepository.save(predmet);
        return mapToDTO(updated);
    }
    
    @Override
    public PredmetDTO deaktiviraj(Long id) {
        Predmet predmet = predmetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Predmet nije pronađen"));
        predmet.setAktivan(false);
        Predmet updated = predmetRepository.save(predmet);
        return mapToDTO(updated);
    }
    
    // Pomocna metoda za mapiranje entiteta u DTO
    private PredmetDTO mapToDTO(Predmet predmet) {
        PredmetDTO dto = new PredmetDTO();
        dto.setId(predmet.getId());
        dto.setNaziv(predmet.getNaziv());
        dto.setOpis(predmet.getOpis());
        dto.setSifra(predmet.getSifra());
        dto.setEspb(predmet.getEspb());
        dto.setSemestar(predmet.getSemestar());
        dto.setAktivan(predmet.isAktivan());
        return dto;
    }
}