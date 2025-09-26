package com.sirmium.predmet.dto;

public class PredmetDTO {

    private Long id;
    private String naziv;
    private int ects;
    private String informacijeOPredmetu;
    private Long godinaStudijaId;
    private boolean deleted;
    private String godinaISmer;

    public PredmetDTO() {}

    public PredmetDTO(Long id, String naziv, int ects, String informacijeOPredmetu, Long godinaStudijaId) {
        this.id = id;
        this.naziv = naziv;
        this.ects = ects;
        this.informacijeOPredmetu = informacijeOPredmetu;
        this.godinaStudijaId = godinaStudijaId;
    }

    public PredmetDTO(Long id, String naziv, int ects, String informacijeOPredmetu) {
        this.id = id;
        this.naziv = naziv;
        this.ects = ects;
        this.informacijeOPredmetu = informacijeOPredmetu;
    }

    // Getteri i setteri
    public Long getId() { 
        return id; 
    }
    
    public void setId(Long id) { 
        this.id = id; 
    }

    public String getNaziv() { 
        return naziv; 
    }
    
    public void setNaziv(String naziv) { 
        this.naziv = naziv; 
    }

    public int getEcts() { 
        return ects; 
    }
    
    public void setEcts(int ects) { 
        this.ects = ects; 
    }

    public String getInformacijeOPredmetu() { 
        return informacijeOPredmetu; 
    }
    
    public void setInformacijeOPredmetu(String informacijeOPredmetu) { 
        this.informacijeOPredmetu = informacijeOPredmetu; 
    }

    public Long getGodinaStudijaId() { 
        return godinaStudijaId; 
    }
    
    public void setGodinaStudijaId(Long godinaStudijaId) { 
        this.godinaStudijaId = godinaStudijaId; 
    }

    public boolean isDeleted() { 
        return deleted; 
    }
    
    public void setDeleted(boolean deleted) { 
        this.deleted = deleted; 
    }

    public String getGodinaISmer() { 
        return godinaISmer; 
    }
    
    public void setGodinaISmer(String godinaISmer) { 
        this.godinaISmer = godinaISmer; 
    }
}