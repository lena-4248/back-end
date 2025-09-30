package com.sirmium.osoblje.repository;

import com.sirmium.osoblje.model.TrebovanjeInventara;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrebovanjeInventaraRepository extends JpaRepository<TrebovanjeInventara, Long> {
    
    List<TrebovanjeInventara> findByOsobljeId(Long osobljeId);
    
    List<TrebovanjeInventara> findByStatus(TrebovanjeInventara.StatusTrebovanja status);
}