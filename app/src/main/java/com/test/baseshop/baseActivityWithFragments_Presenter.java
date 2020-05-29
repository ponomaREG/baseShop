package com.test.baseshop;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.MenuItem;

import com.test.baseshop.fragment_basket.fragment_basket;
import com.test.baseshop.fragment_menu.fragment_menu;
import com.test.baseshop.fragment_profile.fragment_profile;
import com.test.baseshop.anonim.fragment_anonim_basket;

public class baseActivityWithFragments_Presenter implements baseInterfaceMVP.Presenter{


    private baseInterfaceMVP.View main_view;
    private fragment_basket basket;
    private fragment_menu menu;
    private fragment_profile profile;
    private fragment_anonim_basket anonim;
    private int USER_ID;







    baseActivityWithFragments_Presenter(Context context){
        this.main_view = (baseInterfaceMVP.View) context;

        this.basket = fragment_basket.newInstance();
        this.menu = fragment_menu.newInstance();
        this.profile = fragment_profile.newInstance();
        this.anonim = fragment_anonim_basket.newInstance();

        initPreferences(context);

//        main_view.showPage(this.menu);

    }



    @Override
    public void OnItemClick(int item_id) {
        switch (item_id){
            case R.id.menu_main_menu:
                main_view.showPage(this.menu);
                break;
            case R.id.menu_main_basket:
                if(USER_ID == -1) main_view.showPage(this.anonim);
                else main_view.showPage(this.basket);
                break;
            case R.id.menu_main_profile:
                if(USER_ID == -1) main_view.showPage(this.anonim);
                else main_view.showPage(this.profile);
                break;
        }
    }

    private void initPreferences(Context context){
        SharedPreferences sh = context.getSharedPreferences("AUTH_PREF",Context.MODE_PRIVATE);
        USER_ID = sh.getInt("USER_ID",-1);
        Log.d("USER,",USER_ID+"");
    }


    @Override
    public void setIconBySexOfUser(Context context, MenuItem item) {
        SharedPreferences sh = context.getSharedPreferences("AUTH_PREF",Context.MODE_PRIVATE);
        int sex_of_user = sh.getInt("USER_SEX",-1);
        if(sex_of_user == 0) item.setIcon(context.getDrawable(R.drawable.bnv_menu_icon_profile_woman));
    }

    @Override
    public void getStartPage() {
        main_view.initDefaultSelectedMenuItem(R.id.menu_main_menu);
        main_view.initStartPage(this.menu);
    }

}
