package com.sirmium.osoblje.repository;

import com.sirmium.osoblje.model.UpisStudenta;
import com.sirmium.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UpisStudentaRepository extends JpaRepository<UpisStudenta, Long> {
    
    List<UpisStudenta> findByStudent(Student student);
    
    List<UpisStudenta> findBySkolskaGodina(String skolskaGodina);
    
    List<UpisStudenta> findBySkolskaGodinaAndGodinaStudija(String skolskaGodina, int godinaStudija);
    
    @Query("SELECT u FROM UpisStudenta u WHERE u.student.id = :studentId ORDER BY u.datumUpisa DESC")
    List<UpisStudenta> findByStudentId(@Param("studentId") Long studentId);
    
    @Query("SELECT u FROM UpisStudenta u WHERE u.skolskaGodina = :skolskaGodina AND u.status = :status")
    List<UpisStudenta> findBySkolskaGodinaAndStatus(
        @Param("skolskaGodina") String skolskaGodina,
        @Param("status") String status
    );
    
    boolean existsByStudentIdAndSkolskaGodina(Long studentId, String skolskaGodina);
}