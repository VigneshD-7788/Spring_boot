package com.Springboot_web_rest.Repos;

import com.Springboot_web_rest.Model.Peoplemodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface Peoplerepos extends JpaRepository<Peoplemodel,Integer> {

    @Query("select people from Peoplemodel people where email=?1 and password =?2")
    Optional<Peoplemodel> login(String email, String password);
    @Modifying
    @Transactional
    @Query("update Peoplemodel  set password=?1 where id=?2")
    void updatepassword(String password, Integer id);
    @Modifying
    @Transactional
    @Query("update from Peoplemodel set amount=?1 where id=?2")
    void addbalance(Double amount,Integer id);
    @Query("select people from Peoplemodel people where email=?1")
    Optional<Peoplemodel> emailcheck(String email);

    @Query("select people from Peoplemodel people where people.id=?1 and people.token=?2")
    Optional<Peoplemodel> getTokenByUserId(Integer id,String token);

    @Transactional
    @Modifying
    @Query("update Peoplemodel people set people.token=?1 where people.id=?2")
    void updateTokenByUserId(String token,Integer id);

    @Transactional
    @Modifying
    @Query("update Peoplemodel people set people.picture=?2 where people.id=?1")
    void updatePictureByUserId(Integer id,String picture);


}
