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

    @Column(length = 2000) // Omogućava duži tekst
    private String tekst;

    private LocalDate datum;

    @ManyToOne
    @JoinColumn(name = "predmet_id", nullable = false)
    private Predmet predmet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)
    private User autor;

    public Obavestenje() {}

    public Obavestenje(String tekst, LocalDate datum, Predmet predmet, User autor) {
        this.tekst = tekst;
        this.datum = datum;
        this.predmet = predmet;
        this.autor = autor;
    }

    public Long getId() { 
        return id; 
    }
    
    public void setId(Long id) { 
        this.id = id; 
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