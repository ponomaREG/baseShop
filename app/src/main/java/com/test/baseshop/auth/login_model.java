package com.test.baseshop.auth;

import android.util.Log;

import com.test.baseshop.model_helper.Json;

import java.util.Map;

public class login_model implements Interfaces.Model{

    private Interfaces.Presenter presenter;
    private Json json;

    login_model(Interfaces.Presenter presenter){
        this.presenter = presenter;
        this.json = new Json();
    }

    @Override
    public Map authUserByCode(String phone,String code) { //0 - error / <0 (*-1 = id) - doesnt exist / >0 - user
        Map raw_map = null;
        try {
            raw_map = json.jsonify_user_auth(phone, code);
        }catch (Exception e){
            Log.d("ERROR","AUTH");
            return raw_map;
        }
        return raw_map;
    }


    @Override
    public void authUserGenCode(String phone) {
        Map raw_map =null;
        try{
            raw_map = json.jsonify_user_code(phone);
        }catch (Exception e){
            Log.d("ERROR","GEN CODE");
        }
        assert raw_map != null;
        int status = (int) (double) raw_map.get("status");
        Log.d("AUTH_STATUS_CODE",status+"");
        Log.d("AUTH_CODE",String.valueOf(raw_map.get("code")));
        presenter.showCode(String.valueOf(raw_map.get("code")));
    }
}
