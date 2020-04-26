package com.test.baseshop.auth.fill_info;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.test.baseshop.R;

import java.util.Objects;


public class BottomSheetFillInfo extends BottomSheetDialogFragment implements Interfaces.View{

    private fill_info_presenter presenter;

    public static final String TAG = "ActionBottomDialog";

    public static BottomSheetFillInfo newInstance() {

        return new BottomSheetFillInfo();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fill_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initPresenter();
        initOclToButton();
    }


    private void initPresenter(){
        this.presenter =new fill_info_presenter(this);
    }


    private void initOclToButton(){
        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup rg = Objects.requireNonNull(getView()).findViewById(R.id.login_fillInfo_sex_rg);
                EditText editText_name = getView().findViewById(R.id.login_fillInfo_name);
                EditText editText_email = getView().findViewById(R.id.login_fillInfo_email);
                presenter.OnButtonCommitClick(
                        editText_name.getText().toString(),
                        rg.getCheckedRadioButtonId(),
                        editText_email.getText().toString()
                );
            }
        };

        Button button_commit = Objects.requireNonNull(getView()).findViewById(R.id.login_fillInfo_button_commit);

        button_commit.setOnClickListener(ocl);

    }



    @Override
    public void showErrorEmptySex() {
        Toast.makeText(getContext(),"Выберите пол",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorEmptyName() {
        Toast.makeText(getContext(),"Заполните поле имени",Toast.LENGTH_SHORT).show();
    }
}
