package com.sirmium.predmet.controller;

import com.sirmium.predmet.dto.PredmetDTO;
import com.sirmium.predmet.service.PredmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predmeti")
public class PredmetController {

    private final PredmetService predmetService; // koristite 'predmetService'

    @Autowired
    public PredmetController(PredmetService predmetService) {
        this.predmetService = predmetService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public PredmetDTO create(@RequestBody PredmetDTO dto) {
        return predmetService.create(dto); // koristite 'predmetService'
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public List<PredmetDTO> getAll() {
        return predmetService.findAll(); // koristite 'predmetService'
    }
    
    @GetMapping
    public List<PredmetDTO> getAktivni() {
        return predmetService.findAllAktivni(); // koristite 'predmetService'
    }

    @PutMapping("/{id}/aktiviraj")
    @PreAuthorize("hasAnyRole('ADMIN', 'SLUZBENIK')")
    public PredmetDTO aktiviraj(@PathVariable Long id) {
        return predmetService.aktiviraj(id); // koristite 'predmetService'
    }

    @PutMapping("/{id}/deaktiviraj")
    @PreAuthorize("hasAnyRole('ADMIN', 'SLUZBENIK')")
    public PredmetDTO deaktiviraj(@PathVariable Long id) {
        return predmetService.deaktiviraj(id); // koristite 'predmetService'
    }

    @GetMapping("/{id}")
    public PredmetDTO getById(@PathVariable Long id) {
        return predmetService.findById(id); // koristite 'predmetService'
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public PredmetDTO update(@PathVariable Long id, @RequestBody PredmetDTO dto) {
        return predmetService.update(id, dto); // koristite 'predmetService'
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public void delete(@PathVariable Long id) {
        predmetService.delete(id); // koristite 'predmetService'
    }
}