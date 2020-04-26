package com.test.baseshop.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.test.baseshop.R;
import com.test.baseshop.auth.fill_info.BottomSheetFillInfo;
import com.test.baseshop.baseActivityWithFragments;

import java.util.Objects;

import ru.tinkoff.decoro.watchers.FormatWatcher;
//TODO:MVP
public class login extends AppCompatActivity implements Interfaces.View {

    private login_presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        initPresenter();
        initOclToButton();

        presenter.makeMaskToEditText();
    }


    private void initPresenter(){
        this.presenter = new login_presenter(this);
    }


    private void initOclToButton(){
        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.login_numberInput);
                setDisabledButtonOfLogin();
                presenter.OnButtonClick(editText.getText().toString());
            }
        };
        findViewById(R.id.login_button).setOnClickListener(ocl);
    }

    private void initEditTextListener(){

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditText et_first = findViewById(R.id.login_code_firstNumber);
                EditText et_second = findViewById(R.id.login_code_secondNumber);
                EditText et_third = findViewById(R.id.login_code_thirdNumber);
                EditText et_fourth = findViewById(R.id.login_code_fourthNumber);

                if(et_first.getText().toString().length() == 1) et_second.requestFocus();
                if(et_second.getText().toString().length() == 1) et_third.requestFocus();
                if(et_third.getText().toString().length() == 1) et_fourth.requestFocus();
                if(et_fourth.getText().toString().length() == 1) {
                    //TODO:MAKE BUTTON CLEAR
                    hideKeyboardAndClearFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        EditText et_first = findViewById(R.id.login_code_firstNumber);
        EditText et_second = findViewById(R.id.login_code_secondNumber);
        EditText et_third = findViewById(R.id.login_code_thirdNumber);
        EditText et_fourth = findViewById(R.id.login_code_fourthNumber);

        et_first.addTextChangedListener(watcher);
        et_second.addTextChangedListener(watcher);
        et_third.addTextChangedListener(watcher);
        et_fourth.addTextChangedListener(watcher);

    }


    @Override
    public void setMaskToEditText(FormatWatcher formatWatcher) {
        EditText editText = findViewById(R.id.login_numberInput);
        formatWatcher.installOn(editText);
    }

    @Override
    public void startNextActivity() {
        startActivity(new Intent(this, baseActivityWithFragments.class));
        finish();
    }

    @Override
    public void showError() {
        String message = "Произошла непредвиденная ошибка. Пожалуйста , попробуйте позже";
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSectionOfPhoneCode() {
        initEditTextListener();
        findViewById(R.id.login_container_code).setVisibility(View.VISIBLE);
        initTimerToAgainSendCode();
        initOclToSendAgainCode();
    }

    @Override
    public void showOfferOfFillDesc(BottomSheetFillInfo bottom_sheet_fill_info) {
        bottom_sheet_fill_info.setCancelable(false);
        bottom_sheet_fill_info.show(getSupportFragmentManager(), BottomSheetFillInfo.TAG);
    }


    private void initTimerToAgainSendCode(){
        final TextView timer_AND_send_again_code = findViewById(R.id.login_timer_AND_button_send_again);
        timer_AND_send_again_code.setClickable(false);


        new CountDownTimer(61000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                String minutes = String.valueOf(millisUntilFinished/1000/60);
                String seconds = String.valueOf(millisUntilFinished/1000 - (millisUntilFinished/1000/60)*60);

                if(Integer.parseInt(seconds)<10) seconds = "0" + seconds;
                if(Integer.parseInt(minutes)<10) minutes = "0" + minutes;

                timer_AND_send_again_code.setText(String.format(
                        "%s:%s",
                        minutes,
                        seconds
                ));
            }

            @Override
            public void onFinish() {
                timer_AND_send_again_code.setClickable(true);
                timer_AND_send_again_code.setText("Отправить еще раз");
            }
        }.start();
    }


    private void initOclToSendAgainCode(){
        TextView timer_AND_send_again_code = findViewById(R.id.login_timer_AND_button_send_again);
        timer_AND_send_again_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initTimerToAgainSendCode();
            }
        });
    }

    @Override
    public void hideKeyboardAndClearFocus() {
        View v = Objects.requireNonNull(getCurrentFocus());
        InputMethodManager ims = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        assert ims != null;
        assert v != null;
        ims.hideSoftInputFromWindow(v.getWindowToken(),0);
        v.clearFocus();
    }


    private void setEnabledButtonOfLogin(){
        findViewById(R.id.login_button).setEnabled(true);
    }


    private void setDisabledButtonOfLogin(){
        findViewById(R.id.login_button).setEnabled(false);
    }



//TODO:OPTIMIZE
    @Override
    public void setSendAgainButton() {
        TextView timer_AND_send_again_code = findViewById(R.id.login_timer_AND_button_send_again);
        timer_AND_send_again_code.setText("Отправить еще раз");
    }

    @Override
    public void setTimer(String input_time) {
        TextView timer_AND_send_again_code = findViewById(R.id.login_timer_AND_button_send_again);
        timer_AND_send_again_code.setText(input_time);
    }

    @Override
    public void makeSendAgainButtonClickable() {
        TextView timer_AND_send_again_code = findViewById(R.id.login_timer_AND_button_send_again);
        timer_AND_send_again_code.setClickable(true);
    }

    @Override
    public void makeSendAgainButtonNotClickable() {
        TextView timer_AND_send_again_code = findViewById(R.id.login_timer_AND_button_send_again);
        timer_AND_send_again_code.setClickable(false);
    }

}
