package com.sirmium.osoblje.dto;

import java.time.LocalDate;

public class UpisStudentaDTO {
    private Long studentId;
    private String skolskaGodina;
    private int godinaStudija;
    private String status;
    private String budzetVanbudzet;
    private String napomene;
    private LocalDate datumUpisa;
    
    // Konstruktori
    public UpisStudentaDTO() {}
    
    public UpisStudentaDTO(Long studentId, String skolskaGodina, int godinaStudija, String status) {
        this.studentId = studentId;
        this.skolskaGodina = skolskaGodina;
        this.godinaStudija = godinaStudija;
        this.status = status;
    }
    
    // Geteri i seteri
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    
    public String getSkolskaGodina() { return skolskaGodina; }
    public void setSkolskaGodina(String skolskaGodina) { this.skolskaGodina = skolskaGodina; }
    
    public int getGodinaStudija() { return godinaStudija; }
    public void setGodinaStudija(int godinaStudija) { this.godinaStudija = godinaStudija; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getBudzetVanbudzet() { return budzetVanbudzet; }
    public void setBudzetVanbudzet(String budzetVanbudzet) { this.budzetVanbudzet = budzetVanbudzet; }
    
    public String getNapomene() { return napomene; }
    public void setNapomene(String napomene) { this.napomene = napomene; }
    
    public LocalDate getDatumUpisa() { return datumUpisa; }
    public void setDatumUpisa(LocalDate datumUpisa) { this.datumUpisa = datumUpisa; }
}