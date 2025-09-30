/*package com.sirmium.osoblje.controller;

import com.sirmium.osoblje.dto.TrebovanjeRequestDTO;
import com.sirmium.osoblje.model.Osoblje;
import com.sirmium.osoblje.model.TrebovanjeInventara;
import com.sirmium.osoblje.service.OsobljeService;
import com.sirmium.osoblje.service.TrebovanjeInventaraService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/trebovanja")
@CrossOrigin(origins = "*")
public class TrebovanjeInventaraController {

    private final TrebovanjeInventaraService trebovanjeService;
    private final OsobljeService osobljeService;

    public TrebovanjeInventaraController(TrebovanjeInventaraService trebovanjeService,
                                       OsobljeService osobljeService) {
        this.trebovanjeService = trebovanjeService;
        this.osobljeService = osobljeService;
    }

    @PostMapping
    @PreAuthorize("hasRole('OSOBLJE')")
    public ResponseEntity<TrebovanjeInventara> kreirajTrebovanje(
            @RequestBody TrebovanjeRequestDTO requestDTO,
            Principal principal) {
        try {
            Osoblje osoblje = osobljeService.findByUserEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Osoblje nije pronađeno"));
            
            TrebovanjeInventara trebovanje = trebovanjeService.kreirajTrebovanje(
                osoblje, requestDTO.getInventarId(), requestDTO.getKolicina(), requestDTO.getNapomena());
            
            return ResponseEntity.ok(trebovanje);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/moja-trebovanja")
    @PreAuthorize("hasRole('OSOBLJE')")
    public ResponseEntity<List<TrebovanjeInventara>> getMojaTrebovanja(Principal principal) {
        try {
            Osoblje osoblje = osobljeService.findByUserEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Osoblje nije pronađeno"));
            
            List<TrebovanjeInventara> trebovanja = trebovanjeService.getTrebovanjaByOsoblje(osoblje.getId());
            return ResponseEntity.ok(trebovanja);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/odobri")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TrebovanjeInventara> odobriTrebovanje(@PathVariable Long id) {
        try {
            TrebovanjeInventara trebovanje = trebovanjeService.odob*/