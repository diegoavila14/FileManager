package com.mycompany.filemanager.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import com.mycompany.filemanager.General.FileM;
import com.mycompany.filemanager.General.Folder;
import com.mycompany.filemanager.General.Tool;

import java.util.ArrayList;

/**
 * Created by Diego Avila on 25-10-2015.
 */
public class DataBaseManager {

    private DbHelper helper;
    private SQLiteDatabase db;

    //Table names
    public static final  String TABLE_FOLDERS = "Folders";
    public static final  String TABLE_DOCUMENTS = "Documentos";

    //Common column names
    public static final String CN_ID = "_id";
    public static final String CN_TITLE = "folder_name";
    public static final String CN_ID_FOLDER = "id_folder";

    //FOLDERS table - column names
    //NONE

    //DOCUMENTS table - column names
    public static final String CN_ID_DOC = "id_doc";
    public static  final String CN_FILE_NAME = "filename";
    public static final String CN_FOLDER_PATH = "folder_path";
    public static final String CN_IS_IMAGE = "is_image";

    public static final String CREATE_FOLDERS_TABLE = "create table " + TABLE_FOLDERS + " ("
            + CN_ID + " integer primary key autoincrement,"
            + CN_ID_FOLDER + " integer unique,"
            + CN_TITLE + " text not null);";

    public static final String CREATE_DOCUMENTS_TABLE = "create table " + TABLE_DOCUMENTS + " ("
            + CN_ID + " integer primary key autoincrement,"
            + CN_ID_DOC + " integer unique,"
            + CN_ID_FOLDER + " integer,"
            + CN_FOLDER_PATH + " text not null,"
            + CN_FILE_NAME + " text not null,"
            + CN_TITLE + " text,"
            + CN_IS_IMAGE + " integer not null);";


    public DataBaseManager(Context context) {
        helper = new DbHelper(context);
        db = helper.getWritableDatabase();
    }

    public ContentValues generateCV(String name, int id_web)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CN_ID_FOLDER,id_web);
        contentValues.put(CN_TITLE, name);

        return contentValues;
    }

    /*
    Inserta los valores en la base de datos.
    Se usa insertWithOnConflict para que cuando se inserte un contenido que ya existe este se actualice y
    no se duplique.
    */
    public void insertDB(String name, int id_web)
    {
        db.insertWithOnConflict(TABLE_FOLDERS, null, generateCV(name, id_web), SQLiteDatabase.CONFLICT_REPLACE);
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
        String selectQuery = "SELECT  * FROM " + TABLE_FOLDERS;
        Cursor cursor = db.rawQuery(selectQuery, null);

        ArrayList<Folder> folders = new ArrayList<Folder>();

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Folder folder = new Folder(cursor.getString(2),cursor.getInt(1));

                folders.add(folder);

            } while (cursor.moveToNext());
        }
        return folders;
    }

    //Métodos para la tabla de documentos
    public ContentValues generateCV(int id_doc, int id_web_folder, String folder_path, String filename,String title, int is_image)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CN_ID_DOC , id_doc);
        contentValues.put(CN_ID_FOLDER , id_web_folder);
        contentValues.put(CN_FOLDER_PATH, folder_path);
        contentValues.put(CN_FILE_NAME, filename);
        contentValues.put(CN_TITLE,title);
        contentValues.put(CN_IS_IMAGE, is_image);

        return contentValues;
    }

    /*
    Inserta los valores en la base de datos.
    Se usa insertWithOnConflict para que cuando se inserte un contenido que ya existe este se actualice y
    no se duplique.
    */
    public void insertDB(int id_doc, int id_web_folder, String folder_path, String filename, String title, int is_image)
    {
        db.insertWithOnConflict(TABLE_DOCUMENTS, null, generateCV(id_doc, id_web_folder, folder_path, filename, title, is_image), SQLiteDatabase.CONFLICT_REPLACE);
    }

    public ArrayList<FileM> getDBFiles(int id_folder)
    {
        String[] args = new String[]{ Integer.toString(id_folder) };
        String selectQuery = "SELECT  * FROM " + TABLE_DOCUMENTS + " WHERE " + CN_ID_FOLDER + "=? ";
        Cursor cursor = db.rawQuery(selectQuery, args);

        ArrayList<FileM> files = new ArrayList<FileM>();

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                FileM fileM = new FileM(cursor.getString(5),cursor.getInt(6));

                if (fileM.getIs_image() == 1){
                    String aux = cursor.getString(4);
                    String aux2 = cursor.getString(3);
                    Bitmap b = Tool.loadImageFromStorage(aux, aux2); //(filename, folder_path)
                    fileM.setImage(b);
                }
                else{
                    String ah = cursor.getString(3);
                    fileM.setURL(ah);
                }



                files.add(fileM);

            } while (cursor.moveToNext());
        }


        return files;
    }

    public void resetTables()
    {
        String query = "DELETE FROM "+ TABLE_FOLDERS;
        db.execSQL(query);

        query = "DELETE FROM "+ TABLE_DOCUMENTS;
        db.execSQL(query);

    }
}
