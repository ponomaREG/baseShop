package com.test.baseshop.fragment_profile.info;

public interface Interfaces {

    interface View{
        void showError(String message);
        void hideKeyboardAndClearFocus();
    }


    interface Presenter{
        void onFocusChanged(android.view.View v , boolean hasFocus, android.view.View parentView);
        void onCommitClick(android.view.View v, android.view.View parentView);
    }


    interface Model{
        String sendNewInfoAboutUser(String key, String new_info, int user_id);
    }


}
