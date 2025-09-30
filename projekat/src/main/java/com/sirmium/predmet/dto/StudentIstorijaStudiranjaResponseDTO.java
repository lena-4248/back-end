package com.sirmium.predmet.dto;

import java.util.List;

public class StudentIstorijaStudiranjaResponseDTO {
    private List<IstorijaStudiranjaDTO> predmeti;
    private Double prosecnaOcena;
    private int ukupnoESPB;

    public StudentIstorijaStudiranjaResponseDTO() {}

    public StudentIstorijaStudiranjaResponseDTO(List<IstorijaStudiranjaDTO> predmeti, Double prosecnaOcena, int ukupnoESPB) {
        this.predmeti = predmeti;
        this.prosecnaOcena = prosecnaOcena;
        this.ukupnoESPB = ukupnoESPB;
    }

    // Getteri i setteri
    public List<IstorijaStudiranjaDTO> getPredmeti() { 
        return predmeti; 
    }
    
    public void setPredmeti(List<IstorijaStudiranjaDTO> predmeti) { 
        this.predmeti = predmeti; 
    }

    public Double getProsecnaOcena() { 
        return prosecnaOcena; 
    }
    
    public void setProsecnaOcena(Double prosecnaOcena) { 
        this.prosecnaOcena = prosecnaOcena; 
    }

    public int getUkupnoESPB() { 
        return ukupnoESPB; 
    }
    
    public void setUkupnoESPB(int ukupnoESPB) { 
        this.ukupnoESPB = ukupnoESPB; 
    }
}