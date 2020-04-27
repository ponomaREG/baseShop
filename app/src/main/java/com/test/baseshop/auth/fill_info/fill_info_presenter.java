package com.test.baseshop.auth.fill_info;

import android.content.Context;
import android.content.SharedPreferences;

import com.test.baseshop.R;

import java.util.Map;

public class fill_info_presenter implements Interfaces.Presenter {


    private Interfaces.View view;
    private Interfaces.Model model;
    private Context context;
    private String phone;

    fill_info_presenter(BottomSheetFillInfo view , String phone) {
        this.view = view;
        this.model = new fill_info_model();
        this.context = view.getContext();
        this.phone = phone;
    }


    @Override
    public void OnButtonCommitClick(String name, int checkedIdRB_OfSex, String email) {
        int sex_of_user;
        if (!name.isEmpty()) {
            if (checkedIdRB_OfSex == R.id.login_fillInfo_rb_man) sex_of_user = 1;
            else if (checkedIdRB_OfSex == R.id.login_fillInfo_rb_woman) sex_of_user = 0;
            else {
                view.showErrorEmptySex();
                return;
            }
        } else {
            view.showErrorEmptyName();
            return;
        }
        Map result = model.addNewUser(name,phone,email,String.valueOf(sex_of_user));
        int status = (int) (double) result.get("status");
        if(status == 1){
            int user_id = (int) (double) result.get("user");
            initPreferencesOfSex(sex_of_user);
            initPreferences(context,user_id);
            view.startNextActivity();
        }
        else{
            view.showErrorUnknown();
        }
    }

    private void initPreferencesOfSex(int sex_of_user) {
        SharedPreferences sh = context.getSharedPreferences("AUTH_PREF", Context.MODE_PRIVATE);
        sh.edit().putInt("USER_SEX", sex_of_user).apply();
    }

    private void initPreferences(Context context, int user_id){
        SharedPreferences sf = context.getSharedPreferences("AUTH_PREF",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putInt("USER_ID",user_id);
        editor.apply();
    }
}