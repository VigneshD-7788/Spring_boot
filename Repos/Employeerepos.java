package com.Springboot_web_rest.Repos;

import com.Springboot_web_rest.Model.Employeemodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Employeerepos extends JpaRepository<Employeemodel,Integer> {

    @Query("select count(employee_id) from Employeemodel ")
    Integer getTotalCount();
}
