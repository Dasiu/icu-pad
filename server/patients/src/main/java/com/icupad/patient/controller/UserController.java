package com.icupad.patient.controller;//package com.icupad.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserDetailsService userDetailsService;

    @Autowired
    public UserController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping("current")
    public UserDetailsDTO user(Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        return new UserDetailsDTO(userDetails.getUsername(), userDetails.getAuthorities());
    }
}

class UserDetailsDTO {
    public String login;
    public Collection<? extends GrantedAuthority> authorities;

    public UserDetailsDTO(String login, Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
        this.login = login;
    }
}