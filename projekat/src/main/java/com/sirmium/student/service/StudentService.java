package com.sirmium.student.service;

import com.sirmium.student.model.Student;
import com.sirmium.obavestenje.dto.ObavestenjeDTO;
import com.sirmium.student.dto.StudentDTO;
import com.sirmium.student.dto.StudentPredmetDTO;
import com.sirmium.student.repository.StudentRepository;
import com.sirmium.user.model.User;
import com.sirmium.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    // 1. Dobavljanje studenta po ID-u
    public StudentDTO getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student nije pronađen"));
        return mapToStudentDTO(student);
    }

    // 2. Dobavljanje studenta po user ID-u
    public StudentDTO getStudentByUserId(Long userId) {
        Student student = studentRepository.findByUserId(userId)
            .orElseThrow(() -> new RuntimeException("Student nije pronađen za dati user ID"));
        return mapToStudentDTO(student);
    }

    // 3. Dobavljanje studenta po broju indeksa
    public StudentDTO getStudentByBrojIndeksa(String brojIndeksa) {
        Student student = studentRepository.findByBrojIndeksa(brojIndeksa)
            .orElseThrow(() -> new RuntimeException("Student sa brojem indeksa " + brojIndeksa + " nije pronađen"));
        return mapToStudentDTO(student);
    }

    // 4. Dobavljanje svih aktivnih studenata
    public List<StudentDTO> getAllActiveStudents() {
        return studentRepository.findAllActive().stream()
            .map(this::mapToStudentDTO)
            .collect(Collectors.toList());
    }

    // 5. Pretraga studenata
    public List<StudentDTO> searchStudents(String ime, String prezime, String brojIndeksa) {
        return studentRepository.pretraziStudente(ime, prezime, brojIndeksa).stream()
            .map(this::mapToStudentDTO)
            .collect(Collectors.toList());
    }

    // 6. Dobavljanje studenata po godini upisa
    public List<StudentDTO> getStudentsByGodinaUpisa(int godinaUpisa) {
        return studentRepository.findByGodinaUpisa(godinaUpisa).stream()
            .map(this::mapToStudentDTO)
            .collect(Collectors.toList());
    }

    // 7. Kreiranje novog studenta
    public StudentDTO createStudent(Long userId, String brojIndeksa, int godinaUpisa) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen"));

        // Provera da li broj indeksa već postoji
        if (studentRepository.findByBrojIndeksa(brojIndeksa).isPresent()) {
            throw new RuntimeException("Student sa brojem indeksa " + brojIndeksa + " već postoji");
        }

        // Provera da li korisnik već ima studentski profil
        if (studentRepository.findByUserId(userId).isPresent()) {
            throw new RuntimeException("Korisnik već ima studentski profil");
        }

        Student student = new Student(user, brojIndeksa, godinaUpisa);
        Student savedStudent = studentRepository.save(student);
        return mapToStudentDTO(savedStudent);
    }

    // 8. Ažuriranje studentskog profila
    public StudentDTO updateStudent(Long studentId, String brojIndeksa, int godinaUpisa) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student nije pronađen"));

        // Provera jedinstvenosti broja indeksa (ako se menja)
        if (!student.getBrojIndeksa().equals(brojIndeksa)) {
            if (studentRepository.findByBrojIndeksa(brojIndeksa).isPresent()) {
                throw new RuntimeException("Broj indeksa " + brojIndeksa + " već postoji");
            }
            student.setBrojIndeksa(brojIndeksa);
        }

        student.setGodinaUpisa(godinaUpisa);
        Student updatedStudent = studentRepository.save(student);
        return mapToStudentDTO(updatedStudent);
    }

    // 9. Ažuriranje prosečne ocene
    public StudentDTO updateProsecnaOcena(Long studentId, double prosecnaOcena) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student nije pronađen"));
        
        student.setProsecnaOcena(prosecnaOcena);
        Student updatedStudent = studentRepository.save(student);
        return mapToStudentDTO(updatedStudent);
    }

    // 10. Ažuriranje ukupnih ECTS bodova
    public StudentDTO updateUkupnoEcts(Long studentId, int ukupnoEcts) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student nije pronađen"));
        
        student.setUkupnoEcts(ukupnoEcts);
        Student updatedStudent = studentRepository.save(student);
        return mapToStudentDTO(updatedStudent);
    }

    // METODE KOJE ĆEŠ IMPLEMENTIRATI KASNIJE KADA NAPRAVIŠ PREDMET PAKET:

    // 11. Pregled trenutnih predmeta - placeholder
    public List<StudentPredmetDTO> getTrenutniPredmeti(Long studentId) {
        // Ovo ćeš implementirati kada napraviš Predmet paket
        throw new RuntimeException("Metoda nije implementirana - potrebno je kreirati Predmet paket");
    }

    // 12. Prijava ispita - placeholder
    public boolean prijaviIspit(Long studentId, Long predmetId, Long ispitniRokId) {
        // Ovo ćeš implementirati kada napraviš Predmet paket
        throw new RuntimeException("Metoda nije implementirana - potrebno je kreirati Predmet paket");
    }

    // 13. Pregled obaveštenja - placeholder
    public List<ObavestenjeDTO> getObavestenjaZaStudenta(Long studentId) {
        // Ovo ćeš implementirati kada napraviš Predmet paket
        throw new RuntimeException("Metoda nije implementirana - potrebno je kreirati Predmet paket");
    }

    // ISPRAVLJENA metoda za mapiranje Student -> StudentDTO
    private StudentDTO mapToStudentDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setBrojIndeksa(student.getBrojIndeksa()); // OVO JE IZ STUDENT ENTITETA
        studentDTO.setGodinaUpisa(student.getGodinaUpisa());
        studentDTO.setProsecnaOcena(student.getProsecnaOcena());
        studentDTO.setUkupnoEcts(student.getUkupnoEcts());
        
        // Mapiranje user podataka - SAMO OSNOVNI PODACI IZ USER ENTITETA
        if (student.getUser() != null) {
            studentDTO.setUserId(student.getUser().getId());
            studentDTO.setIme(student.getUser().getIme());
            studentDTO.setPrezime(student.getUser().getPrezime());
            studentDTO.setEmail(student.getUser().getEmail());
            studentDTO.setTelefon(student.getUser().getTelefon());
        }
        
        return studentDTO;
    }
}