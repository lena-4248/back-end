package com.sirmium.osoblje.repository;

import com.sirmium.osoblje.model.OpsteObavestenje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OpsteObavestenjeRepository extends JpaRepository<OpsteObavestenje, Long> {
    
    List<OpsteObavestenje> findByAktivanTrue();
    List<OpsteObavestenje> findByAktivanFalse();
    List<OpsteObavestenje> findByAutorId(Long autorId);
    List<OpsteObavestenje> findByAutorIdAndAktivanTrue(Long autorId);
    List<OpsteObavestenje> findByAutorIdAndAktivanFalse(Long autorId);
    
    @Query("SELECT o FROM OpsteObavestenje o WHERE o.datum >= :startDate AND o.aktivan = true ORDER BY o.datum DESC")
    List<OpsteObavestenje> findRecentActive(@Param("startDate") LocalDate startDate);
    
    @Query("SELECT o FROM OpsteObavestenje o WHERE o.naslov LIKE %:tekst% OR o.tekst LIKE %:tekst%")
    List<OpsteObavestenje> pretraziPoTekstu(@Param("tekst") String tekst);
}