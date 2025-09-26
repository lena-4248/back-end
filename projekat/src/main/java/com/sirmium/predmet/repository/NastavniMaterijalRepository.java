
package com.sirmium.predmet.repository;

import com.sirmium.predmet.model.NastavniMaterijal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NastavniMaterijalRepository extends JpaRepository<NastavniMaterijal, Long> {
    List<NastavniMaterijal> findByPredmetId(Long predmetId);
}