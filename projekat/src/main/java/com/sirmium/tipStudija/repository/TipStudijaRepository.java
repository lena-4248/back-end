package com.sirmium.tipStudija.repository;

import com.sirmium.tipStudija.model.TipStudija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipStudijaRepository extends JpaRepository<TipStudija, Long> {
    
    Optional<TipStudija> findByNaziv(String naziv);
    
    List<TipStudija> findByAktivTrue();
    
    @Query("SELECT ts FROM TipStudija ts WHERE LOWER(ts.naziv) LIKE LOWER(CONCAT('%', :naziv, '%'))")
    List<TipStudija> findByNazivContaining(@Param("naziv") String naziv);
    
    @Query("SELECT ts FROM TipStudija ts WHERE ts.aktiv = :aktiv")
    List<TipStudija> findByAktiv(@Param("aktiv") Boolean aktiv);
}