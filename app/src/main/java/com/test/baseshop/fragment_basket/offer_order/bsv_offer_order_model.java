package com.test.baseshop.fragment_basket.offer_order;

import com.test.baseshop.model_helper.Address;
import com.test.baseshop.model_helper.Json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class bsv_offer_order_model implements Interfaces.Model {

    private bsv_offer_order_presenter presenter;
    private Json json;

    bsv_offer_order_model(bsv_offer_order_presenter presenter){
        this.presenter = presenter;
        this.json = new Json();
    }

    @Override
    public Map sendOrder(int user_id, int address_id) {
        Map raw_result = null;

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
