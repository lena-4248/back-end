package com.sirmium.user.service;

import com.sirmium.auth.dto.RegistracijaRequestDTO;
import com.sirmium.user.dto.*;
import com.sirmium.user.dto.Registrovani.ProfilDTO;
import com.sirmium.user.model.User;
import com.sirmium.user.model.Role;
import com.sirmium.user.repository.UserRepository;
import com.sirmium.user.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, 
                     RoleRepository roleRepository,
                     PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Registracija novog korisnika
    public UserDTO registrujKorisnika(RegistracijaRequestDTO registracijaDTO) {
        if (userRepository.existsByEmail(registracijaDTO.getEmail())) {
            throw new RuntimeException("Email već postoji");
        }

        if (userRepository.existsByJmbg(registracijaDTO.getJmbg())) {
            throw new RuntimeException("JMBG već postoji");
        }

        Set<Role> uloge = roleRepository.findByNazivIn(
            List.of(registracijaDTO.getTipKorisnika())
        ).stream().collect(Collectors.toSet());

        User korisnik = new User(
            registracijaDTO.getEmail(),
            passwordEncoder.encode(registracijaDTO.getLozinka()),
            registracijaDTO.getIme(),
            registracijaDTO.getPrezime(),
            registracijaDTO.getAdresa(),
            registracijaDTO.getJmbg(),
            registracijaDTO.getTelefon(),
            registracijaDTO.getSlikaUrl(),
            uloge
        );

        User sacuvanKorisnik = userRepository.save(korisnik);
        return mapToUserDTO(sacuvanKorisnik);
    }

    // Ažuriranje profila
    public UserDTO azurirajProfil(Long korisnikId, ProfilDTO profilDTO) {
        User korisnik = userRepository.findById(korisnikId)
            .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen"));

        korisnik.setIme(profilDTO.getIme());
        korisnik.setPrezime(profilDTO.getPrezime());
        korisnik.setAdresa(profilDTO.getAdresa());
        korisnik.setTelefon(profilDTO.getTelefon());
        korisnik.setSlikaUrl(profilDTO.getSlikaUrl());

        return mapToUserDTO(userRepository.save(korisnik));
    }

    // Resetovanje lozinke
    public void resetujLozinku(String email, String novaLozinka) {
        User korisnik = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen"));
        
        korisnik.setPassword(passwordEncoder.encode(novaLozinka));
        userRepository.save(korisnik);
    }

    // Deaktivacija korisnika
    public void deaktivirajKorisnika(Long korisnikId) {
        userRepository.deaktivirajKorisnika(korisnikId);
    }

    // Aktivacija naloga (posle email verifikacije)
    public void aktivirajNalog(Long korisnikId) {
        userRepository.aktivirajNalog(korisnikId);
    }

    // Dodela uloge korisniku
    public UserDTO dodeliUlogu(Long korisnikId, String nazivUloge) {
        User korisnik = userRepository.findById(korisnikId)
            .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen"));
        
        Role uloga = roleRepository.findByNaziv(nazivUloge)
            .orElseThrow(() -> new RuntimeException("Uloga nije pronađena"));
        
        korisnik.getRoles().add(uloga);
        return mapToUserDTO(userRepository.save(korisnik));
    }

    // Dobavljanje korisnika po ID-u
    public UserDTO dobaviKorisnikaPoId(Long id) {
        User korisnik = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen"));
        return mapToUserDTO(korisnik);
    }

    // Dobavljanje svih korisnika
    public List<UserDTO> dobaviSveKorisnike() {
        return userRepository.findByDeletedFalse().stream()
            .map(this::mapToUserDTO)
            .collect(Collectors.toList());
    }

    // Dobavljanje korisnika po email-u
    public UserDTO dobaviKorisnikaPoEmailu(String email) {
        User korisnik = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen"));
        return mapToUserDTO(korisnik);
    }

    // Privatna metoda za mapiranje User -> UserDTO
    private UserDTO mapToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setIme(user.getIme());
        userDTO.setPrezime(user.getPrezime());
        userDTO.setAdresa(user.getAdresa());
        userDTO.setJmbg(user.getJmbg());
        userDTO.setDeleted(user.isDeleted());
        userDTO.setAktiviran(user.isAktiviran());
        
        Set<RoleDTO> roleDTOs = user.getRoles().stream()
            .map(this::mapToRoleDTO)
            .collect(Collectors.toSet());
        
        userDTO.setRoles(roleDTOs);
        return userDTO;
    }

    private RoleDTO mapToRoleDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setNaziv(role.getNaziv());
        roleDTO.setAktivna(role.isAktivna());
        return roleDTO;
    }
}