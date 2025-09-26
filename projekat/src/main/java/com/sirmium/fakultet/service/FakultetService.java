package com.sirmium.fakultet.service;

import java.util.List;

import com.sirmium.fakultet.dto.FakultetDTO;
import com.sirmium.fakultet.dto.FakultetSimpleDTO;
import com.sirmium.fakultet.model.Fakultet;

public interface FakultetService {
    FakultetDTO create(FakultetDTO dto);
    List<FakultetDTO> findAll();
    FakultetDTO findById(Long id);
    FakultetDTO update(Long id, FakultetDTO dto);
    FakultetSimpleDTO updateSimple(Long id, FakultetSimpleDTO dto);
    FakultetSimpleDTO createSimple(FakultetSimpleDTO dto);
    void delete(Long id);
    
    FakultetDTO toDTO(Fakultet entity);
    Fakultet toEntity(FakultetDTO dto);
    
    List<FakultetSimpleDTO> findAllSimple();
    FakultetSimpleDTO findSimpleById(Long id);
    FakultetSimpleDTO toSimpleDTO(Fakultet entity);
    
    List<FakultetSimpleDTO> findAllSimpleAdmin();
    void setDeleted(Long id, boolean deleted);
}