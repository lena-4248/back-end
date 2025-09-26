package com.sirmium.profesor.dto;

import java.util.List;
import com.sirmium.predmet.dto.PredmetDTO;

public class ProfesorDTO {
    private Long id;
    private String ime;
    private String prezime;
    private String email;
    private String zvanje;
    private String katedra;
    private String naucnaOblast;
    private String biografija;
    private String kabinet;
    private String konsultacije;
    private String slikaPath;
    private List<PredmetDTO> predmeti;

    public ProfesorDTO() {}
    
    // Konstruktori
    public ProfesorDTO(Long id, String ime, String prezime, String email, String zvanje, String katedra,
			String naucnaOblast, String biografija, String kabinet, String konsultacije, String slikaPath,
			List<PredmetDTO> predmeti) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.zvanje = zvanje;
		this.katedra = katedra;
		this.naucnaOblast = naucnaOblast;
		this.biografija = biografija;
		this.kabinet = kabinet;
		this.konsultacije = konsultacije;
		this.slikaPath = slikaPath;
		this.predmeti = predmeti;
	}

	// Geteri i seteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }

    public String getPrezime() { return prezime; }
    public void setPrezime(String prezime) { this.prezime = prezime; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getZvanje() { return zvanje; }
    public void setZvanje(String zvanje) { this.zvanje = zvanje; }

    public String getKatedra() { return katedra; }
    public void setKatedra(String katedra) { this.katedra = katedra; }

    public String getNaucnaOblast() { return naucnaOblast; }
    public void setNaucnaOblast(String naucnaOblast) { this.naucnaOblast = naucnaOblast; }

    public String getBiografija() { return biografija; }
    public void setBiografija(String biografija) { this.biografija = biografija; }

    public String getKabinet() { return kabinet; }
    public void setKabinet(String kabinet) { this.kabinet = kabinet; }

    public String getKonsultacije() { return konsultacije; }
    public void setKonsultacije(String konsultacije) { this.konsultacije = konsultacije; }

    public String getSlikaPath() { return slikaPath; }
    public void setSlikaPath(String slikaPath) { this.slikaPath = slikaPath; }

    public List<PredmetDTO> getPredmeti() { return predmeti; }
    public void setPredmeti(List<PredmetDTO> predmeti) { this.predmeti = predmeti; }
}