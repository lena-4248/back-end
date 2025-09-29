package com.sirmium.studijskiProgram.dto;

public class StudijskiProgramDTO {
    private Long id;
    private String naziv;
    private String opis;
    private Integer trajanje;
    private Integer espb;
    private String stepenStudija;
    private Long fakultetId;
    private String fakultetNaziv;
    private Long rukovodilacId;
    private String rukovodilacIme;
    private String rukovodilacPrezime;
    private Long tipStudijaId;
    private String tipStudijaNaziv;
    private Boolean aktivan;

    // Konstruktori
    public StudijskiProgramDTO() {}
    
    public StudijskiProgramDTO(Long id, String naziv, Integer trajanje, Integer espb) {
        this.id = id;
        this.naziv = naziv;
        this.trajanje = trajanje;
        this.espb = espb;
        this.aktivan = true;
    }

    // Geteri i seteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }
    
    public String getOpis() { return opis; }
    public void setOpis(String opis) { this.opis = opis; }
    
    public Integer getTrajanje() { return trajanje; }
    public void setTrajanje(Integer trajanje) { this.trajanje = trajanje; }
    
    public Integer getEspb() { return espb; }
    public void setEspb(Integer espb) { this.espb = espb; }
    
    public String getStepenStudija() { return stepenStudija; }
    public void setStepenStudija(String stepenStudija) { this.stepenStudija = stepenStudija; }
    
    public Long getFakultetId() { return fakultetId; }
    public void setFakultetId(Long fakultetId) { this.fakultetId = fakultetId; }
    
    public String getFakultetNaziv() { return fakultetNaziv; }
    public void setFakultetNaziv(String fakultetNaziv) { this.fakultetNaziv = fakultetNaziv; }
    
    public Long getRukovodilacId() { return rukovodilacId; }
    public void setRukovodilacId(Long rukovodilacId) { this.rukovodilacId = rukovodilacId; }
    
    public String getRukovodilacIme() { return rukovodilacIme; }
    public void setRukovodilacIme(String rukovodilacIme) { this.rukovodilacIme = rukovodilacIme; }
    
    public String getRukovodilacPrezime() { return rukovodilacPrezime; }
    public void setRukovodilacPrezime(String rukovodilacPrezime) { this.rukovodilacPrezime = rukovodilacPrezime; }
    
    public Long getTipStudijaId() { return tipStudijaId; }
    public void setTipStudijaId(Long tipStudijaId) { this.tipStudijaId = tipStudijaId; }
    
    public String getTipStudijaNaziv() { return tipStudijaNaziv; }
    public void setTipStudijaNaziv(String tipStudijaNaziv) { this.tipStudijaNaziv = tipStudijaNaziv; }
    
    public Boolean getAktivan() { return aktivan; }
    public void setAktivan(Boolean aktivan) { this.aktivan = aktivan; }
}