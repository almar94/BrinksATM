package com.alma.brinksatm.Model;

public class User {

    private String Email, Password, Uid, userName;;

    public User(String email, String password, String uid, String userName) {
        Email = email;
        Password = password;
        Uid = uid;
        this.userName = userName;
    }

    public User() {
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
