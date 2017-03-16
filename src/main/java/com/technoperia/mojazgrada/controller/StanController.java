package com.technoperia.mojazgrada.controller;

import com.technoperia.mojazgrada.dao.*;
import com.technoperia.mojazgrada.model.*;
import com.technoperia.mojazgrada.model.json.OglasJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stan")
public class StanController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private OglasDao oglasDao;
    @Autowired
    private StanDao stanDao;

    @RequestMapping("/{sID}/oglasi")
    public List<Oglas> getOglasi(@PathVariable("sID") Long sID){
        Stan stan = stanDao.findById(sID);
        return oglasDao.findByStan(stan);
    }

    @RequestMapping("/{sID}/oglasi/{oID}")
    public Oglas getOglas(@PathVariable("sID") Long sID, @PathVariable("oID") Long oID){
        Stan stan = stanDao.findById(sID);
        Oglas oglas = oglasDao.findById(oID);
        return oglas;
    }

    @RequestMapping("/novOglas")
    public Oglas createOglas(@RequestBody OglasJson oglasJson){
        User user = userDao.findById(oglasJson.userID);
        Stan stan = stanDao.findById(oglasJson.stanID);
        Oglas oglas = new Oglas();
        oglas.setStan(stan);
        oglas.setUser(user);
        oglas.setImage(oglasJson.image);
        oglas.setTitle(oglasJson.title);
        oglasDao.save(oglas);
        return oglas;
    }

}
