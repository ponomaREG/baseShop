package com.test.baseshop.model_helper;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.test.baseshop.fragment_menu.Item;

import java.net.HttpURLConnection;
import java.net.URL;

public class PhotoDownloader extends AsyncTask<Item,Void, Void> {

    private final static String URL_PHOTO = "http://161.35.108.15:8000/static/images/%s.jpg";

    @SuppressLint("StaticFieldLeak")
    private ImageView image_view_of_photo;
    private Bitmap bitmap_of_photo;

    @Override
    protected Void doInBackground(Item... items) {
        image_view_of_photo = items[0].getImageViewOfIcon();
        try {
            URL url = new URL(String.format(URL_PHOTO,items[0].getId()));
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            bitmap_of_photo = BitmapFactory.decodeStream(connection.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        image_view_of_photo.setImageBitmap(getRoundedCornerBitmap(bitmap_of_photo,30));
    }

//    private Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
//        int width = bm.getWidth();
//        int height = bm.getHeight();
//        float scaleWidth = ((float) newWidth) / width;
//        float scaleHeight = ((float) newHeight) / height;
//        // CREATE A MATRIX FOR THE MANIPULATION
//        Matrix matrix = new Matrix();
//        // RESIZE THE BIT MAP
//        matrix.postScale(scaleWidth, scaleHeight);
//
//        // "RECREATE" THE NEW BITMAP
//        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
//                matrix, false);
//
//        return resizedBitmap;
//    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
}
