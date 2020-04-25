package com.test.baseshop.auth;

import android.widget.EditText;

import ru.tinkoff.decoro.watchers.FormatWatcher;

public interface Interfaces {

    interface View {
        void setMaskToEditText(FormatWatcher formatWatcher);
        void startNextActivity();
        void showError();
        void showOfferOfFillDesc();
    }

    interface Presenter {
           void makeMaskToEditText();
           void OnButtonClick(String input_phone);
    }

    interface Model {
        int authUserByPhone(String phone);

    }
}
