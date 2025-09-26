package com.sirmium.predmet.service.tipEvaluacije;

import com.sirmium.predmet.dto.TipEvaluacijeDTO;

import java.util.List;

public interface TipEvaluacijeService {
    TipEvaluacijeDTO create(TipEvaluacijeDTO dto);
    List<TipEvaluacijeDTO> findAll();
    TipEvaluacijeDTO findById(Long id);
    TipEvaluacijeDTO update(Long id, TipEvaluacijeDTO dto);
    void delete(Long id);
}