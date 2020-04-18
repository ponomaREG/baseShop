package com.test.baseshop;

import androidx.fragment.app.Fragment;

public interface baseInterfaceMVP {

    interface Presenter{
        void OnItemClick(int page);

    }


    interface View{
        void showPage(Fragment fragment);
    }


    interface Model{



    }



}
