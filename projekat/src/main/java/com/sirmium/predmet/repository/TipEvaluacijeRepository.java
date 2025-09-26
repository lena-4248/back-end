package com.sirmium.predmet.repository;

import com.sirmium.predmet.model.TipEvaluacije;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipEvaluacijeRepository extends JpaRepository<TipEvaluacije, Long> {
}