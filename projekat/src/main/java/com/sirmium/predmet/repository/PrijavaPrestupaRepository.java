package com.sirmium.predmet.repository;

import com.sirmium.predmet.model.PrijavaPrestupa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrijavaPrestupaRepository extends JpaRepository<PrijavaPrestupa, Long> {
    
    @Query("SELECT p FROM PrijavaPrestupa p WHERE p.pohadjanje.student.id = :studentId")
    List<PrijavaPrestupa> findByStudentId(@Param("studentId") Long studentId);
}