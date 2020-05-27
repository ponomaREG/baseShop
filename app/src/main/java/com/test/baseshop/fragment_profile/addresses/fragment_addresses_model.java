package com.test.baseshop.fragment_profile.addresses;

import android.os.AsyncTask;

import com.test.baseshop.model_helper.Address;
import com.test.baseshop.model_helper.Json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class fragment_addresses_model implements Interfaces.Model {
    private Interfaces.Presenter presenter;
    private Json json;

    fragment_addresses_model(fragment_addresses_presenter presenter){
        this.presenter = presenter;
        this.json = new Json();
    }


    @Override
    public void getAddresses(int user_id) {
        RXGetAddressesFromServer getAddressesFromServer = new RXGetAddressesFromServer();
        getAddressesFromServer.getAddresses(user_id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Address>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Address> addresses) {
                        presenter.setAddresses(addresses);
                    }

                    @Override
                    public void onError(Throwable e) {
                        presenter.setAddresses(new ArrayList<Address>()); //TODO:ERROR
                    }

                    @Override
                    public void onComplete() {

                    }
                });
//        AsyncGetAddressesFromServer getAddressesFromServerAsync = new AsyncGetAddressesFromServer();
//        getAddressesFromServerAsync.execute(user_id);
//        Map raw_map;
//        List<Address> addresses = new ArrayList<>();
//        try {
//            raw_map = json.jsonify_addresses(user_id);
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.d("ERROR","ADDRESSES");
//            return addresses;
//        }
//        int count = (int) (double) raw_map.get("count");
//        if(count == 0) return addresses;
//        raw_map = (Map) raw_map.get("data");
//
//        assert raw_map != null;
//        for(Object key:raw_map.keySet()){
//            Address new_address = new Address();
//            Map map_address = (Map) raw_map.get(key);
//            assert map_address != null;
//            new_address.setCorpus((String) map_address.get("corpus"))
//                    .setDistrict((String) map_address.get("district"))
//                    .setFlat((String) map_address.get("flat"))
//                    .setFloor((String) map_address.get("floor"))
//                    .setHouse((String) map_address.get("house"))
//                    .setPorch((String) map_address.get("porch"))
//                    .setStreet((String) map_address.get("street"))
//                    .setTitle((String) map_address.get("title"));
//            addresses.add(new_address);
//        }
//        return addresses;
    }


    class RXGetAddressesFromServer implements Interfaces.Model.RX{

        @Override
        public Observable<List<Address>> getAddresses(final int user_id) {
            return Observable.create(new ObservableOnSubscribe<List<Address>>() {
                @Override
                public void subscribe(ObservableEmitter<List<Address>> e) throws Exception {
                    List<Address> addresses = new ArrayList<>();
                    Map raw_map = null;
                    try {
                        raw_map = json.jsonify_addresses(user_id);
                    } catch (Exception exc) {
                        exc.printStackTrace();
                        e.onError(exc);
                        e.onComplete();
                    }
                    int count = (int) (double) raw_map.get("count");
                    if(count == 0) {
                        e.onNext(addresses);
                    }
                    raw_map = (Map) raw_map.get("data");

                    assert raw_map != null;
                    for(Object key:raw_map.keySet()){
                        Address new_address = new Address();
                        Map map_address = (Map) raw_map.get(key);
                        assert map_address != null;
                        new_address.setCorpus((String) map_address.get("corpus"))
                                .setDistrict((String) map_address.get("district"))
                                .setFlat((String) map_address.get("flat"))
                                .setFloor((String) map_address.get("floor"))
                                .setHouse((String) map_address.get("house"))
                                .setPorch((String) map_address.get("porch"))
                                .setStreet((String) map_address.get("street"))
                                .setTitle((String) map_address.get("title"));
                        addresses.add(new_address);
                    }
                    e.onNext(addresses);
                }
            });
        }
    }

//    class AsyncGetAddressesFromServer extends AsyncTask<Integer, Void, Void>{
//
//        List<Address> addresses = new ArrayList<>();
//
//        @Override
//        protected Void doInBackground(Integer... integers) {
//            Map raw_map;
//            try {
//                raw_map = json.jsonify_addresses(integers[0]);
//            } catch (Exception e) {
//                e.printStackTrace();
//                return null;
//            }
//            int count = (int) (double) raw_map.get("count");
//            if(count == 0) return null;
//            raw_map = (Map) raw_map.get("data");
//
//            assert raw_map != null;
//            for(Object key:raw_map.keySet()){
//                Address new_address = new Address();
//                Map map_address = (Map) raw_map.get(key);
//                assert map_address != null;
//                new_address.setCorpus((String) map_address.get("corpus"))
//                        .setDistrict((String) map_address.get("district"))
//                        .setFlat((String) map_address.get("flat"))
//                        .setFloor((String) map_address.get("floor"))
//                        .setHouse((String) map_address.get("house"))
//                        .setPorch((String) map_address.get("porch"))
//                        .setStreet((String) map_address.get("street"))
//                        .setTitle((String) map_address.get("title"));
//                addresses.add(new_address);
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            presenter.setAddresses(addresses);
//        }
//    }
}
