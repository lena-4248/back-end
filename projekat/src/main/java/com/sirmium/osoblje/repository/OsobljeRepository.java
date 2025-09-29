package com.sirmium.osoblje.repository;

import com.sirmium.osoblje.model.Osoblje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OsobljeRepository extends JpaRepository<Osoblje, Long> {
    Optional<Osoblje> findByUserId(Long userId);
    Optional<Osoblje> findByUserEmail(String email);
    
    @Query("SELECT o FROM Osoblje o WHERE o.pozicija = :pozicija")
    List<Osoblje> findByPozicija(@Param("pozicija") String pozicija);
    
    @Query("SELECT o FROM Osoblje o WHERE o.odeljenje = :odeljenje")
    List<Osoblje> findByOdeljenje(@Param("odeljenje") String odeljenje);
    
    @Query("SELECT o FROM Osoblje o WHERE " +
           "LOWER(o.user.ime) LIKE LOWER(CONCAT('%', :ime, '%')) OR " +
           "LOWER(o.user.prezime) LIKE LOWER(CONCAT('%', :prezime, '%'))")
    List<Osoblje> findByImeOrPrezime(@Param("ime") String ime, @Param("prezime") String prezime);
    
    @Query("SELECT o FROM Osoblje o WHERE o.user.deleted = false")
    List<Osoblje> findAllActive();
}