package com.example.fe.model;

import java.text.DecimalFormat;

public class Account {
    private String useremail;
    String userid, userbalance, profile_picture;
    private String userpassword;
    private String gender;
    private String usertype;
    private String password;
    private String newPassword;
    private String emailorname;
    private String username;
    private Integer balance;
    private String imageUrl;
    private String userID;


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

    public Account(String userid, String name, String email, String gender, String password, String usertype, String balance, String picture){
        this.useremail = email;
        this.username = name;
        this.userID = userid;
        this.userbalance = balance;
        this.profile_picture = picture;
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

    public String getUserID (){
        return this.userID;
    }
}
