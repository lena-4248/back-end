package com.sirmium.obavestenje.model;

import com.sirmium.predmet.model.Predmet;
import com.sirmium.user.model.User;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Obavestenje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 200) // DODATO: naslov
    private String naslov;

    @Column(length = 2000) // Omogućava duži tekst
    private String tekst;

    private LocalDate datum;
    
    @Column(nullable = false) // DODATO: aktivan
    private boolean aktivan = true;

    @ManyToOne
    @JoinColumn(name = "predmet_id", nullable = false)
    private Predmet predmet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)
    private User autor;

    public Obavestenje() {}

    public Obavestenje(String naslov, String tekst, LocalDate datum, Predmet predmet, User autor) {
    	this.naslov = naslov;
        this.tekst = tekst;
        this.datum = datum;
        this.predmet = predmet;
        this.autor = autor;
        this.aktivan = true;
    }

    public Long getId() { 
        return id; 
    }
    
    public void setId(Long id) { 
        this.id = id; 
    }
    
    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }
    
    public String getTekst() { 
        return tekst; 
    }
    
    public void setTekst(String tekst) { 
        this.tekst = tekst; 
    }

    public LocalDate getDatum() { 
        return datum; 
    }
    
    public void setDatum(LocalDate datum) { 
        this.datum = datum; 
    }
    
    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }

    public Predmet getPredmet() { 
        return predmet; 
    }
    
    public void setPredmet(Predmet predmet) { 
        this.predmet = predmet; 
    }

    public User getAutor() { 
        return autor; 
    }
    
    public void setAutor(User autor) { 
        this.autor = autor; 
    }
}