package com.test.baseshop.fragment_basket;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.baseshop.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_basket#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_basket extends Fragment {

    public fragment_basket() {
    }

    // TODO: Rename and change types and number of parameters
    public static fragment_basket newInstance() {
        return new fragment_basket();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_basket, container, false);
    }
}
