package com.Springboot_web_rest.Repos;


import com.Springboot_web_rest.Model.newmodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Userrepos extends JpaRepository<newmodel,Integer> {


}
