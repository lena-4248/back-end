package com.sirmium.osoblje.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "trebovanje_inventara")
public class TrebovanjeInventara {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "osoblje_id", nullable = false)
    private Osoblje osoblje;
    
    @ManyToOne
    @JoinColumn(name = "inventar_id", nullable = false)
    private KancelarijskiInventar inventar;
    
    @Column(nullable = false)
    private int kolicina;
    
    @Column(name = "datum_trebovanja", nullable = false)
    private LocalDate datumTrebovanja;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusTrebovanja status;
    
    @Column(length = 1000)
    private String napomena;
    
    @Column(name = "datum_odobrenja")
    private LocalDate datumOdobrenja;
    
    @Column(name = "datum_isporuke")
    private LocalDate datumIsporuke;

    public enum StatusTrebovanja {
        NA_CEKANJU, ODOBRENO, ODBIJENO, ISPORUCENO
    }

    // Konstruktori
    public TrebovanjeInventara() {}
    
    public TrebovanjeInventara(Osoblje osoblje, KancelarijskiInventar inventar, int kolicina, String napomena) {
        this.osoblje = osoblje;
        this.inventar = inventar;
        this.kolicina = kolicina;
        this.napomena = napomena;
        this.datumTrebovanja = LocalDate.now();
        this.status = StatusTrebovanja.NA_CEKANJU;
    }

    // Geteri i seteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Osoblje getOsoblje() { return osoblje; }
    public void setOsoblje(Osoblje osoblje) { this.osoblje = osoblje; }
    
    public KancelarijskiInventar getInventar() { return inventar; }
    public void setInventar(KancelarijskiInventar inventar) { this.inventar = inventar; }
    
    public int getKolicina() { return kolicina; }
    public void setKolicina(int kolicina) { this.kolicina = kolicina; }
    
    public LocalDate getDatumTrebovanja() { return datumTrebovanja; }
    public void setDatumTrebovanja(LocalDate datumTrebovanja) { this.datumTrebovanja = datumTrebovanja; }
    
    public StatusTrebovanja getStatus() { return status; }
    public void setStatus(StatusTrebovanja status) { this.status = status; }
    
    public String getNapomena() { return napomena; }
    public void setNapomena(String napomena) { this.napomena = napomena; }
    
    public LocalDate getDatumOdobrenja() { return datumOdobrenja; }
    public void setDatumOdobrenja(LocalDate datumOdobrenja) { this.datumOdobrenja = datumOdobrenja; }
    
    public LocalDate getDatumIsporuke() { return datumIsporuke; }
    public void setDatumIsporuke(LocalDate datumIsporuke) { this.datumIsporuke = datumIsporuke; }
}