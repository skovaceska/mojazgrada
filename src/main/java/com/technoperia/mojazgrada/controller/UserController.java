package com.technoperia.mojazgrada.controller;

import com.technoperia.mojazgrada.dao.StanDao;
import com.technoperia.mojazgrada.dao.UserDao;
import com.technoperia.mojazgrada.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("/api")
public class UserController {

    @Autowired
    private StanDao stanDao;
    @Autowired
    private UserDao userDao;

    @RequestMapping("/users")
    public List<User> getUsers(){
        return userDao.findAll();
    }

    @RequestMapping("users/user/{uID}")
    public User getUser(@PathVariable("uID") Long uID){
        return userDao.findById(uID);
    }

    @RequestMapping("users/stan/{sID}")
    public List<User> getStan(@PathVariable("sID") Long sID){
        Stan stan = stanDao.findById(sID);
        return userDao.findByStan(stan);
    }


}
