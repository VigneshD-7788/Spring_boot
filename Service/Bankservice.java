package com.Springboot_web_rest.Service;

import com.Springboot_web_rest.Model.Bankmodel;
import com.Springboot_web_rest.Repos.Bankrepos;
import com.Springboot_web_rest.Request.typeclass2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Bankservice {
    @Autowired
    Bankrepos bankrepos;

    public boolean check(typeclass2 typi) throws Exception {

        Bankmodel model = new Bankmodel();
        int entry = 0;

        if (typi.getType().equals("credit")) {
            entry = typi.getCredit();
        } else if (typi.getType().equals("debit")) {
            entry = typi.getDebit();
        } else {
            throw new Exception("Please Provide the proper entry");
        }
        model.setAcc_no(typi.getAcc_no());
        model.setAmount(typi.getAmount());
        model.setType(typi.getType());
        model.setId(entry);
        this.bankrepos.save(model);
        return true;
    }

    public double getAmount() {
        List<Bankmodel> entrylist = this.bankrepos.findAll();
        double total = 0;
        for (Bankmodel bm : entrylist) {
            total = total + bm.getAmount();
        }
        System.out.println("Total" + total);
        return total;
    }

    public double getAmountCredit() {
        List<Bankmodel> entrylist = this.bankrepos.getCreditData();
        double total = 0;
        for (Bankmodel bm : entrylist) {
            total = total + bm.getAmount();
        }
        System.out.println("Total" + total);
        return total;
    }

    public double getAmountDredit() {
        List<Bankmodel> entrylist = this.bankrepos.getDebitData();
        double total = 0;
        for (Bankmodel bm : entrylist) {
            total = total + bm.getAmount();
        }
        System.out.println("Total" + total);
        return total;
    }

    public void getupdateAmount() {
        this.bankrepos.updateAmount();
    }
    public Double updateamountbyID(Integer id,Double amount)throws Exception{
        Optional<Bankmodel> bank=this.bankrepos.findById(id);
        if(bank.isPresent()){
            Bankmodel model= bank.get();
            double final_amount = model.getAmount() + amount;
            this.bankrepos.updateAmount1(final_amount,id);
            return final_amount;
        }else{
            throw new Exception("Bank account is not found");
        }
    }

}
