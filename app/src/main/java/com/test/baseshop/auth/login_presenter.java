package com.test.baseshop.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.test.baseshop.auth.fill_info.BottomSheetFillInfo;
import com.test.baseshop.fragment_basket.offer_order.BottomSheetOfferOrder;

import java.util.Map;
import java.util.Objects;

import ru.tinkoff.decoro.MaskImpl;
import ru.tinkoff.decoro.parser.PhoneNumberUnderscoreSlotsParser;
import ru.tinkoff.decoro.watchers.FormatWatcher;
import ru.tinkoff.decoro.watchers.MaskFormatWatcher;

public class login_presenter implements Interfaces.Presenter{

    private Interfaces.View view;
    private Interfaces.Model model;
    private Context context;
    private String phone;

    private MaskImpl mask_phone;

    login_presenter(login view){
        this.view = view;
        this.model = new login_model(this);
        this.context = view;
    }



    @Override
    public void makeMaskToEditText() {
        mask_phone =  MaskImpl.createTerminated(new PhoneNumberUnderscoreSlotsParser().parseSlots("+7 (___) ___–__–__"));
        FormatWatcher watcher = new MaskFormatWatcher(
                mask_phone
        );
        view.setMaskToEditText(watcher);
    }

    @Override
    public void OnButtonClick(String input_phone) {
        mask_phone.insertFront(input_phone);
        this.phone = mask_phone.toUnformattedString();
        view.hideKeyboardAndClearFocus();
        sendCode();
        view.showSectionOfPhoneCode();
        }

    @Override
    public void sendCode() {
        model.authUserGenCode(this.phone);
    }

    @Override
    public void checkIfUserAlreadyAuth() {
        SharedPreferences sf = context.getSharedPreferences("AUTH_PREF",Context.MODE_PRIVATE);
        int USER_ID = sf.getInt("USER_ID",-1);
        if(USER_ID != -1){
            view.startNextActivity();
        }
    }

    @Override
    public void checkIsCorrectCode(String code) {
        Map result = model.authUserByCode(phone,code);
        int status = (int) (double) result.get("status");
        if(status != -1){
            if(status == 0){
                view.showOfferOfFillDesc(BottomSheetFillInfo.newInstance(this.phone));
            }else{
                int user = (int) (double) result.get("user");
                initPreferences(context,user);
                view.startNextActivity();
            }
        }else{
            view.showErrorIncorrectCode();
        }
    }

    @Override
    public void showCode(String code) {
        Toast.makeText(context,code,Toast.LENGTH_SHORT).show();
    }


    private void initPreferences(Context context, int user_id){
        SharedPreferences sf = context.getSharedPreferences("AUTH_PREF",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putInt("USER_ID",user_id);
        editor.apply();
}

}
