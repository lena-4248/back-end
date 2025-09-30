package com.sirmium.predmet.dto;

public class PredmetDTO {
    private Long id;
    private String naziv;
    private String opis;
    private String sifra;
    private int espb;
    private int semestar;
    private boolean aktivan;
    
    public PredmetDTO() {}
    
	public PredmetDTO(Long id, String naziv, String opis, String sifra, int espb, int semestar, boolean aktivan) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.sifra = sifra;
		this.espb = espb;
		this.semestar = semestar;
		this.aktivan = aktivan;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public int getEspb() {
		return espb;
	}

	public void setEspb(int espb) {
		this.espb = espb;
	}

	public int getSemestar() {
		return semestar;
	}

	public void setSemestar(int semestar) {
		this.semestar = semestar;
	}

	public boolean isAktivan() {
		return aktivan;
	}

	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}

}