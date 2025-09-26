package com.sirmium.fakultet.model;

import java.util.ArrayList;
import java.util.List;

import com.sirmium.departman.model.Departman;
import com.sirmium.profesor.model.Profesor;
import com.sirmium.univerzitet.model.Univerzitet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity 
public class Fakultet {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
    private String naziv;
	
	@Column(nullable=false)
	private String email;
	
	private String lokacija;
	
	private String brojTelefona;
	
	@Column(nullable = false)
	private boolean deleted = false; // default vrednost
	
	@ManyToOne 
    @JoinColumn(name = "univerzitet_id", nullable = false)
    private Univerzitet univerzitet;

	@OneToOne
	@JoinColumn(name = "dekan_id", nullable = true)
	private Profesor dekan;
	
	@OneToMany(mappedBy = "fakultet", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Departman> departmani = new ArrayList<>();
	
	private String opis;

	public Fakultet() {
		super();
	}

	public Fakultet(Long id, String naziv, String email, String lokacija, String brojTelefona, Univerzitet univerzitet,
			Profesor dekan, List<Departman> departmani, String opis) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.email = email;
		this.lokacija = lokacija;
		this.brojTelefona = brojTelefona;
		this.univerzitet = univerzitet;
		this.dekan = dekan;
		this.departmani = departmani;
		this.opis = opis;
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

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Univerzitet getUniverzitet() {
		return univerzitet;
	}

	public void setUniverzitet(Univerzitet univerzitet) {
		this.univerzitet = univerzitet;
	}

	public Profesor getDekan() {
		return dekan;
	}

	public void setDekan(Profesor dekan) {
		this.dekan = dekan;
	}

	public List<Departman> getDepartmani() {
		return departmani;
	}

	public void setDepartmani(List<Departman> departmani) {
		this.departmani = departmani;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
}