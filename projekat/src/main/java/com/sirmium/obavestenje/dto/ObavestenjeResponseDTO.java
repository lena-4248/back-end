package com.sirmium.obavestenje.dto;

import java.time.LocalDate;

public class ObavestenjeResponseDTO {
    private Long id;
    private String naslov;
    private String tekst;
    private LocalDate datum;
    private Long predmetId;
    private String predmetNaziv;
    private Long autorId;
    private String autorIme;
    
    public ObavestenjeResponseDTO() {
    }

    public ObavestenjeResponseDTO(Long id, String naslov, String tekst, LocalDate datum, 
                                 Long predmetId, String predmetNaziv, Long autorId, String autorIme) {
        this.id = id;
        this.naslov = naslov;
        this.tekst = tekst;
        this.datum = datum;
        this.predmetId = predmetId;
        this.predmetNaziv = predmetNaziv;
        this.autorId = autorId;
        this.autorIme = autorIme;
    }

    // Getteri i setteri
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

    public Long getPredmetId() { 
        return predmetId; 
    }
    
    public void setPredmetId(Long predmetId) { 
        this.predmetId = predmetId; 
    }

    public String getPredmetNaziv() { 
        return predmetNaziv; 
    }
    
    public void setPredmetNaziv(String predmetNaziv) { 
        this.predmetNaziv = predmetNaziv; 
    }

    public Long getAutorId() { 
        return autorId; 
    }
    
    public void setAutorId(Long autorId) { 
        this.autorId = autorId; 
    }

    public String getAutorIme() { 
        return autorIme; 
    }
    
    public void setAutorIme(String autorIme) { 
        this.autorIme = autorIme; 
    }
}