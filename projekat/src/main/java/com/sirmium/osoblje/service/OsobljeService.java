package com.sirmium.osoblje.service;

import com.sirmium.osoblje.model.Osoblje;
import com.sirmium.osoblje.repository.OsobljeRepository;
import com.sirmium.user.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OsobljeService {
    
    private final OsobljeRepository osobljeRepository;
    
    public OsobljeService(OsobljeRepository osobljeRepository) {
        this.osobljeRepository = osobljeRepository;
    }
    
    public Optional<Osoblje> findByUser(User user) {
        return osobljeRepository.findByUser(user);
    }
    
    public Optional<Osoblje> findByUserId(Long userId) {
        return osobljeRepository.findByUserId(userId);
    }
    
    public Osoblje save(Osoblje osoblje) {
        return osobljeRepository.save(osoblje);
    }
    
    public List<Osoblje> findByPozicija(String pozicija) {
        return osobljeRepository.findByPozicija(pozicija);
    }
    
    public List<Osoblje> findByOdeljenje(String odeljenje) {
        return osobljeRepository.findByOdeljenje(odeljenje);
    }
    
    public List<Osoblje> findByImeOrPrezime(String ime, String prezime) {
        return osobljeRepository.findByImeOrPrezime(ime, prezime);
    }
    
    public boolean isOsoblje(User user) {
        return osobljeRepository.findByUser(user).isPresent();
    }
}