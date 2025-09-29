package com.sirmium.osoblje.repository;

import com.sirmium.osoblje.model.Potvrda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PotvrdaRepository extends JpaRepository<Potvrda, Long> {
    
    @Query("SELECT p FROM Potvrda p WHERE p.student.id = :studentId ORDER BY p.datumIzdavanja DESC")
    List<Potvrda> findByStudentId(@Param("studentId") Long studentId);
    
    @Query("SELECT p FROM Potvrda p WHERE p.tipPotvrde = :tipPotvrde")
    List<Potvrda> findByTipPotvrde(@Param("tipPotvrde") String tipPotvrde);
    
    @Query("SELECT p FROM Potvrda p WHERE p.status = :status")
    List<Potvrda> findByStatus(@Param("status") String status);
    
    @Query("SELECT p FROM Potvrda p WHERE p.datumIzdavanja BETWEEN :start AND :end")
    List<Potvrda> findByDatumIzdavanjaBetween(
        @Param("start") LocalDate start,
        @Param("end") LocalDate end
    );
    
    @Query("SELECT p FROM Potvrda p WHERE p.tipPotvrde = :tipPotvrde AND p.status = 'IZDATA'")
    List<Potvrda> findIzdanePotvrdeByTip(@Param("tipPotvrde") String tipPotvrde);
}