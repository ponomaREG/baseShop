package com.test.baseshop.fragment_menu;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public interface Interfaces {

    interface View{
        void setAdapter(RecyclerViewAdapter adapter);
        void updateRecyclerView();
        void setSections(String[] sections);
    }

    interface Presenter{
        void getData(Context context, int code);
        void getSections();
        void OnSectionItemClick(android.view.View v);
    }

    interface Model{
        List<Item> getItemsByFilter(int code);

    }

}
