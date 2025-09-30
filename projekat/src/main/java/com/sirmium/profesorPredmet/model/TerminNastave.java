package com.sirmium.profesorPredmet.model;

import com.sirmium.predmet.model.Predmet;
import com.sirmium.profesorPredmet.model.TipTermina;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "termini_nastave")
public class TerminNastave {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "predmet_id", nullable = false)
    private Predmet predmet;
    
    @ManyToOne
    @JoinColumn(name = "profesor_id")
    private Predmet profesor;
    
    @ManyToOne
    @JoinColumn(name = "ishod_id")
    private Ishod ishod;
    
    @Column(nullable = false)
    private LocalDateTime datumVreme;
    
    public Predmet getProfesor() {
		return profesor;
	}
	public void setProfesor(Predmet profesor) {
		this.profesor = profesor;
	}
	@Column(nullable = false, length = 1000)
    private String tema;
    
    @Column(length = 3000)
    private String opis;
    
    @Column
    private String ucionica;
    
    @Enumerated(EnumType.STRING)
    private TipTermina tipTermina;

    // Geteri i seteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Predmet getPredmet() { return predmet; }
    public void setPredmet(Predmet predmet) { this.predmet = predmet; }

    public Ishod getIshod() { return ishod; }
    public void setIshod(Ishod ishod) { this.ishod = ishod; }

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