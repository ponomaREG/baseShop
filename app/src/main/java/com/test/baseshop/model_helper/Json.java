package com.test.baseshop.model_helper;

import android.os.AsyncTask;
import android.util.Log;


import com.google.gson.Gson;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Json {

    private final static String
            SEARCH_API = "http://161.35.108.15:8000/app/items?section_code=%s",
            GET_BASKET_API = "http://161.35.108.15:8000/basket/get?user=%s",
            SET_BASKET_API = "http://161.35.108.15:8000/basket/set?user_id=%s&item_id=%s&count=%s",
            GET_ORDERS_API = "http://161.35.108.15:8000/orders/get?user=%s",
            GET_ADDRESSES_API = "http://161.35.108.15:8000/addresses/get?user=%s",
            SET_USER_INFO = "http://161.35.108.15:8000/user/update?user=%s&%s=%s",
            GET_USER_INFO = "http://161.35.108.15:8000/user/get?user=%s",
            ADD_USER = "http://161.35.108.15:8000/user/add?first_name=%s&phone=%s&email=%s&sex=%s",
            AUTH_USER = "http://161.35.108.15:8000/user/auth?phone=%s&code=%s",
            AUTH_GEN_CODE = "http://161.35.108.15:8000/user/auth_generate?phone=%s",
            ADD_ADDRESS = "http://161.35.108.15:8000/addresses/add?user=%s&corpus=%s&street=%s&house=%s&porch=%s&flat=%s&floor=%s&title=%s&district=%s",
            SET_NEW_ORDER_API = "http://161.35.108.15:8000/orders/add?user=%s&address=%s&persons=%s&desc=%s";


    private Json instance;

    public Json(){
    }

    public Json getInstance(){
        return instance; }

    public Map jsonify(int section_code) throws ExecutionException, InterruptedException, IOException {
//        GetDataFromBackground searchInfo = new GetDataFromBackground();
//        List<Item> items = new ArrayList<>();
        String query = String.format(SEARCH_API,section_code);
//        query = searchInfo.execute(query).get();
        String result = IOUtils.toString(new URL(query), StandardCharsets.UTF_8);
        Gson g = new Gson();
        return g.fromJson(result,Map.class);
    }

        public Map jsonify_basket(int user_id) throws ExecutionException, InterruptedException, IOException {
//            GetDataFromBackground searchInfo = new GetDataFromBackground();
            String info =String.format(GET_BASKET_API,user_id);
            String result_of_query = IOUtils.toString(new URL(info), StandardCharsets.UTF_8);
            Gson g = new Gson();
            return g.fromJson(result_of_query,Map.class);

    }

        public Map jsonify_basket_async(int user_id) throws ExecutionException, InterruptedException, IOException {
            GetDataFromBackground searchInfo = new GetDataFromBackground();
            String info =String.format(GET_BASKET_API,user_id);
            String result_of_query = searchInfo.execute(info).get();
            Gson g = new Gson();
            return g.fromJson(result_of_query,Map.class);

    }

//    public Map jsonify_basket(int user_id, int item_id, int count_of_items_for_order) throws ExecutionException, InterruptedException {
//        GetDataFromBackground searchInfo = new GetDataFromBackground();
//        String result_of_query = searchInfo.execute(String.format(SET_BASKET_API,user_id,item_id,count_of_items_for_order)).get();
//        Gson g = new Gson();
//        Log.d("USER_ID",user_id+"");
//        return g.fromJson(result_of_query,Map.class);
//    }
    public void jsonify_basket(int user_id, int item_id, int count_of_items_for_order){
        GetDataFromBackground searchInfo = new GetDataFromBackground();
        searchInfo.execute(String.format(SET_BASKET_API,user_id,item_id,count_of_items_for_order));
        Log.d("USER_ID",user_id+"");
    }


    public Map jsonify_orders(int user_id) throws ExecutionException, InterruptedException, IOException {
        String query = String.format(GET_ORDERS_API,user_id);
        String result_of_query = IOUtils.toString(new URL(query), StandardCharsets.UTF_8);
//        GetDataFromBackground searchInfo = new GetDataFromBackground();
//        String result_of_query = searchInfo.execute(String.format(GET_ORDERS_API,user_id)).get();
        Gson g = new Gson();
        return g.fromJson(result_of_query,Map.class);
    }

    //TODO:MAKE GET STATUS
    public void jsonify_orders_add(int user_id, int address_id, int persons , String desc){
        GetDataFromBackground makeQuery = new GetDataFromBackground();
        makeQuery.execute(String.format(SET_NEW_ORDER_API,user_id, address_id, persons, desc));
    }

    public Map jsonify_addresses(int user_id) throws ExecutionException, InterruptedException {
        GetDataFromBackground getAddresses = new GetDataFromBackground();
        String result = getAddresses.execute(String.format(GET_ADDRESSES_API,user_id)).get();
        Gson g = new Gson();
        return g.fromJson(result, Map.class);
    }

//ADD_ADDRESS = "http://161.35.108.15:8000/addresses/add?user=%s&corpus=%s&street=%s&house=%s&porch=%s&flat=%s&floor=%s&title=%s&district=%s",
    public void jsonify_addresses_add(
            int user_id,
            Address address
    ){
        Log.d("EXECUTE","MODEL");
        GetDataFromBackground addAddress = new GetDataFromBackground();
        addAddress.execute(String.format(
                ADD_ADDRESS,
                user_id, address.getCorpus(), address.getStreet(),
                address.getHouse(), address.getPorch(), address.getFlat(),
                address.getFloor(), address.getTitle(), address.getDistrict()
        ));

    }

    public Map jsonify_user(int user_id) throws ExecutionException, InterruptedException, IOException {
        String query = String.format(GET_USER_INFO,user_id);
        String result = IOUtils.toString(new URL(query), StandardCharsets.UTF_8);
        Gson g = new Gson();
        return g.fromJson(result,Map.class);
    }

    public Map jsonify_user_add(String name,String phone,String email,String sex) throws ExecutionException, InterruptedException {
        GetDataFromBackground addUserQuery = new GetDataFromBackground();
        String s = addUserQuery.execute(String.format(ADD_USER,name,phone,email,sex)).get();
        Gson g = new Gson();
        return g.fromJson(s,Map.class);
    }

    public int jsonify_user_update(int user_id, String key, String value){
        GetDataFromBackground sendUpdateInfo = new GetDataFromBackground();
        sendUpdateInfo.execute(String.format(SET_USER_INFO,user_id,key,value));
        return 1;
    }

    public Map jsonify_user_auth(String phone, String code) throws ExecutionException, InterruptedException {
        GetDataFromBackground makeQuery = new GetDataFromBackground();
        String s = makeQuery.execute(String.format(AUTH_USER,phone,code)).get();
        Gson g = new Gson();
        return g.fromJson(s,Map.class);
    }

    public Map jsonify_user_code(String phone) throws ExecutionException, InterruptedException {
        GetDataFromBackground makeQuery = new GetDataFromBackground();
        String s = makeQuery.execute(String.format(AUTH_GEN_CODE,phone)).get();
        Gson g = new Gson();
        return g.fromJson(s,Map.class);
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
