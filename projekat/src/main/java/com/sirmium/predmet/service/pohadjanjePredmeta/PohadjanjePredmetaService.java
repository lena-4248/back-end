package com.sirmium.predmet.service.pohadjanjePredmeta;

import com.sirmium.predmet.dto.PohadjanjePredmetaDTO;
import com.sirmium.predmet.model.PohadjanjePredmeta;

import java.util.List;

public interface PohadjanjePredmetaService {
    PohadjanjePredmetaDTO create(PohadjanjePredmetaDTO dto);
    List<PohadjanjePredmetaDTO> findAll();
    PohadjanjePredmetaDTO findById(Long id);
    PohadjanjePredmetaDTO update(Long id, PohadjanjePredmetaDTO dto);
    void delete(Long id);
    void upisiStudenta(Long studentId, List<Long> predmetIds);
    PohadjanjePredmeta findById2(Long id);
}