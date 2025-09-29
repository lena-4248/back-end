package com.sirmium.osoblje.dto;

import java.time.LocalDate;

public class IznajmljivanjeRequestDTO {
    private Long studentId;
    private Long udzbenikId;
    private LocalDate rokVracanja;

    // Konstruktori
    public IznajmljivanjeRequestDTO() {}
    
    public IznajmljivanjeRequestDTO(Long studentId, Long udzbenikId, LocalDate rokVracanja) {
        this.studentId = studentId;
        this.udzbenikId = udzbenikId;
        this.rokVracanja = rokVracanja;
    }

    // Geteri i seteri
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    
    public Long getUdzbenikId() { return udzbenikId; }
    public void setUdzbenikId(Long udzbenikId) { this.udzbenikId = udzbenikId; }
    
    public LocalDate getRokVracanja() { return rokVracanja; }
    public void setRokVracanja(LocalDate rokVracanja) { this.rokVracanja = rokVracanja; }
}