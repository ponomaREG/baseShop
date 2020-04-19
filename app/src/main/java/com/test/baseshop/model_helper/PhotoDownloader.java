package com.test.baseshop.model_helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.net.MalformedURLException;
import java.net.URL;

public class PhotoDownloader extends AsyncTask<Integer,Void, Bitmap> {

    private final static String URL_PHOTO = "http://161.35.108.15:8000/images?image_id=%s";

    @Override
    protected Bitmap doInBackground(Integer... integers) {
        int item_id = integers[0];
        try {
            URL url = new URL(String.format(URL_PHOTO,item_id));
            return BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
    }
}
