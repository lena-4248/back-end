package com.sirmium.predmet.dto;

public class UpdateEvaluacijaDTO {
    private Long tipEvaluacijeId;
    private Integer brojBodova;

    public UpdateEvaluacijaDTO() {}

    public UpdateEvaluacijaDTO(Long tipEvaluacijeId, Integer brojBodova) {
        this.tipEvaluacijeId = tipEvaluacijeId;
        this.brojBodova = brojBodova;
    }

    // Getteri i setteri
    public Long getTipEvaluacijeId() {
        return tipEvaluacijeId;
    }

    public void setTipEvaluacijeId(Long tipEvaluacijeId) {
        this.tipEvaluacijeId = tipEvaluacijeId;
    }

    public Integer getBrojBodova() {
        return brojBodova;
    }

    public void setBrojBodova(Integer brojBodova) {
        this.brojBodova = brojBodova;
    }
}