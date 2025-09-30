package com.sirmium.test;

import com.sirmium.user.model.Role;
import com.sirmium.user.model.User;
import com.sirmium.user.repository.UserRepository;
import com.sirmium.user.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class TestRunner implements CommandLineRunner {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Override
    public void run(String... args) throws Exception {
        System.out.println("-- TESTIRANJE REPOSITORYJA --");
        
        // PRVO proverite da li role već postoje u bazi
        Optional<Role> existingAdminRole = roleRepository.findByNaziv("ADMIN");
        Optional<Role> existingProfesorRole = roleRepository.findByNaziv("PROFESOR");
        Optional<Role> existingStudentRole = roleRepository.findByNaziv("STUDENT");
        
        Role adminRole;
        Role profesorRole;
        Role studentRole;
        
        if (existingAdminRole.isPresent()) {
            adminRole = existingAdminRole.get();
            System.out.println("ADMIN role već postoji u bazi");
        } else {
            adminRole = new Role();
            adminRole.setNaziv("ADMIN");
            adminRole = roleRepository.save(adminRole);
            System.out.println("Kreirana ADMIN role");
        }
        
        if (existingProfesorRole.isPresent()) {
            profesorRole = existingProfesorRole.get();
            System.out.println("PROFESOR role već postoji u bazi");
        } else {
            profesorRole = new Role();
            profesorRole.setNaziv("PROFESOR");
            profesorRole = roleRepository.save(profesorRole);
            System.out.println("Kreirana PROFESOR role");
        }
        
        if (existingStudentRole.isPresent()) {
            studentRole = existingStudentRole.get();
            System.out.println("STUDENT role već postoji u bazi");
        } else {
            studentRole = new Role();
            studentRole.setNaziv("STUDENT");
            studentRole = roleRepository.save(studentRole);
            System.out.println("Kreirana STUDENT role");
        }
        
        // Proverite da li test korisnici već postoje
        Optional<User> existingTestUser1 = userRepository.findByEmail("test@example.com");
        Optional<User> existingTestUser2 = userRepository.findByEmail("test@student.com");
        
        if (!existingTestUser1.isPresent()) {
            // Kreiranje prvog test korisnika (sa ADMIN i PROFESOR rolama)
            User user1 = new User();
            user1.setIme("Admin");
            user1.setPrezime("Korisnik");
            user1.setEmail("test@example.com");
            user1.setPassword("password");
            user1.setAdresa("Test Adresa 123"); // DODAJTE ADRESU
            user1.setJmbg("1234567890123");     // DODAJTE JMBG
            user1.setTelefon("061123456");      // DODAJTE TELEFON
            user1.setAktiviran(true);           // DODAJTE AKTIVIRAN
            user1.setDeleted(false);            // DODAJTE DELETED
            user1.setSlikaUrl(null); 
            user1.setRoles(Set.of(adminRole, profesorRole));
            
            userRepository.save(user1);
            System.out.println("Sačuvan prvi test korisnik: " + user1.getEmail());
        } else {
            System.out.println("Prvi test korisnik već postoji: " + existingTestUser1.get().getEmail());
        }
        
        if (!existingTestUser2.isPresent()) {
            // Kreiranje drugog test korisnika (sa STUDENT rolom)
            User user2 = new User();
            user2.setIme("Marko");
            user2.setPrezime("Marković");
            user2.setEmail("test@student.com");
            user2.setPassword("password123");
            user2.setAdresa("Studentska adresa 456"); // DODAJTE ADRESU
            user2.setJmbg("9876543210987");           // DODAJTE JMBG
            user2.setTelefon("061987654");            // DODAJTE TELEFON
            user2.setAktiviran(true);                 // DODAJTE AKTIVIRAN
            user2.setDeleted(false);                  // DODAJTE DELETED
            user2.setSlikaUrl(null);
            user2.setRoles(Set.of(studentRole));
            
            userRepository.save(user2);
            System.out.println("Sačuvan drugi test korisnik: " + user2.getEmail());
        } else {
            System.out.println("Drugi test korisnik već postoji: " + existingTestUser2.get().getEmail());
        }
        
        // Testiranje repository metoda
        Optional<User> foundUser = userRepository.findByEmail("test@student.com");
        if (foundUser.isPresent()) {
            System.out.println("Pronađen korisnik: " + foundUser.get().getIme() + " " + foundUser.get().getPrezime());
        }
        
        List<User> activeUsers = userRepository.findByDeletedFalse();
        System.out.println("Aktivni korisnici: " + activeUsers.size());
        
        System.out.println("-- TESTIRANJE ZAVRŠENO --");
    }
}