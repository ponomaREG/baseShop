package com.test.baseshop.anonim;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.test.baseshop.R;
import com.test.baseshop.auth.login;

import java.util.Objects;


public class fragment_anonim_basket extends Fragment {

    public fragment_anonim_basket() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static fragment_anonim_basket newInstance(){
        return new fragment_anonim_basket();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_anonim_basket,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initOclButtonEntry(view);

    }


    private void initOclButtonEntry(View view){
        Button button_entry = view.findViewById(R.id.fragment_anonim_basket_button_entry);
        button_entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).startActivity(new Intent(getContext(), login.class));
                Objects.requireNonNull(getActivity()).finish();
            }
        });
    }

}
