package com.test.baseshop.fragment_basket.offer_order;

import com.test.baseshop.model_helper.Json;

import java.util.Map;

public class bsv_offer_order_model implements Interfaces.Model {

    private bsv_offer_order_presenter presenter;
    private Json json;

    bsv_offer_order_model(bsv_offer_order_presenter presenter){
        this.presenter = presenter;
        this.json = new Json();
    }

    @Override
    public Map sendOrder(int user_id, int address_id, String persons, String desc) {
        Map raw_result = null;
        json.jsonify_orders_add(user_id,address_id,persons,desc);
        return raw_result;
    }

    @Override
    public Map getAddressesForUser(int user_id) {
        Map raw_map = null;
        try {
            raw_map = json.jsonify_addresses(user_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return raw_map;
    }
}
