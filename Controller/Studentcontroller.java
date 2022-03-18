package com.Springboot_web_rest.Controller;

import com.Springboot_web_rest.Model.Studentmodel;
import com.Springboot_web_rest.Request.Studentrequest;
import com.Springboot_web_rest.Response.Newresponse;
import com.Springboot_web_rest.Response.Studentresponse;
import com.Springboot_web_rest.Service.Studentservice;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class Studentcontroller {
    private static final Logger logger = LoggerFactory.getLogger(Studentcontroller.class);

    @Autowired
    Studentservice studentservice;
    @Autowired
    Environment env;

    @PostMapping("studentRegister")
    public ResponseEntity studentRegistration(@RequestBody Studentrequest studentrequest) {
        Studentresponse res = new Studentresponse();
        logger.info("Going to insert");
        try {
            logger.info("Inserted");
            studentservice.studentRegister(studentrequest);
            res.setMessage(" Your profile has been successfully registered");
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            logger.error(e.getMessage());
            res.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(res);
        }
    }

    @PostMapping("studentLogin")
    public ResponseEntity studentLogin(@RequestBody Studentrequest studentrequest) {
        logger.info("Going to login");
        try {
            logger.info("login currently");
             Studentresponse res = studentservice.studentLogin(studentrequest.getEmail(), studentrequest.getPassword());
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            logger.error(e.getMessage());
           throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("studentLogout/{id}")
    public ResponseEntity studentLogout(@PathVariable Integer id) {
        logger.info("Going to logout from book portal");
        Newresponse res = studentservice.userUpdateToken("  ", id);
        logger.info("You are successfully logout from book portal");
        return ResponseEntity.ok(res);
    }

    @PostMapping("insertBookRequest")
    public ResponseEntity insertBookRequest(@Valid @RequestBody Studentrequest studentrequest) {
        logger.info("START");
        Studentresponse res = new Studentresponse();
        try {
            logger.info("Inserting");
            studentservice.addBookRequest(studentrequest);
            res.setMessage("Book request inserted");
            logger.info("response {}", res);
        } catch (Exception e) {
            logger.error(e.getMessage());
            res.setMessage(e.getMessage());
        }
        return ResponseEntity.badRequest().body(res);
    }

    @GetMapping("getStudent/{id}")
    public ResponseEntity getStudent(@PathVariable Integer id,@RequestHeader("student_id") Integer id_header ) {
        logger.info("IN");
        try {
            logger.info("OUT");
            if(id.equals(id_header)) {
                return ResponseEntity.ok(studentservice.getStudentById(id));
            }else{
                throw new Exception("Don't have access to api");
            }
        } catch (Exception e) {
            logger.info("ERROR {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("getBook/{id}")
    public ResponseEntity getBook(@PathVariable Integer id) {
        logger.info("IN");
        try {
            logger.info("OUT");
            return ResponseEntity.ok(studentservice.getBookById(id));
        } catch (Exception e) {
            logger.info("ERROR {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("getStudents")
    public ResponseEntity getStudents(@RequestHeader String token, @RequestHeader Integer student_id) {
        try {
            String student_id_str = Integer.toString(student_id);
            studentservice.checkTokenForStudentId(student_id_str, token);
            return ResponseEntity.ok(studentservice.getAllStudents());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "studentImage/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] readProfileImgByStudentId(@PathVariable Integer id) {
        try {
            String UPLOADED_FOLDER = "C:\\Users\\10583\\IdeaProjects\\Uploaded_Images\\";
            logger.info("file insert {}", id);
            Studentmodel studentmodel = studentservice.getStudentById(id);
            logger.info("file select");
            String fileWithPath = UPLOADED_FOLDER + studentmodel.getProfile_image();
            logger.info("file path {}", fileWithPath);
            InputStream in = new FileInputStream(fileWithPath);
            return IOUtils.toByteArray(in);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    @PostMapping("uploadProfileImage")
    public ResponseEntity uploadProfileImage(@RequestParam MultipartFile file, @RequestParam Integer student_id) {
        logger.info("Success");
        try {
            logger.info("Profile Image Uploaded");
            logger.info(file.getContentType());
            String File_Name = "C:\\Users\\10583\\IdeaProjects\\Uploaded_Images";
            Path path = Paths.get(File_Name, file.getOriginalFilename());
            Files.write(path, file.getBytes());
            studentservice.uploadProfileImage(file.getOriginalFilename(), student_id);
            return ResponseEntity.ok("Profile Image Uploaded Successfully" + ":" + file.getOriginalFilename());
        } catch (Exception e) {
            logger.error("Unable to upload your file");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("getCountry")
    public String getCountryFromProps() {
        System.out.println(env.getProperty("COUNTRY_CODE"));
        logger.info(env.getProperty("COUNTRY_CODE"));
        String con_code = env.getProperty("COUNTRY_CODE");
        if (con_code.equals("INDIA")) {
            logger.info("con_code {}",con_code);
            return "Rs";
        } else if (con_code.equals("USA")) {
            return "$";
        }else{
            return env.getProperty("COUNTRY_CODE");

    }
}
    @GetMapping("searchStudent")
    public ResponseEntity searchStudent(@RequestParam(required = false) String name,@RequestParam(required = false) String email){
        Studentresponse res= new Studentresponse();
        try{
            return ResponseEntity.ok(studentservice.searchUser(name,email));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(res);
        }
    }
}


