package com.Springboot_web_rest.Repos;

import com.Springboot_web_rest.Model.Bankmodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface Bankrepos extends JpaRepository<Bankmodel,Integer> {


    @Query("select bm from Bankmodel bm where type='credit'")
    List<Bankmodel> getCreditData();
    @Query("select bm from Bankmodel bm where type='debit'")
    List<Bankmodel> getDebitData();
    @Modifying
    @Transactional
    @Query("update Bankmodel bm set bm.Amount=6000 where id=1")
    void updateAmount();
    @Modifying
    @Transactional
    @Query("update Bankmodel bm set bm.Amount=?1 where id=?2")
    void updateAmount1(Double amount,Integer id);
}
