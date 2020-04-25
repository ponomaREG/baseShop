package com.test.baseshop.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import ru.tinkoff.decoro.MaskImpl;
import ru.tinkoff.decoro.parser.PhoneNumberUnderscoreSlotsParser;
import ru.tinkoff.decoro.watchers.FormatWatcher;
import ru.tinkoff.decoro.watchers.MaskFormatWatcher;

public class login_presenter implements Interfaces.Presenter {

    private Interfaces.View view;
    private Interfaces.Model model;
    private Context context;

    private MaskImpl mask_phone;

    login_presenter(login view){
        this.view = view;
        this.model = new login_model(this);
        this.context = view;
    }



    @Override
    public void makeMaskToEditText() {
        mask_phone =  MaskImpl.createTerminated(new PhoneNumberUnderscoreSlotsParser().parseSlots("+7 (___) ___-__-__"));
        FormatWatcher watcher = new MaskFormatWatcher(
                mask_phone
        );
        view.setMaskToEditText(watcher);
    }

    @Override
    public void OnButtonClick(String input_phone) {
        mask_phone.insertFront(input_phone);
        int user_id_OR_status_of_query = model.authUserByPhone(input_phone);
        if(user_id_OR_status_of_query == 0){
            view.showError();
        }else if (user_id_OR_status_of_query < 0){
            view.showOfferOfFillDesc();
        }else{
            initPreferences(this.context, user_id_OR_status_of_query);
            view.startNextActivity();
        }
    }


    private void initPreferences(Context context, int user_id){
        SharedPreferences sf = context.getSharedPreferences("AUTH_PREF",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putInt("USER_ID",user_id);
        editor.apply();
    }
}
