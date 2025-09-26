package com.sirmium.godinaStudija.dto; 

public class GodinaStudijaCreateUpdateDTO {
    private int godina;
    private Long studijskiProgramId;

    // Getteri i setteri
    public int getGodina() {
        return godina;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    public Long getStudijskiProgramId() {
        return studijskiProgramId;
    }

    public void setStudijskiProgramId(Long studijskiProgramId) {
        this.studijskiProgramId = studijskiProgramId;
    }
}