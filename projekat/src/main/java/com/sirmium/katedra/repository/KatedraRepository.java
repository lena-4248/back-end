package com.sirmium.katedra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sirmium.katedra.model.Katedra;

@Repository
public interface KatedraRepository extends JpaRepository<Katedra, Long> {
    
    List<Katedra> findByDepartmanId(Long departmanId);
    
    List<Katedra> findByDeletedFalse();

    List<Katedra> findByDepartmanIdAndDeletedFalse(Long departmanId);

    @Query("SELECT k FROM Katedra k ORDER BY k.deleted ASC, k.departman.fakultet.naziv ASC")
    List<Katedra> findAllSortiranoPoAktivnostiIFakultetu();
    
    // Dodatna metoda za pronalaženje katedri po ID šefa katedre
    List<Katedra> findBySefKatedreId(Long sefKatedreId);
}