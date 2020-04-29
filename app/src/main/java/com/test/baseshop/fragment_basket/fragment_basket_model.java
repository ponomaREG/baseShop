package com.test.baseshop.fragment_basket;


import com.test.baseshop.model_helper.Item;
import com.test.baseshop.model_helper.Json;
import com.test.baseshop.model_helper.PhotoDownloader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class fragment_basket_model implements Interfaces.Model.Basket,Interfaces.Model.Photo{

    private Json json;

    fragment_basket_model(){
        this.json = new Json();
    }



    @Override
    public List<Item> getDataOFBasketInfoRemote(int user_id) {
        List<Item> list_of_items = new ArrayList<>();
        Map raw_result_of_query;
        try {
            raw_result_of_query = json.jsonify_basket(user_id);
        } catch (Exception e) {
            e.printStackTrace();
            return list_of_items;
        }
        int count_of_items_in_basket = (int) (double) raw_result_of_query.get("count");
        if(count_of_items_in_basket == 0) return list_of_items;
        raw_result_of_query = (Map) raw_result_of_query.get("data");
        assert raw_result_of_query != null;
        for(Object key:raw_result_of_query.keySet()){
            Map  raw_map_of_curent_item = (Map) raw_result_of_query.get(key);
            Item item = new Item();
            assert raw_map_of_curent_item != null;
            item.setId(Integer.parseInt((String) key))
                    .setTitle((String) raw_map_of_curent_item.get("title"))
                    .setDesc((String) raw_map_of_curent_item.get("desc"))
                    .setNumberOfItemForOrder((int) (double) raw_map_of_curent_item.get("count"))
                    .setPrice((int) (double) raw_map_of_curent_item.get("price"))
                    .setWeight((int) (double) raw_map_of_curent_item.get("weight"));
            list_of_items.add(item);
        }

        return list_of_items;
    }

    @Override
    public void sendNewNumberOfItemsForOrder(int user_id, int item_id, int count_of_items_for_order) {
//        Map raw_map_status;
        try {
            json.jsonify_basket(user_id,item_id,count_of_items_for_order);
//            raw_map_status = json.jsonify_basket(user_id,item_id,count_of_items_for_order);
//            int status_code = (int) (double) raw_map_status.get("status");
//            Log.d("Status code of query",String.valueOf(status_code));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Deprecated
//    @Override
//    public void sendThatUserWantToMakeOrder(int user_id) {
//        json.jsonify_orders_add(user_id);
//    }

    @Override
    public void setImageInBackground(Item item) {
        try {
            new PhotoDownloader().execute(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setImageWithPicasso(Item item) {
        new PhotoDownloader().setImageWithPicasso(item);
    }
}
