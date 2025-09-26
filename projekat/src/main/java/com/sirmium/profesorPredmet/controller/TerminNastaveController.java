package com.sirmium.profesorPredmet.controller;

import com.sirmium.profesorPredmet.dto.TerminNastaveDTO;
import com.sirmium.profesorPredmet.service.TerminNastaveService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/termini-nastave")
@CrossOrigin(origins = "*")
public class TerminNastaveController {
    
    private final TerminNastaveService terminNastaveService;
    
    public TerminNastaveController(TerminNastaveService terminNastaveService) {
        this.terminNastaveService = terminNastaveService;
    }
    
    @GetMapping("/predmet/{predmetId}")
    @PreAuthorize("hasRole('PROFESOR') or hasRole('ADMIN')")
    public ResponseEntity<List<TerminNastaveDTO>> getTerminiZaPredmet(@PathVariable Long predmetId) {
        // Implementacija za dobavljanje termina za predmet
        return ResponseEntity.ok().build();
    }
    
    @PostMapping
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<TerminNastaveDTO> zakaziTermin(@RequestBody TerminNastaveDTO requestDTO) {
        // Implementacija za zakazivanje termina
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<TerminNastaveDTO> azurirajTermin(
            @PathVariable Long id,
            @RequestBody TerminNastaveDTO requestDTO) {
        // Implementacija za ažuriranje termina
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<Void> otkaziTermin(@PathVariable Long id) {
        // Implementacija za otkazivanje termina
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/profesor/{profesorId}/buduci")
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<List<TerminNastaveDTO>> getBuduciTermini(@PathVariable Long profesorId) {
        // Implementacija za dobavljanje budućih termina profesora
        return ResponseEntity.ok().build();
    }
}