package com.test.baseshop.fragment_profile.orders;

import android.content.Context;

import java.util.HashMap;

public interface Interfaces {

    interface View{
        void setAdapter(RecyclerViewAdapterOrders adapter);
        void hideProgressBar();
        void showMessageEmptyOrders();
    }

    interface Presenter{
        void getData();
        void setAdapter(HashMap<String, Object> orders);
    }

    interface Model{
        void getOrdersByID(int user_id);

    }

}
