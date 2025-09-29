package com.sirmium.studijskiProgram.controller;

import com.sirmium.studijskiProgram.dto.StudijskiProgramDTO;
import com.sirmium.studijskiProgram.model.StudijskiProgram;
import com.sirmium.studijskiProgram.service.StudijskiProgramService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/studijski-programi")
@CrossOrigin(origins = "*")
public class StudijskiProgramController {

    private final StudijskiProgramService studijskiProgramService;

    public StudijskiProgramController(StudijskiProgramService studijskiProgramService) {
        this.studijskiProgramService = studijskiProgramService;
    }

    @GetMapping
    public ResponseEntity<List<StudijskiProgramDTO>> getAll() {
        List<StudijskiProgramDTO> programi = studijskiProgramService.findAll().stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(programi);
    }

    @GetMapping("/aktivni")
    public ResponseEntity<List<StudijskiProgramDTO>> getAktivni() {
        List<StudijskiProgramDTO> programi = studijskiProgramService.findAktivni().stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(programi);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudijskiProgramDTO> getById(@PathVariable Long id) {
        try {
            StudijskiProgram program = studijskiProgramService.findById(id);
            return ResponseEntity.ok(mapToDTO(program));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudijskiProgramDTO> create(@RequestBody StudijskiProgramDTO programDTO) {
        try {
            StudijskiProgram program = mapToEntity(programDTO);
            StudijskiProgram saved = studijskiProgramService.save(program);
            return ResponseEntity.ok(mapToDTO(saved));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudijskiProgramDTO> update(@PathVariable Long id, @RequestBody StudijskiProgramDTO programDTO) {
        try {
            StudijskiProgram program = mapToEntity(programDTO);
            StudijskiProgram updated = studijskiProgramService.update(id, program);
            return ResponseEntity.ok(mapToDTO(updated));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            studijskiProgramService.delete(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/deaktiviraj")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudijskiProgramDTO> deaktiviraj(@PathVariable Long id) {
        try {
            StudijskiProgram program = studijskiProgramService.deaktiviraj(id);
            return ResponseEntity.ok(mapToDTO(program));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/aktiviraj")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudijskiProgramDTO> aktiviraj(@PathVariable Long id) {
        try {
            StudijskiProgram program = studijskiProgramService.aktiviraj(id);
            return ResponseEntity.ok(mapToDTO(program));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/rukovodilac/{profesorId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudijskiProgramDTO> postaviRukovodioca(@PathVariable Long id, @PathVariable Long profesorId) {
        try {
            StudijskiProgram program = studijskiProgramService.postaviRukovodioca(id, profesorId);
            return ResponseEntity.ok(mapToDTO(program));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/fakultet/{fakultetId}")
    public ResponseEntity<List<StudijskiProgramDTO>> getByFakultet(@PathVariable Long fakultetId) {
        List<StudijskiProgramDTO> programi = studijskiProgramService.findByFakultetId(fakultetId).stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(programi);
    }

    @GetMapping("/fakultet/{fakultetId}/aktivni")
    public ResponseEntity<List<StudijskiProgramDTO>> getAktivniByFakultet(@PathVariable Long fakultetId) {
        List<StudijskiProgramDTO> programi = studijskiProgramService.findAktivniByFakultetId(fakultetId).stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(programi);
    }

    @GetMapping("/pretraga")
    public ResponseEntity<List<StudijskiProgramDTO>> pretrazi(@RequestParam String naziv) {
        List<StudijskiProgramDTO> programi = studijskiProgramService.pretraziPoNazivu(naziv).stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(programi);
    }

    @GetMapping("/{id}/broj-predmeta")
    public ResponseEntity<Long> getBrojPredmeta(@PathVariable Long id) {
        try {
            Long brojPredmeta = studijskiProgramService.getBrojPredmeta(id);
            return ResponseEntity.ok(brojPredmeta);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Helper metode za mapiranje
    private StudijskiProgramDTO mapToDTO(StudijskiProgram program) {
        StudijskiProgramDTO dto = new StudijskiProgramDTO();
        dto.setId(program.getId());
        dto.setNaziv(program.getNaziv());
        dto.setOpis(program.getOpis());
        dto.setTrajanje(program.getTrajanje());
        dto.setEspb(program.getEspb());
        dto.setStepenStudija(program.getStepenStudija());
        dto.setAktivan(program.getAktivan());
        
        if (program.getFakultet() != null) {
            dto.setFakultetId(program.getFakultet().getId());
            dto.setFakultetNaziv(program.getFakultet().getNaziv());
        }
        
        if (program.getRukovodilac() != null) {
            dto.setRukovodilacId(program.getRukovodilac().getId());
            dto.setRukovodilacIme(program.getRukovodilac().getIme());
            dto.setRukovodilacPrezime(program.getRukovodilac().getPrezime());
        }
        
        if (program.getTipStudija() != null) {
            dto.setTipStudijaId(program.getTipStudija().getId());
            dto.setTipStudijaNaziv(program.getTipStudija().getNaziv());
        }
        
        return dto;
    }

    private StudijskiProgram mapToEntity(StudijskiProgramDTO dto) {
        StudijskiProgram program = new StudijskiProgram();
        program.setNaziv(dto.getNaziv());
        program.setOpis(dto.getOpis());
        program.setTrajanje(dto.getTrajanje());
        program.setEspb(dto.getEspb());
        program.setStepenStudija(dto.getStepenStudija());
        program.setAktivan(dto.getAktivan() != null ? dto.getAktivan() : true);
        return program;
    }
}