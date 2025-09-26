package com.sirmium.osoblje.model;

import com.sirmium.user.model.User;
import jakarta.persistence.*;

@Entity
@Table(name = "osoblje")
public class Osoblje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
    
    @Column(nullable = false)
    private String pozicija; // "Referent", "Administrator", "Bibliotekar"
    
    @Column
    private String odeljenje;
    
    @Column(name = "broj_kancelarije")
    private String brojKancelarije;
    
    @Column(name = "radno_vreme")
    private String radnoVreme;
    
    // Konstruktori
    public Osoblje() {}
    
    public Osoblje(User user, String pozicija) {
        this.user = user;
        this.pozicija = pozicija;
    }
    
    // Geteri i seteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public String getPozicija() { return pozicija; }
    public void setPozicija(String pozicija) { this.pozicija = pozicija; }
    
    public String getOdeljenje() { return odeljenje; }
    public void setOdeljenje(String odeljenje) { this.odeljenje = odeljenje; }
    
    public String getBrojKancelarije() { return brojKancelarije; }
    public void setBrojKancelarije(String brojKancelarije) { this.brojKancelarije = brojKancelarije; }
    
    public String getRadnoVreme() { return radnoVreme; }
    public void setRadnoVreme(String radnoVreme) { this.radnoVreme = radnoVreme; }
    
    // Helper metode
    public String getIme() { return user != null ? user.getIme() : null; }
    public String getPrezime() { return user != null ? user.getPrezime() : null; }
    public String getEmail() { return user != null ? user.getEmail() : null; }
}