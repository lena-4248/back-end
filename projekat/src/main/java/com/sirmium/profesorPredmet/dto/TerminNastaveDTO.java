package com.sirmium.profesorPredmet.dto;

import com.sirmium.profesorPredmet.model.TipTermina;

import java.time.LocalDateTime;

public class TerminNastaveDTO {
    private Long id;
    private Long predmetId;
    private String predmetNaziv;
    private Long ishodId;
    private String ishodOpis;
    private LocalDateTime datumVreme;
    private String tema;
    private String opis;
    private String ucionica;
    private TipTermina tipTermina;

    public TerminNastaveDTO() {}
    
    public TerminNastaveDTO(Long id, Long predmetId, String predmetNaziv, Long ishodId, String ishodOpis,
			LocalDateTime datumVreme, String tema, String opis, String ucionica, TipTermina tipTermina) {
		super();
		this.id = id;
		this.predmetId = predmetId;
		this.predmetNaziv = predmetNaziv;
		this.ishodId = ishodId;
		this.ishodOpis = ishodOpis;
		this.datumVreme = datumVreme;
		this.tema = tema;
		this.opis = opis;
		this.ucionica = ucionica;
		this.tipTermina = tipTermina;
	}
	// Geteri i seteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPredmetId() { return predmetId; }
    public void setPredmetId(Long predmetId) { this.predmetId = predmetId; }

    public String getPredmetNaziv() { return predmetNaziv; }
    public void setPredmetNaziv(String predmetNaziv) { this.predmetNaziv = predmetNaziv; }

    public Long getIshodId() { return ishodId; }
    public void setIshodId(Long ishodId) { this.ishodId = ishodId; }

    public String getIshodOpis() { return ishodOpis; }
    public void setIshodOpis(String ishodOpis) { this.ishodOpis = ishodOpis; }

    public LocalDateTime getDatumVreme() { return datumVreme; }
    public void setDatumVreme(LocalDateTime datumVreme) { this.datumVreme = datumVreme; }

    public String getTema() { return tema; }
    public void setTema(String tema) { this.tema = tema; }

    public String getOpis() { return opis; }
    public void setOpis(String opis) { this.opis = opis; }

    public String getUcionica() { return ucionica; }
    public void setUcionica(String ucionica) { this.ucionica = ucionica; }

    public TipTermina getTipTermina() { return tipTermina; }
    public void setTipTermina(TipTermina tipTermina) { this.tipTermina = tipTermina; }
}