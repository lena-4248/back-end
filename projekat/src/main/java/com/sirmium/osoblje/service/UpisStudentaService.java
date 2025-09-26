package com.sirmium.osoblje.service;

import com.sirmium.osoblje.model.UpisStudenta;
import com.sirmium.osoblje.repository.UpisStudentaRepository;
import com.sirmium.student.model.Student;
import com.sirmium.student.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class UpisStudentaService {
    
    private final UpisStudentaRepository upisStudentaRepository;
    private final StudentRepository studentRepository;
    
    public UpisStudentaService(UpisStudentaRepository upisStudentaRepository,
                             StudentRepository studentRepository) {
        this.upisStudentaRepository = upisStudentaRepository;
        this.studentRepository = studentRepository;
    }
    
    public UpisStudenta upisiStudenta(UpisStudenta upis) {
        return upisStudentaRepository.save(upis);
    }
    
    public List<UpisStudenta> getUpisiByStudent(Long studentId) {
        return upisStudentaRepository.findByStudentId(studentId);
    }
    
    public List<UpisStudenta> getUpisiBySkolskaGodina(String skolskaGodina) {
        return upisStudentaRepository.findBySkolskaGodina(skolskaGodina);
    }
    
    public List<UpisStudenta> getUpisiBySkolskaGodinaIGodinaStudija(String skolskaGodina, int godinaStudija) {
        return upisStudentaRepository.findBySkolskaGodinaAndGodinaStudija(skolskaGodina, godinaStudija);
    }
    
    public boolean studentJeUpisanUSkolnuGodinu(Long studentId, String skolskaGodina) {
        return upisStudentaRepository.existsByStudentIdAndSkolskaGodina(studentId, skolskaGodina);
    }
    
    public UpisStudenta napraviUpisStudenta(Long studentId, String skolskaGodina, int godinaStudija, 
                                          String status, String budzetVanbudzet, String napomene) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student nije pronađen"));
        
        UpisStudenta upis = new UpisStudenta();
        upis.setStudent(student);
        upis.setSkolskaGodina(skolskaGodina);
        upis.setGodinaStudija(godinaStudija);
        upis.setStatus(status);
        upis.setBudzetVanbudzet(budzetVanbudzet);
        upis.setNapomene(napomene);
        upis.setDatumUpisa(LocalDate.now());
        
        return upisStudentaRepository.save(upis);
    }
}