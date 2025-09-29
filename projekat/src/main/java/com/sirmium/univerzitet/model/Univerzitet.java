package com.sirmium.univerzitet.model;

import com.sirmium.profesor.model.Profesor;
import jakarta.persistence.*;

@Entity
@Table(name = "univerziteti")
public class Univerzitet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String naziv;
    
    @Column(columnDefinition = "TEXT")
    private String opis;
    
    @Column(nullable = false)
    private String adresa;
    
    @Column(name = "kontakt_email")
    private String kontaktEmail;
    
    @Column(name = "kontakt_telefon")
    private String kontaktTelefon;
    
    @Column(columnDefinition = "TEXT")
    private String istorijat;
    
    @ManyToOne
    @JoinColumn(name = "rektor_id")
    private Profesor rektor;
    
    @Column(name = "logo_path")
    private String logoPath;
    
    @Column(name = "website_url")
    private String websiteUrl;

    // Konstruktori
    public Univerzitet() {}
    
    public Univerzitet(String naziv, String adresa) {
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
    
    public Profesor getRektor() { return rektor; }
    public void setRektor(Profesor rektor) { this.rektor = rektor; }
    
    public String getLogoPath() { return logoPath; }
    public void setLogoPath(String logoPath) { this.logoPath = logoPath; }
    
    public String getWebsiteUrl() { return websiteUrl; }
    public void setWebsiteUrl(String websiteUrl) { this.websiteUrl = websiteUrl; }
}