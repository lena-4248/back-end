package com.sirmium.osoblje.model;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.sirmium.student.model.Student;

@Entity
@Table(name = "iznajmljivanje_udzbenika")
public class IznajmljivanjeUdzbenika {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    
    @ManyToOne
    @JoinColumn(name = "udzbenik_id", nullable = false)
    private BibliotekaUdzbenik udzbenik;
    
    @ManyToOne
    @JoinColumn(name = "osoblje_id", nullable = false)
    private Osoblje osoblje;
    
    @Column(name = "datum_iznajmljivanja", nullable = false)
    private LocalDate datumIznajmljivanja;
    
    @Column(name = "datum_vracanja")
    private LocalDate datumVracanja;
    
    @Column(name = "rok_vracanja", nullable = false)
    private LocalDate rokVracanja;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusIznajmljivanja status;

    public enum StatusIznajmljivanja {
        IZNAJMLJEN, VRACEN, KASNI
    }

    // Konstruktori
    public IznajmljivanjeUdzbenika() {}
    
    public IznajmljivanjeUdzbenika(Student student, BibliotekaUdzbenik udzbenik, Osoblje osoblje, LocalDate rokVracanja) {
        this.student = student;
        this.udzbenik = udzbenik;
        this.osoblje = osoblje;
        this.datumIznajmljivanja = LocalDate.now();
        this.rokVracanja = rokVracanja;
        this.status = StatusIznajmljivanja.IZNAJMLJEN;
    }

    // Geteri i seteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    
    public BibliotekaUdzbenik getUdzbenik() { return udzbenik; }
    public void setUdzbenik(BibliotekaUdzbenik udzbenik) { this.udzbenik = udzbenik; }
    
    public Osoblje getOsoblje() { return osoblje; }
    public void setOsoblje(Osoblje osoblje) { this.osoblje = osoblje; }
    
    public LocalDate getDatumIznajmljivanja() { return datumIznajmljivanja; }
    public void setDatumIznajmljivanja(LocalDate datumIznajmljivanja) { this.datumIznajmljivanja = datumIznajmljivanja; }
    
    public LocalDate getDatumVracanja() { return datumVracanja; }
    public void setDatumVracanja(LocalDate datumVracanja) { this.datumVracanja = datumVracanja; }
    
    public LocalDate getRokVracanja() { return rokVracanja; }
    public void setRokVracanja(LocalDate rokVracanja) { this.rokVracanja = rokVracanja; }
    
    public StatusIznajmljivanja getStatus() { return status; }
    public void setStatus(StatusIznajmljivanja status) { this.status = status; }
    
    // Helper metode
    public boolean isKasni() {
        return LocalDate.now().isAfter(rokVracanja) && status == StatusIznajmljivanja.IZNAJMLJEN;
    }
    
    public long getDanaKasnjenja() {
        if (!isKasni()) return 0;
        return java.time.temporal.ChronoUnit.DAYS.between(rokVracanja, LocalDate.now());
    }
}