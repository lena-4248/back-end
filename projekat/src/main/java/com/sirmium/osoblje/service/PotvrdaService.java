package com.sirmium.osoblje.service;

import com.sirmium.osoblje.model.Potvrda;
import com.sirmium.osoblje.repository.PotvrdaRepository;
import com.sirmium.student.model.Student;
import com.sirmium.student.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class PotvrdaService {
    
    private final PotvrdaRepository potvrdaRepository;
    private final StudentRepository studentRepository;
    
    public PotvrdaService(PotvrdaRepository potvrdaRepository,
                        StudentRepository studentRepository) {
        this.potvrdaRepository = potvrdaRepository;
        this.studentRepository = studentRepository;
    }
    
    public Potvrda izdajPotvrdu(Potvrda potvrda) {
        return potvrdaRepository.save(potvrda);
    }
    
    public List<Potvrda> getPotvrdeByStudent(Long studentId) {
        return potvrdaRepository.findByStudentId(studentId);
    }
    
    public List<Potvrda> getPotvrdeByTip(String tipPotvrde) {
        return potvrdaRepository.findByTipPotvrde(tipPotvrde);
    }
    
    public List<Potvrda> getPotvrdeByStatus(String status) {
        return potvrdaRepository.findByStatus(status);
    }}