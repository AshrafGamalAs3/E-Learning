package com.example.e_learning;

public class Users {
     String name,email,password,userType,UserId;



    public Users() {
    }

    public Users(String name, String email, String password, String userType, String userId) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;

         this.UserId = userId;
    }

    public String getUserId() {
        return UserId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public String getUserType() {
        return userType;
    }



}
