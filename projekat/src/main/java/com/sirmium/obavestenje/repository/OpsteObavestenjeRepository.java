package com.sirmium.obavestenje.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sirmium.obavestenje.model.OpsteObavestenje;


@Repository
public interface OpsteObavestenjeRepository extends JpaRepository<OpsteObavestenje, Long> {
    
    List<OpsteObavestenje> findByAktivanTrue();
    
    List<OpsteObavestenje> findByAktivanFalse();
    
    List<OpsteObavestenje> findByAutorId(Long autorId);
    
    List<OpsteObavestenje> findByAutorIdAndAktivanTrue(Long autorId);
    
    List<OpsteObavestenje> findByAutorIdAndAktivanFalse(Long autorId);
}