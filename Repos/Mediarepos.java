package com.Springboot_web_rest.Repos;

import com.Springboot_web_rest.Model.Socialmodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Mediarepos extends JpaRepository<Socialmodel,Integer> {

}
