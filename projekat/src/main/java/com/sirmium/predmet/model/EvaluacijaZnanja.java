package com.sirmium.predmet.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "evaluacije_znanja")
public class EvaluacijaZnanja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime vremePocetka;
    private Integer brojBodova;

    @ManyToOne
    @JoinColumn(name = "tip_evaluacije_id", nullable = false)
    private TipEvaluacije tipEvaluacije;

    @ManyToOne
    @JoinColumn(name = "pohadjanje_id", nullable = false)
    private PohadjanjePredmeta pohadjanje;

    public EvaluacijaZnanja() {}

    public EvaluacijaZnanja(LocalDateTime vremePocetka, Integer brojBodova, 
                           TipEvaluacije tipEvaluacije, PohadjanjePredmeta pohadjanje) {
        this.vremePocetka = vremePocetka;
        this.brojBodova = brojBodova;
        this.tipEvaluacije = tipEvaluacije;
        this.pohadjanje = pohadjanje;
    }

    // Getteri i setteri
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getVremePocetka() {
        return vremePocetka;
    }

    public void setVremePocetka(LocalDateTime vremePocetka) {
        this.vremePocetka = vremePocetka;
    }

    public Integer getBrojBodova() {
        return brojBodova;
    }

    public void setBrojBodova(Integer brojBodova) {
        this.brojBodova = brojBodova;
    }

    public TipEvaluacije getTipEvaluacije() {
        return tipEvaluacije;
    }

    public void setTipEvaluacije(TipEvaluacije tipEvaluacije) {
        this.tipEvaluacije = tipEvaluacije;
    }

    public PohadjanjePredmeta getPohadjanje() {
        return pohadjanje;
    }

    public void setPohadjanje(PohadjanjePredmeta pohadjanje) {
        this.pohadjanje = pohadjanje;
    }
}