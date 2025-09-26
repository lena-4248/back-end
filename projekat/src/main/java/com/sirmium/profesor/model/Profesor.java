package com.sirmium.profesor.model;

import com.sirmium.user.model.User;
import jakarta.persistence.*;

@Entity
@Table(name = "profesori")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(length = 3000)
    private String biografija;
    
    @Column(name = "slika_path")
    private String slikaPath;
    
    @Column(nullable = false)
    private String zvanje; // docent, vanredni profesor, redovni profesor
    
    @Column
    private String katedra;
    
    @Column(name = "naucna_oblast")
    private String naucnaOblast;
    
    @Column
    private String kabinet;
    
    @Column
    private String konzultacije;

    // Konstruktori
    public Profesor() {}

    public Profesor(User user, String zvanje) {
        this.user = user;
        this.zvanje = zvanje;
    }

    // Geteri i seteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getBiografija() { return biografija; }
    public void setBiografija(String biografija) { this.biografija = biografija; }

    public String getSlikaPath() { return slikaPath; }
    public void setSlikaPath(String slikaPath) { this.slikaPath = slikaPath; }

    public String getZvanje() { return zvanje; }
    public void setZvanje(String zvanje) { this.zvanje = zvanje; }

    public String getKatedra() { return katedra; }
    public void setKatedra(String katedra) { this.katedra = katedra; }

    public String getNaucnaOblast() { return naucnaOblast; }
    public void setNaucnaOblast(String naucnaOblast) { this.naucnaOblast = naucnaOblast; }

    public String getKabinet() { return kabinet; }
    public void setKabinet(String kabinet) { this.kabinet = kabinet; }

    public String getKonzultacije() { return konzultacije; }
    public void setKonzultacije(String konzultacije) { this.konzultacije = konzultacije; }

    // Helper metode
    public String getIme() { return user != null ? user.getIme() : null; }
    public String getPrezime() { return user != null ? user.getPrezime() : null; }
    public String getEmail() { return user != null ? user.getEmail() : null; }
}