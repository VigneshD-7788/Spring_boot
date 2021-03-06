package com.Springboot_web_rest.Service;

import com.Springboot_web_rest.Model.Employeemodel;
import com.Springboot_web_rest.Repos.Employeerepos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class Employeeservice {

    private static final Logger logger = LoggerFactory.getLogger(Employeeservice.class);

    @Autowired
    Employeerepos employeerepos;
    @Autowired
    EntityManager em;

    public Integer getPage(){
       Integer count = employeerepos.getTotalCount();
       return count;
    }

    public List<Employeemodel> searchEmployeePage(int pageNo){
        int totalCount = 10;
//      int page = pageNo*totalCount;
        CriteriaBuilder cb= em.getCriteriaBuilder();
        CriteriaQuery<Employeemodel> cq= cb.createQuery(Employeemodel.class);

        Root<Employeemodel> employeeModel = cq.from(Employeemodel.class);//get class from criteria query
        return em.createQuery(cq).setFirstResult(pageNo).setMaxResults(totalCount).getResultList();
    }
}
