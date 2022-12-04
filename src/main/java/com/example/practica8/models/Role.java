package com.example.practica8.models;


import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, ZAKUPMANAGER, SELLMANAGER;


    @Override
    public String getAuthority() {
        return name();
    }
}