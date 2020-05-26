package com.test.baseshop.model_helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "shop.db";
    private static String DB_PATH = "";
    private SQLiteDatabase mDataBase;
    private boolean mNeedUpdate = true;
    private Context context;
    @SuppressLint("StaticFieldLeak")
    private static DBHelper instance;


//    private final String sql_update_status = "update testL set status=%s where id = %s;";


    public DBHelper(Context context){
        super(context, DB_NAME, null, 1);
        DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        this.context = context;
        instance = this;
    }

    public static DBHelper getInstance(){
        return instance;
    }

    public void updateDataBase(){
        if (mNeedUpdate) {
            Log.d("UPDATE","123");
            File dbFile = new File(DB_PATH + DB_NAME);
            if (dbFile.exists())
                dbFile.delete();
            copyDataBase();

            mNeedUpdate = false;
        }
    }
    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        Log.d("EXIST",dbFile.exists()+"");
        return dbFile.exists();
    }
    public void copyDataBase() {
        if (!checkDataBase()) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDBFile();
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }
    private void copyDBFile() throws IOException {
        InputStream mInput = context.getAssets().open(DB_NAME);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }
    @Deprecated
    public boolean openDataBase() throws SQLException {
        mDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDataBase != null;
    }

    @Override
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion){
            mNeedUpdate = true;
            updateDataBase();
        }

    }

    public void clearMenu(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete("menu",null,null);
//        sqLiteDatabase.rawQuery("delete from menu;",null).close();
        sqLiteDatabase.close();
    }

    public Cursor getMenuItems(){
        String query = "select * from menu;";
        SQLiteDatabase sql = getReadableDatabase();
        return sql.rawQuery(query,null);
    }

}