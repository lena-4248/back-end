package com.sirmium.profesorPredmet.dto;

public class ProfesorPredmetDTO {
    private Long id;
    private Long profesorId;
    private String profesorIme;
    private String profesorPrezime;
    private Long predmetId;
    private String predmetNaziv;
    private String tipAngazmana;
    private int brojCasova;

    public ProfesorPredmetDTO() {}
    
    public ProfesorPredmetDTO(Long id, Long profesorId, String profesorIme, String profesorPrezime, Long predmetId,
			String predmetNaziv, String tipAngazmana, int brojCasova) {
		super();
		this.id = id;
		this.profesorId = profesorId;
		this.profesorIme = profesorIme;
		this.profesorPrezime = profesorPrezime;
		this.predmetId = predmetId;
		this.predmetNaziv = predmetNaziv;
		this.tipAngazmana = tipAngazmana;
		this.brojCasova = brojCasova;
	}
	// Geteri i seteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProfesorId() { return profesorId; }
    public void setProfesorId(Long profesorId) { this.profesorId = profesorId; }

    public String getProfesorIme() { return profesorIme; }
    public void setProfesorIme(String profesorIme) { this.profesorIme = profesorIme; }

    public String getProfesorPrezime() { return profesorPrezime; }
    public void setProfesorPrezime(String profesorPrezime) { this.profesorPrezime = profesorPrezime; }

    public Long getPredmetId() { return predmetId; }
    public void setPredmetId(Long predmetId) { this.predmetId = predmetId; }

    public String getPredmetNaziv() { return predmetNaziv; }
    public void setPredmetNaziv(String predmetNaziv) { this.predmetNaziv = predmetNaziv; }

    public String getTipAngazmana() { return tipAngazmana; }
    public void setTipAngazmana(String tipAngazmana) { this.tipAngazmana = tipAngazmana; }

    public int getBrojCasova() { return brojCasova; }
    public void setBrojCasova(int brojCasova) { this.brojCasova = brojCasova; }
}