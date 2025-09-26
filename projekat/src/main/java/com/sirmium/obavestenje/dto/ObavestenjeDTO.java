package com.sirmium.obavestenje.dto;

import java.time.LocalDate;

public class ObavestenjeDTO {
    private String naslov;
    private String tekst;
    private LocalDate datum;
    private Long predmetId;
    private Long autorId;

    // Konstruktori
    public ObavestenjeDTO() {
    }

    public ObavestenjeDTO(String naslov, String tekst, LocalDate datum, Long predmetId, Long autorId) {
        this.naslov = naslov;
        this.tekst = tekst;
        this.datum = datum;
        this.predmetId = predmetId;
        this.autorId = autorId;
    }

    // Getteri i setteri
    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public Long getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(Long predmetId) {
        this.predmetId = predmetId;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }
}