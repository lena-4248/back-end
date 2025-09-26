package com.sirmium.departman.model;

import java.util.ArrayList;
import java.util.List;

import com.sirmium.fakultet.model.Fakultet;
import com.sirmium.katedra.model.Katedra;
import com.sirmium.profesor.model.Profesor;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Departman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String naziv;

    private String opis;
    
    @Column(nullable = false)
    private boolean deleted = false;

    @OneToMany(mappedBy = "departman", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Katedra> katedre = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "fakultet_id", nullable = true)
    private Fakultet fakultet;

    @OneToOne
    @JoinColumn(name = "sef_departmana_id", nullable = true)
    private Profesor sefDepartmana;

    public Departman() {}

    public Departman(Long id) {
        this.id = id;
    }

    public Departman(Long id, String naziv, String opis, List<Katedra> katedre, 
                    Fakultet fakultet, Profesor sefDepartmana) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.katedre = katedre;
        this.fakultet = fakultet;
        this.sefDepartmana = sefDepartmana;
    }

    // Getteri i setteri
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

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

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public List<Katedra> getKatedre() {
        return katedre;
    }

    public void setKatedre(List<Katedra> katedre) {
        this.katedre = katedre;
    }

    public Fakultet getFakultet() {
        return fakultet;
    }

    public void setFakultet(Fakultet fakultet) {
        this.fakultet = fakultet;
    }

    public Profesor getSefDepartmana() {
        return sefDepartmana;
    }

    public void setSefDepartmana(Profesor sefDepartmana) {
        this.sefDepartmana = sefDepartmana;
    }
}