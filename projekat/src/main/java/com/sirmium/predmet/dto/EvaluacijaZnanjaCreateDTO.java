package com.sirmium.predmet.dto;

import java.time.LocalDateTime;

public class EvaluacijaZnanjaCreateDTO {
    private Long predmetId;
    private Long tipEvaluacijeId;
    private LocalDateTime vremePocetka;

    public EvaluacijaZnanjaCreateDTO() {}

    public EvaluacijaZnanjaCreateDTO(Long predmetId, Long tipEvaluacijeId, LocalDateTime vremePocetka) {
        this.predmetId = predmetId;
        this.tipEvaluacijeId = tipEvaluacijeId;
        this.vremePocetka = vremePocetka;
    }

    // Getteri i setteri
    public Long getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(Long predmetId) {
        this.predmetId = predmetId;
    }

    public Long getTipEvaluacijeId() {
        return tipEvaluacijeId;
    }

    public void setTipEvaluacijeId(Long tipEvaluacijeId) {
        this.tipEvaluacijeId = tipEvaluacijeId;
    }

    public LocalDateTime getVremePocetka() {
        return vremePocetka;
    }

    public void setVremePocetka(LocalDateTime vremePocetka) {
        this.vremePocetka = vremePocetka;
    }
}