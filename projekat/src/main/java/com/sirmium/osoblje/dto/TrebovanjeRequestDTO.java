package com.sirmium.osoblje.dto;

public class TrebovanjeRequestDTO {
    private Long inventarId;
    private int kolicina;
    private String napomena;

    // Konstruktori
    public TrebovanjeRequestDTO() {}
    
    public TrebovanjeRequestDTO(Long inventarId, int kolicina, String napomena) {
        this.inventarId = inventarId;
        this.kolicina = kolicina;
        this.napomena = napomena;
    }

    // Geteri i seteri
    public Long getInventarId() { return inventarId; }
    public void setInventarId(Long inventarId) { this.inventarId = inventarId; }
    
    public int getKolicina() { return kolicina; }
    public void setKolicina(int kolicina) { this.kolicina = kolicina; }
    
    public String getNapomena() { return napomena; }
    public void setNapomena(String napomena) { this.napomena = napomena; }
}