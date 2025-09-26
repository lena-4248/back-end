package com.sirmium.profesorPredmet.controller;

import com.sirmium.profesorPredmet.dto.ProfesorPredmetDTO;
import com.sirmium.profesorPredmet.service.ProfesorPredmetService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesor-predmet")
@CrossOrigin(origins = "*")
public class ProfesorPredmetController {
    
    private final ProfesorPredmetService profesorPredmetService;
    
    public ProfesorPredmetController(ProfesorPredmetService profesorPredmetService) {
        this.profesorPredmetService = profesorPredmetService;
    }
    
    @GetMapping("/profesor/{profesorId}")
    @PreAuthorize("hasRole('PROFESOR') or hasRole('ADMIN')")
    public ResponseEntity<List<ProfesorPredmetDTO>> getAngazmaniByProfesor(@PathVariable Long profesorId) {
        // Implementacija za dobavljanje angažmana profesora
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/predmet/{predmetId}")
    @PreAuthorize("hasRole('PROFESOR') or hasRole('ADMIN')")
    public ResponseEntity<List<ProfesorPredmetDTO>> getAngazmaniByPredmet(@PathVariable Long predmetId) {
        // Implementacija za dobavljanje angažmana na predmetu
        return ResponseEntity.ok().build();
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProfesorPredmetDTO> dodajAngazman(@RequestBody ProfesorPredmetDTO requestDTO) {
        // Implementacija za dodavanje angažmana
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProfesorPredmetDTO> azurirajAngazman(
            @PathVariable Long id,
            @RequestBody ProfesorPredmetDTO requestDTO) {
        // Implementacija za ažuriranje angažmana
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> ukloniAngazman(@PathVariable Long id) {
        // Implementacija za uklanjanje angažmana
        return ResponseEntity.ok().build();
    }
}