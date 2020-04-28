package com.test.baseshop.fragment_profile.addresses.add_new;

import android.content.Context;
import android.content.SharedPreferences;

import com.test.baseshop.model_helper.Address;

import java.util.Objects;

public class fragment_add_new_presenter implements Interfaces.Presenter {

    private Interfaces.View view;
    private Interfaces.Model model;
    private int USER_ID;

    fragment_add_new_presenter(BottomSheetAddNewAddress view){
        this.view = view;
        this.model = new fragment_add_new_model();

        initPreferences(Objects.requireNonNull(view.getContext()));
    }


    @Override
    public void OnButtonAddClick(String title, String district,
                                 String street, String house, String corpus,
                                 String porch, String floor, String flat)
    {
        //TODO:MAKE CHECK
        if(title.isEmpty()) view.showErrorTitleIsEmpty();
        else if(street.isEmpty()) view.showErrorStreetIsEmpty();
        else if(house.isEmpty()) view.showErrorHouseOfEmpty();
        else {
            Address new_address = new Address();
            new_address.setTitle(title)
                    .setDistrict(district)
                    .setStreet(street)
                    .setHouse(house)
                    .setCorpus(corpus)
                    .setPorch(porch)
                    .setFloor(floor)
                    .setFlat(flat);
            model.sendNewInfo(USER_ID,new_address);
            view.hideThisBottomSheet();
        }
    }





    private void initPreferences(Context context){
        SharedPreferences sh = context.getSharedPreferences("AUTH_PREF",Context.MODE_PRIVATE);
        USER_ID = sh.getInt("USER_ID",0);
    }
}
