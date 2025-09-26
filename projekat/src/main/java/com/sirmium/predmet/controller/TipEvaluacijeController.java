package com.sirmium.predmet.controller;

import com.sirmium.predmet.dto.TipEvaluacijeDTO;
import com.sirmium.predmet.service.tipEvaluacije.TipEvaluacijeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tip-evaluacije")
public class TipEvaluacijeController {

    @Autowired
    private TipEvaluacijeService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public TipEvaluacijeDTO create(@RequestBody TipEvaluacijeDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<TipEvaluacijeDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public TipEvaluacijeDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public TipEvaluacijeDTO update(@PathVariable Long id, @RequestBody TipEvaluacijeDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}