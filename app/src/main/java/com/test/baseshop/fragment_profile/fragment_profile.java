package com.test.baseshop.fragment_profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.baseshop.R;

import java.util.Objects;

public class fragment_profile extends Fragment implements Interfaces.View{

    private Interfaces.Presenter presenter;
    private FragmentManager fm;


    public fragment_profile() {
    }
    // TODO: Rename and change types and number of parameters
    public static fragment_profile newInstance() {
        return new fragment_profile();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
        initFragmentManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initOclOfSectionLayouts();
    }

    private void initFragmentManager(){
        this.fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
    }

    private void initPresenter(){
        this.presenter = new fragment_profile_presenter(this);
    }

    private void initOclOfSectionLayouts(){

        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.OnSectionItemClick(v);
            }
        };

        Objects.requireNonNull(getView()).findViewById(R.id.fragment_profile_section_orders).setOnClickListener(ocl);
        getView().findViewById(R.id.fragment_profile_section_info).setOnClickListener(ocl);
        getView().findViewById(R.id.fragment_profile_section_addresses).setOnClickListener(ocl);
    }


    //first page
//    private void showFirstPageWhenLauch(){
//        int FIRST_SECTION_ID = R.id.fragment_profile_section_info;
//        presenter.OnSectionItemClick(Objects.requireNonNull(getView()).findViewById(FIRST_SECTION_ID));
//    }


    @Override
    public void showPage(Fragment fragment) {
        FragmentTransaction ft = fm.beginTransaction();
        if(!fm.popBackStackImmediate(fragment.getClass().getName(),0)) {
            ft.replace(R.id.fragment_profile_current_section, fragment);
            ft.addToBackStack(fragment.getClass().getName());
            ft.commit();
        }
    }
}
