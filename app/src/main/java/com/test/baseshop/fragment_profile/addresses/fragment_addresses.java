package com.test.baseshop.fragment_profile.addresses;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.test.baseshop.R;
import com.test.baseshop.fragment_profile.addresses.add_new.BottomSheetAddNewAddress;

import java.util.Objects;
//TODO:ADD ADDRESS
public class fragment_addresses extends Fragment implements Interfaces.View{

    private fragment_addresses_presenter presenter;

    private View view;
    private boolean isLoaded = false;


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
        if(!isLoaded)
        view = inflater.inflate(R.layout.fragment_profile_fragment_addresses, container, false);
      return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(!isLoaded) {
            presenter.getAddresses((ViewGroup) view.findViewById(R.id.fragment_profile_fragment_addresses_container));
            initOclToButtonNewAddress();
            isLoaded = true;
        }
    }

    private void initPresenter(){
        this.presenter = new fragment_addresses_presenter(this);
    }

    private void initOclToButtonNewAddress(){
        View parent = getView();
        if(parent != null){
            View plusButton = parent.findViewById(R.id.fragment_profile_fragment_addresses_plus);
            plusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.onButtonNewAddressClick();
                }
            });
        }
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
        bottomSheetAddNewAddress.show(getChildFragmentManager(),BottomSheetAddNewAddress.TAG);
    }

    @Override
    public void updateCurrentAddressesIfNewIsAdded(){
        LinearLayout container_addresses = Objects.requireNonNull(getView()).findViewById(R.id.fragment_profile_fragment_addresses_container);
        container_addresses.removeAllViews();
        presenter.getAddresses(container_addresses);
    }

    @Override
    public void hideProgressBar() {
        View parent = getView();
        if(parent != null) {
            ProgressBar progressBar = parent.findViewById(R.id.fragment_profile_fragment_addresses_progressBar);
            progressBar.setVisibility(View.GONE);
        }
    }
}
