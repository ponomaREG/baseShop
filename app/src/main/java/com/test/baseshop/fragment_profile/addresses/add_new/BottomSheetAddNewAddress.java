package com.test.baseshop.fragment_profile.addresses.add_new;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.test.baseshop.R;
import com.test.baseshop.fragment_profile.addresses.fragment_addresses;



public class BottomSheetAddNewAddress extends BottomSheetDialogFragment implements Interfaces.View{

    public static String TAG = "ActionAddNewAddress";

    private Interfaces.Presenter presenter;

    // TODO: Customize parameters
    public static BottomSheetAddNewAddress newInstance() {
        return new BottomSheetAddNewAddress();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = new fragment_add_new_presenter(this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_profile_fragment_addresses_add_new_bsv, container, false);
        Button button_add_new_address = v.findViewById(R.id.fragment_profile_fragment_addresses_add_new_button_for_add);
        button_add_new_address.setOnClickListener(getOclForButtonForAdd(v));
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }


    private View.OnClickListener getOclForButtonForAdd(final View parent){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText title = parent.findViewById(R.id.fragment_profile_fragment_addresses_add_new_et_title);
                EditText district = parent.findViewById(R.id.fragment_profile_fragment_addresses_add_new_et_district);
                EditText street = parent.findViewById(R.id.fragment_profile_fragment_addresses_add_new_et_street);
                EditText house = parent.findViewById(R.id.fragment_profile_fragment_addresses_add_new_et_house);
                EditText corpus = parent.findViewById(R.id.fragment_profile_fragment_addresses_add_new_et_corpus);
                EditText porch = parent.findViewById(R.id.fragment_profile_fragment_addresses_add_new_et_porch);
                EditText floor = parent.findViewById(R.id.fragment_profile_fragment_addresses_add_new_et_floor);
                EditText flat = parent.findViewById(R.id.fragment_profile_fragment_addresses_add_new_et_flat);
                presenter.OnButtonAddClick(
                        title.getText().toString(), district.getText().toString(), street.getText().toString(),
                        house.getText().toString(), corpus.getText().toString(),porch.getText().toString(),
                        floor.getText().toString(), flat.getText().toString()
                );
            }
        };
    }


    @Override
    public void showErrorStreetIsEmpty() {
        Toast.makeText(getContext(),"Небходимо заполнить улицу",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorHouseOfEmpty() {
        Toast.makeText(getContext(),"Небходимо заполнить дом",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorTitleIsEmpty() {
        Toast.makeText(getContext(),"Необходимо заполнить название",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideThisBottomSheet() {
        try{
            assert getParentFragment() != null;
            ((fragment_addresses) getParentFragment()).updateCurrentAddressesIfNewIsAdded();
        }catch (Exception e){
            Log.d("PARENT FRAGMENT","WHAT THE FUCK");
        }
        this.dismiss();
    }

}
