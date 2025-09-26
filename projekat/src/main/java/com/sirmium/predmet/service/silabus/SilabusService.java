package com.sirmium.predmet.service.silabus;

import com.sirmium.predmet.dto.SilabusDTO;

import java.util.List;

public interface SilabusService {
    SilabusDTO create(SilabusDTO dto);
    List<SilabusDTO> findAll();
    SilabusDTO findById(Long id);
    SilabusDTO update(Long id, SilabusDTO dto);
    void delete(Long id);
    SilabusDTO findByPredmetId(Long predmetId);
}