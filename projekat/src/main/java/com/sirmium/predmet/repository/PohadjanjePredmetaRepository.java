package com.sirmium.predmet.repository;

import com.sirmium.predmet.model.PohadjanjePredmeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PohadjanjePredmetaRepository extends JpaRepository<PohadjanjePredmeta, Long> {
    
    @Query("SELECT pp FROM PohadjanjePredmeta pp JOIN FETCH pp.predmet WHERE pp.student.id = :studentId")
    List<PohadjanjePredmeta> findByStudentId(@Param("studentId") Long studentId);
    
    @Query("SELECT p FROM PohadjanjePredmeta p WHERE p.predmet.id = :predmetId")
    List<PohadjanjePredmeta> findByPredmetId(@Param("predmetId") Long predmetId);
    
    @Query("SELECT pp FROM PohadjanjePredmeta pp " +
           "WHERE pp.predmet.id IN (" +
           "SELECT p.predmet.id FROM ProfesorPredmet p WHERE p.profesor.id = :profesorId)")
    List<PohadjanjePredmeta> findByProfesorId(@Param("profesorId") Long profesorId);
}