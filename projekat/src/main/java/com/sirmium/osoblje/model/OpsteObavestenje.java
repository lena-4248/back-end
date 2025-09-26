package com.sirmium.osoblje.model;

import com.sirmium.user.model.User;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "opste_obavestenje")
public class OpsteObavestenje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String naslov;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String tekst;

    @Column(nullable = false)
    private LocalDate datum;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)
    private User autor;
    
    @Column(nullable = false)
    private boolean aktivan = true;

    // Konstruktori
    public OpsteObavestenje() {}

    public OpsteObavestenje(String naslov, String tekst, LocalDate datum, User autor) {
        this.naslov = naslov;
        this.tekst = tekst;
        this.datum = datum;
        this.autor = autor;
        this.aktivan = true;
    }

    // Getteri i setteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNaslov() { return naslov; }
    public void setNaslov(String naslov) { this.naslov = naslov; }

    public String getTekst() { return tekst; }
    public void setTekst(String tekst) { this.tekst = tekst; }

    public LocalDate getDatum() { return datum; }
    public void setDatum(LocalDate datum) { this.datum = datum; }

    public User getAutor() { return autor; }
    public void setAutor(User autor) { this.autor = autor; }

    public boolean isAktivan() { return aktivan; }
    public void setAktivan(boolean aktivan) { this.aktivan = aktivan; }
    
    public String getImeAutora() {
        if (this.autor != null) {
            return this.autor.getIme() + " " + this.autor.getPrezime();
        }
        return "Nepoznat autor";
    }
    
    @Override
    public String toString() {
        return "OpsteObavestenje{" +
                "id=" + id +
                ", naslov='" + naslov + '\'' +
                ", datum=" + datum +
                ", aktivan=" + aktivan +
                '}';
    }
}