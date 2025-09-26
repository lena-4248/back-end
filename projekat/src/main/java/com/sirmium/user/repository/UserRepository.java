package com.sirmium.user.repository;

import com.sirmium.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    //Pronalazi korisnika po email-u (za prijavu)
    Optional<User> findByEmail(String email);
    
    //Proverava da li postoji korisnik sa email-om
    boolean existsByEmail(String email);
    
    //Proverava da li postoji korisnik sa JMBG-om
    boolean existsByJmbg(String jmbg);
    
    // Pronalazi sve aktivne korisnike
    List<User> findByDeletedFalse();
    
    // Logičko brisanje korisnika
    @Modifying
    @Query("UPDATE User u SET u.deleted = true WHERE u.id = ?1")
    void deaktivirajKorisnika(Long id);
    
    // Aktivira korisnički nalog (posle verifikacije email-a)
    @Modifying
    @Query("UPDATE User u SET u.aktiviran = true WHERE u.id = ?1")
    void aktivirajNalog(Long id);
    
    // Pronalazi korisnike po ulozi
    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.naziv = ?1 AND u.deleted = false")
    List<User> pronadjiPoUlozi(String nazivUloge);
    
    // Resetuje lozinku korisnika
    @Modifying
    @Query("UPDATE User u SET u.password = ?2 WHERE u.email = ?1")
    void resetujLozinku(String email, String novaEnkriptovanaLozinka);
}