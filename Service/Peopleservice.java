package com.Springboot_web_rest.Service;

import com.Springboot_web_rest.Model.Citymodel;
import com.Springboot_web_rest.Model.Peoplemodel;
import com.Springboot_web_rest.Model.Socialmodel;
import com.Springboot_web_rest.Model.Transactionmodel;
import com.Springboot_web_rest.Repos.Cityrepos;
import com.Springboot_web_rest.Repos.Mediarepos;
import com.Springboot_web_rest.Repos.Peoplerepos;
import com.Springboot_web_rest.Repos.Transrepos;
import com.Springboot_web_rest.Request.Peoplerequest;
import com.Springboot_web_rest.Request.Peoplerequest2;
import com.Springboot_web_rest.Request.Peoplerequest3;
import com.Springboot_web_rest.Request.Socialrequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Peopleservice {
    private static final Logger logger = LoggerFactory.getLogger(Peopleservice.class);
    @Autowired
    Peoplerepos peoplerepos;
    @Autowired
    Transrepos transrepos;
    @Autowired
    Cityrepos cityrepos;
    @Autowired
    Mediarepos mediarepos;

    public boolean newregister(Peoplerequest obj) throws Exception {
        Peoplemodel model = new Peoplemodel();
        if (obj.getEmail().equals(""))
            throw new Exception("email can't be empty");
        else if (obj.getPassword().equals(""))
            throw new Exception("password can't be empty");
        model.setEmail(obj.getEmail());
        model.setPassword(obj.getPassword());
        peoplerepos.save(model);
        return true;
    }

    public boolean logincheck(String email, String password) throws Exception {
        Optional<Peoplemodel> people = peoplerepos.login(email, password);
        if (people.isPresent()) {
            throw new Exception("email and password is present");
        } else
            throw new Exception("Invalid login");
    }


    public boolean updation(Peoplerequest2 pl2) throws Exception {
        Optional<Peoplemodel> peoplemodel = peoplerepos.findById(pl2.getUser_id());
        if (peoplemodel.isPresent()) {
            Peoplemodel model = peoplemodel.get();
            System.out.println(model.getPassword() + "" + pl2.getOld_password());
            if (model.getPassword().equals(pl2.getOld_password())) {
                if (pl2.getNew_password().equals(pl2.getConfirm_password())) {
                    peoplerepos.updatepassword(pl2.getNew_password(), pl2.getUser_id());
                } else {
                    throw new Exception("new password and confirm password does not match");
                }
            } else {
                throw new Exception("existing password does not match");
            }
        }
        return true;
    }

    public boolean addamount(Peoplerequest3 pl3) throws Exception {
        Optional<Peoplemodel> peoplemodel = peoplerepos.findById(pl3.getUser_id());
        if (pl3.getUser_id().equals("")) {
            throw new Exception("Id can't be empty ");
        } else if (pl3.getAmount() < 0)
            throw new Exception("Amount can't be empty");
        else if (pl3.getType().equals("")) {
            throw new Exception("Type can't be empty");
        } else if (peoplemodel.isPresent()) {
            Peoplemodel model = peoplemodel.get();
            System.out.println(pl3.getType());
            if (pl3.getType().equals("deposit")) {
                System.out.println(model.getAmount());
                double final_amount = model.getAmount() + pl3.getAmount();
                System.out.println("The amount" + final_amount);
                peoplerepos.addbalance(final_amount, pl3.getUser_id());
            }
            if (pl3.getType().equals("withdraw")) {
                System.out.println("inside withdraw");
                if (model.getAmount() > pl3.getAmount()) {
                    double final_amount = model.getAmount() - pl3.getAmount();
                    System.out.println("The amount" + final_amount);
                    peoplerepos.addbalance(final_amount, pl3.getUser_id());
                } else {
                    throw new Exception("You are having minimum balance,hence you are not able to withdraw");
                }
            }
            Transactionmodel model2 = new Transactionmodel();
            pl3.setUser_id(model.getId());
            pl3.setAmount(model.getAmount());
            pl3.setType(model.getType());
            transrepos.save(model2);
        }
        return true;
    }

    public List<Peoplemodel> getpeopledetails() {
        List<Peoplemodel> model3 = peoplerepos.findAll();
        return model3;
    }

    public boolean Checkmail(Peoplerequest obj) throws Exception {
        Optional<Peoplemodel> p_model = peoplerepos.emailcheck(obj.getEmail());
        System.out.println("Inserted" + obj.getEmail());
        if (obj.getEmail().equals(" ")) {
            throw new Exception("email can't be empty");
        } else if (obj.getPassword().equals(" ")) {
            throw new Exception("password can't be empty");
        } else if (p_model.isPresent()) {
            throw new Exception("email is already present");
        }

        Peoplemodel pm = new Peoplemodel();
        pm.setEmail(obj.getEmail());
        pm.setPassword(obj.getPassword());
        peoplerepos.save(pm);
        return true;
    }

    public HashMap<String, Integer> checkentry(List<Peoplerequest> obj) throws Exception {
        HashMap<String, Integer> hashMap = new HashMap<>();
        int success_count = 0, failure_count = 0;
        for (Peoplerequest trace : obj) {
            Optional<Peoplemodel> peoplemodel = peoplerepos.emailcheck(trace.getEmail());
            System.out.println(trace.getPassword() + " " + trace.getEmail());
            if (trace.getEmail().equals(" ")) {
                failure_count++;
                System.out.println("email is empty");
            } else if (trace.getPassword().equals(" ")) {
                failure_count++;
                System.out.println("password is empty");
            } else if (peoplemodel.isPresent()) {
                failure_count++;
            } else {
                Peoplemodel model = new Peoplemodel();
                model.setEmail(trace.getEmail()); //set the request email to model
                model.setPassword(trace.getPassword());
                peoplerepos.save(model); // save the all data in repo
                success_count++;
                System.out.println("success" + success_count);
            }
        }
        hashMap.put("success_count", success_count);
        hashMap.put("failure_count", failure_count);

        return hashMap;
    }

    public String getToken(String token) {
        String rep = Base64.getEncoder().encodeToString(token.getBytes());//convert string to bytes for encode
        System.out.println(rep);
        return rep;
    }

    public String emailToken(String email) {
        String token = email + System.currentTimeMillis();
        String rep = Base64.getEncoder().encodeToString(token.getBytes());
        return rep;
    }

    public String tokenGenerate(String email,String name) {
        Calendar c= Calendar.getInstance();
        c.add(Calendar.MINUTE,1);
        Date date = c.getTime();
        String token = Jwts.builder().setSubject(name)
                .setIssuer(email)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, "SECRET").compact();
        return token;
    }
    public Boolean tokenDecode(String token)throws Exception{
        try{
            Jws<Claims> jwt = Jwts.parser()
                    .setSigningKey("SECRET")
                    .parseClaimsJws(token);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }

        return true;
    }

    public HashMap<String, String> tokenRegister(Peoplerequest obj) throws Exception {
        HashMap<String, String> hmap = new HashMap<>();
        if (obj.getEmail().equals(" ")) {
            throw new Exception("email can't be empty");
        } else if (obj.getPassword().equals(" ")) {
            throw new Exception("password can't be empty");

        } else {
            Peoplemodel pm = new Peoplemodel();
            pm.setEmail(obj.getEmail());
            pm.setPassword(obj.getPassword());
            pm.setToken(emailToken(pm.getEmail()));
            peoplerepos.save(pm);
            emailToken(obj.getEmail());
            hmap.put("Message", "Success");
            hmap.put("Token", emailToken(obj.getEmail()));

            return hmap;
        }
    }

    public Peoplemodel getUserById(Integer id) throws Exception {
        Optional<Peoplemodel> people = peoplerepos.findById(id);
        if (people.isPresent()) {
            return people.get();
        } else {
            throw new Exception("User is not exist");
        }
    }

    public List<Peoplemodel> getUserFromUserTable() {
        List<Peoplemodel> userList = peoplerepos.findAll();//get all the data from the table.
        return userList;
    }

    public HashMap<String, String> registerToken(String email, String password) {
        Optional<Peoplemodel> peoplemodel = peoplerepos.login(email, password);
        Peoplemodel peoplemodel1 = new Peoplemodel();
        HashMap<String, String> hashmap = new HashMap<>();
        String token = this.getToken(email);
        if (peoplemodel.isPresent()) {
            hashmap.put("email is already exist", "Please Provide Proper Credentials");
            hashmap.put("Token", "  ");
        } else {
            hashmap.put("message", "Successfully Registered");
            hashmap.put("Token:", token);
            peoplemodel1.setEmail(email);
            peoplemodel1.setPassword(password);
            peoplemodel1.setToken(token);
            peoplerepos.save(peoplemodel1);
            return hashmap;
        }
        return hashmap;
    }

    public HashMap<String, String> loginToken(String email, String password) throws Exception {
        Optional<Peoplemodel> peoplemodel = peoplerepos.login(email, password);
        HashMap<String, String> hashmap = new HashMap<>();
        if (peoplemodel.isPresent()) {
            Peoplemodel p_model= peoplemodel.get();
            String token = tokenGenerate(email,p_model.getName());
            hashmap.put("message", "Login Successfully");
            hashmap.put("Token:", token);
            peoplerepos.updateTokenByUserId(token, peoplemodel.get().getId());
            return hashmap;
        }
        hashmap.put("message:", "Login failed");
        hashmap.put("token", "  ");

        return hashmap;
    }

    public boolean loginToken2(Peoplerequest peoplerequest) throws Exception {
        Optional<Peoplemodel> peoplemodel = peoplerepos.login(peoplerequest.getEmail(), peoplerequest.getPassword());
        String token = emailToken(peoplerequest.getEmail());
        if (peoplemodel.isPresent()) {
            peoplerepos.updateTokenByUserId(token, peoplemodel.get().getId());
            return true;
        } else {
            throw new Exception("Unable to login, Please try again.....");

        }
    }

    public boolean checkTokenForUserId(int id, String token) throws Exception {
        peoplerepos.getTokenByUserId(id, token).orElseThrow(() -> new Exception("Token is not valid"));
        return true;

    }

    public  Boolean checkTokenForUserId(String userId, String token) throws Exception {

        if (token == null || token.equals("")) {
            System.out.println("token is null..so it will not go to the controller.");
            throw new Exception("Token is null");
        } else if (userId == null || userId.equals("")) {
            System.out.println("token is null..so it will not go to the controller.");
            throw new Exception("User ID is null");
        }
        Integer user_id_int = Integer.parseInt(userId);//string to int
        peoplerepos.getTokenByUserId(user_id_int, token).orElseThrow(() -> new Exception("Token is not valid for this user"));
        return true;
    }

    public void uploadPicture(String picture, Integer id) {
        peoplerepos.updatePictureByUserId(id, picture);
    }
    public boolean userUpdateToken(String token, Integer id) {
        try {
            peoplerepos.updateTokenByUserId(token, id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean userTokenWithoutDb(String token) {
        try {
            this.getToken(token);
            if (token.equals(token)) {
                System.out.println("logout success");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public HashMap<String, String> loginTokenWithoutDb(String email, String password) {
        Optional<Peoplemodel> peoplemodel = peoplerepos.login(email, password);
        HashMap<String, String> hashmap = new HashMap<>();
        String token = this.emailToken(email);
        if (peoplemodel.isPresent()) {
            hashmap.put("message", "Login Successfully");
            hashmap.put("Token:", token);
            return hashmap;
        }
        hashmap.put("message:", "Login failed");
        hashmap.put("token", "  ");

        return hashmap;
    }

    public Boolean storeUsers(List<Peoplerequest> req) {
        List<Peoplemodel> peoplemodelList = new ArrayList<Peoplemodel>();
        req.stream().forEach(
                r -> {
                    Peoplemodel peoplemodel = new Peoplemodel();
                    peoplemodel.setName(r.getName());
                    peoplemodel.setEmail(r.getEmail());
                    peoplemodel.setPassword(r.getPassword());
                    peoplemodelList.add(peoplemodel);
                }
        );
        peoplerepos.saveAll(peoplemodelList);
        return true;
    }

    public Boolean storeUsersCity(List<Peoplerequest> req) {
        try {
            List<Peoplemodel> peoplemodelList = new ArrayList<Peoplemodel>();
            req.stream().forEach(
                    r -> {
                        try {
                            Peoplemodel peoplemodel = new Peoplemodel();
                          //  Citymodel citymodel=cityrepos.getCityByName(r.getCity_name()).orElseThrow(()->new Exception("No city found"));
                            Citymodel citymodel= createOrReturnCity(r.getCity_name());
                            peoplemodel.setCity(citymodel);
                            peoplemodel.setName(r.getName());
                            peoplemodel.setEmail(r.getEmail());
                            peoplemodel.setPassword(r.getPassword());
                            peoplemodelList.add(peoplemodel);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
            );
            System.out.println(peoplemodelList.size() + "going to insert");
            peoplerepos.saveAll(peoplemodelList);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return true;
    }
    private Citymodel createOrReturnCity(String name) throws Exception{
        Optional<Citymodel> citymodel= cityrepos.getCityByName(name);
        if(citymodel.isPresent()){
            return citymodel.get();
        }else{
            Citymodel city = new Citymodel();
            city.setName(name);
            cityrepos.save(city);
            return city;
        }
    }
    private List<Socialmodel> createSocialModelWithRequest(List<String> mediaReq) {
        List<Socialmodel> socialModelList = new ArrayList<>();

        mediaReq.stream().forEach((s) -> {
                    Socialmodel socialmodel = new Socialmodel();
                    socialmodel.setAccount_name(s);
                    socialModelList.add(socialmodel);
                }
        );
        return socialModelList;
    }
    public boolean addSocialRequest(Socialrequest socialrequest)throws Exception{
        Optional<Peoplemodel> peoplemodel= peoplerepos.findById(socialrequest.getUser_id());
        logger.info("Start");
        if(!peoplemodel.isPresent()){
            logger.info("check {}",peoplemodel);
            throw new Exception("User id is not found");
        }
        Socialmodel socialmodel= new Socialmodel();
        if(socialrequest.getAccount_name().equals("")){
            throw new Exception("Account name is required field");
        }else if(socialrequest.getMedia_name().equals("")) {
            throw new Exception("Media name is required field");
        }else{
            socialmodel.setAccount_name(socialrequest.getAccount_name());
            socialmodel.setMedia_name(socialrequest.getMedia_name());
            socialmodel.setUser_id(socialrequest.getUser_id());
            mediarepos.save(socialmodel);

        }
        return true;
    }

    }









