package com.sirmium.student.repository;

import com.sirmium.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByBrojIndeksa(String brojIndeksa);
    Optional<Student> findByUserId(Long userId);
}