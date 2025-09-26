package com.sirmium.user.repository;

import com.sirmium.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
    // Pronalazi ulogu po nazivu
    Optional<Role> findByNaziv(String naziv);
    
    // Pronalazi sve aktivne uloge
    List<Role> findByAktivnaTrue();
    
    // Logičko brisanje uloge
    @Modifying
    @Query("UPDATE Role r SET r.aktivna = false WHERE r.id = ?1")
    void deaktivirajUlogu(Long id);
    
    // Pronalazi uloge po listi naziva
    @Query("SELECT r FROM Role r WHERE r.naziv IN ?1 AND r.aktivna = true")
    List<Role> pronadjiPoNazivimaUloga(List<String> naziviUloga);
}