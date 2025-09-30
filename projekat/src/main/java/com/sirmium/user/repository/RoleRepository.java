package com.sirmium.user.repository;

import com.sirmium.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByNaziv(String naziv);
    java.util.List<Role> findByAktivnaTrue();
    
    @Query("SELECT r FROM Role r WHERE r.naziv IN :nazivi")
    List<Role> findByNazivIn(@Param("nazivi") List<String> nazivi);

    @Modifying
    @Query("UPDATE Role r SET r.aktivna = false WHERE r.id = :roleId")
    void deaktivirajUlogu(@Param("roleId") Long roleId);
}