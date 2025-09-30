package com.sirmium.profesorPredmet.repository;

import com.sirmium.profesor.model.Profesor;
import com.sirmium.profesorPredmet.model.ProfesorPredmet;
import com.sirmium.predmet.model.Predmet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesorPredmetRepository extends JpaRepository<ProfesorPredmet, Long> {
	
	List<ProfesorPredmet> findByPredmetId(Long predmetId);
    List<ProfesorPredmet> findByProfesorId(Long profesorId);
    List<ProfesorPredmet> findByProfesorIdAndSemestar(Long profesorId, int semestar);
    
    /*List<ProfesorPredmet> findByProfesor(Profesor profesor);
    
    List<ProfesorPredmet> findByPredmet(Predmet predmet);
    
    List<ProfesorPredmet> findByProfesorAndTipAngazmana(Profesor profesor, String tipAngazmana);
    
    @Query("SELECT np FROM NastavnikPredmet np WHERE np.profesor.id = :profesorId")
    List<ProfesorPredmet> findByProfesorId(@Param("profesorId") Long profesorId);
    
    @Query("SELECT np FROM NastavnikPredmet np WHERE np.predmet.id = :predmetId")
    List<ProfesorPredmet> findByPredmetId(@Param("predmetId") Long predmetId);
    
    boolean existsByProfesorAndPredmet(Profesor profesor, Predmet predmet);
    
    @Query("SELECT np FROM NastavnikPredmet np WHERE np.profesor.id = :profesorId AND np.predmet.semestar = :semestar")
    List<ProfesorPredmet> findByProfesorIdAndSemestar(@Param("profesorId") Long profesorId, @Param("semestar") int semestar);*/
}