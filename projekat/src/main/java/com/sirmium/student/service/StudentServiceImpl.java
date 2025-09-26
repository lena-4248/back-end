package com.sirmium.student.service;

import com.sirmium.student.dto.StudentDTO;
import com.sirmium.student.dto.StudentPredmetDTO;
import com.sirmium.student.model.Student;
import com.sirmium.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    
    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentPredmetDTO> getTrenutniPredmeti(Long studentId) {
        // Privremena implementacija - zameniti kada se napravi Predmet servis
        // Ova metoda vraća trenutne predmete koje student sluša
        return List.of(
            new StudentPredmetDTO(1L, "Matematika", "MAT101", 6, true, false, null, null, 0, null),
            new StudentPredmetDTO(2L, "Programiranje", "PROG202", 8, true, false, null, null, 0, null)
        );
    }

    @Override
    public boolean prijaviIspit(Long studentId, Long predmetId, Long ispitniRokId) {
        // Privremena implementacija - zameniti kada se napravi Predmet i IspitniRok servisi
        // Ova metoda prijavljuje ispit za studenta ako je ispitni rok aktivan
        return true; // Privremeno uvek vraća true
    }
    
    @Override
    public StudentDTO getStudentByUserId(Long userId) {
        Student student = studentRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Student nije pronađen"));
        
        return new StudentDTO(
            student.getId(),
            student.getUser().getIme(),
            student.getUser().getPrezime(),
            student.getUser().getEmail(),
            student.getBrojIndeksa()
        );
    }
    
    // METODE KOJE ĆEŠ DODATI KASNIJE KADA NAPRAVIŠ PREDMET PAKET:
    
    /*
    // Ovu metodu ćeš dodati kada napraviš Predmet paket
    @Override
    public List<ObavestenjeDTO> getObavestenjaZaStudenta(Long studentId) {
        // Implementacija za dobavljanje obaveštenja za predmete koje student sluša
        return predmetService.getObavestenjaZaStudenta(studentId);
    }
    */
    
    /*
    // Ovu metodu ćeš dodati kada napraviš Predmet paket  
    @Override
    public IstorijaStudiranjaDTO getIstorijaStudiranja(Long studentId) {
        // Implementacija za dobavljanje istorije studiranja
        return predmetService.getIstorijaStudiranja(studentId);
    }
    */
    
    // Pomocna metoda za konverziju Student -> StudentDTO
    private StudentDTO convertToDTO(Student student) {
        return new StudentDTO(
            student.getId(),
            student.getUser().getIme(),
            student.getUser().getPrezime(),
            student.getUser().getEmail(),
            student.getBrojIndeksa()
        );
    }
}
