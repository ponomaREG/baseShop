package com.test.baseshop.auth.fill_info;

import android.util.Log;

import com.test.baseshop.model_helper.Json;

import java.util.Map;

public class fill_info_model implements Interfaces.Model{

    private Json json;

    fill_info_model(){
        this.json = new Json();
    }


    @Override
    public Map addNewUser(String name, String phone, String email, String sex) {
        Map raw_map = null;
        try{
            raw_map = json.jsonify_user_add(name,phone,email,sex);
        }catch (Exception e){
            Log.d("ERROR","ADD USER");
        }
        return raw_map;
    }
}
