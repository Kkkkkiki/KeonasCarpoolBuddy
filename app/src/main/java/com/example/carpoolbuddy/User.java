package com.example.carpoolbuddy;

import java.util.ArrayList;

public class User {
    private String uid;
    private String name;
    private String email;
    private String userType;
    private String password;
    private Double priceMultiplier;

    public User(String uid, String name, String email, String userType, Double priceMultiplier, String password, ArrayList<String> ownedVehicles) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.userType = userType;
        this.priceMultiplier = priceMultiplier;
        this.password = password;
        this.ownedVehicles = ownedVehicles;
    }

    public User(String email, String password, String uid){
        this.email = email;
        this.password = password;
        this.uid = uid;
    }

    public User() {
        uid = "some uid";
        name = "Keona";
        email = "CIS email";
        userType = "student";
        priceMultiplier = 1.0;
        password = "abcdefu";
    }


    ArrayList<String> ownedVehicles;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Double getPriceMultiplier() {
        return priceMultiplier;
    }

    public void setPriceMultiplier(Double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }

    public ArrayList<String> getOwnedVehicles() {
        return ownedVehicles;
    }

    public void setOwnedVehicles(ArrayList<String> ownedVehicles) {
        this.ownedVehicles = ownedVehicles;
    }


}