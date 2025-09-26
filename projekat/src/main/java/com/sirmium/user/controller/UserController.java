package com.sirmium.user.controller;

import com.sirmium.auth.dto.RegistracijaRequestDTO;
import com.sirmium.user.dto.*;
import com.sirmium.user.dto.Registrovani.ProfilDTO;
import com.sirmium.user.model.User;
import com.sirmium.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/korisnici")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // REGISTRACIJA (javno dostupno)
    @PostMapping("/registracija")
    public ResponseEntity<UserDTO> registracija(@RequestBody RegistracijaRequestDTO registracijaDTO) {
        try {
            UserDTO noviKorisnik = userService.registrujKorisnika(registracijaDTO);
            return ResponseEntity.ok(noviKorisnik);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // DOHVATANJE SVIH KORISNIKA (samo admin)
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDTO> dobaviSveKorisnike() {
        return userService.dobaviSveKorisnike();
    }

    // DOHVATANJE KORISNIKA PO ID-U (admin i službenik)
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'OSOBLJE')")
    public ResponseEntity<UserDTO> dobaviKorisnikaPoId(@PathVariable Long id) {
        try {
            UserDTO korisnik = userService.dobaviKorisnikaPoId(id);
            return ResponseEntity.ok(korisnik);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DOHVATANJE SVOG PROFILA (autentifikovani korisnici)
    @GetMapping("/moj-profil")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDTO> dobaviMojProfil() {
        try {
            User trenutniKorisnik = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserDTO korisnik = userService.dobaviKorisnikaPoId(trenutniKorisnik.getId());
            return ResponseEntity.ok(korisnik);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // AŽURIRANJE SVOG PROFILA (autentifikovani korisnici)
    @PutMapping("/moj-profil")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDTO> azurirajMojProfil(@RequestBody ProfilDTO profilDTO) {
        try {
            User trenutniKorisnik = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserDTO azuriraniKorisnik = userService.azurirajProfil(trenutniKorisnik.getId(), profilDTO);
            return ResponseEntity.ok(azuriraniKorisnik);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // PROMENA LOZINKE (autentifikovani korisnici)
    @PutMapping("/promena-lozinke")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> promeniLozinku(@RequestBody PromenaLozinkeDTO promenaLozinkeDTO) {
        try {
            User trenutniKorisnik = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userService.resetujLozinku(trenutniKorisnik.getEmail(), promenaLozinkeDTO.getNovaLozinka());
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // DEAKTIVACIJA KORISNIKA (samo admin)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deaktivirajKorisnika(@PathVariable Long id) {
        try {
            userService.deaktivirajKorisnika(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DODELJIVANJE ULOGE KORISNIKU (samo admin)
    @PostMapping("/{id}/uloge")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> dodeliUloguKorisniku(@PathVariable Long id, @RequestBody DodelaUlogeDTO dodelaUlogeDTO) {
        try {
            UserDTO korisnik = userService.dodeliUlogu(id, dodelaUlogeDTO.getNazivUloge());
            return ResponseEntity.ok(korisnik);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}