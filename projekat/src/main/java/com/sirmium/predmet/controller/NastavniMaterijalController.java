package com.sirmium.predmet.controller;

import com.sirmium.predmet.dto.NastavniMaterijalDTO;
import com.sirmium.predmet.service.nastavniMaterijal.NastavniMaterijalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/nastavni-materijali")
public class NastavniMaterijalController {

    @Autowired
    private NastavniMaterijalService service;

    @PostMapping("/upload")
    @PreAuthorize("hasRole('PROFESOR')")
    public NastavniMaterijalDTO upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("naziv") String naziv,
            @RequestParam("predmetId") Long predmetId
    ) throws IOException {
        return service.upload(file, naziv, predmetId);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('PROFESOR')")
    public NastavniMaterijalDTO update(
            @PathVariable Long id,
            @RequestParam("naziv") String naziv,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws IOException {
        return service.update(id, naziv, file);
    }

    @GetMapping("/predmet/{predmetId}")
    public List<NastavniMaterijalDTO> getByPredmet(@PathVariable Long predmetId) {
        return service.findByPredmet(predmetId);
    }

    @GetMapping("/preuzmi/{id}")
    public ResponseEntity<Resource> preuzmi(@PathVariable Long id) {
        Resource resource = service.preuzmiMaterijal(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename())
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}