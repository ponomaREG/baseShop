package com.test.baseshop.fragment_profile.addresses;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.baseshop.R;

public class fragment_addresses extends Fragment implements Interfaces.View{

    private fragment_addresses_presenter presenter;

    public fragment_addresses() {
        // Required empty public constructor
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
        return inflater.inflate(R.layout.fragment_profile_fragment_addreses, container, false);
    }

    private void initPresenter(){
        this.presenter = new fragment_addresses_presenter(this);
    }

}
