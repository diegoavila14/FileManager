package com.mycompany.filemanager.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mycompany.filemanager.General.FileM;

import java.util.ArrayList;

/**
 * Created by Diego Avila on 25-10-2015.
 */
public class DataBaseManager2 {

    public static final  String TABLE_NAME = "Documentos";

    public static final String CN_ID = "_id";
    public static final String CN_ID_DOC = "folder_name";
    public static final String CN_ID_WEB_FOLDER = "id_web_folder";
    public static  final String CN_FILE_NAME = "filename";
    public static final String CN_FOLDER_PATH = "folder_path";

    public static final String CREATE_TABLE = "create table " + TABLE_NAME + " ("
            + CN_ID + " integer primary key autoincrement,"
            + CN_ID_DOC + " integer unique,"
            + CN_ID_WEB_FOLDER + " integer,"
            + CN_FOLDER_PATH + " text not null,"
            + CN_FILE_NAME + " text not null);";

    private DbHelper2 helper;
    private SQLiteDatabase db;

    public DataBaseManager2(Context context) {
        helper = new DbHelper2(context);
        db = helper.getWritableDatabase();
    }

    public ContentValues generateCV(int id_doc, int id_web_folder, String folder_path, String filename)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CN_ID_DOC , id_doc);
        contentValues.put(CN_ID_WEB_FOLDER , id_web_folder);
        contentValues.put(CN_FOLDER_PATH, folder_path);
        contentValues.put(CN_FILE_NAME, filename);

        return contentValues;
    }

    /*
    Inserta los valores en la base de datos.
    Se usa insertWithOnConflict para que cuando se inserte un contenido que ya existe este se actualice y
    no se duplique.
    */
    public void insertDB(int id_doc, int id_web_folder, String folder_path, String filename)
    {
        db.insertWithOnConflict(TABLE_NAME, null, generateCV(id_doc, id_web_folder, folder_path, filename), SQLiteDatabase.CONFLICT_REPLACE);
    }

    public void deleteDB(String id_web)
    {
        //db.delete(TABLE_NAME, CN_ID_WEB+"=?",new String[]{id_web} );
        //https://www.youtube.com/watch?v=ma8z1rcFyjI
    }

    public ArrayList<FileM> getDBFiles(int id_folder)
    {
        String[] args = new String[]{ Integer.toString(id_folder) };
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + CN_ID_WEB_FOLDER + "=? ";
        Cursor cursor = db.rawQuery(selectQuery, args);

        ArrayList<FileM> files = new ArrayList<FileM>();

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                FileM fileM = new FileM(cursor.getString(4));

                files.add(fileM);

            } while (cursor.moveToNext());
        }


        return files;
    }

    //Método llamado cuando se hace update a los contenidos.
    //Saca los contenidos de la base de datos y los pone en la lista de contenidos a ser mostrados


    /*public ArrayList<Folder> getDBFolders()
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
    }*/
}
