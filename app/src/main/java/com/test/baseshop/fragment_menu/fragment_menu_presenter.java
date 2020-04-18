package com.test.baseshop.fragment_menu;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

public class fragment_menu_presenter implements Interfaces.Presenter{

    private Interfaces.View view;
    private Interfaces.Model model;
    private View section_current = null;

    static final int ALL = 0, SUSHI = 1, PIZZA = 2, BURGERS = 3, DRINKS = 4, WOK = 5;

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
        String[] sections = new String[]{"Картошка", "Бургеры","Роллы","Суши","Пицца","Напитки","Кофе","Воки","Комбо","Акции",};
        view.setSections(sections);
    }

    @Override
    public void OnSectionItemClick(View v) {
        int code_section = (int) v.getTag();
        if(section_current != null) section_current.setSelected(false);
        v.setSelected(true);
        section_current = v;
    }
}
