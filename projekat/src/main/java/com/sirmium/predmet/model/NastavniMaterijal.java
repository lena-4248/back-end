package com.sirmium.predmet.model;

import jakarta.persistence.*;

@Entity
@Table(name = "nastavni_materijali")
public class NastavniMaterijal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String naziv;

    @Column(nullable = false)
    private String fajlPutanja;

    @ManyToOne
    @JoinColumn(name = "predmet_id", nullable = false)
    private Predmet predmet;

    public NastavniMaterijal() {}

    public NastavniMaterijal(String naziv, String fajlPutanja, Predmet predmet) {
        this.naziv = naziv;
        this.fajlPutanja = fajlPutanja;
        this.predmet = predmet;
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

    public String getFajlPutanja() {
        return fajlPutanja;
    }

    public void setFajlPutanja(String fajlPutanja) {
        this.fajlPutanja = fajlPutanja;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }
}