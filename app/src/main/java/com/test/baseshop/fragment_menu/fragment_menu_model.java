package com.test.baseshop.fragment_menu;

import java.util.ArrayList;
import java.util.List;

public class fragment_menu_model implements Interfaces.Model {

    private Interfaces.Presenter presenter;

    fragment_menu_model(Interfaces.Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public List<Item> getItemsByFilter(int code) {
        List<Item> arr = new ArrayList<>();
        switch (code){
            case fragment_menu_presenter.BURGERS:
                for(int i = 0;i<3;i++){arr.add(new Item().setTitle(String.valueOf(i)));}
                break;
            case fragment_menu_presenter.SUSHI:
                for(int i = 0;i<6;i++){arr.add(new Item().setTitle(String.valueOf(i)));}
                break;
            case fragment_menu_presenter.WOK:
                for(int i = 0;i<10;i++){arr.add(new Item().setTitle(String.valueOf(i)));}
                break;
            default:
                for(int i = 0;i<25;i++){arr.add(new Item().setTitle(String.valueOf(i)));}

        }
        return arr;
    }
}
