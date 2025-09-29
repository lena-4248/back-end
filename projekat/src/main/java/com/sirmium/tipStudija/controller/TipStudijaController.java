package com.sirmium.tipStudija.controller;

import com.sirmium.tipStudija.dto.TipStudijaDTO;
import com.sirmium.tipStudija.model.TipStudija;
import com.sirmium.tipStudija.service.TipStudijaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tipovi-studija")
@CrossOrigin(origins = "*")
public class TipStudijaController {

    private final TipStudijaService tipStudijaService;

    public TipStudijaController(TipStudijaService tipStudijaService) {
        this.tipStudijaService = tipStudijaService;
    }

    @GetMapping
    public ResponseEntity<List<TipStudijaDTO>> getAll() {
        List<TipStudijaDTO> tipovi = tipStudijaService.findAll().stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(tipovi);
    }

    @GetMapping("/aktivni")
    public ResponseEntity<List<TipStudijaDTO>> getAktivni() {
        List<TipStudijaDTO> tipovi = tipStudijaService.findAllAktivni().stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(tipovi);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipStudijaDTO> getById(@PathVariable Long id) {
        return tipStudijaService.findById(id)
            .map(this::mapToDTO)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TipStudijaDTO> create(@RequestBody TipStudijaDTO tipStudijaDTO) {
        TipStudija tipStudija = mapToEntity(tipStudijaDTO);
        TipStudija saved = tipStudijaService.save(tipStudija);
        return ResponseEntity.ok(mapToDTO(saved));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TipStudijaDTO> update(@PathVariable Long id, @RequestBody TipStudijaDTO tipStudijaDTO) {
        try {
            TipStudija tipStudija = mapToEntity(tipStudijaDTO);
            TipStudija updated = tipStudijaService.update(id, tipStudija);
            return ResponseEntity.ok(mapToDTO(updated));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            tipStudijaService.delete(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/deaktiviraj")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TipStudijaDTO> deaktiviraj(@PathVariable Long id) {
        try {
            TipStudija tipStudija = tipStudijaService.deaktiviraj(id);
            return ResponseEntity.ok(mapToDTO(tipStudija));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/aktiviraj")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TipStudijaDTO> aktiviraj(@PathVariable Long id) {
        try {
            TipStudija tipStudija = tipStudijaService.aktiviraj(id);
            return ResponseEntity.ok(mapToDTO(tipStudija));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/pretraga")
    public ResponseEntity<List<TipStudijaDTO>> pretrazi(@RequestParam String naziv) {
        List<TipStudijaDTO> tipovi = tipStudijaService.pretraziPoNazivu(naziv).stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(tipovi);
    }

    // Helper metode za mapiranje
    private TipStudijaDTO mapToDTO(TipStudija tipStudija) {
        TipStudijaDTO dto = new TipStudijaDTO();
        dto.setId(tipStudija.getId());
        dto.setNaziv(tipStudija.getNaziv());
        dto.setOpis(tipStudija.getOpis());
        dto.setTrajanjeGodine(tipStudija.getTrajanjeGodine());
        dto.setAktiv(tipStudija.getAktiv());
        return dto;
    }

    private TipStudija mapToEntity(TipStudijaDTO dto) {
        TipStudija tipStudija = new TipStudija();
        tipStudija.setNaziv(dto.getNaziv());
        tipStudija.setOpis(dto.getOpis());
        tipStudija.setTrajanjeGodine(dto.getTrajanjeGodine());
        tipStudija.setAktiv(dto.getAktiv() != null ? dto.getAktiv() : true);
        return tipStudija;
    }
}