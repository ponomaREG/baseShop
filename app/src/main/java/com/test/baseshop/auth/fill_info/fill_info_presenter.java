package com.test.baseshop.auth.fill_info;

import android.content.Context;
import android.content.SharedPreferences;

import com.test.baseshop.R;

public class fill_info_presenter implements Interfaces.Presenter{


    private Interfaces.View view;
    private Interfaces.Model model;
    private Context context;

    fill_info_presenter(BottomSheetFillInfo view){
        this.view = view;
        this.context = view.getContext();
    }


    @Override
    public void OnButtonCommitClick(String name, int checkedIdRB_OfSex, String email) {
        if(!name.isEmpty()){
            int sex_of_user;
            if(checkedIdRB_OfSex == R.id.login_fillInfo_rb_man) sex_of_user = 1;
            else if (checkedIdRB_OfSex == R.id.login_fillInfo_rb_woman) sex_of_user = 0;
            else{
                view.showErrorEmptySex();
                return;
            }
            initPreferencesOfSex(sex_of_user);
        }else{
            view.showErrorEmptyName();
        }
    }

    private void initPreferencesOfSex(int sex_of_user){
        SharedPreferences sh = context.getSharedPreferences("AUTH_PREF",Context.MODE_PRIVATE);
        sh.edit().putInt("USER_SEX",sex_of_user).apply();
    }
}
