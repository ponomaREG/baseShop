package com.test.baseshop.auth.fill_info;

import java.util.Map;

public interface Interfaces {

    interface View{
        void showErrorEmptySex();
        void showErrorEmptyName();
        void showErrorUnknown();
        void startNextActivity();

    }


    interface Presenter{
        void OnButtonCommitClick(String name, int checkedIdRB_OfSex, String email);
    }


    interface Model{
        Map addNewUser(String name, String phone, String email , String sex);
    }


}
