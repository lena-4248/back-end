package com.sirmium.auth.dto;

public class PrijavaResponseDTO {
    private String jwtToken;
    private String tipTokena = "Bearer";
    private Long korisnikId;
    private String email;
    private String ime;
    private String prezime;
    private String uloga;

    // Konstruktori
    public PrijavaResponseDTO() {}
    
    public PrijavaResponseDTO(String jwtToken, Long korisnikId, String email, 
                            String ime, String prezime, String uloga) {
        this.jwtToken = jwtToken;
        this.korisnikId = korisnikId;
        this.email = email;
        this.ime = ime;
        this.prezime = prezime;
        this.uloga = uloga;
    }

    // Geteri i seteri
    public String getJwtToken() { return jwtToken; }
    public void setJwtToken(String jwtToken) { this.jwtToken = jwtToken; }
    
    public String getTipTokena() { return tipTokena; }
    public void setTipTokena(String tipTokena) { this.tipTokena = tipTokena; }
    
    public Long getKorisnikId() { return korisnikId; }
    public void setKorisnikId(Long korisnikId) { this.korisnikId = korisnikId; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }
    
    public String getPrezime() { return prezime; }
    public void setPrezime(String prezime) { this.prezime = prezime; }
    
    public String getUloga() { return uloga; }
    public void setUloga(String uloga) { this.uloga = uloga; }
}