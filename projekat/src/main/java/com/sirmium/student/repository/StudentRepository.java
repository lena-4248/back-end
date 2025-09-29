package com.sirmium.student.repository;

import com.sirmium.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByBrojIndeksa(String brojIndeksa);
    Optional<Student> findByUserId(Long userId);
    
    @Query("SELECT s FROM Student s WHERE s.godinaUpisa = :godina")
    List<Student> findByGodinaUpisa(@Param("godina") int godinaUpisa);
    
    @Query("SELECT s FROM Student s WHERE s.smer = :smer")
    List<Student> findBySmer(@Param("smer") String smer);
    
    @Query("SELECT s FROM Student s WHERE s.prosecnaOcena >= :minOcena")
    List<Student> findByProsecnaOcenaGreaterThanEqual(@Param("minOcena") double minOcena);
    
    @Query("SELECT s FROM Student s WHERE s.user.deleted = false")
    List<Student> findAllActive();
    
    @Query("SELECT s FROM Student s WHERE " +
           "LOWER(s.user.ime) LIKE LOWER(CONCAT('%', :ime, '%')) OR " +
           "LOWER(s.user.prezime) LIKE LOWER(CONCAT('%', :prezime, '%')) OR " +
           "s.brojIndeksa LIKE CONCAT('%', :brojIndeksa, '%')")
    List<Student> pretraziStudente(@Param("ime") String ime, 
                                  @Param("prezime") String prezime, 
                                  @Param("brojIndeksa") String brojIndeksa);
}