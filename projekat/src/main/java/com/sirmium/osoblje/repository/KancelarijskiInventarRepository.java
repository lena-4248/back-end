package com.sirmium.osoblje.repository;

import com.sirmium.osoblje.model.KancelarijskiInventar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KancelarijskiInventarRepository extends JpaRepository<KancelarijskiInventar, Long> {
    
    List<KancelarijskiInventar> findByKategorija(String kategorija);
    
    @Query("SELECT k FROM KancelarijskiInventar k WHERE k.kolicina <= k.minKolicina")
    List<KancelarijskiInventar> findPotrebnoNabaviti();
    
    List<KancelarijskiInventar> findByNazivContainingIgnoreCase(String naziv);
}