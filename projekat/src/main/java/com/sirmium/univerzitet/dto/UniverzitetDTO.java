package com.sirmium.univerzitet.dto;

public class UniverzitetDTO {
    private Long id;
    private String naziv;
    private String opis;
    private String adresa;
    private String kontaktEmail;
    private String kontaktTelefon;
    private String istorijat;
    private Long rektorId;
    private String rektorIme;
    private String rektorPrezime;
    private String logoPath;
    private String websiteUrl;

    // Konstruktori
    public UniverzitetDTO() {}
    
    public UniverzitetDTO(Long id, String naziv, String adresa) {
        this.id = id;
        this.naziv = naziv;
        this.adresa = adresa;
    }

    // Geteri i seteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }
    
    public String getOpis() { return opis; }
    public void setOpis(String opis) { this.opis = opis; }
    
    public String getAdresa() { return adresa; }
    public void setAdresa(String adresa) { this.adresa = adresa; }
    
    public String getKontaktEmail() { return kontaktEmail; }
    public void setKontaktEmail(String kontaktEmail) { this.kontaktEmail = kontaktEmail; }
    
    public String getKontaktTelefon() { return kontaktTelefon; }
    public void setKontaktTelefon(String kontaktTelefon) { this.kontaktTelefon = kontaktTelefon; }
    
    public String getIstorijat() { return istorijat; }
    public void setIstorijat(String istorijat) { this.istorijat = istorijat; }
    
    public Long getRektorId() { return rektorId; }
    public void setRektorId(Long rektorId) { this.rektorId = rektorId; }
    
    public String getRektorIme() { return rektorIme; }
    public void setRektorIme(String rektorIme) { this.rektorIme = rektorIme; }
    
    public String getRektorPrezime() { return rektorPrezime; }
    public void setRektorPrezime(String rektorPrezime) { this.rektorPrezime = rektorPrezime; }
    
    public String getLogoPath() { return logoPath; }
    public void setLogoPath(String logoPath) { this.logoPath = logoPath; }
    
    public String getWebsiteUrl() { return websiteUrl; }
    public void setWebsiteUrl(String websiteUrl) { this.websiteUrl = websiteUrl; }
}