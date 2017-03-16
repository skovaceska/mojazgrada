package com.technoperia.mojazgrada.model.json;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SmetkaJson {
    public Long userID;
    public Long stanID;
    public String title;
    public String image;
}
