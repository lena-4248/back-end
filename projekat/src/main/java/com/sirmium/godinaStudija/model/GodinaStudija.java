
package com.sirmium.godinaStudija.model; 

import java.util.ArrayList;
import java.util.List;

import com.sirmium.predmet.model.Predmet; 
import com.sirmium.studijskiProgram.model.StudijskiProgram; 

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class GodinaStudija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int godina;

    @Column(nullable = false)
    private boolean deleted = false;

    @ManyToOne
    @JoinColumn(name = "studijski_program_id")
    private StudijskiProgram studijskiProgram;

    @OneToMany(mappedBy = "godinaStudija")
    private List<Predmet> predmeti = new ArrayList<Predmet>();

    // Konstruktori
    public GodinaStudija() {
        super();
    }

    public GodinaStudija(Long id, int godina, StudijskiProgram studijskiProgram, List<Predmet> predmeti) {
        super();
        this.id = id;
        this.godina = godina;
        this.studijskiProgram = studijskiProgram;
        this.predmeti = predmeti;
    }

    // Getteri i setteri
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGodina() {
        return godina;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public StudijskiProgram getStudijskiProgram() {
        return studijskiProgram;
    }

    public void setStudijskiProgram(StudijskiProgram studijskiProgram) {
        this.studijskiProgram = studijskiProgram;
    }

    public List<Predmet> getPredmeti() {
        return predmeti;
    }

    public void setPredmeti(List<Predmet> predmeti) {
        this.predmeti = predmeti;
    }
}