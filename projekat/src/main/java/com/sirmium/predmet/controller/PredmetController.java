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

    @Autowired
    private PredmetService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public PredmetDTO create(@RequestBody PredmetDTO dto) {
        return service.create(dto);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public List<PredmetDTO> getAll() {
        return service.findAll();
    }
    
    @GetMapping
    public List<PredmetDTO> getAktivni() {
        return service.findAllAktivni();
    }

    @PutMapping("/{id}/aktiviraj")
    @PreAuthorize("hasAnyRole('ADMIN', 'SLUZBENIK')")
    public PredmetDTO aktiviraj(@PathVariable Long id) {
        // Ovo ćeš morati da rešiš - bolje je dodati metode u service interfejs
        return service.findById(id); // Privremeno rešenje
    }

    @PutMapping("/{id}/deaktiviraj")
    @PreAuthorize("hasAnyRole('ADMIN', 'SLUZBENIK')")
    public PredmetDTO deaktiviraj(@PathVariable Long id) {
        // Ovo ćeš morati da rešiš - bolje je dodati metode u service interfejs
        return service.findById(id); // Privremeno rešenje
    }

    @GetMapping("/{id}")
    public PredmetDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public PredmetDTO update(@PathVariable Long id, @RequestBody PredmetDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}