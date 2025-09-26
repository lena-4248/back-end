package com.sirmium.obavestenje.dto;

import java.time.LocalDate;

public class ObavestenjeStudentuDTO {
    private String predmetNaziv;
    private String naslov; // Dodato naslov polje
    private String tekst;
    private LocalDate datum;

    // Konstruktori
    public ObavestenjeStudentuDTO() {
    }

    public ObavestenjeStudentuDTO(String predmetNaziv, String naslov, String tekst, LocalDate datum) {
        this.predmetNaziv = predmetNaziv;
        this.naslov = naslov;
        this.tekst = tekst;
        this.datum = datum;
    }

    // Getteri i setteri
    public String getPredmetNaziv() {
        return predmetNaziv;
    }

    public void setPredmetNaziv(String predmetNaziv) {
        this.predmetNaziv = predmetNaziv;
    }

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
    
    // toString metoda za debugging
    @Override
    public String toString() {
        return "ObavestenjeStudentuDTO{" +
                "predmetNaziv='" + predmetNaziv + '\'' +
                ", naslov='" + naslov + '\'' +
                ", datum=" + datum +
                '}';
    }
}