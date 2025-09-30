package com.sirmium.fakultet.dto;

public class FakultetSimpleDTO {
	private Long id;
	private String naziv;
	private String email;
	private String lokacija;
	private String brojTelefona;
	private String opis;
	private boolean deleted;
	private Long univerzitetId;
	private String univerzitetNaziv;
	private Long dekanId;
	private String dekanIme;

	// Getteri i setteri (isti kao FakultetDTO)
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLokacija() {
		return lokacija;
	}

	public void setLokacija(String lokacija) {
		this.lokacija = lokacija;
	}

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Long getUniverzitetId() {
		return univerzitetId;
	}

	public void setUniverzitetId(Long univerzitetId) {
		this.univerzitetId = univerzitetId;
	}

	public String getUniverzitetNaziv() {
		return univerzitetNaziv;
	}

	public void setUniverzitetNaziv(String univerzitetNaziv) {
		this.univerzitetNaziv = univerzitetNaziv;
	}

	public Long getDekanId() {
		return dekanId;
	}

	public void setDekanId(Long dekanId) {
		this.dekanId = dekanId;
	}

	public String getDekanIme() {
		return dekanIme;
	}

	public void setDekanIme(String dekanIme) {
		this.dekanIme = dekanIme;
	}
}