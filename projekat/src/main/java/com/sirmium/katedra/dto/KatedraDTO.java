package com.sirmium.katedra.dto;

import java.util.List;

import com.sirmium.departman.dto.DepartmanDTO;
import com.sirmium.predmet.dto.PredmetDTO;
import com.sirmium.profesor.dto.ProfesorDTO;
import com.sirmium.tipstudija.dto.TipStudijaDTO;

public class KatedraDTO {

    private Long id;
    private String naziv;
    private List<PredmetDTO> predmeti;
    private String opis;
    private DepartmanDTO departman;
    private List<TipStudijaDTO> tipoviStudija;
    private ProfesorDTO sefKatedre;
    private boolean deleted;

    public KatedraDTO() {}

    public KatedraDTO(Long id, String naziv, List<PredmetDTO> predmeti, String opis,
                      DepartmanDTO departman, List<TipStudijaDTO> tipoviStudija,
                      ProfesorDTO sefKatedre, boolean deleted) {
        this.id = id;
        this.naziv = naziv;
        this.predmeti = predmeti;
        this.opis = opis;
        this.departman = departman;
        this.tipoviStudija = tipoviStudija;
        this.sefKatedre = sefKatedre;
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<PredmetDTO> getPredmeti() {
        return predmeti;
    }

    public void setPredmeti(List<PredmetDTO> predmeti) {
        this.predmeti = predmeti;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public DepartmanDTO getDepartman() {
        return departman;
    }

    public void setDepartman(DepartmanDTO departman) {
        this.departman = departman;
    }

    public List<TipStudijaDTO> getTipoviStudija() {
        return tipoviStudija;
    }

    public void setTipoviStudija(List<TipStudijaDTO> tipoviStudija) {
        this.tipoviStudija = tipoviStudija;
    }

    public ProfesorDTO getSefKatedre() {
        return sefKatedre;
    }

    public void setSefKatedre(ProfesorDTO sefKatedre) {
        this.sefKatedre = sefKatedre;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}