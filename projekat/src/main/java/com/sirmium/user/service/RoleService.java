package com.sirmium.user.service;

import com.sirmium.user.dto.RoleDTO;
import com.sirmium.user.model.Role;
import com.sirmium.user.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Kreiranje nove uloge
    public RoleDTO kreirajUlogu(RoleDTO roleDTO) {
        if (roleRepository.findByNaziv(roleDTO.getNaziv()).isPresent()) {
            throw new RuntimeException("Uloga već postoji");
        }

        Role uloga = new Role();
        uloga.setNaziv(roleDTO.getNaziv());
        uloga.setAktivna(true);

        Role sacuvanaUloga = roleRepository.save(uloga);
        return mapToRoleDTO(sacuvanaUloga);
    }

    // Dobavljanje svih aktivnih uloga
    public List<RoleDTO> dobaviSveAktivneUloge() {
        return roleRepository.findByAktivnaTrue().stream()
            .map(this::mapToRoleDTO)
            .collect(Collectors.toList());
    }

    // Deaktivacija uloge
    public void deaktivirajUlogu(Long roleId) {
        Role uloga = roleRepository.findById(roleId)
            .orElseThrow(() -> new RuntimeException("Uloga nije pronađena"));

        uloga.setAktivna(false);
        roleRepository.save(uloga);}

    // Dobavljanje uloge po ID-u
    public RoleDTO dobaviUloguPoId(Long roleId) {
        Role uloga = roleRepository.findById(roleId)
            .orElseThrow(() -> new RuntimeException("Uloga nije pronađena"));
        return mapToRoleDTO(uloga);
    }

    // Privatna metoda za mapiranje Role -> RoleDTO
    private RoleDTO mapToRoleDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setNaziv(role.getNaziv());
        roleDTO.setAktivna(role.isAktivna());
        return roleDTO;
    }
}