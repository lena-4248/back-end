package com.sirmium.predmet.service;

import com.sirmium.predmet.dto.PredmetDTO;
import java.util.List;

public interface PredmetService {
    PredmetDTO create(PredmetDTO dto);
    List<PredmetDTO> findAll();
    List<PredmetDTO> findAllAktivni();
    PredmetDTO findById(Long id);
    PredmetDTO update(Long id, PredmetDTO dto);
    void delete(Long id);
    PredmetDTO aktiviraj(Long id);
    PredmetDTO deaktiviraj(Long id);
}