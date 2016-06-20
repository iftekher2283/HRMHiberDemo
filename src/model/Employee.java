/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author students
 */
@Entity
public class Employee {
    @Id
    private int id;
    private String name;
    private String designation;
    private String joiningDate;

    public Employee() {
    }

    public Employee(int id, String name, String designation, String joiningDate) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.joiningDate = joiningDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name=" + name + ", designation=" + designation + ", joiningDate=" + joiningDate + '}';
    }
    
    
}
