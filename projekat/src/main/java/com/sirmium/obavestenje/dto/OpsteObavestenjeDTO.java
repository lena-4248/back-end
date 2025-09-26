package com.sirmium.obavestenje.dto;

import java.time.LocalDate;

import com.sirmium.user.model.User;

public class OpsteObavestenjeDTO {
    private Long id;
    private String naslov;
    private String tekst;
    private LocalDate datum;
    private User autor;
    private boolean aktivan;

    // Konstruktori
    public OpsteObavestenjeDTO() {
    }

    public OpsteObavestenjeDTO(Long id, String naslov, String tekst, LocalDate datum, User autor, boolean aktivan) {
        this.id = id;
        this.naslov = naslov;
        this.tekst = tekst;
        this.datum = datum;
        this.autor = autor;
        this.aktivan = aktivan;
    }

    // Getteri i setteri
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getAutor() {
        return autor;
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }

    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }
    
    // toString metoda za debugging
    @Override
    public String toString() {
        return "OpsteObavestenjeDTO{" +
                "id=" + id +
                ", naslov='" + naslov + '\'' +
                ", datum=" + datum +
                ", aktivan=" + aktivan +
                '}';
    }
}