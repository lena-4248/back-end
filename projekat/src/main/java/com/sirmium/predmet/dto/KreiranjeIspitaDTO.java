package com.sirmium.predmet.dto;

import java.time.LocalDateTime;

public class KreiranjeIspitaDTO {
    private Long predmetId;
    private LocalDateTime datumIspita;

    public KreiranjeIspitaDTO() {}

    public KreiranjeIspitaDTO(Long predmetId, LocalDateTime datumIspita) {
        this.predmetId = predmetId;
        this.datumIspita = datumIspita;
    }

    // Getteri i setteri
    public Long getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(Long predmetId) {
        this.predmetId = predmetId;
    }

    public LocalDateTime getDatumIspita() {
        return datumIspita;
    }

    public void setDatumIspita(LocalDateTime datumIspita) {
        this.datumIspita = datumIspita;
    }
}