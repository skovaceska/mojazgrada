package com.technoperia.mojazgrada.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String email;

    private int role;

    public String fullName;

    @ManyToOne(fetch = FetchType.EAGER)
    private Stan stan;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Oglas> oglasi = new ArrayList<>();

    @OneToOne(mappedBy = "admin", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, optional = false)
    private Stan admin;


    public User(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Stan getStan() {
        return stan;
    }

    public void setStan(Stan stan) {
        this.stan = stan;
    }

    public Stan getAdmin() {
        return admin;
    }

    public void setAdmin(Stan admin) {
        this.admin = admin;
    }

    public List<Oglas> getOglasi() {
        return oglasi;
    }

    public void setOglasi(List<Oglas> oglasi) {
        this.oglasi = oglasi;
    }
}
