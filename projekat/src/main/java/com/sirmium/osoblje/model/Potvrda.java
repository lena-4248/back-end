package com.sirmium.osoblje.model;

import com.sirmium.student.model.Student;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "potvrde")
public class Potvrda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    
    @ManyToOne
    @JoinColumn(name = "osoblje_id", nullable = false)
    private Osoblje osoblje;
    
    @Column(nullable = false)
    private String tipPotvrde; // "STUDIRANJE", "POLOŽENI_ISPITI", "STATUS"
    
    @Column(name = "datum_izdavanja", nullable = false)
    private LocalDate datumIzdavanja;
    
    @Column(name = "datum_vazenja")
    private LocalDate datumVazenja;
    
    @Column(length = 3000)
    private String sadrzaj;
    
    @Column
    private String status; // "IZDATA", "U_OBRADI", "ODBIJENA"
    
    @Column(name = "pdf_path")
    private String pdfPath;

    // Geteri i seteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    
    public Osoblje getOsoblje() { return osoblje; }
    public void setOsoblje(Osoblje osoblje) { this.osoblje = osoblje; }
    
    public String getTipPotvrde() { return tipPotvrde; }
    public void setTipPotvrde(String tipPotvrde) { this.tipPotvrde = tipPotvrde; }
    
    public LocalDate getDatumIzdavanja() { return datumIzdavanja; }
    public void setDatumIzdavanja(LocalDate datumIzdavanja) { this.datumIzdavanja = datumIzdavanja; }
    
    public LocalDate getDatumVazenja() { return datumVazenja; }
    public void setDatumVazenja(LocalDate datumVazenja) { this.datumVazenja = datumVazenja; }
    
    public String getSadrzaj() { return sadrzaj; }
    public void setSadrzaj(String sadrzaj) { this.sadrzaj = sadrzaj; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getPdfPath() { return pdfPath; }
    public void setPdfPath(String pdfPath) { this.pdfPath = pdfPath; }
}