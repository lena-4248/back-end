// ProfilDTO.java (za ažuriranje profila)
package com.sirmium.user.dto.Registrovani;

public class ProfilDTO {
    private String ime;
    private String prezime;
    private String adresa;
    private String telefon;
    private String slikaUrl;
    
    
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getSlikaUrl() {
		return slikaUrl;
	}
	public void setSlikaUrl(String slikaUrl) {
		this.slikaUrl = slikaUrl;
	}
    
}