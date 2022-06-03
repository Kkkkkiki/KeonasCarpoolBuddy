package com.example.carpoolbuddy;

import java.util.ArrayList;

public class Vehicle {

    private String owner;
    private String model;
    private int capacity;
    private String vehicleID;
    ArrayList<String> riderUIDs;
    private boolean open;
    private String age;
    private double basePrice;
    private String email;

    public String getEmail() {
        return email;
    }

    public Vehicle(String owner, String model, int capacity, String age, String email) {
        this.owner = owner;
        this.model = model;
        this.capacity = capacity;
        this.age = age;
        this.email = email;
        vehicleID = String.valueOf((int)(Math.random()*10000));
    }

    public Vehicle() {
        this.owner = "owner";
        this.model = "model";
        this.capacity = 3;
        this.age = "44";
        vehicleID = String.valueOf((int)(Math.random()*10000));
    }

    public Vehicle(String owner, String model, int capacity, String vehicleID, ArrayList<String> riderUIDs, boolean open, String age, double basePrice) {
        this.owner = owner;
        this.model = model;
        this.capacity = capacity;
        this.vehicleID = vehicleID;
        this.riderUIDs = riderUIDs;
        this.open = open;
        this.age = age;
        this.basePrice = basePrice;
    }

    public String getOwner() {
        return owner;
    }

    public String openToString() {
        return "The status is" +
                 open+ "";
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getModel() {
        return model;
    }


    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public ArrayList<String> getRiderUIDs() {
        return riderUIDs;
    }

    public void setRiderUIDs(ArrayList<String> riderUIDs) {
        this.riderUIDs = riderUIDs;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

}
