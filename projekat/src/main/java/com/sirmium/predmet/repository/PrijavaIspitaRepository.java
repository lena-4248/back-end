package com.sirmium.predmet.repository;

import com.sirmium.predmet.model.PrijavaIspita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrijavaIspitaRepository extends JpaRepository<PrijavaIspita, Long> {
    
    @Query("SELECT p FROM PrijavaIspita p " +
           "WHERE p.status = false " +
           "AND p.datumIspita > CURRENT_TIMESTAMP " +
           "AND p.pohadjanje.student.id = :studentId")
    List<PrijavaIspita> findDostupnePrijave(@Param("studentId") Long studentId);
    
    @Query("SELECT p FROM PrijavaIspita p WHERE p.status = false AND p.datumPrijave IS NULL AND p.pohadjanje.student.id = :studentId")
    List<PrijavaIspita> findNeprijavljeneZaStudenta(@Param("studentId") Long studentId);
}