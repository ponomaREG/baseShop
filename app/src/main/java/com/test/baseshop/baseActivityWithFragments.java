package com.test.baseshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class baseActivityWithFragments extends AppCompatActivity implements baseInterfaceMVP.View{


    private baseInterfaceMVP.Presenter main_presenter;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        prepareView();
        initFragmentManager();
        initPresenter();
        setListenerToNavigationView();
    }

    private void prepareView(){
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
    }

    private void initPresenter(){
        main_presenter = new baseActivityWithFragments_Presenter(this);
    }

    private void initFragmentManager(){
        fm = this.getSupportFragmentManager();
    }


    private void setListenerToNavigationView(){
        BottomNavigationView bnv = findViewById(R.id.bottom_navigation);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                main_presenter.OnItemClick(menuItem.getItemId());
                return false;
            }
        });
    }



    @Override
    public void showPage(Fragment fragment) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.current_page,fragment);
        ft.commit();
    }
}
