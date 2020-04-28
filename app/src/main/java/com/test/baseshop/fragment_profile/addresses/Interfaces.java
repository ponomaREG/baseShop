package com.test.baseshop.fragment_profile.addresses;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.test.baseshop.fragment_profile.addresses.add_new.BottomSheetAddNewAddress;
import com.test.baseshop.model_helper.Address;

import java.util.List;

public interface Interfaces {


    interface View{
        void addNewCellOfAddressView(android.view.View v);
        void showBottomSheetAddNewAddress(BottomSheetAddNewAddress bottomSheetAddNewAddress);
        void updateCurrentAddressesIfNewIsAdded();
    }

    interface Presenter{
        void getAddresses(LayoutInflater inflater, ViewGroup parent);
    }

    interface Model{
         List<Address> getAddresses(int user_id);
    }
}
