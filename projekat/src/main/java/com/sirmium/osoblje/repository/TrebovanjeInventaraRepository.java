package com.sirmium.osoblje.repository;

import com.sirmium.osoblje.model.TrebovanjeInventara;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrebovanjeInventaraRepository extends JpaRepository<TrebovanjeInventara, Long> {
    
    List<TrebovanjeInventara> findByOsobljeId(Long osobljeId);
    
    List<TrebovanjeInventara> findByStatus(TrebovanjeInventara.StatusTrebovanja status);
    
    @Query("SELECT t FROM TrebovanjeInventara t WHERE t.inventar.id = :inventarId")
    List<TrebovanjeInventara> findByInventarId(@Param("inventarId") Long inventarId);
    
    @Query("SELECT t FROM TrebovanjeInventara t WHERE t.datumTrebovanja >= CURRENT_DATE - 30")
    List<TrebovanjeInventara> findRecentTrebovanja();
}