package com.sirmium.osoblje.dto;

public class OsobljeDTO {
    private Long id;
    private String ime;
    private String prezime;
    private String email;
    private String pozicija;
    private String odeljenje;
    private String brojKancelarije;
    private String radnoVreme;
    
    // Konstruktori
    public OsobljeDTO() {}
    
    public OsobljeDTO(Long id, String ime, String prezime, String email, String pozicija) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.pozicija = pozicija;
    }
    
    // Geteri i seteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }
    
    public String getPrezime() { return prezime; }
    public void setPrezime(String prezime) { this.prezime = prezime; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPozicija() { return pozicija; }
    public void setPozicija(String pozicija) { this.pozicija = pozicija; }
    
    public String getOdeljenje() { return odeljenje; }
    public void setOdeljenje(String odeljenje) { this.odeljenje = odeljenje; }
    
    public String getBrojKancelarije() { return brojKancelarije; }
    public void setBrojKancelarije(String brojKancelarije) { this.brojKancelarije = brojKancelarije; }
    
    public String getRadnoVreme() { return radnoVreme; }
    public void setRadnoVreme(String radnoVreme) { this.radnoVreme = radnoVreme; }
}