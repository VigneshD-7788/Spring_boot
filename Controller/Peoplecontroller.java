package com.Springboot_web_rest.Controller;

import com.Springboot_web_rest.Model.Peoplemodel;
import com.Springboot_web_rest.Request.*;
import com.Springboot_web_rest.Response.Loginresponse;
import com.Springboot_web_rest.Response.Peopleresponse;
import com.Springboot_web_rest.Service.Peopleservice;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

@RestController
public class Peoplecontroller {
    private static final Logger logger = LoggerFactory.getLogger(Peoplecontroller.class);

    @Autowired
    Peopleservice peopleservice;

    @PostMapping("register")
    public ResponseEntity register(@RequestBody Peoplerequest pr) {
        Loginresponse lp = new Loginresponse();
        try {
            peopleservice.newregister(pr);
            lp.setMessage("Successfully Registered");
            return ResponseEntity.ok(lp);
        } catch (Exception e) {
            e.printStackTrace();
            lp.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(lp);
        }

    }
    @PostMapping("hashmap")
    public ResponseEntity hashmap(@RequestParam HashMap<Integer,String> pr) {
        Loginresponse lp = new Loginresponse();
        try {
            System.out.println(pr);
            lp.setMessage("Hashmap Created Successfully");
            return ResponseEntity.ok(lp);
        } catch (Exception e) {
            e.printStackTrace();
            lp.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(lp);
        }

    }
    @PostMapping("Path/{id}")
    public ResponseEntity set_path(@PathVariable Integer id,@RequestParam String name){
        Loginresponse res = new Loginresponse();
        try{
            System.out.println("Path variable:"+id);
            System.out.println("Your name is:"+name);
            res.setMessage("Path variable Created Successfully");
            return  ResponseEntity.ok(res);
        }catch(Exception e){
            res.setMessage(e.getMessage());
            return  ResponseEntity.badRequest().body(res);
        }
    }
    @PostMapping("login")
    public ResponseEntity login(@RequestBody Peoplerequest pr) {

        Loginresponse lp = new Loginresponse();
        try {
            peopleservice.logincheck(pr.getEmail(), pr.getPassword());
            lp.setMessage("Successfully logined");
            return ResponseEntity.ok(lp);
        } catch (Exception e) {
            e.printStackTrace();
            lp.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(lp);

        }
    }

    @PostMapping("updatepassword")
    public ResponseEntity updatepassword(@RequestBody Peoplerequest2 pr2) {

        Loginresponse lp = new Loginresponse();
        try {
            peopleservice.updation(pr2);
            lp.setMessage("Password Updated Successfully");
            return ResponseEntity.ok(lp);
        } catch (Exception e) {
            e.printStackTrace();
            lp.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(lp);

        }
    }

    @PostMapping("bank")
    public ResponseEntity bank(@RequestBody Peoplerequest3 pl3) {
        Peopleresponse rep = new Peopleresponse();
        try {
            System.out.println(pl3.getType());
            peopleservice.addamount(pl3);
            rep.setMessage("Transaction successful");
            return ResponseEntity.ok(rep);
        } catch (Exception e) {
            e.printStackTrace();
            rep.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(rep);
        }
    }

    @GetMapping("peoplecity")
    public ResponseEntity city() {
        return ResponseEntity.ok(peopleservice.getpeopledetails());

    }

    @PostMapping("multi_store_user")
    public ResponseEntity user_register(@RequestBody List<Peoplerequest> pr) {
        Loginresponse lp = new Loginresponse();
        try {
            for (Peoplerequest req : pr) {
                peopleservice.newregister(req);
            }
            lp.setMessage(pr.size() + "stored successfully");
            return ResponseEntity.ok(lp);
        } catch (Exception e) {
            e.printStackTrace();
            lp.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(lp);
        }
    }
    @PostMapping("multiUsers")
    public ResponseEntity multiUsers(@RequestBody List<Peoplerequest> pr) {
        Loginresponse lp = new Loginresponse();
        try {
            peopleservice.storeUsers(pr);
            lp.setMessage("Multi request satisfied....");
            return ResponseEntity.ok(lp);
        } catch (Exception e) {
            e.printStackTrace();
            lp.setMessage("Error while Inserting....");
            return ResponseEntity.badRequest().body(lp);
        }
    }
    @PostMapping("multiUsersCity")
    public ResponseEntity multiUsersCity(@RequestBody List<Peoplerequest> pr) {
        Loginresponse lp = new Loginresponse();
        try {
            peopleservice.storeUsersCity(pr);
            lp.setMessage("Multi request satisfied....");
            return ResponseEntity.ok(lp);
        } catch (Exception e) {
            e.printStackTrace();
            lp.setMessage("Error while Inserting....");
            return ResponseEntity.badRequest().body(lp);
        }
    }

