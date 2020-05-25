package com.test.baseshop.fragment_menu;



import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.test.baseshop.model_helper.Item;
import com.test.baseshop.model_helper.Json;
import com.test.baseshop.model_helper.PhotoDownloader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class fragment_menu_model implements Interfaces.Model, Interfaces.Model.Photo, Interfaces.Model.Basket{

    Interfaces.Presenter presenter;
    private Json json;

    fragment_menu_model(Interfaces.Presenter presenter){
        this.presenter = presenter;
        this.json = new Json();
    }

    @Override
    public void getItemsByFilter(int code) {

        AsyncGetItemsMenuRemote getItemsByFilter = new AsyncGetItemsMenuRemote();
        getItemsByFilter.execute(code);
//        List<Item> arr = null;
//        try {
//            arr = json.jsonify(code);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return arr;
    }

    @Override
    public int[] getSections() {
        return new int[]{2,3,4,5,6,7,8,9,10};
    }

    @Override
    public void setImageInBackground(Item item) {
        try {
//            new PhotoDownloader().execute(item);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setImageWithPicasso(Item item) {
        new PhotoDownloader().setImageWithPicasso(item);
    }

    @Override
    public HashMap<Integer,Integer> getBasketForUser(int user_id) {
        HashMap<Integer,Integer> items_whichs_already_in_basket = new HashMap<>();
        Map raw_map_result_of_basket_query;
        try {
            raw_map_result_of_basket_query = json.jsonify_basket_async(user_id);
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



    class AsyncGetItemsMenuRemote extends AsyncTask<Integer, Void, Void>{

        private List<Item> items = new ArrayList<>();


        @Override
        protected Void doInBackground(Integer... integers) {
            Map data = null;
            try {
                data = json.jsonify(integers[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(data.containsKey("count")) {
                if ((int) (double) data.get("count") == 0) return null;
            }else return null;
            ArrayList jsonArrayOfItem = (ArrayList) data.get("data");
            if(jsonArrayOfItem == null) return null;
            for(Object item_obj:jsonArrayOfItem){
                Map map = (Map) item_obj;
                Item item = new Item();
                int id = (int) (double) map.get("id");
                String title = (String) map.get("title");
                String desc = (String) map.get("desc");
                int price = (int) (double) map.get("price");
                int weight = (int) (double) map.get("weight");
                int section = (int) (double) map.get("section");
                Log.d("Title",title);
                item.setId(id)
                        .setTitle(title)
                        .setDesc(desc)
                        .setPrice(price)
                        .setSection(section)
                        .setWeight(weight);
                items.add(item);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            presenter.setDataFromModel(items);
        }
    }

}
