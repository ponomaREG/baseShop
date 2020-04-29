package com.test.baseshop.fragment_basket.offer_order;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.baseshop.model_helper.Address;

import java.util.List;
import java.util.Map;

public interface Interfaces {

    interface View{
        void showErrorEmptyAddress();
        void showErrorStrange();
        void showErrorZeroAddresses();
        void clearBasket();
        void showDescOfOfferThatUserCanCheckStatusOfOrderInAnotherSection();
        void addNewAddressCellInContainer(android.view.View view);
    }

    interface Presenter{
        void OnAddressClick(android.view.View v);
        void OnButtonOrderClick();
        void getAddresses(LayoutInflater inflater, ViewGroup container);
    }

    interface Model{
        Map sendOrder(int user_id, int address_title);
        Map getAddressesForUser(int user_id);
    }
}
