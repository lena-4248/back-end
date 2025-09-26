package com.sirmium.predmet.service;

import com.sirmium.predmet.dto.PredmetDTO;

import java.util.List;

public interface PredmetService {
    PredmetDTO create(PredmetDTO dto);
    List<PredmetDTO> findAll();
    PredmetDTO findById(Long id);
    PredmetDTO update(Long id, PredmetDTO dto);
    void delete(Long id);
    List<PredmetDTO> findAllAktivni();
}