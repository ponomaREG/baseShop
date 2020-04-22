package com.test.baseshop.fragment_menu;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.test.baseshop.model_helper.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class fragment_menu_presenter implements Interfaces.Presenter, Interfaces.Presenter.ConnectionBetweenViewAndRecyclerList, Interfaces.Presenter.ConnectionBetweenModelAndRecyclerList{

    private Interfaces.View view;
    private Interfaces.Model model;
    private View section_current = null;
    private int USER_ID;

    static final int ALL = 1, SUSHI = 2, PIZZA = 3, BURGERS = 5, DRINKS = 6, WOK = 4, SETS = 8;

    fragment_menu_presenter(fragment_menu view){
        this.view = view;
        this.model = new fragment_menu_model(this);
        SharedPreferences sh = Objects.requireNonNull(view.getContext()).getSharedPreferences("AUTH_PREF",Context.MODE_PRIVATE);
        USER_ID = sh.getInt("USER_ID",0);
    }


    @Override
    public void getData(Context context, int code) {
        List<Item> items = model.getItemsByFilter(code);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(context,items,(Interfaces.Model.Photo) model, this);
        view.setAdapter(adapter);
        view.updateRecyclerView();
    }

    @Override
    public void getSections() {
        int[] sections = model.getSections();
        view.setSections(sections);
    }

    @Override
    public void OnSectionItemClick(View v) {
        if(section_current != null) section_current.setSelected(false);
        v.setSelected(true);
        section_current = v;
    }

    @Override
    public String getTitleOfSectionByCode(int code) {
        switch(code){
            case fragment_menu_presenter.ALL:
                return "Все";
            case fragment_menu_presenter.SUSHI:
                return "Роллы";
            case fragment_menu_presenter.PIZZA:
                return "Пицца";
            case fragment_menu_presenter.WOK:
                return "Воки";
            case fragment_menu_presenter.BURGERS:
                return "Бургеры";
            case fragment_menu_presenter.DRINKS:
                return "Напитки";
            case fragment_menu_presenter.SETS:
                return "Сеты";
        }
        return null;
    }

    @Override
    public void tellViewToShowMinusIconAndNumberOfItemForOrder(int position) {
        view.showMinusIconAndNumberOfItemForOrder(position);
        }

    @Override
    public void tellViewToHideMinusIconAndNumberOfItemForOrder(int position) {
        view.hideMinusIconAndNumberOfItemForOrder(position);
    }

    @Override
    public void tellViewToSetNumberOfItemForOrder(int position, int number_of_item_for_order) {
        view.setNumberOfItemForOrder(position,number_of_item_for_order);
    }

    @Override
    public void tellModelToSetNewNumberOfItemsForOrder( int item_id, int new_count_of_items_for_order) {
        ((Interfaces.Model.Basket) model).sendNewNumberOfItemsForOrder(USER_ID,item_id,new_count_of_items_for_order);
    }

    @Override
    public HashMap<Integer,Integer> tellModelToGetBasketOfItemsForOrder() {
        return ((Interfaces.Model.Basket) model).getBasketForUser(USER_ID);
    }
}
