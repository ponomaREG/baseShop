package com.test.baseshop.fragment_profile.orders;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.baseshop.R;


public class fragment_orders extends Fragment {

    public fragment_orders() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static fragment_orders newInstance(String param1, String param2) {
        fragment_orders fragment = new fragment_orders();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     return inflater.inflate(R.layout.fragment_profile_fragment_orders,container,false);
    }
}
