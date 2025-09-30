package com.sirmium.predmet.model;

import java.util.List;

import com.sirmium.godinaStudija.model.GodinaStudija;
import com.sirmium.obavestenje.model.Obavestenje;
import com.sirmium.studijskiProgram.model.StudijskiProgram;

import jakarta.persistence.*;

@Entity
@Table(name = "predmet")
public class Predmet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String naziv;
	private String opis;
	private String sifra;
	private int espb;
	private int semestar;
	private boolean aktivan = true;

	@ManyToOne
	@JoinColumn(name = "godina_studija_id")
	private GodinaStudija godinaStudija;

	@ManyToOne
	@JoinColumn(name = "studijski_program_id")
	private StudijskiProgram studijskiProgram;

	@OneToMany(mappedBy = "predmet", cascade = CascadeType.ALL)
	private List<Obavestenje> obavestenja;

	public Predmet() {
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

	public GodinaStudija getGodinaStudija() {
		return godinaStudija;
	}

	public void setGodinaStudija(GodinaStudija godinaStudija) {
		this.godinaStudija = godinaStudija;
	}

	public StudijskiProgram getStudijskiProgram() {
		return studijskiProgram;
	}

	public void setStudijskiProgram(StudijskiProgram studijskiProgram) {
		this.studijskiProgram = studijskiProgram;
	}

	public List<Obavestenje> getObavestenja() {
		return obavestenja;
	}

	public void setObavestenja(List<Obavestenje> obavestenja) {
		this.obavestenja = obavestenja;
	}
}