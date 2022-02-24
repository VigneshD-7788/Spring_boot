package com.Springboot_web_rest.Service;

import com.Springboot_web_rest.Model.Book_historymodel;
import com.Springboot_web_rest.Model.Bookdatamodel;
import com.Springboot_web_rest.Model.Rolemodel;
import com.Springboot_web_rest.Model.Studentmodel;
import com.Springboot_web_rest.Repos.BookDataRepos;
import com.Springboot_web_rest.Repos.BookHistoryRepos;
import com.Springboot_web_rest.Repos.Rolerepos;
import com.Springboot_web_rest.Repos.Studentrepos;
import com.Springboot_web_rest.Request.Studentrequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Service
public class Studentservice {
    private static final Logger logger = LoggerFactory.getLogger(Studentservice.class);
    @Autowired
    Studentrepos studentrepos;
    @Autowired
    BookHistoryRepos bookHistoryRepos;
    @Autowired
    BookDataRepos bookDataRepos;
    @Autowired
    Rolerepos rolerepos;
    @Autowired
    Environment env;
    @Autowired
    EntityManager em;

    public Boolean studentRegister(Studentrequest studentrequest) throws Exception {
        Studentmodel studentmodel = new Studentmodel();
        logger.info("Registration Started");
        if (studentrequest.getName().equals("")) {
            throw new Exception("Name is required field");
        } else if (studentrequest.getEmail().equals("")) {
            throw new Exception("Email is required field");
        } else if (studentrequest.getPassword().equals("")) {
            throw new Exception("Password is required field");
        }
        studentmodel.setName(studentrequest.getName());
        studentmodel.setEmail(studentrequest.getEmail());
        studentmodel.setPassword(studentrequest.getPassword());
        studentrepos.save(studentmodel);
        return true;
    }

    public String tokenGenerate(String email,String name) {
        System.out.println(env.getProperty("JWT_TOKEN_SECRET"));
        Calendar c= Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH,1);
        Date date = c.getTime();
        String token = Jwts.builder().setSubject(name)
                .setIssuer(email)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512,env.getProperty("JWT_TOKEN_SECRET")).compact();
        return token;
    }
    public Boolean tokenDecode(String token)throws Exception{
        try{
            Jws<Claims> jwt = Jwts.parser()
                    .setSigningKey(env.getProperty("JWT_TOKEN_SECRET"))
                    .parseClaimsJws(token);
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }

        return true;
    }

    public HashMap<String, String> studentLogin(String email, String password) {
        Optional<Studentmodel> studentModel = studentrepos.login(email, password);
        HashMap<String, String> hashMap = new HashMap<>();
        if (studentModel.isPresent()) {
            Studentmodel studentmodel=studentModel.get();
            String token = tokenGenerate(email,studentmodel.getName());
            hashMap.put("Message:", "You are successfully login,Welcome to our book portal");
            hashMap.put("Token:", token);
            studentrepos.updateTokenByStudentID(token, studentModel.get().getStudent_id());
            return hashMap;

        }
        hashMap.put("Message:", "login failed,Please try again.....");
        hashMap.put("Token:", "  ");
        return hashMap;
    }

    public boolean addBookRequest(Studentrequest studentrequest) throws Exception {
        Book_historymodel book_historymodel = new Book_historymodel();
        if (studentrequest.getStudent_id().equals("")) {
            throw new Exception("Student ID is required field");
        } else if (studentrequest.getBook_id().equals("")) {
            throw new Exception("Book name is required field");
        } else if (studentrequest.getType().equals("")) {
            throw new Exception("Type is required field");
        } else {
            book_historymodel.setBook_id(studentrequest.getBook_id());
            book_historymodel.setType(studentrequest.getType());
            book_historymodel.setStudent_id(studentrequest.getStudent_id());
            bookHistoryRepos.save(book_historymodel);

        }
        return true;
    }
    public Boolean checkTokenForStudentId(String student_id, String token) throws Exception {

        if (token == null || token.equals("")) {
            System.out.println("token is null..so it will not go to the controller.");
            throw new Exception("Token is null");
        } else if (student_id == null || student_id.equals("")) {
            System.out.println("token is null..so it will not go to the controller.");
            throw new Exception("Student ID is null");
        }
        Integer student_id_int = Integer.parseInt(student_id);//string to int
        studentrepos.getTokenByStudentId(student_id_int, token).orElseThrow(() -> new Exception("Token is not valid for this user"));
        return true;
    }

    @Cacheable(cacheNames = "Student", key = "#id")
    public Studentmodel getStudentById(Integer id) throws Exception {
        Optional<Studentmodel> studentModel = studentrepos.findById(id);
        logger.info("Cache started");
        if (studentModel.isPresent()) {
            logger.info("check id {}",id);
            return studentModel.get();
        } else {
            throw new Exception("Student is not exist");
        }
    }
    public Bookdatamodel getBookById(Integer id) throws Exception {
        Optional<Bookdatamodel> bookdatamodel = bookDataRepos.findById(id);
        if (bookdatamodel.isPresent()) {
            return bookdatamodel.get();
        } else {
            throw new Exception("Student is not exist");
        }
    }
    public List<Studentmodel> getAllStudents() {
        List<Studentmodel> studentModelList = studentrepos.findAll();//get all the data from the table.
        return studentModelList;
    }
    public String getRole(Integer student_id){
        Optional<Studentmodel> studentmodel= studentrepos.getRoleByStudentId(student_id);
        Studentmodel studentmodels= studentmodel.get();
        return studentmodels.getRole();
    }
    public boolean checkRoleAccess(String method_name,String role_name){
        logger.info("check {} {}",method_name,role_name);
        String url[]= method_name.split("/");
        logger.info("split {} {}",url,url[3]);
        Optional<Rolemodel> rolemodel= rolerepos.roleAccess(url[3],role_name);
        if(rolemodel.isPresent()){
            logger.info("Role is present {}",url[3]);
            return true;
        }else{
            logger.info("No role is exist");
            return false;
        }
    }
    public void uploadProfileImage(String profile_image, Integer student_id) {
        studentrepos.updateProfileImageByStudentId(student_id,profile_image);
    }
    public List <Studentmodel> searchUser(String req_name,String req_email){
        CriteriaBuilder cb= em.getCriteriaBuilder();
        CriteriaQuery<Studentmodel> cq= cb.createQuery(Studentmodel.class);

        Root<Studentmodel> studentmodel = cq.from(Studentmodel.class);//get class from criteria query
        List<Predicate> predicates = new ArrayList<>();
        if(req_name!=null && !req_name.equals("")) { //if not empty add below query
            predicates.add(cb.like(studentmodel.get("name"), req_name+"%"));
        }
        if(req_email!=null && !req_email.equals("")){
            predicates.add(cb.equal(studentmodel.get("email"),req_email));
        }
        cq.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(cq).getResultList();
    }
}
