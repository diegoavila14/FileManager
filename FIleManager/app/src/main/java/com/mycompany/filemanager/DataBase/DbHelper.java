package com.mycompany.filemanager.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Diego Avila on 25-10-2015.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final  String DB_NAME = "folders.sqlite";
    private static final int DB_SCHEME_VERSION = 1;


    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DataBaseManager.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
