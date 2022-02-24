package com.Springboot_web_rest.Service;

import com.Springboot_web_rest.Model.newmodel;
import com.Springboot_web_rest.Repos.Userrepos;
import com.Springboot_web_rest.Repos.Userrepos2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Homeservice {

    @Autowired
    Userrepos userrepos;
    @Autowired
    Userrepos2 userrepos2;

    public String getName() {
        return "Hi vignesh, this is from service method";
    }

    public List<newmodel> getemployeeFromemployeetable() {
        List<newmodel> userlist = this.userrepos.findAll();
        return userlist;
    }

    public Optional getemployeebyId(Integer id) {
        Optional model = this.userrepos.findById(id);
        return model;

    }
    public boolean storeuser(newmodel obj) throws Exception {
        newmodel emp = new newmodel();

        if (obj.getEmployeeid().equals("")) {
            throw new Exception("id cant be empty");
        } else if (obj.getEmployeename().equals("")) {
            throw new Exception("name cant be empty");
        } else if (obj.getEmployeelocation().equals("")) {
            throw new Exception("location cant be empty");
        } else {
            this.userrepos.save(emp);
        }
        return true;
    }

}
