package com.technoperia.mojazgrada.controller;

import com.technoperia.mojazgrada.dao.*;
import com.technoperia.mojazgrada.model.*;
import com.technoperia.mojazgrada.model.json.OglasJson;
import com.technoperia.mojazgrada.model.json.SmetkaJson;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/stan")
public class StanController {

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
    private UserDao userDao;
    @Autowired
    private OglasDao oglasDao;
    @Autowired
    private StanDao stanDao;
    @Autowired
    private SmetkaDao smetkaDao;

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

    @RequestMapping(value = "/novOglas", method = RequestMethod.POST)
    public Oglas createOglas(@RequestBody OglasJson oglasJson)throws CustomException,
            IOException, ParseException{

        User user = userDao.findById(oglasJson.userID);
        Stan stan = stanDao.findById(oglasJson.stanID);
        Oglas oglas = new Oglas();
        oglas.setStan(stan);
        oglas.setUser(user);

        if(oglasJson.title != null){
            oglas.setTitle(oglasJson.title);
        }

        if(oglasJson.image != null){
            String imageUrl = sendImage(oglasJson.image, oglasJson.userID);
            oglas.setImage(imageUrl);
        }

        oglas.setImage(oglasJson.image);
        oglas.setTitle(oglasJson.title);

        oglasDao.save(oglas);
        return oglas;
    }

    @RequestMapping(value = "/novaSmetka", method = RequestMethod.POST)
    public Smetka createSmetka(@RequestBody SmetkaJson smetkaJson)throws CustomException, IOException, ParseException{
        User user = userDao.findById(smetkaJson.userID);
        Stan stan = stanDao.findById(smetkaJson.stanID);
        Smetka smetka = new Smetka();
        smetka.setUser(user);
        smetka.setStan(stan);

        if(smetkaJson.title == null && smetkaJson.image == null) throw new  CustomException("Both Fields Are Empty");

        if(smetkaJson.title != null){
            smetka.setTitle(smetkaJson.title);
        }

        if(smetkaJson.image != null){
            String imageUrl = sendImage(smetkaJson.image, smetkaJson.userID);
            smetka.setImage(imageUrl);
        }

        smetkaDao.save(smetka);
        return smetka;
    }

}
