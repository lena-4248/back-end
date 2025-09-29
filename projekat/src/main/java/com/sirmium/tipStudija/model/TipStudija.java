package com.sirmium.tipStudija.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tipovi_studija")
public class TipStudija {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String naziv;
    
    @Column
    private String opis;
    
    @Column(name = "trajanje_godine")
    private Integer trajanjeGodine;
    
    @Column
    private Boolean aktiv = true;

    // Konstruktori
    public TipStudija() {}
    
    public TipStudija(String naziv) {
        this.naziv = naziv;
        this.aktiv = true;
    }

    // Geteri i seteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }
    
    public String getOpis() { return opis; }
    public void setOpis(String opis) { this.opis = opis; }
    
    public Integer getTrajanjeGodine() { return trajanjeGodine; }
    public void setTrajanjeGodine(Integer trajanjeGodine) { this.trajanjeGodine = trajanjeGodine; }
    
    public Boolean getAktiv() { return aktiv; }
    public void setAktiv(Boolean aktiv) { this.aktiv = aktiv; }
}