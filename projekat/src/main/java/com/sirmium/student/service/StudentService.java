package com.sirmium.student.service;

import com.sirmium.student.dto.StudentDTO;
import com.sirmium.student.dto.StudentPredmetDTO;

import java.util.List;

public interface StudentService {
    List<StudentPredmetDTO> getTrenutniPredmeti(Long studentId);
    boolean prijaviIspit(Long studentId, Long predmetId, Long ispitniRokId);
    StudentDTO getStudentByUserId(Long userId);
}