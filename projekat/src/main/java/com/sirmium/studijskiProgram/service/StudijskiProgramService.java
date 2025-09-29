package com.sirmium.studijskiProgram.service;

import com.sirmium.studijskiProgram.model.StudijskiProgram;
import com.sirmium.studijskiProgram.repository.StudijskiProgramRepository;
import com.sirmium.fakultet.model.Fakultet;
import com.sirmium.fakultet.repository.FakultetRepository;
import com.sirmium.profesor.model.Profesor;
import com.sirmium.profesor.repository.ProfesorRepository;
import com.sirmium.tipStudija.model.TipStudija;
import com.sirmium.tipStudija.repository.TipStudijaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudijskiProgramService {

    private final StudijskiProgramRepository studijskiProgramRepository;
    private final FakultetRepository fakultetRepository;
    private final ProfesorRepository profesorRepository;
    private final TipStudijaRepository tipStudijaRepository;

    public StudijskiProgramService(StudijskiProgramRepository studijskiProgramRepository,
                                 FakultetRepository fakultetRepository,
                                 ProfesorRepository profesorRepository,
                                 TipStudijaRepository tipStudijaRepository) {
        this.studijskiProgramRepository = studijskiProgramRepository;
        this.fakultetRepository = fakultetRepository;
        this.profesorRepository = profesorRepository;
        this.tipStudijaRepository = tipStudijaRepository;
    }

    public List<StudijskiProgram> findAll() {
        return studijskiProgramRepository.findAll();
    }

    public List<StudijskiProgram> findAktivni() {
        return studijskiProgramRepository.findAktivni();
    }

    public StudijskiProgram findById(Long id) {
        return studijskiProgramRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Studijski program nije pronađen"));
    }

    public StudijskiProgram save(StudijskiProgram studijskiProgram) {
        return studijskiProgramRepository.save(studijskiProgram);
    }

    public StudijskiProgram update(Long id, StudijskiProgram updatedProgram) {
        StudijskiProgram existing = findById(id);
        
        existing.setNaziv(updatedProgram.getNaziv());
        existing.setOpis(updatedProgram.getOpis());
        existing.setTrajanje(updatedProgram.getTrajanje());
        existing.setEspb(updatedProgram.getEspb());
        existing.setStepenStudija(updatedProgram.getStepenStudija());
        existing.setFakultet(updatedProgram.getFakultet());
        existing.setRukovodilac(updatedProgram.getRukovodilac());
        existing.setTipStudija(updatedProgram.getTipStudija());
        
        return studijskiProgramRepository.save(existing);
    }

    public void delete(Long id) {
        studijskiProgramRepository.deleteById(id);
    }

    public StudijskiProgram deaktiviraj(Long id) {
        StudijskiProgram program = findById(id);
        program.setAktivan(false);
        return studijskiProgramRepository.save(program);
    }

    public StudijskiProgram aktiviraj(Long id) {
        StudijskiProgram program = findById(id);
        program.setAktivan(true);
        return studijskiProgramRepository.save(program);
    }

    public StudijskiProgram postaviRukovodioca(Long programId, Long profesorId) {
        StudijskiProgram program = findById(programId);
        Profesor profesor = profesorRepository.findById(profesorId)
            .orElseThrow(() -> new RuntimeException("Profesor nije pronađen"));
        
        program.setRukovodilac(profesor);
        return studijskiProgramRepository.save(program);
    }

    public List<StudijskiProgram> findByFakultetId(Long fakultetId) {
        return studijskiProgramRepository.findByFakultetId(fakultetId);
    }

    public List<StudijskiProgram> findAktivniByFakultetId(Long fakultetId) {
        return studijskiProgramRepository.findAktivniByFakultetId(fakultetId);
    }

    public List<StudijskiProgram> pretraziPoNazivu(String naziv) {
        return studijskiProgramRepository.findByNazivContaining(naziv);
    }

    public Long getBrojPredmeta(Long programId) {
        return studijskiProgramRepository.countPredmetiByProgramId(programId);
    }
}