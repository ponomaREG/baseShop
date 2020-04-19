package com.test.baseshop.fragment_menu;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ImageView;

import com.test.baseshop.model_helper.Json;
import com.test.baseshop.model_helper.PhotoDownloader;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class fragment_menu_model implements Interfaces.Model, Interfaces.Model.Photo{

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
}
