package com.test.baseshop.fragment_menu;

import com.test.baseshop.model_helper.Json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class fragment_menu_model implements Interfaces.Model {

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
}
