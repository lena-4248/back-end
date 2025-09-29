package com.sirmium.osoblje.repository;

import com.sirmium.osoblje.model.UpisStudenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UpisStudentaRepository extends JpaRepository<UpisStudenta, Long> {
    
    @Query("SELECT u FROM UpisStudenta u WHERE u.student.id = :studentId ORDER BY u.datumUpisa DESC")
    List<UpisStudenta> findByStudentId(@Param("studentId") Long studentId);
    
    @Query("SELECT u FROM UpisStudenta u WHERE u.skolskaGodina = :skolskaGodina")
    List<UpisStudenta> findBySkolskaGodina(@Param("skolskaGodina") String skolskaGodina);
    
    @Query("SELECT u FROM UpisStudenta u WHERE u.skolskaGodina = :skolskaGodina AND u.godinaStudija = :godinaStudija")
    List<UpisStudenta> findBySkolskaGodinaAndGodinaStudija(
        @Param("skolskaGodina") String skolskaGodina,
        @Param("godinaStudija") int godinaStudija
    );
    
    @Query("SELECT u FROM UpisStudenta u WHERE u.skolskaGodina = :skolskaGodina AND u.status = :status")
    List<UpisStudenta> findBySkolskaGodinaAndStatus(
        @Param("skolskaGodina") String skolskaGodina,
        @Param("status") String status
    );
    
    @Query("SELECT COUNT(u) > 0 FROM UpisStudenta u WHERE u.student.id = :studentId AND u.skolskaGodina = :skolskaGodina")
    boolean existsByStudentIdAndSkolskaGodina(@Param("studentId") Long studentId, 
                                             @Param("skolskaGodina") String skolskaGodina);
}