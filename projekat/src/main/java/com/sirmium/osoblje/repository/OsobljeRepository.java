package com.sirmium.osoblje.repository;

import com.sirmium.osoblje.model.Osoblje;
import com.sirmium.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OsobljeRepository extends JpaRepository<Osoblje, Long> {
    
    Optional<Osoblje> findByUser(User user);
    
    Optional<Osoblje> findByUserId(Long userId);
    
    List<Osoblje> findByPozicija(String pozicija);
    
    List<Osoblje> findByOdeljenje(String odeljenje);
    
    @Query("SELECT o FROM Osoblje o WHERE LOWER(o.user.ime) LIKE LOWER(CONCAT('%', :ime, '%')) OR LOWER(o.user.prezime) LIKE LOWER(CONCAT('%', :prezime, '%'))")
    List<Osoblje> findByImeOrPrezime(@Param("ime") String ime, @Param("prezime") String prezime);
}