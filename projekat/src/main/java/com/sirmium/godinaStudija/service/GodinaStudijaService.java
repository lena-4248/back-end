package com.sirmium.godinaStudija.service; 

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.sirmium.godinaStudija.dto.GodinaStudijaCreateUpdateDTO; 
import com.sirmium.godinaStudija.dto.GodinaStudijaDTO;
import com.sirmium.godinaStudija.dto.GodinaStudijaReadDTO; 
public interface GodinaStudijaService {

    List<GodinaStudijaReadDTO> findAllAktivne(Optional<String> nazivPrograma);
    Map<String, List<GodinaStudijaReadDTO>> findAllZaAdmin(Optional<String> nazivPrograma);
    GodinaStudijaReadDTO deaktiviraj(Long id);
    GodinaStudijaReadDTO aktiviraj(Long id);
    
    GodinaStudijaReadDTO create(GodinaStudijaCreateUpdateDTO dto);
    GodinaStudijaReadDTO update(Long id, GodinaStudijaCreateUpdateDTO dto);
    List<GodinaStudijaReadDTO> findAll();
    GodinaStudijaReadDTO findById(Long id);
}