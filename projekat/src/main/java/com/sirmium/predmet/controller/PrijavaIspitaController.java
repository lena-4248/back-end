package com.sirmium.predmet.controller;

import com.sirmium.predmet.dto.KreiranjeIspitaDTO;
import com.sirmium.predmet.dto.PrijavaIspitaDTO;
import com.sirmium.predmet.service.prijavaIspita.PrijavaIspitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prijave-ispita")
public class PrijavaIspitaController {

    private final PrijavaIspitaService prijavaIspitaService;

    @Autowired
    public PrijavaIspitaController(PrijavaIspitaService prijavaIspitaService) {
        this.prijavaIspitaService = prijavaIspitaService;
    }

    // Standardni CRUD
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public PrijavaIspitaDTO create(@RequestBody PrijavaIspitaDTO dto) {
        return prijavaIspitaService.create(dto);
    }

    @GetMapping
    public List<PrijavaIspitaDTO> getAll() {
        return prijavaIspitaService.findAll();
    }

    @GetMapping("/{id}")
    public PrijavaIspitaDTO getById(@PathVariable Long id) {
        return prijavaIspitaService.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public PrijavaIspitaDTO update(@PathVariable Long id, @RequestBody PrijavaIspitaDTO dto) {
        return prijavaIspitaService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public void delete(@PathVariable Long id) {
        prijavaIspitaService.delete(id);
    }

    // Custom funkcionalnosti za studenta
    @GetMapping("/dostupne/{studentId}")
    public ResponseEntity<List<PrijavaIspitaDTO>> getDostupnePrijave(@PathVariable Long studentId) {
        return ResponseEntity.ok(prijavaIspitaService.getDostupnePrijave(studentId));
    }

    @PostMapping("/prijavi/{prijavaId}")
    public ResponseEntity<PrijavaIspitaDTO> prijavi(@PathVariable Long prijavaId) {
        return ResponseEntity.ok(prijavaIspitaService.prijaviIspit(prijavaId));
    }
    
    @PostMapping("/kreiraj")
    public ResponseEntity<String> kreiraj(@RequestBody KreiranjeIspitaDTO dto) {
        prijavaIspitaService.kreirajPrijaveZaPredmet(dto.getPredmetId(), dto.getDatumIspita());
        return ResponseEntity.ok("Ispit uspešno kreiran.");
    }
}