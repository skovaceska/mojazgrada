package com.technoperia.mojazgrada.model.json;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OglasJson {
    public String title;
    public String image;
    public Long userID;
    public Long stanID;
}
