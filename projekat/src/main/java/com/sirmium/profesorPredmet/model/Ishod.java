package com.sirmium.profesorPredmet.model;

import com.sirmium.predmet.model.Predmet;
import jakarta.persistence.*;

@Entity
@Table(name = "ishodi")
public class Ishod {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "predmet_id", nullable = false)
    private Predmet predmet;
    
    @Column(nullable = false, length = 1000)
    private String opis;
    
    @Column(name = "redni_broj")
    private int redniBroj;
    
    @Column(name = "espb_bodovi")
    private int espbBodovi;

    // Geteri i seteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Predmet getPredmet() { return predmet; }
    public void setPredmet(Predmet predmet) { this.predmet = predmet; }

    public String getOpis() { return opis; }
    public void setOpis(String opis) { this.opis = opis; }

    public int getRedniBroj() { return redniBroj; }
    public void setRedniBroj(int redniBroj) { this.redniBroj = redniBroj; }

    public int getEspbBodovi() { return espbBodovi; }
    public void setEspbBodovi(int espbBodovi) { this.espbBodovi = espbBodovi; }
}