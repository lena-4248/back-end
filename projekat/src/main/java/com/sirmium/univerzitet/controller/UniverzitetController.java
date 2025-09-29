package com.sirmium.univerzitet.controller;

import com.sirmium.univerzitet.dto.UniverzitetDTO;
import com.sirmium.univerzitet.model.Univerzitet;
import com.sirmium.univerzitet.service.UniverzitetService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/univerziteti")
@CrossOrigin(origins = "*")
public class UniverzitetController {

    private final UniverzitetService univerzitetService;

    public UniverzitetController(UniverzitetService univerzitetService) {
        this.univerzitetService = univerzitetService;
    }

    @GetMapping
    public ResponseEntity<List<UniverzitetDTO>> getAll() {
        List<UniverzitetDTO> univerziteti = univerzitetService.findAll().stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(univerziteti);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UniverzitetDTO> getById(@PathVariable Long id) {
        return univerzitetService.findById(id)
            .map(this::mapToDTO)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UniverzitetDTO> create(@RequestBody UniverzitetDTO univerzitetDTO) {
        Univerzitet univerzitet = mapToEntity(univerzitetDTO);
        Univerzitet saved = univerzitetService.save(univerzitet);
        return ResponseEntity.ok(mapToDTO(saved));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UniverzitetDTO> update(@PathVariable Long id, @RequestBody UniverzitetDTO univerzitetDTO) {
        try {
            Univerzitet univerzitet = mapToEntity(univerzitetDTO);
            Univerzitet updated = univerzitetService.update(id, univerzitet);
            return ResponseEntity.ok(mapToDTO(updated));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            univerzitetService.delete(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/rektor/{profesorId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UniverzitetDTO> postaviRektora(@PathVariable Long id, @PathVariable Long profesorId) {
        try {
            Univerzitet univerzitet = univerzitetService.postaviRektora(id, profesorId);
            return ResponseEntity.ok(mapToDTO(univerzitet));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/pretraga")
    public ResponseEntity<List<UniverzitetDTO>> pretrazi(@RequestParam String naziv) {
        List<UniverzitetDTO> univerziteti = univerzitetService.pretraziPoNazivu(naziv).stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(univerziteti);
    }

    @GetMapping("/{id}/broj-fakulteta")
    public ResponseEntity<Long> getBrojFakulteta(@PathVariable Long id) {
        try {
            Long brojFakulteta = univerzitetService.getBrojFakulteta(id);
            return ResponseEntity.ok(brojFakulteta);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Helper metode za mapiranje
    private UniverzitetDTO mapToDTO(Univerzitet univerzitet) {
        UniverzitetDTO dto = new UniverzitetDTO();
        dto.setId(univerzitet.getId());
        dto.setNaziv(univerzitet.getNaziv());
        dto.setOpis(univerzitet.getOpis());
        dto.setAdresa(univerzitet.getAdresa());
        dto.setKontaktEmail(univerzitet.getKontaktEmail());
        dto.setKontaktTelefon(univerzitet.getKontaktTelefon());
        dto.setIstorijat(univerzitet.getIstorijat());
        dto.setLogoPath(univerzitet.getLogoPath());
        dto.setWebsiteUrl(univerzitet.getWebsiteUrl());
        
        if (univerzitet.getRektor() != null) {
            dto.setRektorId(univerzitet.getRektor().getId());
            dto.setRektorIme(univerzitet.getRektor().getIme());
            dto.setRektorPrezime(univerzitet.getRektor().getPrezime());
        }
        
        return dto;
    }

    private Univerzitet mapToEntity(UniverzitetDTO dto) {
        Univerzitet univerzitet = new Univerzitet();
        univerzitet.setNaziv(dto.getNaziv());
        univerzitet.setOpis(dto.getOpis());
        univerzitet.setAdresa(dto.getAdresa());
        univerzitet.setKontaktEmail(dto.getKontaktEmail());
        univerzitet.setKontaktTelefon(dto.getKontaktTelefon());
        univerzitet.setIstorijat(dto.getIstorijat());
        univerzitet.setLogoPath(dto.getLogoPath());
        univerzitet.setWebsiteUrl(dto.getWebsiteUrl());
        return univerzitet;
    }
}