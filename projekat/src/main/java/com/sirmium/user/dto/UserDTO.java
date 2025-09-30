package com.sirmium.user.dto;

import java.util.Set;

public class UserDTO {
    private Long id;
    private String email;
    private String ime;
    private String prezime;
    private String adresa;
    private String jmbg;
    private String telefon;
    private String slikaUrl;
    private Set<RoleDTO> roles;
    private boolean deleted;
    private boolean aktiviran;

    public UserDTO() {}

    public UserDTO(Long id, String email, String ime, String prezime, 
                 String adresa, String jmbg, String telefon, String slikaUrl, Set<RoleDTO> roles) {
        this.id = id;
        this.email = email;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.jmbg = jmbg;
        this.telefon = telefon;
        this.slikaUrl = slikaUrl;
        this.roles = roles;
        this.deleted = false;
        this.aktiviran = false;
    }

    
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getJmbg() {
        return jmbg;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isAktiviran() {
        return aktiviran;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
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

	public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setAktiviran(boolean aktiviran) {
        this.aktiviran = aktiviran;
    }

	public Object map(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
}