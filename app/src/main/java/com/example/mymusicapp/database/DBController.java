package com.example.mymusicapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBController extends SQLiteOpenHelper {
    private final Object AudioModel;
    private String id;

    public DBController(Context context){
        super(context,"mymusic",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table music (id integer primary  key, letak lagu)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists music");
        onCreate(db);

    }
    public void insertData(HashMap<String,String>queryValues){
        SQLiteDatabase basisdata = this.getWritableDatabase();
        ContentValues musik = newContentValues();
        musik.put("path",queryValues.get("path"));
        musik.put("title",queryValues.get("title"));
        musik.put("duration",queryValues.get("duration"));
        basisdata.insert("musik",null,musik);
        basisdata.close();
    }


    ArrayList<HashMap<java.lang.String, java.lang.String>> getAll(){
        ArrayList<HashMap<String,String>>AudioModel;
        AudioModel = new ArrayList<HashMap<String,String>>();
        String selectQuery = "Select * from music";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("path", String.valueOf(cursor.getInt(0)));
                map.put("title", String.valueOf(cursor.getInt(1)));
                map.put("duration", String.valueOf(cursor.getInt(2)));
                AudioModel.add(map);
            } while (cursor.moveToNext());
        }
            db.close();
        return AudioModel;
    }

    public void UpdateMusik(HashMap<String,String> queryValues){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues musik = newContentValues();
        musik.put("path",queryValues.get("path"));
        musik.put("title",queryValues.get("title"));
        musik.put("duration",queryValues.get("duration"));
        db.update("music", "id=?", new String[]{queryValues.get("id")});
        db.close();
    }

    public void DeleteMusik(HashMap<String,String> queryValues){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("musik", "id=?",new String[]{queryValues.get(id)});
    }

}
