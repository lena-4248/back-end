package com.sirmium.user.controller;

import com.sirmium.auth.dto.RegistracijaRequestDTO;
import com.sirmium.user.dto.UserDTO;
import com.sirmium.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public List<UserDTO> getAllActiveUsers() {
        return userService.dobaviSveKorisnike();
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        try {
            UserDTO user = userService.dobaviKorisnikaPoId(id);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        try {
            UserDTO user = userService.dobaviKorisnikaPoEmailu(email);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody RegistracijaRequestDTO registracijaDTO) {
        return userService.registrujKorisnika(registracijaDTO);
    }
    
    
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateUser(@PathVariable Long id) {
        userService.deaktivirajKorisnika(id);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{id}/activate")
    public ResponseEntity<Void> activateUser(@PathVariable Long id) {
        userService.aktivirajNalog(id);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{id}/roles/{roleName}")
    public UserDTO assignRole(@PathVariable Long id, @PathVariable String roleName) {
        return userService.dodeliUlogu(id, roleName);
    }
}