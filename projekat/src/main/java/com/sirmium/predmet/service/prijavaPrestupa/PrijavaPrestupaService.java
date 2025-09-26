package com.sirmium.predmet.service.prijavaPrestupa;

import com.sirmium.predmet.dto.PrijavaPrestupaDTO;

import java.util.List;

public interface PrijavaPrestupaService {
    PrijavaPrestupaDTO create(PrijavaPrestupaDTO dto);
    List<PrijavaPrestupaDTO> findAll();
    PrijavaPrestupaDTO findById(Long id);
    PrijavaPrestupaDTO update(Long id, PrijavaPrestupaDTO dto);
    void delete(Long id);
}