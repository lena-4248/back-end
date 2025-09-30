package com.sirmium.profesorPredmet.repository;

import com.sirmium.profesorPredmet.model.TerminNastave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TerminNastaveRepository extends JpaRepository<TerminNastave, Long> {
    
    @Query("SELECT t FROM TerminNastave t WHERE t.predmet.id = :predmetId ORDER BY t.datumVreme ASC")
    List<TerminNastave> findByPredmetIdOrderByDatumVremeAsc(@Param("predmetId") Long predmetId);
    
    @Query("SELECT t FROM TerminNastave t WHERE t.predmet.id = :predmetId AND t.datumVreme BETWEEN :start AND :end")
    List<TerminNastave> findByPredmetIdAndDatumVremeBetween(
        @Param("predmetId") Long predmetId,
        @Param("start") LocalDateTime start,
        @Param("end") LocalDateTime end
    );
    
    @Query("SELECT t FROM TerminNastave t WHERE t.predmet.id IN :predmetIds AND t.datumVreme >= :fromDate")
    List<TerminNastave> findByPredmetIdsAndDatumVremeAfter(
        @Param("predmetIds") List<Long> predmetIds,
        @Param("fromDate") LocalDateTime fromDate
    );
    
    @Query("SELECT t FROM TerminNastave t WHERE t.profesor.id = :profesorId AND t.datumVreme >= CURRENT_DATE")
    List<TerminNastave> findBuduciTerminiByProfesor(@Param("profesorId") Long profesorId);//
}