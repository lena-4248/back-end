package com.sirmium.predmet.controller;

import com.sirmium.predmet.dto.PohadjanjePredmetaDTO;
import com.sirmium.predmet.service.pohadjanjePredmeta.PohadjanjePredmetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pohadjanja")
public class PohadjanjePredmetaController {

    @Autowired
    private PohadjanjePredmetaService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public PohadjanjePredmetaDTO create(@RequestBody PohadjanjePredmetaDTO dto) {
        return service.create(dto);
    }
    
    @PostMapping("/upisi")
    public ResponseEntity<Void> upisiStudenta(@RequestParam Long studentId, @RequestParam List<Long> predmetIds) {
        service.upisiStudenta(studentId, predmetIds);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<PohadjanjePredmetaDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PohadjanjePredmetaDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public PohadjanjePredmetaDTO update(@PathVariable Long id, @RequestBody PohadjanjePredmetaDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}