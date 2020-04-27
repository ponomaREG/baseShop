package com.test.baseshop;

import android.content.Context;
import android.view.MenuItem;

import androidx.fragment.app.Fragment;

public interface baseInterfaceMVP {

    interface Presenter{
        void OnItemClick(int page);
        void setIconBySexOfUser(Context context, MenuItem item);
    }


    interface View{
        void showPage(Fragment fragment);
    }


    interface Model{



    }



}
