package com.test.baseshop.fragment_menu;

public class Item {

    private String title = "TEST",desc = "TEST DESCRIPTION";
    private int weight = 1000, id, price;
    private String image_link = "TeSt";


    public Item(){
    }

    public Item setId(int id){this.id = id; return this;}
    public Item setTitle(String title){this.title = title; return this;}
    public Item setDesc(String desc){this.desc = desc; return this;}
    public Item setImageLink(String image_link){this.image_link = image_link; return this;}
    public Item setWeight(int weight){this.weight = weight; return this;}
    public Item setPrice(int price){this.price = price; return  this;}

    public int getId(){return this.id; }
    public String getTitle(){return this.title;}
    public String getDesc(){return this.desc;}
    public String getImage_link(){return this.image_link;}
    public int getWeight(){return this.weight;}
    public int getPrice(){return this.price;}
}
