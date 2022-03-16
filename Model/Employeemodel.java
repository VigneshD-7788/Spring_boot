package com.Springboot_web_rest.Model;

import javax.persistence.*;

@Entity
@Table(name="employee_details")
public class Employeemodel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer employee_id;
    Integer employee_salary;
    @Column(insertable = false,updatable = false)
    String employee_name;
    String employee_email;
    String employee_location;

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public Integer getEmployee_salary() {
        return employee_salary;
    }

    public void setEmployee_salary(Integer employee_salary) {
        this.employee_salary = employee_salary;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_email() {
        return employee_email;
    }

    public void setEmployee_email(String employee_email) {
        this.employee_email = employee_email;
    }

    public String getEmployee_location() {
        return employee_location;
    }

    public void setEmployee_location(String employee_location) {
        this.employee_location = employee_location;
    }

}
