package com.sirmium.student.dto;

import java.time.LocalDateTime;

public class StudentPredmetDTO {
    private Long id;
    private Long predmetId;
    private String nazivPredmeta;
    private String sifraPredmeta;
    private Integer espb;
    private boolean trenutno;
    private boolean polozen;
    private Integer ocena;
    private Integer bodovi;
    private Integer brojPolaganja;
    private LocalDateTime datumUpisa;

    public StudentPredmetDTO() {}

    public StudentPredmetDTO(Long id, String nazivPredmeta, String sifraPredmeta, Integer espb, 
                            boolean trenutno, boolean polozen, Integer ocena, Integer bodovi, 
                            Integer brojPolaganja, LocalDateTime datumUpisa) {
        this.id = id;
        this.nazivPredmeta = nazivPredmeta;
        this.sifraPredmeta = sifraPredmeta;
        this.espb = espb;
        this.trenutno = trenutno;
        this.polozen = polozen;
        this.ocena = ocena;
        this.bodovi = bodovi;
        this.brojPolaganja = brojPolaganja;
        this.datumUpisa = datumUpisa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(Long predmetId) {
        this.predmetId = predmetId;
    }

    public String getNazivPredmeta() {
        return nazivPredmeta;
    }

    public void setNazivPredmeta(String nazivPredmeta) {
        this.nazivPredmeta = nazivPredmeta;
    }

    public String getSifraPredmeta() {
        return sifraPredmeta;
    }

    public void setSifraPredmeta(String sifraPredmeta) {
        this.sifraPredmeta = sifraPredmeta;
    }

    public Integer getEspb() {
        return espb;
    }

    public void setEspb(Integer espb) {
        this.espb = espb;
    }

    public boolean isTrenutno() {
        return trenutno;
    }

    public void setTrenutno(boolean trenutno) {
        this.trenutno = trenutno;
    }

    public boolean isPolozen() {
        return polozen;
    }

    public void setPolozen(boolean polozen) {
        this.polozen = polozen;
    }

    public Integer getOcena() {
        return ocena;
    }

    public void setOcena(Integer ocena) {
        this.ocena = ocena;
    }

    public Integer getBodovi() {
        return bodovi;
    }

    public void setBodovi(Integer bodovi) {
        this.bodovi = bodovi;
    }

    public Integer getBrojPolaganja() {
        return brojPolaganja;
    }

    public void setBrojPolaganja(Integer brojPolaganja) {
        this.brojPolaganja = brojPolaganja;
    }

    public LocalDateTime getDatumUpisa() {
        return datumUpisa;
    }

    public void setDatumUpisa(LocalDateTime datumUpisa) {
        this.datumUpisa = datumUpisa;
    }
}