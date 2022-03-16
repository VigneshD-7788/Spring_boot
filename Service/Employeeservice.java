package com.Springboot_web_rest.Service;

import com.Springboot_web_rest.Model.Employeemodel;
import com.Springboot_web_rest.Repos.Employeerepos;
import com.Springboot_web_rest.Response.Newresponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Service
public class Employeeservice {

    private static final Logger logger = LoggerFactory.getLogger(Employeeservice.class);

    @Autowired
    Employeerepos employeerepos;
    @Autowired
    EntityManager em;

    public Optional<Employeemodel> getPage(String employee_id){
        Newresponse res = new Newresponse();
        Optional<Employeemodel> employeemodel=employeerepos.getTotalCount(employee_id);
        res.setMessage(employee_id);
       return employeemodel;
    }

    public List<Employeemodel> searchEmployee(int pageNo){
        int totalCount = 10;
        int pagePos = pageNo*totalCount;
        CriteriaBuilder cb= em.getCriteriaBuilder();
        CriteriaQuery<Employeemodel> cq= cb.createQuery(Employeemodel.class);

        Root<Employeemodel> employeeModel = cq.from(Employeemodel.class);//get class from criteria query
        return em.createQuery(cq).setFirstResult(pageNo).setMaxResults(10).getResultList();
    }
}
