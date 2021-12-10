package com.example.demojwt.models;

public class UserReq extends UserSQL {
    private String password;

    public UserReq() {
    }

    public UserReq(String id, String name, String email, String tel,
                   String department, String role, String password) {
        super(id, name, email, tel, department, role);
        this.password = password;
    }

    public UserSQL getUserSQL(String id){
        return new UserSQL(id, name, email, tel, department, role);
    }

    public UserMongoDB getUserMongoDB(){
        return new UserMongoDB(id, name, email, password, role);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
