package com.sirmium.user.controller;

import com.sirmium.user.dto.RoleDTO;
import com.sirmium.user.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/uloge")
@CrossOrigin(origins = "*")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // Dohvatanje svih aktivnih uloga
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<RoleDTO> dobaviSveAktivneUloge() {
        return roleService.dobaviSveAktivneUloge();
    }

    // Dohvatanje uloge po ID-u
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleDTO> dobaviUloguPoId(@PathVariable Long id) {
        try {
            RoleDTO uloga = roleService.dobaviUloguPoId(id);
            return ResponseEntity.ok(uloga);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Kreiranje nove uloge
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleDTO> kreirajUlogu(@RequestBody RoleDTO roleDTO) {
        try {
            RoleDTO novaUloga = roleService.kreirajUlogu(roleDTO);
            return ResponseEntity.ok(novaUloga);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Aktivacija uloge
    @PutMapping("/{id}/aktiviraj")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> aktivirajUlogu(@PathVariable Long id) {
        try {
            // Ovo bi zahtevalo dodatnu metodu u servisu
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deaktivacija uloge (logičko brisanje)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deaktivirajUlogu(@PathVariable Long id) {
        try {
            roleService.deaktivirajUlogu(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}