package com.fdmgroup.pojos;

import java.util.List;

public class Managers {

    private int id;
    private Long salary;
    private int age;
    private String name;
    private List<Staffs> staffs; 

    public Managers(){
        super();
    }

    public Managers(int id, Long salary, int age, String name, List<Staffs> staffs) {
        this.id = id;
        this.salary = salary;
        this.age = age;
        this.name = name;
        this.staffs = staffs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Staffs> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<Staffs> staffs) {
        this.staffs = staffs;
    }

    
    
}
