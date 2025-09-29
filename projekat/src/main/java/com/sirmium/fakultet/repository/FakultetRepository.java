package com.sirmium.fakultet.repository;

import com.sirmium.fakultet.model.Fakultet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FakultetRepository extends JpaRepository<Fakultet, Long> {
    
    @Query("SELECT f FROM Fakultet f WHERE f.univerzitet.id = :univerzitetId")
    List<Fakultet> findByUniverzitetId(@Param("univerzitetId") Long univerzitetId);
    
    @Query("SELECT f FROM Fakultet f WHERE LOWER(f.naziv) LIKE LOWER(CONCAT('%', :naziv, '%'))")
    List<Fakultet> findByNazivContaining(@Param("naziv") String naziv);
}