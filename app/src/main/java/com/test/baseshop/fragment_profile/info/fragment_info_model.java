package com.test.baseshop.fragment_profile.info;

public class fragment_info_model implements Interfaces.Model {

    private Interfaces.Presenter presenter;


    fragment_info_model(Interfaces.Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public String sendNewInfoAboutUser(String key, String new_info, int user_id) {
        int result_of_query = 0;
        String message = null;
        if(result_of_query == 1){
            message = "Данный email уже существует";
        }else if(result_of_query == 2){
            message = "Данный номер уже зарегистрирован";
        }
        return message;
    }
}
