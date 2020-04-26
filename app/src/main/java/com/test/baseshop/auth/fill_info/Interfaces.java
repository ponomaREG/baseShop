package com.test.baseshop.auth.fill_info;

public interface Interfaces {

    interface View{
        void showErrorEmptySex();
        void showErrorEmptyName();

    }


    interface Presenter{
        void OnButtonCommitClick(String name, int checkedIdRB_OfSex, String email);
    }


    interface Model{

    }


}
