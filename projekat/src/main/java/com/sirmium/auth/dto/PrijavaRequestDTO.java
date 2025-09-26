package com.sirmium.auth.dto;

import jakarta.validation.constraints.NotBlank;

public class PrijavaRequestDTO {
    @NotBlank(message = "Email je obavezan")
    private String email;
    
    @NotBlank(message = "Lozinka je obavezna")
    private String lozinka;

    // Geteri i seteri ostaju isti
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getLozinka() { return lozinka; }
    public void setLozinka(String lozinka) { this.lozinka = lozinka; }
}