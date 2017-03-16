package com.technoperia.mojazgrada.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Stan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    private int broj;
    private String sifra;

    @JsonIgnore
    @OneToMany(mappedBy = "stan")
    private List<User> users = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "stan")
    private List<Oglas> oglasi = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "stan")
    private List<Smetka> smetki = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private User admin;

    public Stan(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public List<Oglas> getOglasi() {
        return oglasi;
    }

    public void setOglasi(List<Oglas> oglasi) {
        this.oglasi = oglasi;
    }

    public List<Smetka> getSmetki() {
        return smetki;
    }

    public void setSmetki(List<Smetka> smetki) {
        this.smetki = smetki;
    }
}
