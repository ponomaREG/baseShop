package com.test.baseshop.fragment_profile.orders;

import android.content.Context;

import java.util.HashMap;

public interface Interfaces {

    interface View{
        void setAdapter(RecyclerViewAdapterOrders adapter);
    }

    interface Presenter{
        void getData(Context context);

    }

    interface Model{
        HashMap<String, Object> getOrdersByID(int user_id);

    }

}
