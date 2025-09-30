package com.sirmium.auth.controller;

import com.sirmium.auth.dto.PrijavaRequestDTO;
import com.sirmium.auth.dto.PrijavaResponseDTO;
import com.sirmium.auth.dto.RegistracijaRequestDTO;
import com.sirmium.auth.service.AuthService;
import com.sirmium.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }
    
    @PostMapping("/prijava")
    public ResponseEntity<PrijavaResponseDTO> prijava(
            @Valid @RequestBody PrijavaRequestDTO prijavaDTO) {
        try {
            PrijavaResponseDTO response = authService.prijaviKorisnika(prijavaDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/registracija")
    public ResponseEntity<PrijavaResponseDTO> registracija(
            @Valid @RequestBody RegistracijaRequestDTO registracijaDTO) {
        try {
            // Koristimo UserService za registraciju
            var userDTO = userService.registrujKorisnika(registracijaDTO);
            
            // Automatska prijava posle registracije
            PrijavaRequestDTO prijavaDTO = new PrijavaRequestDTO();
            prijavaDTO.setEmail(registracijaDTO.getEmail());
            prijavaDTO.setLozinka(registracijaDTO.getLozinka());
            
            PrijavaResponseDTO response = authService.prijaviKorisnika(prijavaDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/odjava")
    public ResponseEntity<Void> odjava() {
        authService.odjaviKorisnika();
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/test-prijava")
    public ResponseEntity<String> testPrijava(@RequestBody String rawBody) {
        System.out.println("Primljen body: " + rawBody);
        return ResponseEntity.ok("Body primljen: " + rawBody);
    }
    
    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("Auth servis radi - ");
    }
    @GetMapping("/public/status")
    public ResponseEntity<String> publicStatus() {
        return ResponseEntity.ok("PUBLIC Auth servis radi - ");
    }
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Auth endpoint radi! ");
    }
}