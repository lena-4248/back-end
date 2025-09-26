package com.sirmium.osoblje.dto;

import java.time.LocalDate;
import com.sirmium.osoblje.model.OpsteObavestenje;

public class OpsteObavestenjeResponseDTO {
    private Long id;
    private String naslov;
    private String tekst;
    private LocalDate datum;
    private String autorIme;
    private boolean aktivan;

    // Konstruktori
    public OpsteObavestenjeResponseDTO() {}

    public OpsteObavestenjeResponseDTO(OpsteObavestenje o) {
        this.id = o.getId();
        this.naslov = o.getNaslov();
        this.tekst = o.getTekst();
        this.datum = o.getDatum();
        this.autorIme = o.getImeAutora();
        this.aktivan = o.isAktivan();
    }

    // Getteri i setteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNaslov() { return naslov; }
    public void setNaslov(String naslov) { this.naslov = naslov; }

    public String getTekst() { return tekst; }
    public void setTekst(String tekst) { this.tekst = tekst; }

    public LocalDate getDatum() { return datum; }
    public void setDatum(LocalDate datum) { this.datum = datum; }

    public String getAutorIme() { return autorIme; }
    public void setAutorIme(String autorIme) { this.autorIme = autorIme; }

    public boolean isAktivan() { return aktivan; }
    public void setAktivan(boolean aktivan) { this.aktivan = aktivan; }
    
    @Override
    public String toString() {
        return "OpsteObavestenjeResponseDTO{" +
                "id=" + id +
                ", naslov='" + naslov + '\'' +
                ", datum=" + datum +
                ", autorIme='" + autorIme + '\'' +
                ", aktivan=" + aktivan +
                '}';
    }
}