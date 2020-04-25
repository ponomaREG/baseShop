package com.test.baseshop.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.test.baseshop.R;
import com.test.baseshop.baseActivityWithFragments;

import ru.tinkoff.decoro.watchers.FormatWatcher;

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
                presenter.OnButtonClick(editText.getText().toString());
            }
        };
        findViewById(R.id.login_button).setOnClickListener(ocl);
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
    public void showOfferOfFillDesc() {

    }
}
