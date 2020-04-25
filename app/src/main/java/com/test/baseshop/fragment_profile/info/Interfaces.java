package com.test.baseshop.fragment_profile.info;

import java.util.HashMap;
import java.util.Map;

public interface Interfaces {

    interface View{
        void showError(String message);
        void hideKeyboardAndClearFocus();
        void setFirstName(String firstName);
        void setEmail(String email);
        void setPhone(String phone);
    }


    interface Presenter{
        void onFocusChanged(android.view.View v , boolean hasFocus, android.view.View parentView);
        void onCommitClick(android.view.View v, android.view.View parentView);
        void getUserInfo();
    }


    interface Model{
        String sendNewInfoAboutUser(String key, String new_info, int user_id);
        HashMap<String,String> getUserInfo(int user_id);
    }


}
