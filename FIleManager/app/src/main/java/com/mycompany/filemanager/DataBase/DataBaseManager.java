package com.mycompany.filemanager.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import com.mycompany.filemanager.Folder;
import com.mycompany.filemanager.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diego Avila on 25-10-2015.
 */
public class DataBaseManager {

    public static final  String TABLE_NAME = "Folders";

    public static final String CN_ID = "_id";
    public static final String CN_ID_WEB = "id_web";
    public static final String CN_NAME = "title";

    public static final String CREATE_TABLE = "create table " + TABLE_NAME + " ("
            + CN_ID + " integer primary key autoincrement,"
            + CN_ID_WEB + " integer unique,"
            + CN_NAME + " text not null);";

    private DbHelper helper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context) {
        helper = new DbHelper(context);
        db = helper.getWritableDatabase();
    }

    public ContentValues generateCV(String name, int id_web)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CN_ID_WEB,id_web);
        contentValues.put(CN_NAME, name);

        return contentValues;
    }

    /*
    Inserta los valores en la base de datos.
    Se usa insertWithOnConflict para que cuando se inserte un contenido que ya existe este se actualice y
    no se duplique.
    */
    public void insertDB(String name, int id_web)
    {
        db.insertWithOnConflict(TABLE_NAME, null, generateCV(name,id_web), SQLiteDatabase.CONFLICT_REPLACE);
    }

    public void deleteDB(String id_web)
    {
        //db.delete(TABLE_NAME, CN_ID_WEB+"=?",new String[]{id_web} );
        //https://www.youtube.com/watch?v=ma8z1rcFyjI
    }

    //Método llamado cuando se hace update a los contenidos.
    //Saca los contenidos de la base de datos y los pone en la lista de contenidos a ser mostrados
    public ArrayList<Folder> getDBFolders()
    {
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);

        ArrayList<Folder> folders = new ArrayList<Folder>();

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Folder folder = new Folder(cursor.getString(2));

                folders.add(folder);

            } while (cursor.moveToNext());
        }
        return folders;
    }
}
