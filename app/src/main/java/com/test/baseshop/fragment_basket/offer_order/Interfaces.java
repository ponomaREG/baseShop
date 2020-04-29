package com.test.baseshop.fragment_basket.offer_order;

import android.view.LayoutInflater;
import android.view.ViewGroup;


import java.util.Map;

public interface Interfaces {

    interface View{
        void showErrorEmptyAddress();
        void showErrorStrange();
        void showErrorZeroAddresses();
        void clearBasket();
        void showDescOfOfferThatUserCanCheckStatusOfOrderInAnotherSection();
        void addNewAddressCellInContainer(android.view.View view);
        void setButtonForOrderDisabled();
        void dismissBSV();
    }

    interface Presenter{
        void OnAddressClick(android.view.View v);
        void OnButtonOrderClick(String persons, String desc);
        void getAddresses(LayoutInflater inflater, ViewGroup container);
    }

    interface Model{
        Map sendOrder(int user_id, int address_id, String persons, String desc);
        Map getAddressesForUser(int user_id);
    }
}