    @GetMapping("token")
    public String getusername(@RequestHeader String token) {
        System.out.println("user token is" + token);
        return "from user controller";
    }

    @PostMapping("checkmail")
    public ResponseEntity checkmail(@RequestBody Peoplerequest obj) {
        Loginresponse res = new Loginresponse();
        System.out.println(obj.getEmail());
        System.out.println(obj.getPassword());
        try {
            peopleservice.Checkmail(obj);//call the service class method
            res.setMessage("Inserted Successfully");
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            e.printStackTrace();//the response will print the console page
            res.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(res);
        }
    }

    @PostMapping("checkCount")
    public ResponseEntity checkentry(@RequestBody List<Peoplerequest> request) {
        Loginresponse rep = new Loginresponse();
        try {
            HashMap<String, Integer> obj2 = peopleservice.checkentry(request);
            return ResponseEntity.ok(obj2);
        } catch (Exception e) {
            e.printStackTrace();
            rep.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(rep);
        }
    }

    @GetMapping("generateToken")
    public ResponseEntity new_token(@RequestBody String str) {
        return ResponseEntity.ok(peopleservice.getToken(str));
    }

    @PostMapping("mailToken")
    public ResponseEntity email_token(@RequestBody String name, @RequestHeader String token) {
        if (token.equals("email_token")) {
            return ResponseEntity.ok("token is matched");
        } else {
            return ResponseEntity.badRequest().body("token is not matched");
        }
    }
    @PostMapping("tokenEntry")
    public ResponseEntity token_entry(@RequestBody Peoplerequest obj){
        Loginresponse res = new Loginresponse();
        try {
            HashMap<String, String> hmap = peopleservice.tokenRegister(obj);
            return ResponseEntity.ok(hmap);
        }catch(Exception e){
            e.printStackTrace();
            res.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(res);

        }
    }
    @PostMapping("tokenRegister")
    public ResponseEntity token_register(@RequestBody Peoplerequest peoplerequest){
        Loginresponse res=new Loginresponse();
        try{
            HashMap<String,String> hashMap=peopleservice.registerToken(peoplerequest.getEmail(), peoplerequest.getPassword());
            res.setMessage("Registered and token generated Successfully");
            return ResponseEntity.ok(hashMap);
        }catch(Exception e){
            e.printStackTrace();
            res.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(res);
        }
    }
    @PostMapping("tokenLogin")
    public ResponseEntity tokenLogin(@RequestBody Tokenrequest tokenrequest){
        Loginresponse res=new Loginresponse();
        try{
            HashMap<String,String> hashMap=peopleservice.loginToken(tokenrequest.getEmail(), tokenrequest.getPassword());
            res.setMessage(" Login Successfully");
            return ResponseEntity.ok(hashMap);
        }catch(Exception e){
            e.printStackTrace();
            res.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(res);
        }
    }
    @PostMapping("tokenLogin2")
    public ResponseEntity tokenLogin2(@RequestBody Peoplerequest peoplerequest){
        Loginresponse res=new Loginresponse();
        try{
            peopleservice.loginToken2(peoplerequest);
            res.setMessage(" Login Successfully and token generated in mysql database");
            return ResponseEntity.ok(res);
        }catch(Exception e){
            e.printStackTrace();
            res.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(res);
        }
    }
    @GetMapping("checkToken")
    public ResponseEntity checkToken(@RequestHeader String token,@RequestHeader Integer id){
        try{
            peopleservice.checkTokenForUserId(id,token);
            return ResponseEntity.ok("Token is valid");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("updateToken")
    public ResponseEntity updateToken(@RequestHeader String token,@RequestHeader Integer id) {
        try {
            peopleservice.userUpdateToken(token,id);
            return ResponseEntity.ok("Token updated");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("getUsers")
    public ResponseEntity getUsers(@RequestHeader String token,@RequestHeader Integer id){
        try{
            peopleservice.checkTokenForUserId(id,token);
            return ResponseEntity.ok(peopleservice.getUserFromUserTable());
        }catch(Exception e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }
//    @GetMapping(value="fileName/{name}",produces = MediaType.IMAGE_JPEG_VALUE)
//    public @ResponseBody byte[] readImg(@PathVariable String name){
//        try{
//            String UPLOADED_FOLDER="C:\\Users\\10583\\IdeaProjects\\Uploaded_Images\\";
//            String fileWithPath = UPLOADED_FOLDER+name;
//            InputStream in= new FileInputStream(fileWithPath);
//            return IOUtils.toByteArray(in);
//        }catch (Exception e){
//            return null;
//        }
//    }
    @GetMapping(value="fileName/{name}",produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] readWithNoImage(@PathVariable String name){
        try{
            String UPLOADED_FOLDER="C:\\Users\\10583\\IdeaProjects\\Uploaded_Images\\";
            String filePath = UPLOADED_FOLDER+name;
            File file = new File(filePath);
            if(!file.exists()){
                filePath= UPLOADED_FOLDER+"no_image.jpg";
            }
            InputStream in= new FileInputStream(filePath);
            return IOUtils.toByteArray(in);
        }catch (Exception e){
            return null;
        }
    }
    @GetMapping(value="imageName/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] readImgById(@PathVariable Integer id){
        try{
            String UPLOADED_FOLDER="C:\\Users\\10583\\IdeaProjects\\Uploaded_Images\\";
            logger.info("file insert {}",id);
            Peoplemodel peoplemodel=peopleservice.getUserById(id);
            logger.info("file select");
            String fileWithPath = UPLOADED_FOLDER+peoplemodel.getPicture();
            logger.info("file path {}",fileWithPath);
            InputStream in= new FileInputStream(fileWithPath);
            return IOUtils.toByteArray(in);
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }
    @GetMapping("getUser/{id}")
    public ResponseEntity getUser(@PathVariable Integer id){
        logger.info("IN");
        try{
            logger.info("OUT");
            return ResponseEntity.ok(peopleservice.getUserById(id));
        }catch(Exception e){
            logger.info("ERROR {}",e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
    @PostMapping("insertSocialRequest")
    public ResponseEntity insertSocialRequest(@RequestBody Socialrequest socialrequest){
        logger.info("START");
        Loginresponse res= new Loginresponse();
        try{
            logger.info("Inserting");
            peopleservice.addSocialRequest(socialrequest);
            res.setMessage("Social request inserted");
            logger.info("response {}",res);
        }catch(Exception e){
            logger.error(e.getMessage());
            res.setMessage(e.getMessage());
        }
        return  ResponseEntity.badRequest().body(res);
    }
    @PostMapping("logout/{id}")
    public ResponseEntity user_logout(@PathVariable Integer id){
        peopleservice.userUpdateToken("  ",id);
        return ResponseEntity.ok("logout success");
    }
    @PostMapping("uploadFile")
    public ResponseEntity uploadFile(@RequestParam MultipartFile file,@RequestParam Integer id){
        logger.info("Success");
        try {
            logger.info("File Uploaded");
            logger.info(file.getContentType());
            String File_Name = "C:\\Users\\10583\\IdeaProjects\\Uploaded_Images";
            if (file.getContentType().equalsIgnoreCase("application/pdf")) {
                throw new RuntimeException("Type pdf is not supported for uploading");
            }else if(file.getContentType().equalsIgnoreCase("text/csv")) {
                throw new RuntimeException("Type excel is not supported for uploading");
            } else{
            Path path = Paths.get(File_Name, file.getOriginalFilename());
            Files.write(path, file.getBytes());
            peopleservice.uploadPicture(file.getOriginalFilename(), id);
        } return ResponseEntity.ok("File Uploaded Successfully"+":"+file.getOriginalFilename());
        }catch(Exception e){
            logger.error("Unable to upload your file");
            return ResponseEntity.badRequest().body(e.getMessage());//response with obj
        }
    }
    @PostMapping("uploadMultiFile")
    public ResponseEntity uploadFiles(@RequestParam MultipartFile[] file,@RequestParam Integer id) {
        try {
            String UPLOADED_FOLDER = "C:\\Users\\10583\\IdeaProjects\\Uploaded_Images";

            for (MultipartFile f : file) {
//                String fileName=f.getOriginalFilename();
//                String folderWithName= UPLOADED_FOLDER+fileName;
                Path path = Paths.get(UPLOADED_FOLDER, f.getOriginalFilename());
                Files.write(path, f.getBytes());
            }
            return ResponseEntity.ok("Files are Uploaded Successfully and total files are:" + file.length);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("tokenWithoutDb")
    public ResponseEntity tokenLoginWithoutDb(@RequestBody Tokenrequest tokenrequest){
        Loginresponse res=new Loginresponse();
        try{
            HashMap<String,String> hashMap=peopleservice.loginTokenWithoutDb(tokenrequest.getEmail(), tokenrequest.getPassword());
            res.setMessage(" Login Successfully");
            return ResponseEntity.ok(hashMap);
        }catch(Exception e){
            e.printStackTrace();
            res.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(res);
        }
    }

    @PostMapping("logout")
    public ResponseEntity logout(@RequestParam String token){
        peopleservice.userTokenWithoutDb(" ");
        return ResponseEntity.ok("logout success");
    }
}
