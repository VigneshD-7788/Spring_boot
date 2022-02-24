package com.Springboot_web_rest.Repos;

import com.Springboot_web_rest.Model.Bookdatamodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDataRepos extends JpaRepository<Bookdatamodel,Integer> {

}
