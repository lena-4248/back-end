package com.sirmium.predmet.dto;

import java.time.LocalDateTime;

public class PromenaTipaEvaluacijeDTO {
    private LocalDateTime vremePocetka;
    private Long predmetId;
    private Long noviTipId;

    public PromenaTipaEvaluacijeDTO() {}

    public PromenaTipaEvaluacijeDTO(LocalDateTime vremePocetka, Long predmetId, Long noviTipId) {
        this.vremePocetka = vremePocetka;
        this.predmetId = predmetId;
        this.noviTipId = noviTipId;
    }

    // Getteri i setteri
    public LocalDateTime getVremePocetka() {
        return vremePocetka;
    }

    public void setVremePocetka(LocalDateTime vremePocetka) {
        this.vremePocetka = vremePocetka;
    }

    public Long getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(Long predmetId) {
        this.predmetId = predmetId;
    }

    public Long getNoviTipId() {
        return noviTipId;
    }

    public void setNoviTipId(Long noviTipId) {
        this.noviTipId = noviTipId;
    }
}