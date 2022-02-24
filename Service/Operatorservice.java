package com.Springboot_web_rest.Service;

import com.Springboot_web_rest.Model.newmodel2;
import com.Springboot_web_rest.Repos.Userrepos2;
import com.Springboot_web_rest.Request.typeclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Operatorservice {
    @Autowired
    Userrepos2 userrepos2;

    public boolean check(typeclass typo) throws Exception
    {
        newmodel2 model2= new newmodel2();
        int result=0;
        if (typo.getType().equals("add"))
        {
            result= typo.getNum1()+typo.getNum2();

        }
        else if (typo.getType().equals("sub"))
        {
            result=typo.getNum1()-typo.getNum2();
        }
        else if (typo.getType().equals("mul"))
        {
            result=typo.getNum1()*typo.getNum2();
        }
        else
        {
            throw new Exception("Please Provide the Proper Condition");
        }
        model2.setNum1(typo.getNum1());
        model2.setNum2(typo.getNum2());
        model2.setResult(result);
        this.userrepos2.save(model2);
        return true;
    }
    }

