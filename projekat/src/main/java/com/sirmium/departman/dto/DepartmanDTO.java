package com.sirmium.departman.dto;

public class DepartmanDTO {
    private Long id;
    private String naziv;
    private String opis;
    private boolean deleted;
    private Long fakultetId;
    private String fakultetNaziv;
    private Long sefDepartmanaId;
    private String sefDepartmanaIme;

    // Getteri i setteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }
    public String getOpis() { return opis; }
    public void setOpis(String opis) { this.opis = opis; }
    public boolean isDeleted() { return deleted; }
    public void setDeleted(boolean deleted) { this.deleted = deleted; }
    public Long getFakultetId() { return fakultetId; }
    public void setFakultetId(Long fakultetId) { this.fakultetId = fakultetId; }
    public String getFakultetNaziv() { return fakultetNaziv; }
    public void setFakultetNaziv(String fakultetNaziv) { this.fakultetNaziv = fakultetNaziv; }
    public Long getSefDepartmanaId() { return sefDepartmanaId; }
    public void setSefDepartmanaId(Long sefDepartmanaId) { this.sefDepartmanaId = sefDepartmanaId; }
    public String getSefDepartmanaIme() { return sefDepartmanaIme; }
    public void setSefDepartmanaIme(String sefDepartmanaIme) { this.sefDepartmanaIme = sefDepartmanaIme; }
}