package com.mycompany.filemanager.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Diego Avila on 25-10-2015.
 */
public class DbHelper2 extends SQLiteOpenHelper {

    private static final  String DB_NAME = "Documentos.sqlite";
    private static final int DB_SCHEME_VERSION = 1;



    public DbHelper2(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DataBaseManager2.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
