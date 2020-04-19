package com.test.baseshop.fragment_menu;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public interface Interfaces {

    interface View{
        void setAdapter(RecyclerViewAdapter adapter);
        void updateRecyclerView();
        void setSections(int[] sections_codes);
    }

    interface Presenter{
        void getData(Context context, int code);
        void getSections();
        void OnSectionItemClick(android.view.View v);
        String getTitleOfSectionByCode(int code);
    }

    interface Model{
        List<Item> getItemsByFilter(int code);
        int[] getSections();

    }

}
