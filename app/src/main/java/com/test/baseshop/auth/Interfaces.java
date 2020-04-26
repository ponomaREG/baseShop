package com.test.baseshop.auth;

import com.test.baseshop.auth.fill_info.BottomSheetFillInfo;

import ru.tinkoff.decoro.watchers.FormatWatcher;

public interface Interfaces {

    interface View {
        void setMaskToEditText(FormatWatcher formatWatcher);
        void startNextActivity();
        void showError();
        void showSectionOfPhoneCode();
        void showOfferOfFillDesc(BottomSheetFillInfo bottom_sheet_fill_info);
        void hideKeyboardAndClearFocus();
        void setSendAgainButton();
        void setTimer(String input_time);
        void makeSendAgainButtonClickable();
        void makeSendAgainButtonNotClickable();
    }

    interface Presenter {
           void makeMaskToEditText();
           void OnButtonClick(String input_phone);
    }

    interface Model {
        int authUserByPhone(String phone);

    }
}
