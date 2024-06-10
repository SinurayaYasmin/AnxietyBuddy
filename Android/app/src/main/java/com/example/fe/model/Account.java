package com.example.fe.model;

import java.text.DecimalFormat;

public class Account {
    private String useremail;
    private String userpassword;
    private String gender;
    private String usertype;
    private String password;
    private String newPassword;
    private String emailorname;
    private String username;
    private Integer balance;
    private String imageUrl;


    public Account(String email, String password){
        this.emailorname = email;
        this.password = password;
    }

    public Account(){
    }

    public void forgotPass(String email, String password){
        this.emailorname = email;
        this.newPassword = password;
    }

    public Account(int balance){
        this.balance = balance;
    }

    public Account(String imageUrl){
        this.imageUrl = imageUrl;
    }


    public Account(String name, String email, String password, String gender, String usertype){
        this.useremail = email;
        this.username = name;
        this.userpassword = password;
        this.gender = gender;
        this.usertype = usertype;

    }
    public void setUseremail (String useremail){
        this.useremail = useremail;
    }
    public void setUserpassword(String userpassword){
        this.userpassword = userpassword;
    }
    public String getUseremail (){
        return this.useremail;
    }
    public String  getUserpassword (){
        return this.userpassword;
    }
}
