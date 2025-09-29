package com.sirmium.profesor.repository;

import com.sirmium.profesor.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    Optional<Profesor> findByUserId(Long userId);
    Optional<Profesor> findByUserEmail(String email);
    
    @Query("SELECT p FROM Profesor p WHERE p.zvanje = :zvanje")
    List<Profesor> findByZvanje(@Param("zvanje") String zvanje);
    
    @Query("SELECT p FROM Profesor p WHERE p.katedra = :katedra")
    List<Profesor> findByKatedra(@Param("katedra") String katedra);
    
    @Query("SELECT p FROM Profesor p WHERE " +
           "LOWER(p.user.ime) LIKE LOWER(CONCAT('%', :ime, '%')) OR " +
           "LOWER(p.user.prezime) LIKE LOWER(CONCAT('%', :prezime, '%'))")
    List<Profesor> findByImeOrPrezime(@Param("ime") String ime, @Param("prezime") String prezime);
    
    @Query("SELECT p FROM Profesor p WHERE p.user.deleted = false")
    List<Profesor> findAllActive();
}