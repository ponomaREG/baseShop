package com.test.baseshop.auth;

import android.util.Log;

import com.test.baseshop.model_helper.Json;

import java.util.Map;

public class login_model implements Interfaces.Model{

    private Interfaces.Presenter presenter;
    private Json json;

    login_model(Interfaces.Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public int authUserByPhone(String phone) { //0 - error / <0 (*-1 = id) - doesnt exist / >0 - user
        Map raw_map;
        int user_id = 0;
        try {
            raw_map = json.jsonify_user_auth(phone);
        }catch (Exception e){
            Log.d("ERROR","AUTH");
            return user_id;
        }
        user_id = (int) (double) raw_map.get("status");
        return user_id;
    }
}
