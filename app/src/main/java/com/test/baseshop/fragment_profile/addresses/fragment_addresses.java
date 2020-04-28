package com.test.baseshop.fragment_profile.addresses;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.test.baseshop.R;
import com.test.baseshop.fragment_profile.addresses.add_new.BottomSheetAddNewAddress;

import java.util.Objects;
//TODO:ADD ADDRESS
public class fragment_addresses extends Fragment implements Interfaces.View{

    private fragment_addresses_presenter presenter;


    public fragment_addresses() {
    }

    // TODO: Rename and change types and number of parameters
    public static fragment_addresses newInstance() {
        return new fragment_addresses();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      return inflater.inflate(R.layout.fragment_profile_fragment_addresses, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.getAddresses(getLayoutInflater(), (ViewGroup) view.findViewById(R.id.fragment_profile_fragment_addresses_container));
    }

    private void initPresenter(){
        this.presenter = new fragment_addresses_presenter(this);
    }



    @Override
    public void addNewCellOfAddressView(View v) {
        LinearLayout container_ll = Objects.requireNonNull(getView()).findViewById(R.id.fragment_profile_fragment_addresses_container);
        container_ll.addView(v);
    }

    @Override
    public void showBottomSheetAddNewAddress(BottomSheetAddNewAddress bottomSheetAddNewAddress) {
        bottomSheetAddNewAddress.setCancelable(true);
        assert getFragmentManager() != null;
        bottomSheetAddNewAddress.show(getFragmentManager(),BottomSheetAddNewAddress.TAG);
    }

    @Override
    public void updateCurrentAddressesIfNewIsAdded(){
        assert getFragmentManager() != null;
        getFragmentManager()
                .beginTransaction()
                .detach(fragment_addresses.this)
                .attach(fragment_addresses.this)
                .commit();
    }
}
