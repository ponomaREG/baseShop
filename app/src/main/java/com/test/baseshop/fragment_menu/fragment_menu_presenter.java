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

    static final int ALL = 1;
    private static final int  SALMON = 2,
            TROUT = 3, PIKE = 4, TUNA = 5,
            LOBSTERS = 6, OYSTERS = 7, MUSSELS = 8,CARP = 9,COMBO =10;

    fragment_menu_presenter(fragment_menu view){
        this.view = view;
        this.model = new fragment_menu_model();
        initPreferences(Objects.requireNonNull(view.getContext()));
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
            case fragment_menu_presenter.SALMON:
                return "Лосось";
            case fragment_menu_presenter.CARP:
                return "Карп";
            case fragment_menu_presenter.TUNA:
                return "Тунец";
            case fragment_menu_presenter.TROUT:
                return "Форель";
            case fragment_menu_presenter.PIKE:
                return "Щука";
            case fragment_menu_presenter.MUSSELS:
                return "Мидии";
            case fragment_menu_presenter.OYSTERS:
                return "Устрицы";
            case fragment_menu_presenter.LOBSTERS:
                return "Лобстеры";
            case fragment_menu_presenter.COMBO:
                return "Наборы";
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
        if(USER_ID > 0)
        ((Interfaces.Model.Basket) model).sendNewNumberOfItemsForOrder(USER_ID,item_id,new_count_of_items_for_order);
    }

    @Override
    public void tellModelToDownloadImageOfItemAndSetToImageViewByItem(Item item) {
        ((Interfaces.Model.Photo) model).setImageWithPicasso(item);
    }

    @Override
    public HashMap<Integer,Integer> tellModelToGetBasketOfItemsForOrder() {
        if(USER_ID <= 0) return new HashMap<>();
        return ((Interfaces.Model.Basket) model).getBasketForUser(USER_ID);
    }


    private void initPreferences(Context context){
        SharedPreferences sh = context.getSharedPreferences("AUTH_PREF",Context.MODE_PRIVATE);
        USER_ID = sh.getInt("USER_ID",0);
    }
}
