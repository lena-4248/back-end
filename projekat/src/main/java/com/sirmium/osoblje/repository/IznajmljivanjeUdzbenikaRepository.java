package com.sirmium.osoblje.repository;

import com.sirmium.osoblje.model.IznajmljivanjeUdzbenika;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IznajmljivanjeUdzbenikaRepository extends JpaRepository<IznajmljivanjeUdzbenika, Long> {
    
    List<IznajmljivanjeUdzbenika> findByStudentId(Long studentId);
    
    List<IznajmljivanjeUdzbenika> findByUdzbenikId(Long udzbenikId);
    
    List<IznajmljivanjeUdzbenika> findByStatus(IznajmljivanjeUdzbenika.StatusIznajmljivanja status);
    
    @Query("SELECT i FROM IznajmljivanjeUdzbenika i WHERE i.rokVracanja < CURRENT_DATE AND i.status = 'IZNAJMLJEN'")
    List<IznajmljivanjeUdzbenika> findKasnaIznajmljivanja();
    
    @Query("SELECT i FROM IznajmljivanjeUdzbenika i WHERE i.student.id = :studentId AND i.udzbenik.id = :udzbenikId AND i.status = 'IZNAJMLJEN'")
    List<IznajmljivanjeUdzbenika> findAktivnaIznajmljivanjaStudenta(
        @Param("studentId") Long studentId, 
        @Param("udzbenikId") Long udzbenikId
    );
}