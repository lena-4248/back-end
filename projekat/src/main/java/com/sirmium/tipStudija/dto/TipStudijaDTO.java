package com.sirmium.tipStudija.dto;

public class TipStudijaDTO {
    private Long id;
    private String naziv;
    private String opis;
    private Integer trajanjeGodine;
    private Boolean aktiv;

    // Konstruktori
    public TipStudijaDTO() {}
    
    public TipStudijaDTO(Long id, String naziv) {
        this.id = id;
        this.naziv = naziv;
        this.aktiv = true;
    }

    // Geteri i seteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }
    
    public String getOpis() { return opis; }
    public void setOpis(String opis) { this.opis = opis; }
    
    public Integer getTrajanjeGodine() { return trajanjeGodine; }
    public void setTrajanjeGodine(Integer trajanjeGodine) { this.trajanjeGodine = trajanjeGodine; }
    
    public Boolean getAktiv() { return aktiv; }
    public void setAktiv(Boolean aktiv) { this.aktiv = aktiv; }
}