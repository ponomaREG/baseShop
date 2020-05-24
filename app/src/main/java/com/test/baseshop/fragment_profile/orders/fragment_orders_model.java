package com.test.baseshop.fragment_profile.orders;

import android.os.AsyncTask;
import android.util.Log;

import com.test.baseshop.model_helper.Item;
import com.test.baseshop.model_helper.Json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class fragment_orders_model implements Interfaces.Model{

    private Json json = new Json();
    private  Interfaces.Presenter presenter;

    fragment_orders_model(Interfaces.Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void getOrdersByID(int user_id) {
        AsyncRemoteGetOrders getOrdersAsyns = new AsyncRemoteGetOrders();
        getOrdersAsyns.execute(user_id);
//        Map raw_result;
//        try {
//            raw_result = json.jsonify_orders(user_id);
//        }catch (Exception e){
//            Log.d("Error","Orders");
//            return null;
//        }
//        if((int) (double) raw_result.get("count") == 0){return null;}
//        HashMap<String, Object> hashMap_of_result = new HashMap<>();
//        raw_result = (Map) raw_result.get("data");
//        assert raw_result != null;
//        hashMap_of_result.put("total", raw_result.get("total"));
//        hashMap_of_result.put("orders_numbers",raw_result.get("orders_numbers"));
//        for(Object key:raw_result.keySet()){
////            raw_result.remove("desc_order"); // TODO:FIX
////            raw_result.remove("desc_order");
//            if(!(key).equals("total") &&!(key).equals("orders_numbers")&&!(key).equals("desc_order")&&!(key).equals("persons")&&!(key).equals("status")) {
//                Map item_map = (Map) raw_result.get(key);
//
//                assert item_map != null;
//                List<Item> items = new ArrayList<>();
//                for(Object key_item:item_map.keySet()) {
//                    Log.d("KEY", (String) key);
//                    Map item = (Map) item_map.get(key_item);
//                    Item new_lot_in_previously_orders = new Item();
//                    assert item != null;
//                    new_lot_in_previously_orders.setTitle((String) item.get("title"))
//                            .setDesc((String) item.get("desc"))
//                            .setNumberOfItemForOrder((int) (double) item.get("count"))
//                            .setPrice((int) (double) item.get("price"))
//                            .setWeight((int) (double) item.get("weight"))
//                            .calculateTotalPrice();
//                    items.add(new_lot_in_previously_orders);
//                }
//                hashMap_of_result.put((String) key,items);
//            }
//        }
    }


    class AsyncRemoteGetOrders extends AsyncTask<Integer, Void ,Void>{
        private HashMap<String, Object> orders = new HashMap<>();

        @Override
        protected Void doInBackground(Integer... integers) {
            Map raw_result;
            try {
                raw_result = json.jsonify_orders(integers[0]);
            }catch (Exception e){
                Log.d("Error","Orders");
                return null;
            }
            if((int) (double) raw_result.get("count") == 0){return null;}
            raw_result = (Map) raw_result.get("data");
            assert raw_result != null;
            orders.put("total", raw_result.get("total"));
            orders.put("orders_numbers",raw_result.get("orders_numbers"));
            for(Object key:raw_result.keySet()){
//            raw_result.remove("desc_order"); // TODO:FIX
//            raw_result.remove("desc_order");
                if(!(key).equals("total") &&!(key).equals("orders_numbers")&&!(key).equals("desc_order")&&!(key).equals("persons")&&!(key).equals("status")) {
                    Map item_map = (Map) raw_result.get(key);

                    assert item_map != null;
                    List<Item> items = new ArrayList<>();
                    for(Object key_item:item_map.keySet()) {
                        Log.d("KEY", (String) key);
                        Map item = (Map) item_map.get(key_item);
                        Item new_lot_in_previously_orders = new Item();
                        assert item != null;
                        new_lot_in_previously_orders.setTitle((String) item.get("title"))
                                .setDesc((String) item.get("desc"))
                                .setNumberOfItemForOrder((int) (double) item.get("count"))
                                .setPrice((int) (double) item.get("price"))
                                .setWeight((int) (double) item.get("weight"))
                                .calculateTotalPrice();
                        items.add(new_lot_in_previously_orders);
                    }
                    orders.put((String) key,items);
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            presenter.setAdapter(orders);
        }
    }
}
