package com.test.baseshop.fragment_basket.offer_order;

import android.os.AsyncTask;

import com.test.baseshop.model_helper.Json;

import java.io.IOException;
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
    public Map sendOrder(int user_id, int address_id, int persons, String desc) {
        Map raw_result = null;
        json.jsonify_orders_add(user_id,address_id,persons,desc);
        return raw_result;
    }

    @Override
    public void getAddressesForUser(int user_id) {
        AsyncGetAddressesRemote getAddressesRemote = new AsyncGetAddressesRemote();
        getAddressesRemote.execute(user_id);
//        Map raw_map = null;
//        try {
//            raw_map = json.jsonify_addresses(user_id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return raw_map;
    }


    class AsyncGetAddressesRemote extends AsyncTask<Integer, Void,Void>{

        private Map raw_map;

        @Override
        protected Void doInBackground(Integer... integers) {
            try {
                raw_map = json.jsonify_addresses(integers[0]);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            presenter.setAddresses(raw_map);
        }
    }
}
