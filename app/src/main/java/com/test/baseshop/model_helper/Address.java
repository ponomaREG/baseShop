package com.test.baseshop.model_helper;

public class Address {
    private String title , district, street, house , corpus , porch , floor , flat;



    public Address setTitle(String title){this.title = title; return this;}
    public Address setDistrict(String district){this.district = district; return this;}
    public Address setStreet(String street){this.street = street; return this;}
    public Address setHouse(String house){this.house = house; return this;}
    public Address setCorpus(String corpus){this.corpus = corpus; return this;}
    public Address setPorch(String porch){this.porch = porch; return this;}
    public Address setFloor(String floor){this.floor = floor; return this;}
    public Address setFlat(String flat){this.flat = flat; return this;}

    public String getTitle(){ return this.title; }
    public String getDistrict(){ return this.district; }
    public String getStreet(){ return this.street; }
    public String getHouse(){ return this.house; }
    public String getCorpus(){ return this.corpus; }
    public String getPorch(){ return this.porch;}
    public String getFloor(){ return this.floor;}
    public String getFlat(){ return this.flat;}




}
