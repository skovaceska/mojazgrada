package com.technoperia.mojazgrada.model.json;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignupJson {
    public String fullName;
    public String username;
    public String password;
    public String email;
    public int role;
    public String sifra;
    public String adresa;
    public int broj;
}
