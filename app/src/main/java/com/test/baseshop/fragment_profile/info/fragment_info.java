package com.test.baseshop.fragment_profile.info;

import android.content.Context;
import android.inputmethodservice.InputMethodService;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        View parent = inflater.inflate(R.layout.fragment_profile_fragment_info,container,false);
        ImageView user_icon = parent.findViewById(R.id.fragment_profile_fragment_info_usericon);
        presenter.setImageBySexOfUser(Objects.requireNonNull(getContext()),user_icon);
        return parent;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initOfclToEdittexts();
        initOclToCommitInEdittext();

        presenter.getUserInfo();
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

    private void initOclToCommitInEdittext(){
        ImageView commit_name = Objects.requireNonNull(getView()).findViewById(R.id.fragment_profile_fragment_info_name_commit);
        ImageView commit_email = getView().findViewById(R.id.fragment_profile_fragment_info_email_commit);
        ImageView commit_phone = getView().findViewById(R.id.fragment_profile_fragment_info_phone_commit);

        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onCommitClick(v,getView());
            }
        };

        commit_name.setOnClickListener(ocl);
        commit_email.setOnClickListener(ocl);
        commit_phone.setOnClickListener(ocl);
    }


    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }



    @Override
    public void hideKeyboardAndClearFocus() {
        View v = Objects.requireNonNull(getActivity()).getCurrentFocus();
        InputMethodManager ims = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        assert ims != null;
        assert v != null;
        ims.hideSoftInputFromWindow(v.getWindowToken(),0);
        v.clearFocus();
    }

    @Override
    public void setFirstName(String firstName) {
        EditText first_name_view = Objects.requireNonNull(getView()).findViewById(R.id.fragment_profile_fragment_info_name);
        first_name_view.setText(firstName);
    }

    @Override
    public void setEmail(String email) {
        EditText email_view = Objects.requireNonNull(getView()).findViewById(R.id.fragment_profile_fragment_info_email);
        email_view.setText(email);
    }

    @Override
    public void setPhone(String phone) {
        EditText phone_view = Objects.requireNonNull(getView()).findViewById(R.id.fragment_profile_fragment_info_phone);
        phone_view.setText(phone);
    }
}
