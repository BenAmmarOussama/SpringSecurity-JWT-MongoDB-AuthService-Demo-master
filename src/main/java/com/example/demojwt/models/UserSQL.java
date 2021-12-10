package com.example.demojwt.models;

public class UserSQL {
    protected String id;
    protected String name;
    protected String email;
    protected String tel;
    protected String department;
    protected String role;

    public UserSQL() {
    }

    public UserSQL(String id, String name, String email, String tel, String department, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.department = department;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
