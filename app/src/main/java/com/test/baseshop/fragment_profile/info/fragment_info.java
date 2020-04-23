package com.test.baseshop.fragment_profile.info;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.test.baseshop.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_info#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_info extends Fragment implements Interfaces.View{

    private fragment_info_presenter presenter;


    public fragment_info() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static fragment_info newInstance(String param1, String param2) {
        fragment_info fragment = new fragment_info();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_fragment_info,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initOfclToEdittexts();
    }


    private void initPresenter(){ this.presenter = new fragment_info_presenter(this);}


    private void initOfclToEdittexts(){
        EditText name_edit = Objects.requireNonNull(getView()).findViewById(R.id.fragment_profile_fragment_info_name);
        EditText email_edit = getView().findViewById(R.id.fragment_profile_fragment_info_email);
        EditText phone_edit = getView().findViewById(R.id.fragment_profile_fragment_info_phone);

        View.OnFocusChangeListener ofcl = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                presenter.onFocusChanged(v, hasFocus, getView());
            }
        };

        name_edit.setOnFocusChangeListener(ofcl);
        email_edit.setOnFocusChangeListener(ofcl);
        phone_edit.setOnFocusChangeListener(ofcl);

    }
}
