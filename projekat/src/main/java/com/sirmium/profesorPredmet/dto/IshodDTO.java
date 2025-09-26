package com.sirmium.profesorPredmet.dto;

public class IshodDTO {
    private Long id;
    private Long predmetId;
    private String predmetNaziv;
    private String opis;
    private int redniBroj;
    private int espbBodovi;

    public IshodDTO() {}
    
    public IshodDTO(Long id, Long predmetId, String predmetNaziv, String opis, int redniBroj, int espbBodovi) {
		super();
		this.id = id;
		this.predmetId = predmetId;
		this.predmetNaziv = predmetNaziv;
		this.opis = opis;
		this.redniBroj = redniBroj;
		this.espbBodovi = espbBodovi;
	}
	// Geteri i seteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPredmetId() { return predmetId; }
    public void setPredmetId(Long predmetId) { this.predmetId = predmetId; }

    public String getPredmetNaziv() { return predmetNaziv; }
    public void setPredmetNaziv(String predmetNaziv) { this.predmetNaziv = predmetNaziv; }

    public String getOpis() { return opis; }
    public void setOpis(String opis) { this.opis = opis; }

    public int getRedniBroj() { return redniBroj; }
    public void setRedniBroj(int redniBroj) { this.redniBroj = redniBroj; }

    public int getEspbBodovi() { return espbBodovi; }
    public void setEspbBodovi(int espbBodovi) { this.espbBodovi = espbBodovi; }
}