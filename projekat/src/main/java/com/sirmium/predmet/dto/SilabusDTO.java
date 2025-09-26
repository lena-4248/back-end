package com.sirmium.predmet.dto;

public class SilabusDTO {
    private Long id;
    private String sadrzaj;
    private Long predmetId;

    public SilabusDTO() {}

    public SilabusDTO(Long id, String sadrzaj, Long predmetId) {
        this.id = id;
        this.sadrzaj = sadrzaj;
        this.predmetId = predmetId;
    }

    // Getteri i setteri
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public Long getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(Long predmetId) {
        this.predmetId = predmetId;
    }
}