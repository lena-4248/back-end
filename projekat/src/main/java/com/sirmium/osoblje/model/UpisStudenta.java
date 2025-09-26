package com.sirmium.osoblje.model;


import com.sirmium.student.model.Student;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "upis_studenata")
public class UpisStudenta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    
    @ManyToOne
    @JoinColumn(name = "osoblje_id", nullable = false)
    private Osoblje osoblje;
    
    @Column(name = "skolska_godina", nullable = false)
    private String skolskaGodina; // "2023/2024"
    
    @Column(name = "datum_upisa", nullable = false)
    private LocalDate datumUpisa;
    
    @Column(name = "godina_studija", nullable = false)
    private int godinaStudija;
    
    @Column(nullable = false)
    private String status; // "REDOVAN", "VANREDAN", "DOPISAN"
    
    @Column(name = "budzet_vanbudzet")
    private String budzetVanbudzet; // "BUDŽET", "VANBUDŽET"
    
    @Column(length = 2000)
    private String napomene;

    // Geteri i seteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    
    public Osoblje getOsoblje() { return osoblje; }
    public void setOsoblje(Osoblje osoblje) { this.osoblje = osoblje; }
    
    public String getSkolskaGodina() { return skolskaGodina; }
    public void setSkolskaGodina(String skolskaGodina) { this.skolskaGodina = skolskaGodina; }
    
    public LocalDate getDatumUpisa() { return datumUpisa; }
    public void setDatumUpisa(LocalDate datumUpisa) { this.datumUpisa = datumUpisa; }
    
    public int getGodinaStudija() { return godinaStudija; }
    public void setGodinaStudija(int godinaStudija) { this.godinaStudija = godinaStudija; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getBudzetVanbudzet() { return budzetVanbudzet; }
    public void setBudzetVanbudzet(String budzetVanbudzet) { this.budzetVanbudzet = budzetVanbudzet; }
    
    public String getNapomene() { return napomene; }
    public void setNapomene(String napomene) { this.napomene = napomene; }
}