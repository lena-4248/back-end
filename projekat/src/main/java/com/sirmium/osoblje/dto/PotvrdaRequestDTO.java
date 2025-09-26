package com.sirmium.osoblje.dto;

import java.time.LocalDate;

public class PotvrdaRequestDTO {
    private Long studentId;
    private String tipPotvrde;
    private LocalDate datumVazenja;
    private String sadrzaj;
    
    // Konstruktori
    public PotvrdaRequestDTO() {}
    
    public PotvrdaRequestDTO(Long studentId, String tipPotvrde) {
        this.studentId = studentId;
        this.tipPotvrde = tipPotvrde;
    }
    
    // Geteri i seteri
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    
    public String getTipPotvrde() { return tipPotvrde; }
    public void setTipPotvrde(String tipPotvrde) { this.tipPotvrde = tipPotvrde; }
    
    public LocalDate getDatumVazenja() { return datumVazenja; }
    public void setDatumVazenja(LocalDate datumVazenja) { this.datumVazenja = datumVazenja; }
    
    public String getSadrzaj() { return sadrzaj; }
    public void setSadrzaj(String sadrzaj) { this.sadrzaj = sadrzaj; }
}