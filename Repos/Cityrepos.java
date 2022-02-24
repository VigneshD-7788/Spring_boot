package com.Springboot_web_rest.Repos;

import com.Springboot_web_rest.Model.Citymodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Cityrepos extends JpaRepository<Citymodel,Integer> {
    @Query("select c from Citymodel c where c.name=?1")
    Optional<Citymodel> getCityByName(String name);
}
