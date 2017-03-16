package com.technoperia.mojazgrada.controller;

//import java.lang.Exception.*;
import com.technoperia.mojazgrada.dao.*;
import com.technoperia.mojazgrada.model.*;
import com.technoperia.mojazgrada.model.json.LoginJson;
import com.technoperia.mojazgrada.model.json.SignupJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private StanDao stanDao;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(@RequestBody LoginJson loginJson)throws CustomException{
        User user = userDao.findByUsernameAndPassword(loginJson.username, loginJson.password);
        if(user != null) return user;
        throw new CustomException("no user");
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public User signUp(@RequestBody SignupJson signupJson) throws CustomException, NoSuchFieldException{

        if(signupJson.email == null) throw new CustomException("Email Empty");
        if(signupJson.fullName == null) throw new CustomException("FullName Empty");
        if(signupJson.password == null) throw new CustomException("Password Empty");
        if(signupJson.username == null) throw new CustomException("Username Empty");
        if(signupJson.role == 0) throw new CustomException("Role Empty");


        User user = userDao.findByUsername(signupJson.username);
        User userEmail = userDao.findByEmail(signupJson.email);
        if(user == null && userEmail == null){
            user = new User();
            user.setFullName(signupJson.fullName);
            user.setEmail(signupJson.email);
            user.setRole(signupJson.role);
            user.setUsername(signupJson.username);
            user.setPassword(signupJson.password);

            Stan stan = new Stan();

            if(signupJson.role == 1){

                if(stanDao.findBySifra(signupJson.sifra) != null) throw new CustomException("Sifra Exists");
                if(signupJson.adresa == null) throw new CustomException("Adresa Empty");
                if(signupJson.sifra == null) throw new CustomException("Sifra Empty");
                if(signupJson.broj == 0) throw new CustomException("Broj Empty");

                stan.setAdmin(user);
                stan.setBroj(signupJson.broj);
                stan.setAddress(signupJson.adresa);
                stan.setSifra(signupJson.sifra);

                userDao.save(user);
                stanDao.save(stan);

                user.setStan(stan);
                user.setAdmin(stan);
                userDao.save(user);
            }

            if(signupJson.role == 2) {
                stan = stanDao.findBySifra(signupJson.sifra);
                if (stan == null) {
                    throw new CustomException("no stan found");
                }
                user.setStan(stan);
                userDao.save(user);
            }
            return user;
        }
        throw new CustomException("user already exists");
    }

}
