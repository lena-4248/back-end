package com.sirmium.predmet.service.pohadjanjePredmeta;

import com.sirmium.predmet.dto.IstorijaStudiranjaDTO;
import com.sirmium.predmet.dto.StudentIstorijaStudiranjaResponseDTO;
import com.sirmium.predmet.dto.StudentIstorijaStudiranjaResponseDTOProfesor;
import com.sirmium.predmet.dto.PohadjanjePredmetaDTO;
import com.sirmium.predmet.model.PohadjanjePredmeta;
import com.sirmium.predmet.model.Predmet;
import com.sirmium.predmet.repository.PohadjanjePredmetaRepository;
import com.sirmium.predmet.repository.PredmetRepository;
import com.sirmium.student.model.Student;
import com.sirmium.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PohadjanjePredmetaServiceImpl implements PohadjanjePredmetaService {

    @Autowired
    private PohadjanjePredmetaRepository repository;

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private PredmetRepository predmetRepo;

    private PohadjanjePredmetaDTO toDTO(PohadjanjePredmeta entity) {
        PohadjanjePredmetaDTO dto = new PohadjanjePredmetaDTO();
        dto.setId(entity.getId());
        dto.setOcena(entity.getOcena());
        dto.setBrojPolaganja(entity.getBrojPolaganja());
        dto.setAktivan(entity.isAktivan());
        dto.setDatumPocetka(entity.getDatumPocetka());
        dto.setDatumZavrsetka(entity.getDatumZavrsetka());
        dto.setStudentId(entity.getStudent().getId());
        dto.setPredmetId(entity.getPredmet().getId());
        return dto;
    }

    private PohadjanjePredmeta toEntity(PohadjanjePredmetaDTO dto) {
        PohadjanjePredmeta entity = new PohadjanjePredmeta();
        entity.setOcena(dto.getOcena());
        entity.setBrojPolaganja(dto.getBrojPolaganja());
        entity.setAktivan(dto.isAktivan());
        entity.setDatumPocetka(dto.getDatumPocetka());
        entity.setDatumZavrsetka(dto.getDatumZavrsetka());

        Student student = studentRepo.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student nije pronađen"));
        Predmet predmet = predmetRepo.findById(dto.getPredmetId())
                .orElseThrow(() -> new RuntimeException("Predmet nije pronađen"));

        entity.setStudent(student);
        entity.setPredmet(predmet);
        return entity;
    }

    @Override
    public PohadjanjePredmetaDTO create(PohadjanjePredmetaDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    @Override
    public List<PohadjanjePredmetaDTO> findAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public PohadjanjePredmetaDTO findById(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }
    
    @Override
    public PohadjanjePredmeta findById2(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pohadjanje sa ID " + id + " nije pronađeno."));
    }

    @Override
    public PohadjanjePredmetaDTO update(Long id, PohadjanjePredmetaDTO dto) {
        return repository.findById(id).map(existing -> {
            existing.setOcena(dto.getOcena());
            existing.setBrojPolaganja(dto.getBrojPolaganja());
            existing.setAktivan(dto.isAktivan());
            existing.setDatumPocetka(dto.getDatumPocetka());
            existing.setDatumZavrsetka(dto.getDatumZavrsetka());
            existing.setStudent(studentRepo.findById(dto.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student nije pronađen")));
            existing.setPredmet(predmetRepo.findById(dto.getPredmetId())
                    .orElseThrow(() -> new RuntimeException("Predmet nije pronađen")));
            return toDTO(repository.save(existing));
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
    
    public StudentIstorijaStudiranjaResponseDTO getIstorijaStudiranjaZaStudenta(Long studentId) {
        List<PohadjanjePredmeta> pohadjanja = repository.findByStudentId(studentId);

        List<IstorijaStudiranjaDTO> predmetiDTO = pohadjanja.stream()
            .map(p -> new IstorijaStudiranjaDTO(
                    p.getPredmet().getNaziv(),
                    p.getBrojPolaganja(),
                    p.getOcena(), 
                    p.getPredmet().getEspb()
            ))
            .collect(Collectors.toList());

        List<Integer> ocene = pohadjanja.stream()
            .map(PohadjanjePredmeta::getOcena)
            .filter(o -> o != null)
            .collect(Collectors.toList());

        double prosecnaOcena = ocene.isEmpty() ? 0.0 :
            ocene.stream().mapToInt(o -> o).average().orElse(0.0);

        int ukupnoESPB = pohadjanja.stream()
            .filter(p -> p.getOcena() != null) 
            .mapToInt(p -> p.getPredmet().getEspb())
            .sum();

        return new StudentIstorijaStudiranjaResponseDTO(predmetiDTO, prosecnaOcena, ukupnoESPB);
    }
    
    public StudentIstorijaStudiranjaResponseDTOProfesor getIstorijaStudiranjaZaProfesora(Long studentId) {
        List<PohadjanjePredmeta> pohadjanja = repository.findByStudentId(studentId);

        List<IstorijaStudiranjaDTO> predmetiDTO = pohadjanja.stream()
            .map(p -> new IstorijaStudiranjaDTO(
                    p.getPredmet().getNaziv(),
                    p.getBrojPolaganja(),
                    p.getOcena(), 
                    p.getPredmet().getEspb()
            ))
            .collect(Collectors.toList());

        List<Integer> ocene = pohadjanja.stream()
            .map(PohadjanjePredmeta::getOcena)
            .filter(o -> o != null)
            .collect(Collectors.toList());

        double prosecnaOcena = ocene.isEmpty() ? 0.0 :
            ocene.stream().mapToInt(o -> o).average().orElse(0.0);

        int ukupnoESPB = pohadjanja.stream()
            .filter(p -> p.getOcena() != null) 
            .mapToInt(p -> p.getPredmet().getEspb())
            .sum();

        return new StudentIstorijaStudiranjaResponseDTOProfesor(
            new ArrayList<>(),         // upisi
            predmetiDTO,               // polozeni
            new ArrayList<>(),         // neuspesni
            new ArrayList<>(),         // prijavljeni
            new ArrayList<>(),         // prestupi
            null,                      // zavrsni rad
            prosecnaOcena,
            ukupnoESPB
        );
    }
    
    @Override
    public void upisiStudenta(Long studentId, List<Long> predmetIds) {
        Student student = studentRepo.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student ne postoji"));

        for (Long predmetId : predmetIds) {
            Predmet predmet = predmetRepo.findById(predmetId)
                .orElseThrow(() -> new RuntimeException("Predmet ne postoji"));

            PohadjanjePredmeta p = new PohadjanjePredmeta();
            p.setStudent(student);
            p.setPredmet(predmet);
            p.setAktivan(true);
            p.setDatumPocetka(LocalDateTime.now());
            repository.save(p);
        }
    }
}