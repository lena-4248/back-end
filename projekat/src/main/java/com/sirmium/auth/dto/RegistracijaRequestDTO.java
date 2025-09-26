package com.sirmium.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegistracijaRequestDTO {
    @NotBlank @Email
    private String email;
    
    @NotBlank @Size(min = 8)
    private String lozinka;
    
    @NotBlank
    private String ime;
    
    @NotBlank
    private String prezime;
    
    @NotBlank
    private String adresa;
    
    @NotBlank @Size(min = 13, max = 13)
    private String jmbg;
    
    @NotBlank
    private String telefon;
    
    @NotBlank
    private String slikaUrl;
    
    @NotBlank
    private String tipKorisnika;

    // Geteri i seteri
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getLozinka() { return lozinka; }
    public void setLozinka(String lozinka) { this.lozinka = lozinka; }
    
    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }
    
    public String getPrezime() { return prezime; }
    public void setPrezime(String prezime) { this.prezime = prezime; }
    
    public String getAdresa() { return adresa; }
    public void setAdresa(String adresa) { this.adresa = adresa; }
    
    public String getJmbg() { 
    	return jmbg; }
    
    public void setJmbg(String jmbg) { 
    	this.jmbg = jmbg; }
    
    public String getTipKorisnika() { 
    	return tipKorisnika; }
    
    public void setTipKorisnika(String tipKorisnika) { 
    	this.tipKorisnika = tipKorisnika; }
    
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