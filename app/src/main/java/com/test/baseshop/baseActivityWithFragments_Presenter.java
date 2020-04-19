package com.test.baseshop;

import android.content.Context;

import com.test.baseshop.fragment_basket.fragment_basket;
import com.test.baseshop.fragment_menu.fragment_menu;
import com.test.baseshop.fragment_profile.fragment_profile;

public class baseActivityWithFragments_Presenter implements baseInterfaceMVP.Presenter{


    private baseInterfaceMVP.View main_view;
    private fragment_basket basket;
    private fragment_menu menu;
    private fragment_profile profile;




    baseActivityWithFragments_Presenter(Context context){
        this.main_view = (baseInterfaceMVP.View) context;
        this.basket = fragment_basket.newInstance();
        this.menu = fragment_menu.newInstance();
        this.profile = fragment_profile.newInstance();
        main_view.showPage(this.menu);
    }



    @Override
    public void OnItemClick(int item_id) {
        switch (item_id){
            case R.id.menu_main_menu:
                main_view.showPage(this.menu);
                break;
            case R.id.menu_main_basket:
                main_view.showPage(this.basket);
                break;
            case R.id.menu_main_profile:
                main_view.showPage(this.profile);
                break;
        }
    }


}
