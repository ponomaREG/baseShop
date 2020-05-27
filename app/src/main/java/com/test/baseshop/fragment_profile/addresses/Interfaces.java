package com.test.baseshop.fragment_profile.addresses;

import android.view.ViewGroup;

import com.test.baseshop.fragment_profile.addresses.add_new.BottomSheetAddNewAddress;
import com.test.baseshop.model_helper.Address;

import java.util.List;

import io.reactivex.Observable;

public interface Interfaces {


    interface View{
        void addNewCellOfAddressView(android.view.View v);
        void showBottomSheetAddNewAddress(BottomSheetAddNewAddress bottomSheetAddNewAddress);
        void updateCurrentAddressesIfNewIsAdded();
        void hideProgressBar();
    }

    interface Presenter{
        void getAddresses(ViewGroup parent);
        void setAddresses(List<Address> addresses);
        void onButtonNewAddressClick();
    }

    interface Model{
         void getAddresses(int user_id);
         interface RX{
             Observable<List<Address>> getAddresses(int user_id);
         }
    }
}
