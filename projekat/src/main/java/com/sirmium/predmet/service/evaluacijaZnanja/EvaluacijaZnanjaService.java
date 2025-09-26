package com.sirmium.predmet.service.evaluacijaZnanja;

import com.sirmium.predmet.dto.EvaluacijaZnanjaCreateDTO;
import com.sirmium.predmet.dto.EvaluacijaZnanjaDTO;
import com.sirmium.predmet.dto.PromenaTipaEvaluacijeDTO;
import com.sirmium.student.dto.StudentPredmetDTO;

import java.util.List;

public interface EvaluacijaZnanjaService {
    EvaluacijaZnanjaDTO create(EvaluacijaZnanjaDTO dto);
    List<EvaluacijaZnanjaDTO> findAll();
    EvaluacijaZnanjaDTO findById(Long id);
    EvaluacijaZnanjaDTO update(Long id, EvaluacijaZnanjaDTO dto);
    void delete(Long id);
    void kreirajEvaluacijeZaPredmet(EvaluacijaZnanjaCreateDTO dto);
    List<EvaluacijaZnanjaDTO> getEvaluacijeForStudent(Long studentId);
    List<EvaluacijaZnanjaDTO> getEvaluacijeZaPredmetIPredavaca(Long predmetId, Long profesorId);
    void izmeniTipEvaluacije(Long evaluacijaId, Long noviTipId);
    List<EvaluacijaZnanjaDTO> getEvaluacijeZaPredmet(Long predmetId);
    void upisiBodoveIEvaluiraj(Long evaluacijaId, Integer brojBodova);
    List<StudentPredmetDTO> getStudentiSaEvaluacijamaZaPredmet(Long predmetId);
    void promeniTipEvaluacije(PromenaTipaEvaluacijeDTO dto);
}