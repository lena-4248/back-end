package com.sirmium.profesor.controller;

import com.sirmium.profesor.dto.ProfesorDTO;
import com.sirmium.profesor.dto.ProfesorRequestDTO;
import com.sirmium.profesor.service.ProfesorService;
import com.sirmium.profesorPredmet.service.ProfesorPredmetService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesor")
@CrossOrigin(origins = "*")
public class ProfesorController {
    
    private final ProfesorService profesorService;
    private final ProfesorPredmetService profesorPredmetService;
    
    public ProfesorController(ProfesorService profesorService,
                            ProfesorPredmetService profesorPredmetService) {
        this.profesorService = profesorService;
        this.profesorPredmetService = profesorPredmetService;
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('PROFESOR') or hasRole('ADMIN')")
    public ResponseEntity<ProfesorDTO> getProfesor(@PathVariable Long id) {
        // Implementacija za dobavljanje profesora
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/moj-profil")
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<ProfesorDTO> getMojProfil() {
        // Implementacija za dobavljanje profila trenutnog profesora
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/moj-profil")
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<ProfesorDTO> updateMojProfil(@RequestBody ProfesorRequestDTO requestDTO) {
        // Implementacija za ažuriranje profila
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{id}/predmeti")
    @PreAuthorize("hasRole('PROFESOR') or hasRole('ADMIN')")
    public ResponseEntity<List<?>> getPredmetiProfesora(@PathVariable Long id) {
        // Implementacija za dobavljanje predmeta profesora
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/pretraga")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ProfesorDTO>> pretraziProfesore(
            @RequestParam(required = false) String ime,
            @RequestParam(required = false) String prezime,
            @RequestParam(required = false) String katedra,
            @RequestParam(required = false) String zvanje) {
        // Implementacija pretrage profesora
        return ResponseEntity.ok().build();
    }
}