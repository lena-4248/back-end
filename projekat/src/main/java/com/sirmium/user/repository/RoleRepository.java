package com.sirmium.user.repository;

import com.sirmium.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByNaziv(String naziv);
    java.util.List<Role> findByAktivnaTrue();
}