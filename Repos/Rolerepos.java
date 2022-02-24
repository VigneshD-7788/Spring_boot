package com.Springboot_web_rest.Repos;

import com.Springboot_web_rest.Model.Rolemodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Rolerepos extends JpaRepository<Rolemodel,Integer> {

    @Query("select role from Rolemodel role where method_name=?1 and role_name=?2 ")
    Optional<Rolemodel> roleAccess(String method_name,String role_name);

}
