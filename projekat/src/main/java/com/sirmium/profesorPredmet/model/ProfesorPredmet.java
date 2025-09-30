package com.sirmium.profesorPredmet.model;

import com.sirmium.profesor.model.Profesor;
import com.sirmium.predmet.model.Predmet;
import jakarta.persistence.*;

@Entity
@Table(name = "profesor_predmet")
public class ProfesorPredmet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "profesor_id", nullable = false)
	private Profesor profesor;

	@ManyToOne
	@JoinColumn(name = "predmet_id", nullable = false)
	private Predmet predmet;

	private int semestar;

	@Column(nullable = false)
	private String tipAngazmana; // "profesor", "asistent", "saradnik"

	@Column
	private int brojCasova;

	// Geteri i seteri
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Predmet getPredmet() {
		return predmet;
	}

	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}

	public int getSemestar() {
		return semestar;
	}

	public void setSemestar(int semestar) {
		this.semestar = semestar;
	}

	public String getTipAngazmana() {
		return tipAngazmana;
	}

	public void setTipAngazmana(String tipAngazmana) {
		this.tipAngazmana = tipAngazmana;
	}

	public int getBrojCasova() {
		return brojCasova;
	}

	public void setBrojCasova(int brojCasova) {
		this.brojCasova = brojCasova;
	}
}