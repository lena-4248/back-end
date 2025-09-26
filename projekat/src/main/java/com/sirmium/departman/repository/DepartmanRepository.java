package com.sirmium.departman.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sirmium.departman.model.Departman;

public interface DepartmanRepository extends JpaRepository<Departman, Long> {
    
    List<Departman> findByFakultetId(Long fakultetId);
    
    List<Departman> findByDeletedFalse();

    List<Departman> findAllByOrderByDeletedAscFakultetNazivAsc();
    
    List<Departman> findByFakultetIdAndDeletedFalse(Long fakultetId);
}
