package com.Springboot_web_rest.Repos;

import com.Springboot_web_rest.Model.Employeemodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Employeerepos extends JpaRepository<Employeemodel,Integer> {

    @Query("select count(em) from Employeemodel em")
    Optional<Employeemodel> getTotalCount();
}
