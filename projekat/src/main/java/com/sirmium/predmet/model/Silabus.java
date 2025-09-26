package com.sirmium.predmet.model;

import jakarta.persistence.*;

@Entity
@Table(name = "silabusi")
public class Silabus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String sadrzaj;

    @OneToOne
    @JoinColumn(name = "predmet_id", nullable = false, unique = true)
    private Predmet predmet;

    public Silabus() {}

    public Silabus(Long id, String sadrzaj, Predmet predmet) {
        this.id = id;
        this.sadrzaj = sadrzaj;
        this.predmet = predmet;
    }

    // Getteri i setteri
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }
}