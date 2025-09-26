package com.sirmium.predmet.repository;

import com.sirmium.predmet.model.Silabus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SilabusRepository extends JpaRepository<Silabus, Long> {
    Optional<Silabus> findByPredmetId(Long predmetId);
}