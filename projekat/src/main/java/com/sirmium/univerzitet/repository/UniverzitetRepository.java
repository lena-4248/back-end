package com.sirmium.univerzitet.repository;

import com.sirmium.univerzitet.model.Univerzitet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UniverzitetRepository extends JpaRepository<Univerzitet, Long> {
    
    Optional<Univerzitet> findByNaziv(String naziv);
    
    @Query("SELECT u FROM Univerzitet u WHERE LOWER(u.naziv) LIKE LOWER(CONCAT('%', :naziv, '%'))")
    List<Univerzitet> findByNazivContaining(@Param("naziv") String naziv);
    
    @Query("SELECT u FROM Univerzitet u WHERE u.rektor.id = :rektorId")
    List<Univerzitet> findByRektorId(@Param("rektorId") Long rektorId);
    
    @Query("SELECT COUNT(f) FROM Fakultet f WHERE f.univerzitet.id = :univerzitetId")
    Long countFakultetiByUniverzitetId(@Param("univerzitetId") Long univerzitetId);
}