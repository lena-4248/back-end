package com.sirmium.osoblje.service;

import com.sirmium.osoblje.model.KancelarijskiInventar;
import com.sirmium.osoblje.model.Osoblje;
import com.sirmium.osoblje.model.TrebovanjeInventara;
import com.sirmium.osoblje.repository.KancelarijskiInventarRepository;
import com.sirmium.osoblje.repository.TrebovanjeInventaraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class TrebovanjeInventaraService {

    private final TrebovanjeInventaraRepository trebovanjeRepository;
    private final KancelarijskiInventarRepository inventarRepository;

    public TrebovanjeInventaraService(TrebovanjeInventaraRepository trebovanjeRepository,
                                    KancelarijskiInventarRepository inventarRepository) {
        this.trebovanjeRepository = trebovanjeRepository;
        this.inventarRepository = inventarRepository;
    }

    public TrebovanjeInventara kreirajTrebovanje(Osoblje osoblje, Long inventarId, int kolicina, String napomena) {
        KancelarijskiInventar inventar = inventarRepository.findById(inventarId)
            .orElseThrow(() -> new RuntimeException("Inventar nije pronađen"));
        
        TrebovanjeInventara trebovanje = new TrebovanjeInventara(osoblje, inventar, kolicina, napomena);
        return trebovanjeRepository.save(trebovanje);
    }

    public List<TrebovanjeInventara> getTrebovanjaByOsoblje(Long osobljeId) {
        return trebovanjeRepository.findByOsobljeId(osobljeId);
    }

    public List<TrebovanjeInventara> getTrebovanjaByStatus(TrebovanjeInventara.StatusTrebovanja status) {
        return trebovanjeRepository.findByStatus(status);
    }

    public TrebovanjeInventara odobriTrebovanje(Long trebovanjeId) {
        TrebovanjeInventara trebovanje = trebovanjeRepository.findById(trebovanjeId)
            .orElseThrow(() -> new RuntimeException("Trebovanje nije pronađeno"));
        
        trebovanje.setStatus(TrebovanjeInventara.StatusTrebovanja.ODOBRENO);
        trebovanje.setDatumOdobrenja(LocalDate.now());
        
        return trebovanjeRepository.save(trebovanje);
    }

    public TrebovanjeInventara odbijTrebovanje(Long trebovanjeId, String razlog) {
        TrebovanjeInventara trebovanje = trebovanjeRepository.findById(trebovanjeId)
            .orElseThrow(() -> new RuntimeException("Trebovanje nije pronađeno"));
        
        trebovanje.setStatus(TrebovanjeInventara.StatusTrebovanja.ODBIJENO);
        trebovanje.setNapomena(trebovanje.getNapomena() + "\nOdbijeno: " + razlog);
        
        return trebovanjeRepository.save(trebovanje);
    }

    public TrebovanjeInventara oznaciKaoIsporuceno(Long trebovanjeId) {
        TrebovanjeInventara trebovanje = trebovanjeRepository.findById(trebovanjeId)
            .orElseThrow(() -> new RuntimeException("Trebovanje nije pronađeno"));
        
        // Ažuriraj količinu u inventaru
        KancelarijskiInventar inventar = trebovanje.getInventar();
        inventar.setKolicina(inventar.getKolicina() + trebovanje.getKolicina());
        inventarRepository.save(inventar);
        
        trebovanje.setStatus(TrebovanjeInventara.StatusTrebovanja.ISPORUCENO);
        trebovanje.setDatumIsporuke(LocalDate.now());
        
        return trebovanjeRepository.save(trebovanje);
    }

    public List<TrebovanjeInventara> getPotrebnoNabaviti() {
        List<KancelarijskiInventar> inventar = inventarRepository.findPotrebnoNabaviti();
        return inventar.stream()
            .map(item -> new TrebovanjeInventara(null, item, item.getRazlikaDoMinimuma(), "Automatsko trebovanje - minimum dostignut"))
            .toList();
    }
}