CREATE DATABASE IF NOT EXISTS lms_univerzitet CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE lms_univerzitet;

-- Tabela: role (Uloge korisnika)--
CREATE TABLE role (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    naziv VARCHAR(50) NOT NULL UNIQUE,
    opis VARCHAR(255),
    aktivna BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


-- Tabela: users (Korisnici - bazna tabela)--
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    ime VARCHAR(100) NOT NULL,
    prezime VARCHAR(100) NOT NULL,
    adresa TEXT NOT NULL,
    jmbg VARCHAR(13) NOT NULL UNIQUE,
    telefon VARCHAR(20),
    slika_url VARCHAR(500),
    deleted BOOLEAN DEFAULT FALSE,
    aktiviran BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


-- Tabela: user_role (Veza korisnik-uloga)--
CREATE TABLE user_role (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- Tabela: studenti--
CREATE TABLE studenti (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL UNIQUE,
    broj_indeksa VARCHAR(20) NOT NULL UNIQUE,
    godina_upisa INT NOT NULL,
    smer VARCHAR(100),
    prosecna_ocena DECIMAL(3,2) DEFAULT 0.00,
    ukupno_espb INT DEFAULT 0,
    datum_rodjenja DATE,
    mesto_rodjenja VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);


-- Tabela: profesori--
CREATE TABLE profesori (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL UNIQUE,
    zvanje VARCHAR(100) NOT NULL,
    katedra VARCHAR(100),
    naucna_oblast VARCHAR(255),
    biografija TEXT,
    kabinet VARCHAR(50),
    konzultacije VARCHAR(255),
    slika_path VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);


-- Tabela: osoblje--
CREATE TABLE osoblje (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL UNIQUE,
    pozicija VARCHAR(100) NOT NULL,
    odeljenje VARCHAR(100),
    broj_kancelarije VARCHAR(50),
    radno_vreme VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);


-- Tabela: univerzitet--
CREATE TABLE univerzitet (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    naziv VARCHAR(255) NOT NULL,
    opis TEXT,
    adresa TEXT NOT NULL,
    kontakt_email VARCHAR(255),
    kontakt_telefon VARCHAR(20),
    istorijat TEXT,
    rektor_id BIGINT,
    logo_path VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (rektor_id) REFERENCES profesori(id)
);


-- Tabela: fakulteti--
CREATE TABLE fakulteti (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    naziv VARCHAR(255) NOT NULL,
    opis TEXT,
    adresa TEXT NOT NULL,
    kontakt_email VARCHAR(255),
    kontakt_telefon VARCHAR(20),
    dekan_id BIGINT,
    univerzitet_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (dekan_id) REFERENCES profesori(id),
    FOREIGN KEY (univerzitet_id) REFERENCES univerzitet(id)
);


-- Tabela: studijski_programi--
CREATE TABLE studijski_programi (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    naziv VARCHAR(255) NOT NULL,
    opis TEXT,
    trajanje INT NOT NULL, -- u godinama
    espb INT NOT NULL,
    fakultet_id BIGINT NOT NULL,
    rukovodilac_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (fakultet_id) REFERENCES fakulteti(id),
    FOREIGN KEY (rukovodilac_id) REFERENCES profesori(id)
);


-- Tabela: predmeti--
CREATE TABLE predmeti (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    sifra VARCHAR(50) NOT NULL UNIQUE,
    naziv VARCHAR(255) NOT NULL,
    opis TEXT,
    espb INT NOT NULL,
    semestar INT NOT NULL,
    studijski_program_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (studijski_program_id) REFERENCES studijski_programi(id)
);


-- Tabela: nastavnik_predmet (Veza profesor-predmet)--
CREATE TABLE nastavnik_predmet (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    profesor_id BIGINT NOT NULL,
    predmet_id BIGINT NOT NULL,
    tip_angazmana VARCHAR(50) NOT NULL, -- 'profesor', 'asistent', 'saradnik'
    broj_casova INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (profesor_id) REFERENCES profesori(id) ON DELETE CASCADE,
    FOREIGN KEY (predmet_id) REFERENCES predmeti(id) ON DELETE CASCADE,
    UNIQUE KEY unique_nastavnik_predmet (profesor_id, predmet_id)
);


-- Tabela: silabusi--
CREATE TABLE silabusi (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    predmet_id BIGINT NOT NULL UNIQUE,
    sadrzaj TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (predmet_id) REFERENCES predmeti(id) ON DELETE CASCADE
);


-- Tabela: nastavni_materijali--
CREATE TABLE nastavni_materijali (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    silabus_id BIGINT NOT NULL,
    naslov VARCHAR(255) NOT NULL,
    opis TEXT,
    tip_materijala ENUM('KNJIGA', 'SLIDES', 'VIDEO', 'AUDIO', 'DOCUMENT', 'OTHER') NOT NULL,
    file_path VARCHAR(500) NOT NULL,
    velicina BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (silabus_id) REFERENCES silabusi(id) ON DELETE CASCADE
);


-- Tabela: ishodi--
CREATE TABLE ishodi (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    predmet_id BIGINT NOT NULL,
    opis TEXT NOT NULL,
    redni_broj INT NOT NULL,
    espb_bodovi INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (predmet_id) REFERENCES predmeti(id) ON DELETE CASCADE
);


-- Tabela: termini_nastave--
CREATE TABLE termini_nastave (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    predmet_id BIGINT NOT NULL,
    ishod_id BIGINT,
    datum_vreme DATETIME NOT NULL,
    tema VARCHAR(1000) NOT NULL,
    opis TEXT,
    ucionica VARCHAR(100),
    tip_termina ENUM('PREDAVANJE', 'VEZBE', 'LABORATORIJA', 'KONZULTACIJE') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (predmet_id) REFERENCES predmeti(id) ON DELETE CASCADE,
    FOREIGN KEY (ishod_id) REFERENCES ishodi(id)
);


-- Tabela: instrumenti_evaluacije--
CREATE TABLE instrumenti_evaluacije (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    predmet_id BIGINT NOT NULL,
    naziv VARCHAR(255) NOT NULL,
    tip ENUM('KOLOKVIJUM', 'TEST', 'PROJEKAT', 'ISPIT', 'SEMINARSKI') NOT NULL,
    datum DATE NOT NULL,
    maksimalni_bodovi INT NOT NULL,
    opis TEXT,
    rok_za_prijavu DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (predmet_id) REFERENCES predmeti(id) ON DELETE CASCADE
);


-- Tabela: upis_studenata--
CREATE TABLE upis_studenata (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    student_id BIGINT NOT NULL,
    osoblje_id BIGINT NOT NULL,
    skolska_godina VARCHAR(9) NOT NULL, -- '2023/2024'
    datum_upisa DATE NOT NULL,
    godina_studija INT NOT NULL,
    status ENUM('REDOVAN', 'VANREDAN', 'DOPISAN') NOT NULL,
    finansiranje ENUM('BUDZET', 'VANBUDZET') NOT NULL,
    napomene TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES studenti(id) ON DELETE CASCADE,
    FOREIGN KEY (osoblje_id) REFERENCES osoblje(id),
    UNIQUE KEY unique_student_godina (student_id, skolska_godina)
);


-- Tabela: potvrde--
CREATE TABLE potvrde (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    student_id BIGINT NOT NULL,
    osoblje_id BIGINT NOT NULL,
    tip_potvrde ENUM('STUDIRANJE', 'POLOZENI_ISPITI', 'STATUS', 'BUDZET', 'PROSEK') NOT NULL,
    datum_izdavanja DATE NOT NULL,
    datum_vazenja DATE,
    sadrzaj TEXT,
    status ENUM('IZDATA', 'U_OBRADI', 'ODBIJENA') NOT NULL DEFAULT 'U_OBRADI',
    pdf_path VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES studenti(id) ON DELETE CASCADE,
    FOREIGN KEY (osoblje_id) REFERENCES osoblje(id)
);


-- Tabela: obavestenja--
CREATE TABLE obavestenja (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    osoblje_id BIGINT NOT NULL,
    naslov VARCHAR(255) NOT NULL,
    sadrzaj TEXT NOT NULL,
    datum_kreiranja DATETIME NOT NULL,
    datum_isteka DATETIME,
    kategorija ENUM('OPSTE', 'ISPITI', 'NASTAVA', 'UPIS', 'BIBLIOTEKA') NOT NULL,
    hitno BOOLEAN DEFAULT FALSE,
    status ENUM('OBJAVLJENO', 'SKRIVENO', 'ARHIVIRANO') NOT NULL DEFAULT 'OBJAVLJENO',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (osoblje_id) REFERENCES osoblje(id)
);


-- Tabela: obavestenja_predmet (Obaveštenja za specifične predmete)--
CREATE TABLE obavestenja_predmet (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    predmet_id BIGINT NOT NULL,
    profesor_id BIGINT NOT NULL,
    naslov VARCHAR(255) NOT NULL,
    sadrzaj TEXT NOT NULL,
    datum_kreiranja DATETIME NOT NULL,
    datum_isteka DATETIME,
    hitno BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (predmet_id) REFERENCES predmeti(id) ON DELETE CASCADE,
    FOREIGN KEY (profesor_id) REFERENCES profesori(id)
);


-- Tabela: pohadjanje_predmeta--
CREATE TABLE pohadjanje_predmeta (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    student_id BIGINT NOT NULL,
    predmet_id BIGINT NOT NULL,
    skolska_godina VARCHAR(9) NOT NULL,
    status ENUM('UPISAN', 'POLOZIO', 'PAO', 'ODUSTAO') NOT NULL DEFAULT 'UPISAN',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES studenti(id) ON DELETE CASCADE,
    FOREIGN KEY (predmet_id) REFERENCES predmeti(id) ON DELETE CASCADE,
    UNIQUE KEY unique_student_predmet_godina (student_id, predmet_id, skolska_godina)
);


-- Tabela: prijave_ispita--
CREATE TABLE prijave_ispita (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    student_id BIGINT NOT NULL,
    predmet_id BIGINT NOT NULL,
    instrument_id BIGINT NOT NULL,
    datum_prijave DATETIME NOT NULL,
    status ENUM('PRIJAVLJEN', 'ODJAVLJEN', 'POLOZIO', 'PAO') NOT NULL DEFAULT 'PRIJAVLJEN',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES studenti(id) ON DELETE CASCADE,
    FOREIGN KEY (predmet_id) REFERENCES predmeti(id) ON DELETE CASCADE,
    FOREIGN KEY (instrument_id) REFERENCES instrumenti_evaluacije(id) ON DELETE CASCADE
);


-- Tabela: ocene--
CREATE TABLE ocene (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    student_id BIGINT NOT NULL,
    predmet_id BIGINT NOT NULL,
    instrument_id BIGINT NOT NULL,
    profesor_id BIGINT NOT NULL,
    ocena INT NOT NULL CHECK (ocena BETWEEN 5 AND 10),
    bodovi INT NOT NULL,
    datum_unosa DATE NOT NULL,
    napomene TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES studenti(id) ON DELETE CASCADE,
    FOREIGN KEY (predmet_id) REFERENCES predmeti(id) ON DELETE CASCADE,
    FOREIGN KEY (instrument_id) REFERENCES instrumenti_evaluacije(id) ON DELETE CASCADE,
    FOREIGN KEY (profesor_id) REFERENCES profesori(id)
);



-- Tabela: prestupi_pri_polaganju--
CREATE TABLE prestupi_pri_polaganju (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    student_id BIGINT NOT NULL,
    predmet_id BIGINT NOT NULL,
    instrument_id BIGINT NOT NULL,
    opis TEXT NOT NULL,
    datum DATE NOT NULL,
    sankcija ENUM('UPOZORENJE', 'ANNULIRAN_ISPIT', 'ZABRANA_POLAGANJA') NOT NULL,
    trajanje_sankcije INT, -- u danima
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES studenti(id) ON DELETE CASCADE,
    FOREIGN KEY (predmet_id) REFERENCES predmeti(id) ON DELETE CASCADE,
    FOREIGN KEY (instrument_id) REFERENCES instrumenti_evaluacije(id) ON DELETE CASCADE
);



-- Tabela: zavrsni_radovi--
CREATE TABLE zavrsni_radovi (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    student_id BIGINT NOT NULL UNIQUE,
    mentor_id BIGINT NOT NULL,
    naslov VARCHAR(500) NOT NULL,
    opis TEXT,
    status ENUM('U_IZRADI', 'PREDAT', 'ODBRANJEN', 'ODBIJEN') NOT NULL,
    datum_predaje DATE,
    ocena INT CHECK (ocena BETWEEN 6 AND 10),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES studenti(id) ON DELETE CASCADE,
    FOREIGN KEY (mentor_id) REFERENCES profesori(id)
);


-- Tabela: biblioteka_udzbenici--
CREATE TABLE biblioteka_udzbenici (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    isbn VARCHAR(20) UNIQUE,
    naslov VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    izdavac VARCHAR(255),
    godina_izdanja INT,
    izdanje VARCHAR(50),
    kolicina INT DEFAULT 0,
    dostupno INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);



-- Tabela: iznajmljivanje_udzbenika--
CREATE TABLE iznajmljivanje_udzbenika (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    student_id BIGINT NOT NULL,
    udzbenik_id BIGINT NOT NULL,
    osoblje_id BIGINT NOT NULL,
    datum_iznajmljivanja DATE NOT NULL,
    datum_vracanja DATE,
    rok_vracanja DATE NOT NULL,
    status ENUM('IZNAJMLJEN', 'VRACEN', 'KASNI') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES studenti(id) ON DELETE CASCADE,
    FOREIGN KEY (udzbenik_id) REFERENCES biblioteka_udzbenici(id),
    FOREIGN KEY (osoblje_id) REFERENCES osoblje(id)
);



-- Tabela: kancelarijski_inventar--
CREATE TABLE kancelarijski_inventar (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    naziv VARCHAR(255) NOT NULL,
    kategorija VARCHAR(100) NOT NULL,
    kolicina INT DEFAULT 0,
    min_kolicina INT DEFAULT 5,
    jedinica_mere VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);



-- Tabela: trebovanje_inventara--
CREATE TABLE trebovanje_inventara (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    osoblje_id BIGINT NOT NULL,
    inventar_id BIGINT NOT NULL,
    kolicina INT NOT NULL,
    datum_trebovanja DATE NOT NULL,
    status ENUM('NA_CEKANJU', 'ODOBRENO', 'ODBIJENO', 'ISPORUCENO') NOT NULL,
    napomena TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (osoblje_id) REFERENCES osoblje(id),
    FOREIGN KEY (inventar_id) REFERENCES kancelarijski_inventar(id)
);



-- Tabela: rasporedi_nastave--
CREATE TABLE rasporedi_nastave (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    naziv VARCHAR(255) NOT NULL,
    skolska_godina VARCHAR(9) NOT NULL,
    semestar ENUM('ZIMSKI', 'LETNJI') NOT NULL,
    status ENUM('U_IZRADI', 'OBJAVLJEN', 'ARHIVIRAN') NOT NULL,
    created_by BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (created_by) REFERENCES osoblje(id)
);



-- Tabela: rasporedi_ispita--
CREATE TABLE rasporedi_ispita (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    naziv VARCHAR(255) NOT NULL,
    skolska_godina VARCHAR(9) NOT NULL,
    ispitni_rok VARCHAR(50) NOT NULL,
    status ENUM('U_IZRADI', 'OBJAVLJEN', 'ARHIVIRAN') NOT NULL,
    created_by BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (created_by) REFERENCES osoblje(id)
);

-- Tabela: univerzitete--
CREATE TABLE univerziteti (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    naziv VARCHAR(255) NOT NULL,
    opis TEXT,
    adresa TEXT NOT NULL,
    kontakt_email VARCHAR(255),
    kontakt_telefon VARCHAR(20),
    istorijat TEXT,
    rektor_id BIGINT,
    logo_path VARCHAR(500),
    website_url VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (rektor_id) REFERENCES profesori(id)
);

-- Tabela: tipovi studija--
CREATE TABLE tipovi_studija (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    naziv VARCHAR(255) NOT NULL UNIQUE,
    opis TEXT,
    trajanje_godine INT,
    aktiv BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabela: studijski programi--
CREATE TABLE studijski_programi (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    naziv VARCHAR(255) NOT NULL,
    opis TEXT,
    trajanje INT NOT NULL,
    espb INT NOT NULL,
    stepen_studija VARCHAR(50),
    fakultet_id BIGINT NOT NULL,
    rukovodilac_id BIGINT,
    tip_studija_id BIGINT,
    aktivan BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (fakultet_id) REFERENCES fakulteti(id),
    FOREIGN KEY (rukovodilac_id) REFERENCES profesori(id),
    FOREIGN KEY (tip_studija_id) REFERENCES tipovi_studija(id)
);


-- Insert osnovnih uloga--
INSERT INTO role (naziv, opis) VALUES
('ADMIN', 'Administrator sistema'),
('PROFESOR', 'Nastavnik'),
('STUDENT', 'Student'),
('OSOBLJE', 'Osoblje studentske službe');

-- Insert test podataka za univerzitet--
INSERT INTO univerzitet (naziv, opis, adresa, kontakt_email, kontakt_telefon, istorijat) VALUES
('Univerzitet u Novom Sadu', 'Vodeći univerzitet u Srbiji sa bogatom istorijom i tradicijom', 'Studentski trg 1, Novi Sad', 'info@ns.ac.rs', '+381112185288', 'Osnovan 1808. godine kao Velika škola');

-- Insert test podataka za fakultet--
INSERT INTO fakulteti (naziv, opis, adresa, kontakt_email, kontakt_telefon) VALUES
('Sirmium, tehnički fakultet', 'Vodeći fakultet u oblasti elektrotehnike i računarstva', 'Bulevar Oslobođenja 9, Novi Sad', 'info@etf.ns.ac.rs', '+381112248');



