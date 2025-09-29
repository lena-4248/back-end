package com.sirmium.osoblje.model;

import jakarta.persistence.*;

@Entity
@Table(name = "biblioteka_udzbenici")
public class BibliotekaUdzbenik {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String isbn;
    
    @Column(nullable = false)
    private String naslov;
    
    @Column(nullable = false)
    private String autor;
    
    private String izdavac;
    
    @Column(name = "godina_izdanja")
    private Integer godinaIzdanja;
    
    private String izdanje;
    
    @Column(nullable = false)
    private int kolicina;
    
    @Column(nullable = false)
    private int dostupno;

    // Konstruktori
    public BibliotekaUdzbenik() {}
    
    public BibliotekaUdzbenik(String naslov, String autor, int kolicina) {
        this.naslov = naslov;
        this.autor = autor;
        this.kolicina = kolicina;
        this.dostupno = kolicina;
    }

    // Geteri i seteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    
    public String getNaslov() { return naslov; }
    public void setNaslov(String naslov) { this.naslov = naslov; }
    
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    
    public String getIzdavac() { return izdavac; }
    public void setIzdavac(String izdavac) { this.izdavac = izdavac; }
    
    public Integer getGodinaIzdanja() { return godinaIzdanja; }
    public void setGodinaIzdanja(Integer godinaIzdanja) { this.godinaIzdanja = godinaIzdanja; }
    
    public String getIzdanje() { return izdanje; }
    public void setIzdanje(String izdanje) { this.izdanje = izdanje; }
    
    public int getKolicina() { return kolicina; }
    public void setKolicina(int kolicina) { 
        this.kolicina = kolicina;
        // Ažuriraj dostupno ako je potrebno
        if (this.dostupno > kolicina) {
            this.dostupno = kolicina;
        }
    }
    
    public int getDostupno() { return dostupno; }
    public void setDostupno(int dostupno) { this.dostupno = dostupno; }
    
    // Helper metode
    public boolean isDostupno() {
        return dostupno > 0;
    }
    
    public int getIznajmljeno() {
        return kolicina - dostupno;
    }
    
    public void smanjiDostupno() {
        if (dostupno > 0) {
            dostupno--;
        }
    }
    
    public void povecajDostupno() {
        if (dostupno < kolicina) {
            dostupno++;
        }
    }
}