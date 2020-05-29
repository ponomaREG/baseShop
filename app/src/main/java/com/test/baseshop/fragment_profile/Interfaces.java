package com.test.baseshop.fragment_profile;

import androidx.fragment.app.Fragment;

public interface Interfaces {

    interface View{
        void showPage(Fragment fragment);
        void initStartPage(Fragment fragment);
    }

    interface Presenter{
        void OnSectionItemClick(android.view.View v);
        void getStartPage();
    }


}
