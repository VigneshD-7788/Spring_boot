package com.Springboot_web_rest.Controller;

import com.Springboot_web_rest.Request.typeclass2;
import com.Springboot_web_rest.Request.typeclass3;
import com.Springboot_web_rest.Response.Bankresponse;
import com.Springboot_web_rest.Response.Newresponse;
import com.Springboot_web_rest.Service.Bankservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Bankcontroller {
    @Autowired
    Bankservice bankservice;

    @PostMapping("transaction")
    public ResponseEntity Entries(@RequestBody typeclass2 cl2) {

        Newresponse rep = new Newresponse();
        try {
            this.bankservice.check(cl2);
            rep.setMessage("Transaction Successful");
            return ResponseEntity.ok(rep);

        } catch (Exception e) {
            e.printStackTrace();
            rep.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(rep);
        }
    }

    @GetMapping("getamount")
    public ResponseEntity getTotal() {
        try {
            return ResponseEntity.ok(this.bankservice.getAmount());
        } catch (Exception e) {
            Newresponse rep = new Newresponse();
            rep.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(rep);
        }
    }

    @GetMapping("getcredit")
    public ResponseEntity getCreditTotal() {
        try {
            return ResponseEntity.ok(this.bankservice.getAmountCredit());
        } catch (Exception e) {
            Newresponse rep = new Newresponse();
            rep.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(rep);
        }
    }

    @GetMapping("getdebit")
    public ResponseEntity getDebitTotal() {
        try {
            return ResponseEntity.ok(this.bankservice.getAmountDredit());
        } catch (Exception e) {
            Newresponse rep = new Newresponse();
            rep.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(rep);
        }

    }

    @GetMapping("getbankdetails")
    public ResponseEntity getbankdetails() {
        try {
            Bankresponse rep = new Bankresponse();
            rep.setTotal(this.bankservice.getAmount());
            rep.setCredit(this.bankservice.getAmountCredit());
            rep.setDebit(this.bankservice.getAmountDredit());
            return ResponseEntity.ok(rep);
        } catch (Exception e) {
            Newresponse rep = new Newresponse();
            rep.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(rep);
        }

    }

    @GetMapping("updateamount")
    public ResponseEntity getupdateAmount() {
        try {
            this.bankservice.getupdateAmount();
            return ResponseEntity.ok("Updated Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Newresponse rep = new Newresponse();
            rep.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(rep);
        }
    }
    @PostMapping("updateamount2")
    public ResponseEntity getupdateamount2(@RequestBody typeclass3 cl3){
        Newresponse rep = new Newresponse();
        try {
           double updatebalance= this.bankservice.updateamountbyID(cl3.getId(), cl3.getAmount());
           rep.setMessage("updatebalance" +updatebalance);
            return ResponseEntity.ok(rep);

        } catch (Exception e) {
            e.printStackTrace();
            rep.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(rep);
        }

    }
}
