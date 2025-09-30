package com.sirmium.tipStudija.service;

import com.sirmium.tipStudija.model.TipStudija;
import com.sirmium.tipStudija.repository.TipStudijaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TipStudijaService {

    private final TipStudijaRepository tipStudijaRepository;

    public TipStudijaService(TipStudijaRepository tipStudijaRepository) {
        this.tipStudijaRepository = tipStudijaRepository;
    }

    public List<TipStudija> findAll() {
        return tipStudijaRepository.findAll();
    }

    public List<TipStudija> findAllAktivni() {
        return tipStudijaRepository.findByAktivTrue();
    }

    public Optional<TipStudija> findById(Long id) {
        return tipStudijaRepository.findById(id);
    }

    public TipStudija save(TipStudija tipStudija) {
        return tipStudijaRepository.save(tipStudija);
    }

    public TipStudija update(Long id, TipStudija updatedTipStudija) {
        TipStudija existing = tipStudijaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tip studija nije pronađen"));
        
        existing.setNaziv(updatedTipStudija.getNaziv());
        existing.setOpis(updatedTipStudija.getOpis());
        existing.setTrajanjeGodine(updatedTipStudija.getTrajanjeGodine());
        
        return tipStudijaRepository.save(existing);
    }

    public void delete(Long id) {
        tipStudijaRepository.deleteById(id);
    }

    public TipStudija deaktiviraj(Long id) {
        TipStudija tipStudija = tipStudijaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tip studija nije pronađen"));
        
        tipStudija.setAktiv(false);
        return tipStudijaRepository.save(tipStudija);
    }

    public TipStudija aktiviraj(Long id) {
        TipStudija tipStudija = tipStudijaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tip studija nije pronađen"));
        
        tipStudija.setAktiv(true);
        return tipStudijaRepository.save(tipStudija);
    }

    public List<TipStudija> pretraziPoNazivu(String naziv) {
        return tipStudijaRepository.findByNazivContaining(naziv);
    }
}