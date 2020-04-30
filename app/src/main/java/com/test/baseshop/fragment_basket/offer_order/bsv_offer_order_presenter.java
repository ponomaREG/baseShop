package com.test.baseshop.fragment_basket.offer_order;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.baseshop.R;

import java.util.Map;
import java.util.Objects;

public class bsv_offer_order_presenter implements Interfaces.Presenter{

    private final int MAX_PERSONS = 4;
    private final int MIN_PERSONS = 1;

    private Interfaces.View view;
    private Interfaces.Model model;
    private View current_address;
    private int current_address_id;
    private int persons = MIN_PERSONS;

    private int USER_ID;

    bsv_offer_order_presenter(BottomSheetOfferOrder view){
        this.view = view;
        this.model = new bsv_offer_order_model(this);
        initPreferces(Objects.requireNonNull(view.getContext()));
        initStartPersons();
    }


    @Override
    public void OnAddressClick(View v) {
        if(current_address != null) current_address.setSelected(false);
        current_address = v;
        current_address.setSelected(true);
        current_address_id = (int) current_address.getTag();
    }

    @Override
    public void OnButtonOrderClick( String desc) {
        if(current_address != null){
            model.sendOrder(USER_ID,current_address_id, persons, desc);
            view.clearBasket();
            view.showDescOfOfferThatUserCanCheckStatusOfOrderInAnotherSection();
            view.dismissBSV();
        }else{
            view.showErrorEmptyAddress();
        }
    }

    @Override
    public void OnButtonPersonsPlusClick() {
        if(persons == MAX_PERSONS) view.showErrorUserWantsUpperThanMaxPersons();
        else persons++;
        view.showNewPersonsNumber(persons);
    }

    @Override
    public void OnButtonPersonsMinusClick() {
        if(persons == MIN_PERSONS) view.showErrorUserWantsZeroPersons();
        else {
            persons--;
            view.showNewPersonsNumber(persons);
        }
    }

    @Override
    public void getAddresses(LayoutInflater inflater, ViewGroup container) {
        Map raw_result = model.getAddressesForUser(USER_ID);
        if(raw_result == null){
            view.showErrorStrange();
            return;
        }
        int count_address = (int) (double) raw_result.get("count");
        if(count_address == 0) {
            view.showErrorZeroAddresses();
            view.setButtonForOrderDisabled();
        }
        else{
            raw_result = (Map) raw_result.get("data");
            assert raw_result != null;
            for(Object key: raw_result.keySet()) {
                Map raw_result_address = (Map) raw_result.get(key);
                View address_cell = inflater.inflate(R.layout.fragment_basket_bsv_order_cell_address, container, false);
                TextView address_textview = address_cell.findViewById(R.id.fragment_basket_bsv_container_addresses_cell_title);
                assert raw_result_address != null;
                address_textview.setText((String) raw_result_address.get("title"));
                address_textview.setTag(Integer.parseInt((String) key));
                address_textview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OnAddressClick(v);
                    }
                });
                view.addNewAddressCellInContainer(address_cell);
            }

        }
    }



    private void initStartPersons(){
        view.showNewPersonsNumber(persons);
    }

    private void initPreferces(Context context){
        SharedPreferences sh = context.getSharedPreferences("AUTH_PREF",Context.MODE_PRIVATE);
        USER_ID = sh.getInt("USER_ID",0);
    }
}
