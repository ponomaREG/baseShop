package com.test.baseshop.fragment_menu;

public class Item {

    private String title = "TEST",desc = "TEST DESCRIPTION";
    private int weight = 1000, id;
    private String image_link = "TeSt";


    Item(){
    }

    Item setId(int id){this.id = id; return this;}
    Item setTitle(String title){this.title = title; return this;}
    Item setDesc(String desc){this.desc = desc; return this;}
    Item setImageLink(String image_link){this.image_link = image_link; return this;}
    Item setWeight(int weight){this.weight = weight; return this;}

    int getId(){return this.id; }
    String getTitle(){return this.title;}
    String getDesc(){return this.desc;}
    String getImage_link(){return this.image_link;}
    int getWeight(){return this.weight;}
}
