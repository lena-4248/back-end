package com.sirmium.godinaStudija.controller; 
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.sirmium.godinaStudija.dto.GodinaStudijaCreateUpdateDTO; 
import com.sirmium.godinaStudija.dto.GodinaStudijaDTO; 
import com.sirmium.godinaStudija.dto.GodinaStudijaReadDTO; 
import com.sirmium.godinaStudija.service.GodinaStudijaService; 

@RestController
@RequestMapping("/api/godinestudija")
@CrossOrigin(origins = "*")
public class GodinaStudijaController {

    private final GodinaStudijaService godinaStudijaService;

    public GodinaStudijaController(GodinaStudijaService godinaStudijaService) {
        this.godinaStudijaService = godinaStudijaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GodinaStudijaReadDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(godinaStudijaService.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<GodinaStudijaReadDTO> create(@RequestBody GodinaStudijaCreateUpdateDTO dto) {
        return ResponseEntity.ok(godinaStudijaService.create(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<GodinaStudijaReadDTO> update(@PathVariable Long id, @RequestBody GodinaStudijaCreateUpdateDTO dto) {
        return ResponseEntity.ok(godinaStudijaService.update(id, dto));
    }
    
    @GetMapping
    public ResponseEntity<List<GodinaStudijaReadDTO>> getAktivne(
            @RequestParam(name = "nazivPrograma", required = false) String nazivPrograma) {
        return ResponseEntity.ok(godinaStudijaService.findAllAktivne(Optional.ofNullable(nazivPrograma)));
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, List<GodinaStudijaReadDTO>>> getAllForAdmin(
            @RequestParam(name = "nazivPrograma", required = false) String nazivPrograma) {
        return ResponseEntity.ok(godinaStudijaService.findAllZaAdmin(Optional.ofNullable(nazivPrograma)));
    }

    @PutMapping("/{id}/deaktiviraj")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GodinaStudijaReadDTO> deactivate(@PathVariable Long id) {
        return ResponseEntity.ok(godinaStudijaService.deaktiviraj(id));
    }

    @PutMapping("/{id}/aktiviraj")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GodinaStudijaReadDTO> activate(@PathVariable Long id) {
        return ResponseEntity.ok(godinaStudijaService.aktiviraj(id));
    }
}
