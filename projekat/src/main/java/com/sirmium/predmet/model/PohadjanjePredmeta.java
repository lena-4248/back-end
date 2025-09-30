package com.sirmium.predmet.model;

import com.sirmium.student.model.Student;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pohadjanja_predmeta")
public class PohadjanjePredmeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ocena;
    private int brojPolaganja;
    private boolean aktivan;
    private LocalDateTime datumPocetka;
    private LocalDateTime datumZavrsetka;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "predmet_id", nullable = false)
    private Predmet predmet;

    @OneToMany(mappedBy = "pohadjanje", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrijavaPrestupa> prijavePrestupa = new ArrayList<>();

    @OneToMany(mappedBy = "pohadjanje", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrijavaIspita> prijaveIspita = new ArrayList<>();

    /*@OneToMany(mappedBy = "pohadjanje", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EvaluacijaZnanja> evaluacije = new ArrayList<>();*/

    public PohadjanjePredmeta() {}

    public PohadjanjePredmeta(Integer ocena, int brojPolaganja, boolean aktivan, 
                             LocalDateTime datumPocetka, LocalDateTime datumZavrsetka, 
                             Student student, Predmet predmet) {
        this.ocena = ocena;
        this.brojPolaganja = brojPolaganja;
        this.aktivan = aktivan;
        this.datumPocetka = datumPocetka;
        this.datumZavrsetka = datumZavrsetka;
        this.student = student;
        this.predmet = predmet;
    }

    // Getteri i setteri
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOcena() {
        return ocena;
    }

    public void setOcena(Integer ocena) {
        this.ocena = ocena;
    }

    public int getBrojPolaganja() {
        return brojPolaganja;
    }

    public void setBrojPolaganja(int brojPolaganja) {
        this.brojPolaganja = brojPolaganja;
    }

    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }

    public LocalDateTime getDatumPocetka() {
        return datumPocetka;
    }

    public void setDatumPocetka(LocalDateTime datumPocetka) {
        this.datumPocetka = datumPocetka;
    }

    public LocalDateTime getDatumZavrsetka() {
        return datumZavrsetka;
    }

    public void setDatumZavrsetka(LocalDateTime datumZavrsetka) {
        this.datumZavrsetka = datumZavrsetka;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public List<PrijavaPrestupa> getPrijavePrestupa() {
        return prijavePrestupa;
    }

    public void setPrijavePrestupa(List<PrijavaPrestupa> prijavePrestupa) {
        this.prijavePrestupa = prijavePrestupa;
    }

    public List<PrijavaIspita> getPrijaveIspita() {
        return prijaveIspita;
    }

    public void setPrijaveIspita(List<PrijavaIspita> prijaveIspita) {
        this.prijaveIspita = prijaveIspita;
    }

    /*public List<EvaluacijaZnanja> getEvaluacije() {
        return evaluacije;
    }

    public void setEvaluacije(List<EvaluacijaZnanja> evaluacije) {
        this.evaluacije = evaluacije;
    }*/
}