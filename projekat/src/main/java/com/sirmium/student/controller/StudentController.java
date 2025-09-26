package com.sirmium.student.controller;

import com.sirmium.student.dto.StudentDTO;
import com.sirmium.student.dto.StudentPredmetDTO;
import com.sirmium.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/studenti")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 1. Pregled trenutnih predmeta
    @GetMapping("/{studentId}/trenutni-predmeti")
    public ResponseEntity<List<StudentPredmetDTO>> getTrenutniPredmeti(@PathVariable Long studentId) {
        List<StudentPredmetDTO> predmeti = studentService.getTrenutniPredmeti(studentId);
        return ResponseEntity.ok(predmeti);
    }

    // 2. Prijava ispita
    @PostMapping("/{studentId}/prijavi-ispit")
    public ResponseEntity<String> prijaviIspit(
            @PathVariable Long studentId,
            @RequestParam Long predmetId,
            @RequestParam Long ispitniRokId) {
        
        boolean uspesno = studentService.prijaviIspit(studentId, predmetId, ispitniRokId);
        
        if (uspesno) {
            return ResponseEntity.ok("Uspešno prijavljen ispit");
        } else {
            return ResponseEntity.badRequest().body("Greška pri prijavi ispita");
        }
    }

    // 3. Dobavljanje studenta po user ID-u (za trenutno ulogovanog studenta)
    @GetMapping("/korisnik/{userId}")
    public ResponseEntity<StudentDTO> getStudentByUserId(@PathVariable Long userId) {
        StudentDTO student = studentService.getStudentByUserId(userId);
        return ResponseEntity.ok(student);
    }

    // METODE KOJE ĆEŠ DODATI KASNIJE KADA NAPRAVIŠ PREDMET PAKET:
    
    // 4. Pregled obaveštenja - dodati kada napraviš Predmet paket
    @GetMapping("/{studentId}/obavestenja")
    public ResponseEntity<List<ObavestenjeDTO>> getObavestenja(@PathVariable Long studentId) {
        List<ObavestenjeDTO> obavestenja = studentService.getObavestenjaZaStudenta(studentId);
        return ResponseEntity.ok(obavestenja);
    }
    
    // 5. Pregled istorije studiranja - dodati kada napraviš Predmet paket
    @GetMapping("/{studentId}/istorija-studiranja")
    public ResponseEntity<IstorijaStudiranjaDTO> getIstorijaStudiranja(@PathVariable Long studentId) {
        IstorijaStudiranjaDTO istorija = studentService.getIstorijaStudiranja(studentId);
        return ResponseEntity.ok(istorija);
    }
    
}