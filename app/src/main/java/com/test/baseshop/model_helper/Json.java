package com.test.baseshop.model_helper;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;


import com.google.gson.Gson;
import com.test.baseshop.fragment_menu.Item;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Json {

    private final static String
            SEARCH_API = "http://161.35.108.15:8000/app/items?section_code=%s",
            GET_BASKET_API = "http://161.35.108.15:8000/basket/get?user=%s";

    private Json instance;

    public Json(){
    }

    public Json getInstance(){
        return instance; }

    public List<Item> jsonify(int section_code) throws ExecutionException, InterruptedException {
        GetDataFromBackground searchInfo = new GetDataFromBackground();
        List<Item> items = new ArrayList<>();
        String query = String.format(SEARCH_API,section_code);
        query = searchInfo.execute(query).get();
        Gson g = new Gson();
        Map data = g.fromJson(query,Map.class);
        if((int) (double) data.get("count") == 0) return null;
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
            Log.d("Title",title);
            item.setId(id)
            .setTitle(title)
            .setDesc(desc)
            .setPrice(price)
            .setWeight(weight);
            items.add(item);
        }
        return items;
    }

    public Map jsonify_basket(int user_id) throws ExecutionException, InterruptedException {
        GetDataFromBackground searchInfo = new GetDataFromBackground();
        String result_of_query = searchInfo.execute(String.format(GET_BASKET_API,user_id)).get();
        Gson g = new Gson();
        return g.fromJson(result_of_query,Map.class);

    }

}

class GetDataFromBackground extends AsyncTask<String,Void,String> {
    private String s=null;
    @Override
    protected String doInBackground(String... strings) {
        String info = strings[0];
        try {
            s = IOUtils.toString(new URL(info), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
