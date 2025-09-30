package com.sirmium.godinaStudija.service;

import com.sirmium.godinaStudija.dto.GodinaStudijaCreateUpdateDTO;
import com.sirmium.godinaStudija.dto.GodinaStudijaReadDTO;
import com.sirmium.godinaStudija.model.GodinaStudija;
import com.sirmium.godinaStudija.repository.GodinaStudijaRepository;
import com.sirmium.godinaStudija.service.GodinaStudijaService;
import com.sirmium.studijskiProgram.model.StudijskiProgram;
import com.sirmium.studijskiProgram.repository.StudijskiProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class GodinaStudijaServiceImpl implements GodinaStudijaService {

    private final GodinaStudijaRepository godinaStudijaRepository;
    private final StudijskiProgramRepository studijskiProgramRepository;

    @Autowired
    public GodinaStudijaServiceImpl(GodinaStudijaRepository godinaStudijaRepository,
                                   StudijskiProgramRepository studijskiProgramRepository) {
        this.godinaStudijaRepository = godinaStudijaRepository;
        this.studijskiProgramRepository = studijskiProgramRepository;
    }

    @Override
    public List<GodinaStudijaReadDTO> findAllAktivne(Optional<String> nazivPrograma) {
        List<GodinaStudija> godineStudija;
        
        if (nazivPrograma.isPresent() && !nazivPrograma.get().trim().isEmpty()) {
            godineStudija = godinaStudijaRepository.findByDeletedFalseAndStudijskiProgramNazivContainingIgnoreCase(nazivPrograma.get());
        } else {
            godineStudija = godinaStudijaRepository.findByDeletedFalse();
        }
        
        return godineStudija.stream()
                .map(this::toReadDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<GodinaStudijaReadDTO>> findAllZaAdmin(Optional<String> nazivPrograma) {
        List<GodinaStudija> godineStudija;
        
        if (nazivPrograma.isPresent() && !nazivPrograma.get().trim().isEmpty()) {
            // Kombinujemo aktivne i neaktivne sa filtriranjem po nazivu
            List<GodinaStudija> aktivne = godinaStudijaRepository.findByDeletedFalseAndStudijskiProgramNazivContainingIgnoreCase(nazivPrograma.get());
            List<GodinaStudija> neaktivne = godinaStudijaRepository.findByDeletedTrueAndStudijskiProgramNazivContainingIgnoreCase(nazivPrograma.get());
            
            return Map.of(
                "aktivne", aktivne.stream().map(this::toReadDTO).collect(Collectors.toList()),
                "neaktivne", neaktivne.stream().map(this::toReadDTO).collect(Collectors.toList())
            );
        } else {
            // Bez filtera - sve godine studija
            List<GodinaStudija> aktivne = godinaStudijaRepository.findByDeletedFalse();
            List<GodinaStudija> neaktivne = godinaStudijaRepository.findByDeletedTrue();
            
            return Map.of(
                "aktivne", aktivne.stream().map(this::toReadDTO).collect(Collectors.toList()),
                "neaktivne", neaktivne.stream().map(this::toReadDTO).collect(Collectors.toList())
            );
        }
    }

    @Override
    public GodinaStudijaReadDTO deaktiviraj(Long id) {
        GodinaStudija godinaStudija = godinaStudijaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Godina studija nije pronađena"));
        
        godinaStudija.setDeleted(true);
        GodinaStudija deaktivirana = godinaStudijaRepository.save(godinaStudija);
        
        return toReadDTO(deaktivirana);
    }

    @Override
    public GodinaStudijaReadDTO aktiviraj(Long id) {
        GodinaStudija godinaStudija = godinaStudijaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Godina studija nije pronađena"));
        
        godinaStudija.setDeleted(false);
        GodinaStudija aktivirana = godinaStudijaRepository.save(godinaStudija);
        
        return toReadDTO(aktivirana);
    }

    @Override
    public GodinaStudijaReadDTO create(GodinaStudijaCreateUpdateDTO dto) {
        StudijskiProgram studijskiProgram = studijskiProgramRepository.findById(dto.getStudijskiProgramId())
                .orElseThrow(() -> new RuntimeException("Studijski program nije pronađen"));

        // Provera da li godina već postoji za dati program
        List<GodinaStudija> postojeceGodine = godinaStudijaRepository.findByStudijskiProgramId(dto.getStudijskiProgramId());
        boolean godinaPostoji = postojeceGodine.stream()
                .anyMatch(gs -> gs.getGodina() == dto.getGodina() && !gs.isDeleted());
        
        if (godinaPostoji) {
            throw new RuntimeException("Godina studija " + dto.getGodina() + " već postoji za dati studijski program");
        }

        GodinaStudija godinaStudija = new GodinaStudija();
        godinaStudija.setGodina(dto.getGodina());
        godinaStudija.setStudijskiProgram(studijskiProgram);
        godinaStudija.setDeleted(false);

        GodinaStudija sacuvana = godinaStudijaRepository.save(godinaStudija);
        return toReadDTO(sacuvana);
    }

    @Override
    public GodinaStudijaReadDTO update(Long id, GodinaStudijaCreateUpdateDTO dto) {
        GodinaStudija godinaStudija = godinaStudijaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Godina studija nije pronađena"));

        StudijskiProgram studijskiProgram = studijskiProgramRepository.findById(dto.getStudijskiProgramId())
                .orElseThrow(() -> new RuntimeException("Studijski program nije pronađen"));

        // Provera da li nova godina već postoji za dati program (osim trenutne)
        if (godinaStudija.getGodina() != dto.getGodina()) {
            List<GodinaStudija> postojeceGodine = godinaStudijaRepository.findByStudijskiProgramId(dto.getStudijskiProgramId());
            boolean godinaPostoji = postojeceGodine.stream()
                    .anyMatch(gs -> gs.getGodina() == dto.getGodina() && !gs.getId().equals(id) && !gs.isDeleted());
            
            if (godinaPostoji) {
                throw new RuntimeException("Godina studija " + dto.getGodina() + " već postoji za dati studijski program");
            }
        }

        godinaStudija.setGodina(dto.getGodina());
        godinaStudija.setStudijskiProgram(studijskiProgram);

        GodinaStudija azurirana = godinaStudijaRepository.save(godinaStudija);
        return toReadDTO(azurirana);
    }

    @Override
    public List<GodinaStudijaReadDTO> findAll() {
        return godinaStudijaRepository.findAll().stream()
                .map(this::toReadDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GodinaStudijaReadDTO findById(Long id) {
        GodinaStudija godinaStudija = godinaStudijaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Godina studija nije pronađena"));
        return toReadDTO(godinaStudija);
    }

    // Privatna metoda za mapiranje entiteta u DTO
    private GodinaStudijaReadDTO toReadDTO(GodinaStudija entity) {
        GodinaStudijaReadDTO dto = new GodinaStudijaReadDTO();
        dto.setId(entity.getId());
        dto.setGodina(entity.getGodina());
        dto.setDeleted(entity.isDeleted());
        
        if (entity.getStudijskiProgram() != null) {
            dto.setStudijskiProgramId(entity.getStudijskiProgram().getId());
            dto.setStudijskiProgramNaziv(entity.getStudijskiProgram().getNaziv());
        }
        
        return dto;
    }
}