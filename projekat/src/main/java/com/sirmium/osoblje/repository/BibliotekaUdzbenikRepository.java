package com.sirmium.osoblje.repository;

import com.sirmium.osoblje.model.BibliotekaUdzbenik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BibliotekaUdzbenikRepository extends JpaRepository<BibliotekaUdzbenik, Long> {
    
    List<BibliotekaUdzbenik> findByAutorContainingIgnoreCase(String autor);
    
    List<BibliotekaUdzbenik> findByNaslovContainingIgnoreCase(String naslov);
    
    BibliotekaUdzbenik findByIsbn(String isbn);
    
    @Query("SELECT b FROM BibliotekaUdzbenik b WHERE b.dostupno > 0")
    List<BibliotekaUdzbenik> findDostupniUdzbenici();
    
    @Query("SELECT b FROM BibliotekaUdzbenik b WHERE b.dostupno = 0")
    List<BibliotekaUdzbenik> findNedostupniUdzbenici();
}