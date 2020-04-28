package com.test.baseshop.fragment_profile.addresses.add_new;

import com.test.baseshop.model_helper.Address;

public interface Interfaces {

    interface View{
        void showErrorStreetIsEmpty();
        void showErrorHouseOfEmpty();
        void showErrorTitleIsEmpty();
        void hideThisBottomSheet();
    }

    interface Presenter{
        void OnButtonAddClick(
                String title, String district, String street,
                String house , String corpus, String porch,
                String floor, String flat
        );
    }

    interface Model{
        void sendNewInfo(int USER_ID, Address address);
    }
}
