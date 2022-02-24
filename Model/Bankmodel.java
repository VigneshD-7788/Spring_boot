package com.Springboot_web_rest.Model;

import javax.persistence.*;

@Entity
@Table(name="banktable")
public class Bankmodel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer acc_no;
    @Column(insertable = false,updatable = false)
    String trans_date_time;
    Double Amount;
    String type;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAcc_no() {
        return acc_no;
    }

    public void setAcc_no(Integer acc_no) {
        this.acc_no = acc_no;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTrans_date_time() {
        return trans_date_time;
    }

    public void setTrans_date_time(String trans_date_time) {
        this.trans_date_time = trans_date_time;
    }

}
