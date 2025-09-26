package com.sirmium.predmet.service.prijavaIspita;

import com.sirmium.predmet.dto.PrijavaIspitaDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface PrijavaIspitaService {
    PrijavaIspitaDTO create(PrijavaIspitaDTO dto);
    List<PrijavaIspitaDTO> findAll();
    PrijavaIspitaDTO findById(Long id);
    PrijavaIspitaDTO update(Long id, PrijavaIspitaDTO dto);
    void delete(Long id);
    
    List<PrijavaIspitaDTO> getDostupnePrijave(Long studentId);
    PrijavaIspitaDTO prijaviIspit(Long prijavaId);
    
    void kreirajPrijaveZaPredmet(Long predmetId, LocalDateTime datumIspita);
}