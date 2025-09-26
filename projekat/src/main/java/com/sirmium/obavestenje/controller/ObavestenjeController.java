package com.sirmium.obavestenje.controller;

import com.sirmium.obavestenje.dto.ObavestenjeDTO;
import com.sirmium.obavestenje.dto.ObavestenjeResponseDTO;
import com.sirmium.obavestenje.service.ObavestenjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/obavestenja")
public class ObavestenjeController {

    @Autowired
    private ObavestenjeService obavestenjeService;

    // Kreiranje obaveštenja - samo profesori
    @PostMapping
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<ObavestenjeResponseDTO> kreirajObavestenje(@RequestBody ObavestenjeDTO dto) {
        try {
            ObavestenjeResponseDTO response = obavestenjeService.kreirajObavestenje(dto);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Izmena obaveštenja - samo vlasnik obaveštenja
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<ObavestenjeResponseDTO> izmeniObavestenje(
            @PathVariable Long id, 
            @RequestBody ObavestenjeDTO dto) {
        try {
            ObavestenjeResponseDTO response = obavestenjeService.izmeniObavestenje(id, dto);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Brisanje obaveštenja - samo vlasnik obaveštenja
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<Void> obrisiObavestenje(@PathVariable Long id) {
        try {
            obavestenjeService.obrisiObavestenje(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Dobavljanje obaveštenja za profesora (samo njegova obaveštenja)
    @GetMapping("/profesor")
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<List<ObavestenjeResponseDTO>> getObavestenjaZaProfesora() {
        try {
            List<ObavestenjeResponseDTO> obavestenja = obavestenjeService.findObavestenjaZaProfesora();
            return ResponseEntity.ok(obavestenja);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Dobavljanje obaveštenja za studenta
    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasRole('STUDENT') or hasRole('PROFESOR')")
    public ResponseEntity<List<ObavestenjeResponseDTO>> getObavestenjaZaStudenta(@PathVariable Long studentId) {
        try {
            List<ObavestenjeResponseDTO> obavestenja = obavestenjeService.findObavestenjaZaStudenta(studentId);
            return ResponseEntity.ok(obavestenja);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Dobavljanje svih obaveštenja (za administratore)
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ObavestenjeResponseDTO>> svaObavestenja() {
        try {
            List<ObavestenjeResponseDTO> obavestenja = obavestenjeService.svaObavestenjaDTO();
            return ResponseEntity.ok(obavestenja);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Dobavljanje obaveštenja po ID-u
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('STUDENT') or hasRole('PROFESOR') or hasRole('ADMIN')")
    public ResponseEntity<ObavestenjeResponseDTO> getObavestenjeById(@PathVariable Long id) {
        try {
            // Ovo će možda trebati da se adaptira ako service vraća Obavestenje umesto ResponseDTO
            // Za sada pretpostavljamo da imamo odgovarajuću metodu u servisu
            ObavestenjeResponseDTO obavestenje = obavestenjeService.svaObavestenjaDTO().stream()
                    .filter(o -> o.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Obaveštenje nije pronađeno"));
            return ResponseEntity.ok(obavestenje);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Dobavljanje obaveštenja po predmetu
    @GetMapping("/predmet/{predmetId}")
    @PreAuthorize("hasRole('STUDENT') or hasRole('PROFESOR') or hasRole('ADMIN')")
    public ResponseEntity<List<ObavestenjeResponseDTO>> getObavestenjaByPredmet(@PathVariable Long predmetId) {
        try {
            List<ObavestenjeResponseDTO> obavestenja = obavestenjeService.findObavestenjaByPredmetId(predmetId);
            return ResponseEntity.ok(obavestenja);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}