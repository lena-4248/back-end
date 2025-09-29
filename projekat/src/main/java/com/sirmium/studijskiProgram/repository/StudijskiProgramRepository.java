package com.sirmium.studijskiProgram.repository;

import com.sirmium.studijskiProgram.model.StudijskiProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudijskiProgramRepository extends JpaRepository<StudijskiProgram, Long> {
    
    @Query("SELECT sp FROM StudijskiProgram sp WHERE sp.fakultet.id = :fakultetId")
    List<StudijskiProgram> findByFakultetId(@Param("fakultetId") Long fakultetId);
    
    @Query("SELECT sp FROM StudijskiProgram sp WHERE sp.tipStudija.id = :tipStudijaId")
    List<StudijskiProgram> findByTipStudijaId(@Param("tipStudijaId") Long tipStudijaId);
    
    @Query("SELECT sp FROM StudijskiProgram sp WHERE sp.rukovodilac.id = :rukovodilacId")
    List<StudijskiProgram> findByRukovodilacId(@Param("rukovodilacId") Long rukovodilacId);
    
    @Query("SELECT sp FROM StudijskiProgram sp WHERE LOWER(sp.naziv) LIKE LOWER(CONCAT('%', :naziv, '%'))")
    List<StudijskiProgram> findByNazivContaining(@Param("naziv") String naziv);
    
    @Query("SELECT sp FROM StudijskiProgram sp WHERE sp.stepenStudija = :stepenStudija")
    List<StudijskiProgram> findByStepenStudija(@Param("stepenStudija") String stepenStudija);
    
    @Query("SELECT sp FROM StudijskiProgram sp WHERE sp.aktivan = true")
    List<StudijskiProgram> findAktivni();
    
    @Query("SELECT sp FROM StudijskiProgram sp WHERE sp.fakultet.id = :fakultetId AND sp.aktivan = true")
    List<StudijskiProgram> findAktivniByFakultetId(@Param("fakultetId") Long fakultetId);
    
    @Query("SELECT COUNT(p) FROM Predmet p WHERE p.studijskiProgram.id = :programId")
    Long countPredmetiByProgramId(@Param("programId") Long programId);
}