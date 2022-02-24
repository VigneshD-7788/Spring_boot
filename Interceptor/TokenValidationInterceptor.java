package com.Springboot_web_rest.Interceptor;

import com.Springboot_web_rest.Controller.Peoplecontroller;
import com.Springboot_web_rest.Service.Peopleservice;
import com.Springboot_web_rest.Service.Studentservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenValidationInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(Peoplecontroller.class);
    @Autowired
    Peopleservice peopleservice;
    @Autowired
    Studentservice studentservice;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) {
         String current_url = request.getRequestURL().toString();
         logger.info("Url is excluded {}",current_url);
         if(current_url.contains("studentLogin")||current_url.contains("searchStudent")){
             logger.info("Url is excluded {}",current_url);
             return true;
         }
         String token = request.getHeader("token");
         String  student_id = request.getHeader("student_id");
        try {
            //peopleservice.tokenDecode(token);
           // peopleservice.checkTokenForUserId(id, token);//error may throw.
            studentservice.tokenDecode(token);
            studentservice.checkTokenForStudentId(student_id,token);
//            Integer student_id_int = Integer.parseInt(student_id);//string to int
//            String role = studentservice.getRole(student_id_int);
//            boolean check = studentservice.checkRoleAccess(current_url,role);
//            if(!check){
//                throw new Exception("Sorry,You don't have access");
//            }
//            logger.info("check {}",check);
//            boolean check=ApiroleAcess.checkRole(role,current_url);
//            if(!check){
//                logger.info("check{}",current_url);
//                throw new Exception("You don't have access for this api");
//            }
//            logger.info("check role {}",role);
//            logger.info("checking{}",role);
//            logger.info("checking {}",current_url);
            System.out.println("Request is fine..so can go to the controller.");
            return true;
        } catch (Exception e) {
            System.out.println("Exception " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView){
        System.out.println("postHandle before sending the response");
    }
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex){
        System.out.println("preHandle after sending the response");
    }

}
