package com.technoperia.mojazgrada.controller;

import com.technoperia.mojazgrada.dao.OglasDao;
import com.technoperia.mojazgrada.dao.SmetkaDao;
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
    @Autowired
    private OglasDao oglasDao;
    @Autowired
    private SmetkaDao smetkaDao;

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

    @RequestMapping("users/{uID}/oglasi")
    public List<Oglas> getOglasiForUser(@PathVariable("uID") Long uID){
        User user = userDao.findById(uID);
        return oglasDao.findByUser(user);
    }

    @RequestMapping("users/{uID}/smetki")
    public List<Smetka> getSmetkiForUser(@PathVariable("uID") Long uID){
        User user = userDao.findById(uID);
        return smetkaDao.findByUser(user);
    }




}
