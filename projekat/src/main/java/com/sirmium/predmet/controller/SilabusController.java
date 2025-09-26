package com.sirmium.predmet.controller;

import com.sirmium.predmet.dto.SilabusDTO;
import com.sirmium.predmet.service.silabus.SilabusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/silabus")
public class SilabusController {

    @Autowired
    private SilabusService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public SilabusDTO create(@RequestBody SilabusDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<SilabusDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public SilabusDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }
    
    @GetMapping("/predmet/{predmetId}")
    public SilabusDTO getByPredmetId(@PathVariable Long predmetId) {
        return service.findByPredmetId(predmetId);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public SilabusDTO update(@PathVariable Long id, @RequestBody SilabusDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}