package com.technoperia.mojazgrada.controller;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProfilController {
    private final String USER_AGENT = "Mozilla/5.0";

    @RequestMapping("/")
    public JSONObject get(){
        JSONObject object = new JSONObject();
        object.put("name", "ebrar");
        return object;
    }

}
