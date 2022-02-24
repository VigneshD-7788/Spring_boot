package com.Springboot_web_rest.Model;

import javax.persistence.*;

@Entity
@Table(name="employeetable")
public class newmodel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer employeeid;
    Integer employeesalary;
    String employeename, employeelocation;

    public Integer getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Integer employeeid) {
        this.employeeid = employeeid;
    }

    public Integer getEmployeesalary() {
        return employeesalary;
    }

    public void setEmployeesalary(Integer employeesalary) {
        this.employeesalary = employeesalary;
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    public String getEmployeelocation() {
        return employeelocation;
    }

    public void setEmployeelocation(String employeelocation) {
        this.employeelocation = employeelocation;
    }
}


