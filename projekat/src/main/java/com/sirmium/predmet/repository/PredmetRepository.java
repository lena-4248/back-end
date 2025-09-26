package com.sirmium.predmet.repository;

import com.sirmium.predmet.model.Predmet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredmetRepository extends JpaRepository<Predmet, Long> {
    List<Predmet> findByGodinaStudijaId(Long godinaStudijaId);
    
    List<Predmet> findByDeletedFalse(); // za aktivne predmete
    
    List<Predmet> findByGodinaStudijaIdAndDeletedFalse(Long godinaStudijaId);

    List<Predmet> findByProfesoriProfesorId(Long profesorId);
}