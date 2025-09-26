package com.sirmium.katedra.dto;

public class KatedraCreateUpdateDTO {
    private String naziv;
    private String opis;
    private Long sefKatedreId;
    private Long departmanId;

    // Default konstruktor
    public KatedraCreateUpdateDTO() {
    }

    // Konstruktor sa parametrima
    public KatedraCreateUpdateDTO(String naziv, String opis, Long sefKatedreId, Long departmanId) {
        this.naziv = naziv;
        this.opis = opis;
        this.sefKatedreId = sefKatedreId;
        this.departmanId = departmanId;
    }

    // Getteri i setteri
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Long getSefKatedreId() {
        return sefKatedreId;
    }

    public void setSefKatedreId(Long sefKatedreId) {
        this.sefKatedreId = sefKatedreId;
    }

    public Long getDepartmanId() {
        return departmanId;
    }

    public void setDepartmanId(Long departmanId) {
        this.departmanId = departmanId;
    }
}