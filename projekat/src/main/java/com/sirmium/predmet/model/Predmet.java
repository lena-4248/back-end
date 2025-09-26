package com.sirmium.predmet.model;

import com.sirmium.godinaStudija.model.GodinaStudija;
import com.sirmium.katedra.model.Katedra;
import com.sirmium.obavestenje.model.Obavestenje;
import com.sirmium.profesorPredmet.model.ProfesorPredmet;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "predmeti")
public class Predmet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String naziv;

    @Column(nullable = false)
    private int ects;

    @Column(name = "informacije_o_predmetu")
    private String informacijeOPredmetu;
    
    @Column(nullable = false)
    private boolean deleted = false;

    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProfesorPredmet> profesori;

    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PohadjanjePredmeta> pohadjanja = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "godina_studija_id", nullable = false)
    private GodinaStudija godinaStudija;
    
    @OneToOne(mappedBy = "predmet", cascade = CascadeType.ALL, orphanRemoval = true)
    private Silabus silabus;
    
    @OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Obavestenje> obavestenja = new ArrayList<>();

    public Predmet() {}

    public Predmet(String naziv, int ects, String informacijeOPredmetu, GodinaStudija godinaStudija) {
        this.naziv = naziv;
        this.ects = ects;
        this.informacijeOPredmetu = informacijeOPredmetu;
        this.godinaStudija = godinaStudija;
    }

    // Getteri i setteri
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getEcts() {
        return ects;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }

    public String getInformacijeOPredmetu() {
        return informacijeOPredmetu;
    }

    public void setInformacijeOPredmetu(String informacijeOPredmetu) {
        this.informacijeOPredmetu = informacijeOPredmetu;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<ProfesorPredmet> getProfesori() {
        return profesori;
    }

    public void setProfesori(List<ProfesorPredmet> profesori) {
        this.profesori = profesori;
    }

    public List<PohadjanjePredmeta> getPohadjanja() {
        return pohadjanja;
    }

    public void setPohadjanja(List<PohadjanjePredmeta> pohadjanja) {
        this.pohadjanja = pohadjanja;
    }

    public GodinaStudija getGodinaStudija() {
        return godinaStudija;
    }

    public void setGodinaStudija(GodinaStudija godinaStudija) {
        this.godinaStudija = godinaStudija;
    }

    public Silabus getSilabus() {
        return silabus;
    }

    public void setSilabus(Silabus silabus) {
        this.silabus = silabus;
    }

    public List<Obavestenje> getObavestenja() {
        return obavestenja;
    }

    public void setObavestenja(List<Obavestenje> obavestenja) {
        this.obavestenja = obavestenja;
    }
}