package com.test.baseshop.fragment_profile.info;

import android.util.Log;

import com.test.baseshop.model_helper.Json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class fragment_info_model implements Interfaces.Model {

    private Interfaces.Presenter presenter;
    private Json json;

    fragment_info_model(Interfaces.Presenter presenter){
        this.presenter = presenter;
        this.json = new Json();
    }

    @Override
    public String sendNewInfoAboutUser(String key, String new_info, int user_id) {
        int result_of_query = json.jsonify_user_update(user_id,key,new_info);
        String message = null;
        if(result_of_query == 2){
            message = "Данный email уже существует";
        }else if(result_of_query == 3){
            message = "Данный номер уже зарегистрирован";
        }


        return message;
    }

    @Override
    public HashMap<String, String> getUserInfo(int user_id) {
        HashMap<String,String> user_info = new HashMap<>();
        Map raw_map;
        try {
           raw_map = json.jsonify_user(user_id);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("ERROR","USER INFO");
            return user_info;
        }
        int count = (int) (double) raw_map.get("count");
        if(count == 0) return user_info;
        ArrayList arrayListOfJson = (ArrayList) raw_map.get("data");
        assert arrayListOfJson != null;
        for(Object user:arrayListOfJson){
            Map user_info_map = (Map) user;
            String first_name = (String) user_info_map.get("first_name"); //TODO:FIX FOR
            String phone = (String) user_info_map.get("phone");
            String email = (String) user_info_map.get("email");
            user_info.put("first_name",first_name);
            user_info.put("phone",phone);
            user_info.put("email",email);
        }
        return user_info;
    }
}
