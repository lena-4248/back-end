package com.sirmium.katedra.model;

import java.util.ArrayList;
import java.util.List;

import com.sirmium.departman.model.Departman;
import com.sirmium.profesor.model.Profesor;
import com.sirmium.tipStudija.model.TipStudija;

import jakarta.persistence.*;

@Entity
public class Katedra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable=false)
    private String naziv;
    
    private String opis;
    
    @Column(nullable = false)
    private boolean deleted = false;
    
    @ManyToOne
    @JoinColumn(name = "departman_id", nullable = true)
    private Departman departman;
    
    @OneToMany(mappedBy = "katedra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TipStudija> tipoviStudija = new ArrayList<TipStudija>();

    @OneToOne
    @JoinColumn(name = "sefKatedre_id", nullable = true)
    private Profesor sefKatedre;
    
    public Katedra() {
        super();
    }
    
    public Katedra(Long id, String naziv, String opis, Departman departman, 
                  List<TipStudija> tipoviStudija, Profesor sefKatedre) {
        super();
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.departman = departman;
        this.tipoviStudija = tipoviStudija;
        this.sefKatedre = sefKatedre;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Departman getDepartman() {
        return departman;
    }

    public void setDepartman(Departman departman) {
        this.departman = departman;
    }

    public List<TipStudija> getTipoviStudija() {
        return tipoviStudija;
    }

    public void setTipoviStudija(List<TipStudija> tipoviStudija) {
        this.tipoviStudija = tipoviStudija;
    }

    public Profesor getSefKatedre() {
        return sefKatedre;
    }

    public void setSefKatedre(Profesor sefKatedre) {
        this.sefKatedre = sefKatedre;
    }
}