package com.sirmium.osoblje.model;

import jakarta.persistence.*;

@Entity
@Table(name = "kancelarijski_inventar")
public class KancelarijskiInventar {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String naziv;
    
    @Column(nullable = false)
    private String kategorija;
    
    @Column(nullable = false)
    private int kolicina;
    
    @Column(name = "min_kolicina")
    private int minKolicina = 5;
    
    @Column(name = "jedinica_mere")
    private String jedinicaMere;
    
    @Column(length = 1000)
    private String opis;

    // Konstruktori
    public KancelarijskiInventar() {}
    
    public KancelarijskiInventar(String naziv, String kategorija, int kolicina, String jedinicaMere) {
        this.naziv = naziv;
        this.kategorija = kategorija;
        this.kolicina = kolicina;
        this.jedinicaMere = jedinicaMere;
    }

    // Geteri i seteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }
    
    public String getKategorija() { return kategorija; }
    public void setKategorija(String kategorija) { this.kategorija = kategorija; }
    
    public int getKolicina() { return kolicina; }
    public void setKolicina(int kolicina) { this.kolicina = kolicina; }
    
    public int getMinKolicina() { return minKolicina; }
    public void setMinKolicina(int minKolicina) { this.minKolicina = minKolicina; }
    
    public String getJedinicaMere() { return jedinicaMere; }
    public void setJedinicaMere(String jedinicaMere) { this.jedinicaMere = jedinicaMere; }
    
    public String getOpis() { return opis; }
    public void setOpis(String opis) { this.opis = opis; }
    
    // Helper metode
    public boolean isPotrebnoNabaviti() {
        return kolicina <= minKolicina;
    }
    
    public int getRazlikaDoMinimuma() {
        return Math.max(0, minKolicina - kolicina);
    }
}