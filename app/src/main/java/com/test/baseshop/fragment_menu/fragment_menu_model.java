package com.test.baseshop.fragment_menu;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.test.baseshop.model_helper.DBHelper;
import com.test.baseshop.model_helper.Item;
import com.test.baseshop.model_helper.Json;
import com.test.baseshop.model_helper.PhotoDownloader;

import java.net.HttpRetryException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.schedulers.Schedulers;

public class fragment_menu_model implements Interfaces.Model, Interfaces.Model.Photo, Interfaces.Model.Basket{

    Interfaces.Presenter presenter;
    private Json json;

    fragment_menu_model(Interfaces.Presenter presenter){
        this.presenter = presenter;
        this.json = new Json();
    }

    @Override
    public List<Item> getItemsFromDB() {
        List<Item> items = new ArrayList<>();
        DBHelper dbHelper = DBHelper.getInstance();
        Cursor c = dbHelper.getMenuItems();
        c.moveToFirst();
        for(int i = 0;i < c.getCount();i++){
            Item item = new Item();
            int id = c.getInt(c.getColumnIndex("id"));
            String title = c.getString(c.getColumnIndex("title"));
            String desc = c.getString(c.getColumnIndex("desc"));
            int price = c.getInt(c.getColumnIndex("price"));
            int section = c.getInt(c.getColumnIndex("section"));
            int weight = c.getInt(c.getColumnIndex("weight"));

            item.setId(id)
                    .setSection(section)
                    .setPrice(price)
                    .setWeight(weight)
                    .setTitle(title)
                    .setDesc(desc);
            items.add(item);
            c.moveToNext();
        }
        c.close();
        return items;
    }

    @Override
    public void getItemsByFilter(int code) {


        RXGetItemsMenuRemote getItems = new RXGetItemsMenuRemote();
        getItems.getItems(code).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Item>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(List<Item> items) {
                        presenter.setDataFromModel(items);
                    }

                    @Override
                    public void onError(Throwable e) {
                        presenter.setDataFromModel(new ArrayList<Item>());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
//        AsyncGetItemsMenuRemote getItemsByFilter = new AsyncGetItemsMenuRemote();
//        getItemsByFilter.execute(code);

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
    public void pushMenuDataIntoDatabase(List<Item> items) {
        DBHelper dbHelper = DBHelper.getInstance();
        dbHelper.clearMenu();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        for(Item item:items){
            ContentValues cv = new ContentValues();
            cv.put("id",item.getId());
            cv.put("desc",item.getDesc());
            cv.put("title",item.getTitle());
            cv.put("price",item.getPrice());
            cv.put("section",item.getSection());
            cv.put("weight",item.getWeight());
            database.insert("menu",null,cv);
        }
        database.close();
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



    class RXGetItemsMenuRemote implements Interfaces.Model.RXAndroid {


        @Override
        public Observable<List<Item>> getItems(final int code) {
            return Observable.create(new ObservableOnSubscribe<List<Item>>() {
                @Override
                public void subscribe(ObservableEmitter<List<Item>> observableEmitter) throws Exception {
                    List<Item> items = new ArrayList<>();
                    Map data = null;
                    try {
                        data = json.jsonify(code);
                    } catch (Exception e) {
                        e.printStackTrace();
                        observableEmitter.onError(new Exception());
                        observableEmitter.onComplete();
                    }
                    if(data.containsKey("count")) {
                        if ((int) (double) data.get("count") == 0) {
                            observableEmitter.onNext(items);
                            observableEmitter.onComplete();
                        }
                    }else {
                        observableEmitter.onError(new Exception());
                        observableEmitter.onComplete();
                    }
                    ArrayList jsonArrayOfItem = (ArrayList) data.get("data");
                    if(jsonArrayOfItem == null) {
                        observableEmitter.onError(new Exception());
                        observableEmitter.onComplete();
                    }
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
                    observableEmitter.onNext(items);
                    observableEmitter.onComplete();
                }
            });
        }
    }
//    class AsyncGetItemsMenuRemote extends AsyncTask<Integer, Void, Void>{
//
//        private List<Item> items = new ArrayList<>();
//
//
//        @Override
//        protected Void doInBackground(Integer... integers) {
//            Map data = null;
//            try {
//                data = json.jsonify(integers[0]);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            if(data.containsKey("count")) {
//                if ((int) (double) data.get("count") == 0) return null;
//            }else return null;
//            ArrayList jsonArrayOfItem = (ArrayList) data.get("data");
//            if(jsonArrayOfItem == null) return null;
//            for(Object item_obj:jsonArrayOfItem){
//                Map map = (Map) item_obj;
//                Item item = new Item();
//                int id = (int) (double) map.get("id");
//                String title = (String) map.get("title");
//                String desc = (String) map.get("desc");
//                int price = (int) (double) map.get("price");
//                int weight = (int) (double) map.get("weight");
//                int section = (int) (double) map.get("section");
//                Log.d("Title",title);
//                item.setId(id)
//                        .setTitle(title)
//                        .setDesc(desc)
//                        .setPrice(price)
//                        .setSection(section)
//                        .setWeight(weight);
//                items.add(item);
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            presenter.setDataFromModel(items);
//        }
//    }

}
