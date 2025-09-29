package com.sirmium.studijskiProgram.model;

import com.sirmium.profesor.model.Profesor;
import com.sirmium.fakultet.model.Fakultet;
import com.sirmium.tipStudija.model.TipStudija;
import jakarta.persistence.*;

@Entity
@Table(name = "studijski_programi")
public class StudijskiProgram {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String naziv;
    
    @Column(columnDefinition = "TEXT")
    private String opis;
    
    @Column(nullable = false)
    private Integer trajanje; // u godinama
    
    @Column(nullable = false)
    private Integer espb;
    
    @Column(name = "stepen_studija")
    private String stepenStudija; // OSNOVNE, MASTER, DOKTORSKE
    
    @ManyToOne
    @JoinColumn(name = "fakultet_id", nullable = false)
    private Fakultet fakultet;
    
    @ManyToOne
    @JoinColumn(name = "rukovodilac_id")
    private Profesor rukovodilac;
    
    @ManyToOne
    @JoinColumn(name = "tip_studija_id")
    private TipStudija tipStudija;
    
    @Column
    private Boolean aktivan = true;

    // Konstruktori
    public StudijskiProgram() {}
    
    public StudijskiProgram(String naziv, Integer trajanje, Integer espb, Fakultet fakultet) {
        this.naziv = naziv;
        this.trajanje = trajanje;
        this.espb = espb;
        this.fakultet = fakultet;
        this.aktivan = true;
    }

    // Geteri i seteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }
    
    public String getOpis() { return opis; }
    public void setOpis(String opis) { this.opis = opis; }
    
    public Integer getTrajanje() { return trajanje; }
    public void setTrajanje(Integer trajanje) { this.trajanje = trajanje; }
    
    public Integer getEspb() { return espb; }
    public void setEspb(Integer espb) { this.espb = espb; }
    
    public String getStepenStudija() { return stepenStudija; }
    public void setStepenStudija(String stepenStudija) { this.stepenStudija = stepenStudija; }
    
    public Fakultet getFakultet() { return fakultet; }
    public void setFakultet(Fakultet fakultet) { this.fakultet = fakultet; }
    
    public Profesor getRukovodilac() { return rukovodilac; }
    public void setRukovodilac(Profesor rukovodilac) { this.rukovodilac = rukovodilac; }
    
    public TipStudija getTipStudija() { return tipStudija; }
    public void setTipStudija(TipStudija tipStudija) { this.tipStudija = tipStudija; }
    
    public Boolean getAktivan() { return aktivan; }
    public void setAktivan(Boolean aktivan) { this.aktivan = aktivan; }
}