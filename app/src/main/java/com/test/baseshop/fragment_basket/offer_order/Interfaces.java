package com.test.baseshop.fragment_basket.offer_order;

import android.view.LayoutInflater;
import android.view.ViewGroup;


import java.util.Map;

public interface Interfaces {

    interface View{
        void showErrorEmptyAddress();
        void showErrorStrange();
        void showErrorZeroAddresses();
        void showErrorUserWantsZeroPersons();
        void showErrorUserWantsUpperThanMaxPersons();
        void clearBasket();
        void showDescOfOfferThatUserCanCheckStatusOfOrderInAnotherSection();
        void addNewAddressCellInContainer(android.view.View view);
        void setButtonForOrderDisabled();
        void dismissBSV();
        void showNewPersonsNumber(int persons);
    }

    interface Presenter{
        void OnAddressClick(android.view.View v);
        void OnButtonOrderClick(String desc);
        void OnButtonPersonsPlusClick();
        void OnButtonPersonsMinusClick();
        void getAddresses(LayoutInflater inflater, ViewGroup container);
    }

    interface Model{
        Map sendOrder(int user_id, int address_id, int persons, String desc);
        Map getAddressesForUser(int user_id);
    }
}
