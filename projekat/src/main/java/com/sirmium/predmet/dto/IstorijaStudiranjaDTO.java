package com.sirmium.predmet.dto;

import java.time.LocalDateTime;

public class IstorijaStudiranjaDTO {

    private String nazivPredmeta;
    private int brojPolaganja;
    private Integer ocena;
    private int brojESPB;
    private LocalDateTime datum;
    private String opis;
    private int brojBodova;

    public IstorijaStudiranjaDTO() {}

    public IstorijaStudiranjaDTO(String nazivPredmeta, int brojPolaganja, Integer ocena, int brojESPB, int brojBodova) {
        this.nazivPredmeta = nazivPredmeta;
        this.brojPolaganja = brojPolaganja;
        this.ocena = ocena;
        this.brojESPB = brojESPB;
        this.brojBodova = brojBodova;
    }

    public IstorijaStudiranjaDTO(String nazivPredmeta, int brojPolaganja, Integer ocena, int brojESPB) {
        this(nazivPredmeta, brojPolaganja, ocena, brojESPB, 0);
    }

    public IstorijaStudiranjaDTO(String nazivPredmeta, int brojPolaganja, Integer ocena, int brojESPB, LocalDateTime datum) {
        this(nazivPredmeta, brojPolaganja, ocena, brojESPB, 0);
        this.datum = datum;
    }

    public IstorijaStudiranjaDTO(String nazivPredmeta, int brojPolaganja, Integer ocena, int brojESPB, LocalDateTime datum, String opis) {
        this(nazivPredmeta, brojPolaganja, ocena, brojESPB, 0);
        this.datum = datum;
        this.opis = opis;
    }

    // Getteri i setteri
    public String getNazivPredmeta() {
        return nazivPredmeta;
    }

    public void setNazivPredmeta(String nazivPredmeta) {
        this.nazivPredmeta = nazivPredmeta;
    }

    public int getBrojPolaganja() {
        return brojPolaganja;
    }

    public void setBrojPolaganja(int brojPolaganja) {
        this.brojPolaganja = brojPolaganja;
    }

    public Integer getOcena() {
        return ocena;
    }

    public void setOcena(Integer ocena) {
        this.ocena = ocena;
    }

    public int getBrojESPB() {
        return brojESPB;
    }

    public void setBrojESPB(int brojESPB) {
        this.brojESPB = brojESPB;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getBrojBodova() {
        return brojBodova;
    }

    public void setBrojBodova(int brojBodova) {
        this.brojBodova = brojBodova;
    }
}