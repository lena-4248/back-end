package com.sirmium.auth.service;

import com.sirmium.auth.dto.PrijavaRequestDTO;
import com.sirmium.auth.dto.PrijavaResponseDTO;
import com.sirmium.user.model.User;
import com.sirmium.user.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthService(AuthenticationManager authenticationManager,
                     JwtService jwtService,
                     UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    public PrijavaResponseDTO prijaviKorisnika(PrijavaRequestDTO prijavaDTO) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                prijavaDTO.getEmail(),
                prijavaDTO.getLozinka()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        User korisnik = (User) authentication.getPrincipal();
        String jwtToken = jwtService.generisiToken(korisnik);
        
        return new PrijavaResponseDTO(
            jwtToken,
            korisnik.getId(),
            korisnik.getEmail(),
            korisnik.getIme(),
            korisnik.getPrezime(),
            korisnik.getRoles().iterator().next().getNaziv() // Prva uloga
        );
    }

    public void odjaviKorisnika() {
        SecurityContextHolder.clearContext();
    }

	public UserService getUserService() {
		return userService;
	}
}