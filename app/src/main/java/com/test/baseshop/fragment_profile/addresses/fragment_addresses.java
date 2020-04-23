package com.test.baseshop.fragment_profile.addresses;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.baseshop.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_addresses#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_addresses extends Fragment {

    public fragment_addresses() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static fragment_addresses newInstance() {
        fragment_addresses fragment = new fragment_addresses();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_fragment_addreses, container, false);
    }
}
