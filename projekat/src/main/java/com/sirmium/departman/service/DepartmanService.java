package com.sirmium.departman.service;

import java.util.List;

import com.sirmium.departman.dto.DepartmanCreateUpdateDTO;
import com.sirmium.departman.dto.DepartmanDTO;
import com.sirmium.departman.model.Departman;

public interface DepartmanService {
    DepartmanDTO create(DepartmanCreateUpdateDTO dto);
    List<DepartmanDTO> findAll();
    DepartmanDTO findById(Long id);
    DepartmanDTO update(Long id, DepartmanCreateUpdateDTO dto);
    void delete(Long id);
    
    List<DepartmanDTO> findByFakultetId(Long fakultetId);
    
    List<DepartmanDTO> findAllActive();
    List<DepartmanDTO> findAllForAdmin();
    void deaktiviraj(Long id);
    void aktiviraj(Long id);

    DepartmanDTO toDTO(Departman entity);
    Departman toEntity(DepartmanDTO dto);
}