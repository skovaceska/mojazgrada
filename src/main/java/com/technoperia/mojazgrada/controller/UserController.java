package com.technoperia.mojazgrada.controller;

import com.technoperia.mojazgrada.dao.OglasDao;
import com.technoperia.mojazgrada.dao.SmetkaDao;
import com.technoperia.mojazgrada.dao.StanDao;
import com.technoperia.mojazgrada.dao.UserDao;
import com.technoperia.mojazgrada.model.*;
import com.technoperia.mojazgrada.model.json.ChangeImageJson;
import com.technoperia.mojazgrada.model.json.ChangeNameJson;
import com.technoperia.mojazgrada.model.json.ChangePassJson;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!£$%^&*";
    static SecureRandom rnd = new SecureRandom();

    String randomString(){
        StringBuilder sb = new StringBuilder(10);
        for( int i = 0; i < 10; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }

    public String sendImage(String url, Long uID) throws IOException, ParseException {
        BufferedImage image = null;
        byte[] imageByte;
        BASE64Decoder decoder = new BASE64Decoder();
        imageByte = decoder.decodeBuffer(url);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        image = ImageIO.read(bis);
        bis.close();

        BufferedImage outputImage = new BufferedImage(225,
                300, image.getType());

        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(image, 0, 0, 225, 300, null);
        g2d.dispose();

        String date_s = "2011-01-18 00:00:00.0";
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = dt.parse(date_s);
        String name = "image-" + "user="+uID + "-" + new Date()+".png";
        String filename = name.replaceAll(" ", "-");
        filename = filename.replaceAll(":", "-");
        File outputfile = new File(filename);
        ImageIO.write(outputImage, "png", outputfile);
        File dest = new File("/var/www/html/mojazgrada/images/"+outputfile.getName());

        try {
            FileUtils.copyFile(outputfile, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputfile.getName();
    }

    @Autowired
    private StanDao stanDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private OglasDao oglasDao;
    @Autowired
    private SmetkaDao smetkaDao;

    //Get All Users
    @RequestMapping("/users")
    public List<User> getUsers(){
        return userDao.findAll();
    }

    //Get Specific User
    @RequestMapping("users/user/{uID}")
    public User getUser(@PathVariable("uID") Long uID){
        return userDao.findById(uID);
    }

    //Get users bu given Stan ID
    @RequestMapping("users/stan/{sID}")
    public List<User> getStan(@PathVariable("sID") Long sID){
        Stan stan = stanDao.findById(sID);
        return userDao.findByStan(stan);
    }

    //Find Oglasi By User
    @RequestMapping("users/{uID}/oglasi")
    public List<Oglas> getOglasiForUser(@PathVariable("uID") Long uID){
        User user = userDao.findById(uID);
        return oglasDao.findByUser(user);
    }

    //Find Smetki By User
    @RequestMapping("users/{uID}/smetki")
    public List<Smetka> getSmetkiForUser(@PathVariable("uID") Long uID){
        User user = userDao.findById(uID);
        return smetkaDao.findByUser(user);
    }

    //Change Password of given user
    @RequestMapping(value = "/users/changePassword", method = RequestMethod.POST)
    public User changeUserPassword(@RequestBody ChangePassJson json){
        User user = userDao.findById(json.userID);
        user.setPassword(json.pass);
        userDao.save(user);
        return user;
    }

    //Change Fullname of given user
    @RequestMapping(value = "/users/changeName", method = RequestMethod.POST)
    public User changeFullname(@RequestBody ChangeNameJson json){
        User user = userDao.findById(json.userID);
        user.setFullName(json.name);
        userDao.save(user);
        return  user;
    }

    @RequestMapping(value = "/users/changeImage", method = RequestMethod.POST)
    public User changePic(@RequestBody ChangeImageJson json)throws ParseException, IOException{
        User user = userDao.findById(json.userID);
        String imageUrl = sendImage(json.imageUrl, json.userID);
        user.setImage("http://52.39.15.26/mojazgrada/images/"+imageUrl);
        userDao.save(user);
        return user;
    }


}
