package com.sirmium.profesor.dto;

public class ProfesorRequestDTO {
    private Long userId;
    private String zvanje;
    private String katedra;
    private String naucnaOblast;
    private String biografija;
    private String kabinet;
    private String konsultacije;

    public ProfesorRequestDTO() {}
    
    public ProfesorRequestDTO(Long userId, String zvanje, String katedra, String naucnaOblast, String biografija,
			String kabinet, String konsultacije) {
		super();
		this.userId = userId;
		this.zvanje = zvanje;
		this.katedra = katedra;
		this.naucnaOblast = naucnaOblast;
		this.biografija = biografija;
		this.kabinet = kabinet;
		this.konsultacije = konsultacije;
	}
	// Geteri i seteri
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getZvanje() { return zvanje; }
    public void setZvanje(String zvanje) { this.zvanje = zvanje; }

    public String getKatedra() { return katedra; }
    public void setKatedra(String katedra) { this.katedra = katedra; }

    public String getNaucnaOblast() { return naucnaOblast; }
    public void setNaucnaOblast(String naucnaOblast) { this.naucnaOblast = naucnaOblast; }

    public String getBiografija() { return biografija; }
    public void setBiografija(String biografija) { this.biografija = biografija; }

    public String getKabinet() { return kabinet; }
    public void setKabinet(String kabinet) { this.kabinet = kabinet; }

    public String getKonsultacije() { return konsultacije; }
    public void setKonsultacije(String konsultacije) { this.konsultacije = konsultacije; }
}