package com.example.fe.model;

public class Medicine {
    private String medicinecode;
    private String medicinename;
    private int price;
    private String description;
    private String dosage;
    private int quantity;
    private String medicinetype;

    public Medicine(String medicinename, int price, String medicinetype){
        this.medicinetype = medicinetype;
        this.medicinename = medicinename;
        this.price = price;
    }
    public Medicine(String medicinetype){
        this.medicinetype = medicinetype;
    }

    public Medicine(String medicinecode, String medicinename, int price, String description, String dosage, int quantity, String medicinetype){
        this.medicinecode = medicinecode;
        this.medicinename = medicinename;
        this.price = price;
        this.description = description;
        this.dosage= dosage;
        this.quantity= quantity;
        this.medicinetype= medicinetype;


    }

    public String getName (){
        return this.medicinename;
    }
    public String getCode (){
        return this.medicinecode;
    }
    public int getPrice (){
        return this.price;
    }
}


