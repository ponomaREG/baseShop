package com.test.baseshop.fragment_menu;



import com.test.baseshop.model_helper.Item;
import com.test.baseshop.model_helper.Json;
import com.test.baseshop.model_helper.PhotoDownloader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class fragment_menu_model implements Interfaces.Model, Interfaces.Model.Photo, Interfaces.Model.Basket{

    private Interfaces.Presenter presenter;
    private Json json;

    fragment_menu_model(Interfaces.Presenter presenter){
        this.presenter = presenter;
        this.json = new Json();
    }

    @Override
    public List<Item> getItemsByFilter(int code) {
        List<Item> arr = null;
        try {
            arr = json.jsonify(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    @Override
    public int[] getSections() {
        return new int[]{1,2,3,4,5,6,8};
    }

    @Override
    public void setImageInBackground(Item item) {
        try {
            new PhotoDownloader().execute(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap<Integer,Integer> getBasketForUser(int user_id) {
        HashMap<Integer,Integer> items_whichs_already_in_basket = new HashMap<>();
        Map raw_map_result_of_basket_query;
        try {
            raw_map_result_of_basket_query = json.jsonify_basket(user_id);
        } catch (Exception e) {
            e.printStackTrace();
            return items_whichs_already_in_basket;
        }
        if((int) (double) raw_map_result_of_basket_query.get("count") == 0) return items_whichs_already_in_basket;
        raw_map_result_of_basket_query = (Map) raw_map_result_of_basket_query.get("data");
        assert raw_map_result_of_basket_query != null;
        for(Object key:raw_map_result_of_basket_query.keySet()){
            Map map_in = (Map) raw_map_result_of_basket_query.get(key);
            assert map_in != null;
            items_whichs_already_in_basket.put(Integer.parseInt((String) key), (int) (double) map_in.get("count"));
        }
        return items_whichs_already_in_basket;
    }

    @Override
    public void sendNewNumberOfItemsForOrder(int user_id, int item_id, int count) {
//        Map raw_map_status;
        try {
            json.jsonify_basket(user_id,item_id,count);
//            raw_map_status = json.jsonify_basket(user_id,item_id,count);
//            int status_code = (int) (double) raw_map_status.get("status");
//            Log.d("Status code of query",String.valueOf(status_code));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
