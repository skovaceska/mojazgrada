package com.technoperia.mojazgrada.controller;

import com.technoperia.mojazgrada.dao.*;
import com.technoperia.mojazgrada.model.*;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ProfilController {
    private final String USER_AGENT = "Mozilla/5.0";

    @Autowired
    private UserDao userDao;

    @RequestMapping("/")
    public User get(){
        User user = new User();
        user.setUsername("ebrarislami");
        userDao.save(user);
        return userDao.findByUsername("ebrarislami");
    }

}
