package com.Springboot_web_rest.Repos;

import com.Springboot_web_rest.Model.Transactionmodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Transrepos extends JpaRepository<Transactionmodel,Integer> {

}
