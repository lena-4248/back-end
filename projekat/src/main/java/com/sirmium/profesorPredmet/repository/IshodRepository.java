package com.sirmium.profesorPredmet.repository;

import com.sirmium.profesorPredmet.model.Ishod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IshodRepository extends JpaRepository<Ishod, Long> {
    
    @Query("SELECT i FROM Ishod i WHERE i.predmet.id = :predmetId ORDER BY i.redniBroj ASC")
    List<Ishod> findByPredmetIdOrderByRedniBrojAsc(@Param("predmetId") Long predmetId);
    
    @Query("SELECT i FROM Ishod i WHERE i.predmet.id = :predmetId AND i.espbBodovi > :minBodovi")
    List<Ishod> findByPredmetIdAndMinBodovi(
        @Param("predmetId") Long predmetId,
        @Param("minBodovi") int minBodovi
    );
}