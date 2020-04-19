package com.test.baseshop.fragment_menu;

import android.content.Context;
import android.view.View;

import java.util.List;

public class fragment_menu_presenter implements Interfaces.Presenter{

    private Interfaces.View view;
    private Interfaces.Model model;
    private View section_current = null;

    static final int ALL = 1, SUSHI = 2, PIZZA = 3, BURGERS = 5, DRINKS = 6, WOK = 4, SETS = 8;

    fragment_menu_presenter(Interfaces.View view){
        this.view = view;
        this.model = new fragment_menu_model(this);
    }


    @Override
    public void getData(Context context, int code) {
        List<Item> items = model.getItemsByFilter(code);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(context,items);
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
}
