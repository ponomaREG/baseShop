package com.test.baseshop.fragment_menu;

import android.content.Context;

import java.util.List;

public interface Interfaces {

    interface View{
        void setAdapter(RecyclerViewAdapter adapter);
        void updateRecyclerView();
        void setSections(int[] sections_codes);
        void showMinusIconAndNumberOfItemForOrder(int position);
        void hideMinusIconAndNumberOfItemForOrder(int position);
        void setNumberOfItemForOrder(int position, int number_of_item_for_order);
    }

    interface Presenter{
        void getData(Context context, int code);
        void getSections();
        void OnSectionItemClick(android.view.View v);
        String getTitleOfSectionByCode(int code);

        interface ConnectionBetweenViewAndRecyclerList {
            void tellViewToShowMinusIconAndNumberOfItemForOrder(int position);
            void tellViewToHideMinusIconAndNumberOfItemForOrder(int position);
            void tellViewToSetNumberOfItemForOrder(int position, int number_of_item_for_order);
        }
    }

    interface Model{
        List<Item> getItemsByFilter(int code);
        int[] getSections();

        interface Photo{
            void setImageInBackground(Item item);
            }
    }

}
