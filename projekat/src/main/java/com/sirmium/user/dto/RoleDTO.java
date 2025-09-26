package com.sirmium.user.dto;

public class RoleDTO {
    private Long id;
    private String naziv;
    private boolean aktivna;

    public RoleDTO() {}

    public RoleDTO(Long id, String naziv, boolean aktivna) {
        this.id = id;
        this.naziv = naziv;
        this.aktivna = aktivna;
    }

    
    public Long getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public boolean isAktivna() {
        return aktivna;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setAktivna(boolean aktivna) {
        this.aktivna = aktivna;
    }
}