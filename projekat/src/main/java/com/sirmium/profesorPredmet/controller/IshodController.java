package com.sirmium.profesorPredmet.controller;

import com.sirmium.profesorPredmet.dto.IshodDTO;
import com.sirmium.profesorPredmet.service.IshodService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ishodi")
@CrossOrigin(origins = "*")
public class IshodController {
    
    private final IshodService ishodService;
    
    public IshodController(IshodService ishodService) {
        this.ishodService = ishodService;
    }
    
    @GetMapping("/predmet/{predmetId}")
    @PreAuthorize("hasRole('PROFESOR') or hasRole('ADMIN')")
    public ResponseEntity<List<IshodDTO>> getIshodiZaPredmet(@PathVariable Long predmetId) {
        // Implementacija za dobavljanje ishoda za predmet
        return ResponseEntity.ok().build();
    }
    
    @PostMapping
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<IshodDTO> kreirajIshod(@RequestBody IshodDTO requestDTO) {
        // Implementacija za kreiranje ishoda
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<IshodDTO> azurirajIshod(
            @PathVariable Long id,
            @RequestBody IshodDTO requestDTO) {
        // Implementacija za ažuriranje ishoda
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<Void> obrisiIshod(@PathVariable Long id) {
        // Implementacija za brisanje ishoda
        return ResponseEntity.ok().build();
    }
}