package com.sirmium.osoblje.service;

import com.sirmium.osoblje.model.BibliotekaUdzbenik;
import com.sirmium.osoblje.model.IznajmljivanjeUdzbenika;
import com.sirmium.osoblje.model.Osoblje;
import com.sirmium.student.model.Student;
import com.sirmium.osoblje.repository.BibliotekaUdzbenikRepository;
import com.sirmium.osoblje.repository.IznajmljivanjeUdzbenikaRepository;
import com.sirmium.student.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class BibliotekaService {

    private final BibliotekaUdzbenikRepository udzbenikRepository;
    private final IznajmljivanjeUdzbenikaRepository iznajmljivanjeRepository;
    private final StudentRepository studentRepository;

    public BibliotekaService(BibliotekaUdzbenikRepository udzbenikRepository,
                           IznajmljivanjeUdzbenikaRepository iznajmljivanjeRepository,
                           StudentRepository studentRepository) {
        this.udzbenikRepository = udzbenikRepository;
        this.iznajmljivanjeRepository = iznajmljivanjeRepository;
        this.studentRepository = studentRepository;
    }

    public IznajmljivanjeUdzbenika iznajmiUdzbenik(Long studentId, Long udzbenikId, Osoblje osoblje, LocalDate rokVracanja) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student nije pronađen"));
        
        BibliotekaUdzbenik udzbenik = udzbenikRepository.findById(udzbenikId)
            .orElseThrow(() -> new RuntimeException("Udžbenik nije pronađen"));
        
        if (!udzbenik.isDostupno()) {
            throw new RuntimeException("Udžbenik nije dostupan");
        }

        // Proveri da li student već ima iznajmljen ovaj udžbenik
        List<IznajmljivanjeUdzbenika> aktivnaIznajmljivanja = iznajmljivanjeRepository
            .findAktivnaIznajmljivanjaStudenta(studentId, udzbenikId);
        
        if (!aktivnaIznajmljivanja.isEmpty()) {
            throw new RuntimeException("Student već ima iznajmljen ovaj udžbenik");
        }

        // Kreiraj iznajmljivanje
        IznajmljivanjeUdzbenika iznajmljivanje = new IznajmljivanjeUdzbenika(student, udzbenik, osoblje, rokVracanja);
        
        // Smanji dostupnu količinu
        udzbenik.smanjiDostupno();
        udzbenikRepository.save(udzbenik);
        
        return iznajmljivanjeRepository.save(iznajmljivanje);
    }

    public IznajmljivanjeUdzbenika vratiUdzbenik(Long iznajmljivanjeId, Osoblje osoblje) {
        IznajmljivanjeUdzbenika iznajmljivanje = iznajmljivanjeRepository.findById(iznajmljivanjeId)
            .orElseThrow(() -> new RuntimeException("Iznajmljivanje nije pronađeno"));
        
        if (iznajmljivanje.getStatus() == IznajmljivanjeUdzbenika.StatusIznajmljivanja.VRACEN) {
            throw new RuntimeException("Udžbenik je već vraćen");
        }

        // Vrati udžbenik u inventar
        BibliotekaUdzbenik udzbenik = iznajmljivanje.getUdzbenik();
        udzbenik.povecajDostupno();
        udzbenikRepository.save(udzbenik);
        
        // Ažuriraj iznajmljivanje
        iznajmljivanje.setDatumVracanja(LocalDate.now());
        iznajmljivanje.setStatus(IznajmljivanjeUdzbenika.StatusIznajmljivanja.VRACEN);
        
        return iznajmljivanjeRepository.save(iznajmljivanje);
    }

    public List<IznajmljivanjeUdzbenika> getIznajmljivanjaStudenta(Long studentId) {
        return iznajmljivanjeRepository.findByStudentId(studentId);
    }

    public List<IznajmljivanjeUdzbenika> getKasnaIznajmljivanja() {
        return iznajmljivanjeRepository.findKasnaIznajmljivanja();
    }

    public BibliotekaUdzbenik dodajNoviUdzbenik(BibliotekaUdzbenik udzbenik) {
        return udzbenikRepository.save(udzbenik);
    }

    public List<BibliotekaUdzbenik> getDostupniUdzbenici() {
        return udzbenikRepository.findDostupniUdzbenici();
    }

    public List<BibliotekaUdzbenik> pretraziUdzbenike(String pojam) {
        List<BibliotekaUdzbenik> poNaslovu = udzbenikRepository.findByNaslovContainingIgnoreCase(pojam);
        List<BibliotekaUdzbenik> poAutoru = udzbenikRepository.findByAutorContainingIgnoreCase(pojam);
        
        // Kombinuj rezultate bez duplikata
        poNaslovu.addAll(poAutoru.stream()
            .filter(udzbenik -> !poNaslovu.contains(udzbenik))
            .toList());
        
        return poNaslovu;
    }
}