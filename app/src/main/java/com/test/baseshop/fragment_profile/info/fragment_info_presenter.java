package com.test.baseshop.fragment_profile.info;

import android.view.View;

import com.test.baseshop.R;

import java.util.Objects;

public class fragment_info_presenter implements Interfaces.Presenter {

    private Interfaces.View view;
    private Interfaces.Model model;


    fragment_info_presenter(Interfaces.View view){
        this.view = view;
        this.model = new fragment_info_model(this);
    }


    @Override
    public void onFocusChanged(View v, boolean hasFocus, View parentView) {
        int VISIBILITY;
        int id_of_commit_button = -1;
        if(hasFocus){
            VISIBILITY = View.VISIBLE;
        }else{
            VISIBILITY = View.INVISIBLE;
        }

        switch (v.getId()){
            case R.id.fragment_profile_fragment_info_email:
                id_of_commit_button = R.id.fragment_profile_fragment_info_email_commit;
                break;
            case R.id.fragment_profile_fragment_info_phone:
                id_of_commit_button = R.id.fragment_profile_fragment_info_phone_commit;
                break;
            case R.id.fragment_profile_fragment_info_name:
                id_of_commit_button = R.id.fragment_profile_fragment_info_name_commit;
                break;
        }
        Objects.requireNonNull(parentView).findViewById(id_of_commit_button).setVisibility(VISIBILITY);
    }
}
