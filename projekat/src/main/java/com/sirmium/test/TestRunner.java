package com.sirmium.test;

import com.sirmium.user.model.Role;
import com.sirmium.user.model.User;
import com.sirmium.user.repository.UserRepository;
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
    
    @Override
    public void run(String... args) throws Exception {
        // Testiranje da li repository radi
        System.out.println("=== TESTIRANJE REPOSITORYJA ===");
        
        // Kreiranje test uloga
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("STUDENT"));
        
        // Kreiranje test korisnika
        User testUser = new User(
            "test@student.com", 
            "password123", 
            "Marko", 
            "Marković", 
            "Test Adresa", 
            "1234567890123", 
            "061123456", 
            null, 
            roles
        );
        
        // Čuvanje korisnika - Spring Data JPA automatski implementira save
        User savedUser = userRepository.save(testUser);
        System.out.println("✅ Sačuvan korisnik: " + savedUser.getEmail());
        
        // Pronalaženje po emailu - Spring Data JPA automatski implementira findByEmail
        Optional<User> foundUser = userRepository.findByEmail("test@student.com");
        if (foundUser.isPresent()) {
            System.out.println("✅ Pronađen korisnik: " + foundUser.get().getIme() + " " + foundUser.get().getPrezime());
        }
        
        // Pronalaženje aktivnih korisnika
        List<User> activeUsers = userRepository.findByDeletedFalse();
        System.out.println("✅ Aktivni korisnici: " + activeUsers.size());
    }
}