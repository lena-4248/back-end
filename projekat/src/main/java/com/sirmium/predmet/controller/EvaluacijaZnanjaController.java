package com.sirmium.predmet.controller;

import com.sirmium.predmet.dto.EvaluacijaZnanjaCreateDTO;
import com.sirmium.predmet.dto.EvaluacijaZnanjaDTO;
import com.sirmium.predmet.dto.PromenaTipaEvaluacijeDTO;
import com.sirmium.predmet.dto.UpdateEvaluacijaDTO;
import com.sirmium.predmet.model.PohadjanjePredmeta;
import com.sirmium.predmet.model.TipEvaluacije;
import com.sirmium.predmet.repository.PohadjanjePredmetaRepository;
import com.sirmium.predmet.repository.TipEvaluacijeRepository;
import com.sirmium.predmet.service.evaluacijaZnanja.EvaluacijaZnanjaService;
import com.sirmium.predmet.service.pohadjanjePredmeta.PohadjanjePredmetaService;
import com.sirmium.student.dto.StudentPredmetDTO;
import com.sirmium.student.model.Student;
import com.sirmium.utils.PdfGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/evaluacija-znanja")
public class EvaluacijaZnanjaController {

    @Autowired
    private EvaluacijaZnanjaService service;
    
    @Autowired
    private PohadjanjePredmetaService pohadjanjePredmetaService;
    
    @Autowired
    private TipEvaluacijeRepository tipRepo;
    
    @Autowired
    private PohadjanjePredmetaRepository pohadjanjePredmetaRepo;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public EvaluacijaZnanjaDTO create(@RequestBody EvaluacijaZnanjaDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<EvaluacijaZnanjaDTO> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public EvaluacijaZnanjaDTO getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public EvaluacijaZnanjaDTO update(@PathVariable Long id, @RequestBody EvaluacijaZnanjaDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESOR', 'SLUZBENIK')")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
    
    @PostMapping("/kreiraj")
    public ResponseEntity<Void> kreirajEvaluacije(@RequestBody EvaluacijaZnanjaCreateDTO dto) {
        service.kreirajEvaluacijeZaPredmet(dto);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EvaluacijaZnanjaDTO>> getEvaluacijeForStudent(@PathVariable Long studentId) {
        List<EvaluacijaZnanjaDTO> lista = service.getEvaluacijeForStudent(studentId);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/pdf/{evaluacijaId}")
    public ResponseEntity<byte[]> generatePdf(@PathVariable Long evaluacijaId) {
        EvaluacijaZnanjaDTO evaluacija = service.findById(evaluacijaId);
        PohadjanjePredmeta pohadjanje = pohadjanjePredmetaService.findById2(evaluacija.getPohadjanjeId());

        String nazivTipaEvaluacije = tipRepo.findById(evaluacija.getTipEvaluacijeId())
            .map(TipEvaluacije::getTip)
            .orElse("Nepoznat tip");

        Student student = pohadjanje.getStudent();

        ByteArrayInputStream bis = PdfGeneratorUtil.generateEvaluacijaPdf(
            evaluacija, pohadjanje, nazivTipaEvaluacije, student
        );

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=evaluacija_" + evaluacijaId + ".pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(bis.readAllBytes());
    }
    
    @GetMapping("/profesor/{profesorId}/predmet/{predmetId}")
    public List<EvaluacijaZnanjaDTO> getEvaluacijeZaProfesoraIPredmet(
            @PathVariable Long profesorId,
            @PathVariable Long predmetId) {
        return service.getEvaluacijeZaPredmetIPredavaca(predmetId, profesorId);
    }

    @PutMapping("/{evaluacijaId}/tip")
    public ResponseEntity<?> izmeniTipEvaluacije(
            @PathVariable Long evaluacijaId,
            @RequestBody UpdateEvaluacijaDTO dto) {
        service.izmeniTipEvaluacije(evaluacijaId, dto.getTipEvaluacijeId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/predmet/{predmetId}")
    public List<EvaluacijaZnanjaDTO> getEvaluacijeZaPredmet(@PathVariable Long predmetId) {
        return service.getEvaluacijeZaPredmet(predmetId);
    }
    
    @PutMapping("/bodovi/{evaluacijaId}")
    public ResponseEntity<?> upisiBodove(
            @PathVariable Long evaluacijaId,
            @RequestBody UpdateEvaluacijaDTO dto) {
        service.upisiBodoveIEvaluiraj(evaluacijaId, dto.getBrojBodova());
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/predmet/{predmetId}/studenti")
    @PreAuthorize("hasRole('PROFESOR')")
    public ResponseEntity<List<StudentPredmetDTO>> getStudentiSaEvaluacijama(@PathVariable Long predmetId) {
        return ResponseEntity.ok(service.getStudentiSaEvaluacijamaZaPredmet(predmetId));
    }

    @PostMapping("/promeni-tip")
    public ResponseEntity<?> promeniTipEvaluacije(@RequestBody PromenaTipaEvaluacijeDTO dto) {
        service.promeniTipEvaluacije(dto);
        return ResponseEntity.ok("Uspešno ažurirano.");
    }
}