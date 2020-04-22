package com.test.baseshop.model_helper;

import android.widget.ImageView;

public class Item {

    private String title = "TEST",desc = "TEST DESCRIPTION";
    private int weight = 1000, id, price , number_of_item_for_order = 0;
    private String image_link = "TeSt";
    private ImageView image_view_of_icon;


    public Item(){
    }

    public Item setId(int id){this.id = id; return this;}
    public Item setTitle(String title){this.title = title; return this;}
    public Item setDesc(String desc){this.desc = desc; return this;}
    public Item setImageLink(String image_link){this.image_link = image_link; return this;}
    public Item setWeight(int weight){this.weight = weight; return this;}
    public Item setPrice(int price){this.price = price; return  this;}
    public Item setImageView(ImageView view){this.image_view_of_icon = view; return this;}
    public Item setNumberOfItemForOrder(int number_of_item_for_order){this.number_of_item_for_order = number_of_item_for_order;return this;}

    public int getId(){return this.id; }
    public String getTitle(){return this.title;}
    public String getDesc(){return this.desc;}
    public String getImage_link(){return this.image_link;}
    public int getWeight(){return this.weight;}
    public int getPrice(){return this.price;}
    public ImageView getImageViewOfIcon(){return this.image_view_of_icon;}
    public int getNumberOfItemForOrder(){return this.number_of_item_for_order;}
}
