package com.sirmium.predmet.repository;

import com.sirmium.predmet.model.Predmet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PredmetRepository extends JpaRepository<Predmet, Long> {
    /*Optional<Predmet> findBySifra(String sifra);
    
    @Query("SELECT p FROM Predmet p WHERE p.studijskiProgram.id = :programId")
    List<Predmet> findByStudijskiProgramId(@Param("programId") Long programId);
    
    @Query("SELECT p FROM Predmet p WHERE p.semestar = :semestar")
    List<Predmet> findBySemestar(@Param("semestar") int semestar);
    
    @Query("SELECT p FROM Predmet p WHERE LOWER(p.naziv) LIKE LOWER(CONCAT('%', :naziv, '%'))")
    List<Predmet> findByNazivContaining(@Param("naziv") String naziv);
    
    @Query("SELECT p FROM Predmet p WHERE p.espb = :espb")
    List<Predmet> findByEspb(@Param("espb") int espb);
    */
}