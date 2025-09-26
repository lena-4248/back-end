package com.sirmium.osoblje.controller;

import com.sirmium.osoblje.dto.OpsteObavestenjeDTO;
import com.sirmium.osoblje.dto.OpsteObavestenjeResponseDTO;
import com.sirmium.osoblje.model.OpsteObavestenje;
import com.sirmium.osoblje.service.OpsteObavestenjeService;
import com.sirmium.user.model.User;
import com.sirmium.user.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/opsta-obavestenja")
@CrossOrigin(origins = "*")
public class OpsteObavestenjeController {

    private final OpsteObavestenjeService service;
    private final UserRepository userRepository;

    public OpsteObavestenjeController(OpsteObavestenjeService service, UserRepository userRepository) {
        this.service = service;
        this.userRepository = userRepository;
    }

    // Get all active obaveštenja - svi mogu da vide
    @GetMapping
    public ResponseEntity<List<OpsteObavestenjeResponseDTO>> getAll() {
        try {
            List<OpsteObavestenjeResponseDTO> obavestenja = service.findAll().stream()
                .map(OpsteObavestenjeResponseDTO::new)
                .collect(Collectors.toList());
            return ResponseEntity.ok(obavestenja);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Get all including inactive - samo admin/osoblje
    @GetMapping("/all")
    @PreAuthorize("hasRole('OSOBLJE') or hasRole('ADMIN')")
    public ResponseEntity<List<OpsteObavestenjeResponseDTO>> getAllIncludingInactive() {
        try {
            List<OpsteObavestenjeResponseDTO> obavestenja = service.findAllIncludingInactive().stream()
                .map(OpsteObavestenjeResponseDTO::new)
                .collect(Collectors.toList());
            return ResponseEntity.ok(obavestenja);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Get by ID - svi mogu da vide
    @GetMapping("/{id}")
    public ResponseEntity<OpsteObavestenjeResponseDTO> getOne(@PathVariable Long id) {
        try {
            OpsteObavestenje o = service.findById(id);
            return ResponseEntity.ok(new OpsteObavestenjeResponseDTO(o));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Create - samo osoblje
    @PostMapping
    @PreAuthorize("hasRole('OSOBLJE')")
    public ResponseEntity<?> create(@RequestBody OpsteObavestenjeDTO dto, Principal principal) {
        try {
            User autor = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen"));

            OpsteObavestenje obavestenje = service.create(dto, autor);
            OpsteObavestenjeResponseDTO responseDTO = new OpsteObavestenjeResponseDTO(obavestenje);
            return ResponseEntity.ok(responseDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Update - samo osoblje
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('OSOBLJE')")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody OpsteObavestenjeDTO dto) {
        try {
            OpsteObavestenje updated = service.update(id, dto);
            return ResponseEntity.ok(new OpsteObavestenjeResponseDTO(updated));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Soft delete - samo osoblje
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('OSOBLJE')")
    public ResponseEntity<Void> softDelete(@PathVariable Long id) {
        try {
            service.softDelete(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Hard delete - samo admin
    @DeleteMapping("/hard/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> hardDelete(@PathVariable Long id) {
        try {
            service.hardDelete(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Activate - samo osoblje
    @PutMapping("/activate/{id}")
    @PreAuthorize("hasRole('OSOBLJE')")
    public ResponseEntity<Void> activate(@PathVariable Long id) {
        try {
            service.activate(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Get by author - samo osoblje/admin
    @GetMapping("/autor/{autorId}")
    @PreAuthorize("hasRole('OSOBLJE') or hasRole('ADMIN')")
    public ResponseEntity<List<OpsteObavestenjeResponseDTO>> getByAutor(@PathVariable Long autorId) {
        try {
            List<OpsteObavestenjeResponseDTO> obavestenja = service.findByAutorId(autorId).stream()
                .map(OpsteObavestenjeResponseDTO::new)
                .collect(Collectors.toList());
            return ResponseEntity.ok(obavestenja);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Get inactive - samo osoblje/admin
    @GetMapping("/neaktivna")
    @PreAuthorize("hasRole('OSOBLJE') or hasRole('ADMIN')")
    public ResponseEntity<List<OpsteObavestenjeResponseDTO>> getNeaktivna() {
        try {
            List<OpsteObavestenjeResponseDTO> obavestenja = service.findNeaktivna().stream()
                .map(OpsteObavestenjeResponseDTO::new)
                .collect(Collectors.toList());
            return ResponseEntity.ok(obavestenja);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Get recent obaveštenja (last 30 days)
    @GetMapping("/recent")
    public ResponseEntity<List<OpsteObavestenjeResponseDTO>> getRecent() {
        try {
            List<OpsteObavestenjeResponseDTO> obavestenja = service.findRecent(30).stream()
                .map(OpsteObavestenjeResponseDTO::new)
                .collect(Collectors.toList());
            return ResponseEntity.ok(obavestenja);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}