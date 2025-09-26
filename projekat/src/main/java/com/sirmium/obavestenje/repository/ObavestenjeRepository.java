package com.sirmium.obavestenje.repository;

import com.sirmium.obavestenje.model.Obavestenje;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObavestenjeRepository extends JpaRepository<Obavestenje, Long> {
    
    List<Obavestenje> findByAutorId(Long autorId);
    
    List<Obavestenje> findByPredmetId(Long predmetId);
    
    List<Obavestenje> findByAutorIdAndPredmetId(Long autorId, Long predmetId);
    
    List<Obavestenje> findByAktivanTrue();
    
    List<Obavestenje> findByAktivanFalse();
    
    List<Obavestenje> findByPredmetIdAndAktivanTrue(Long predmetId);
}