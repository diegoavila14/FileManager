package com.mycompany.filemanager.General;

import android.content.Context;

import com.mycompany.filemanager.DataBase.DataBaseManager;
import com.mycompany.filemanager.DataBase.DataBaseManager2;
import com.mycompany.filemanager.mainActivity;

import java.util.List;

/**
 * Created by Diego Avila on 25-10-2015.
 */
public class FileManagerBackend {

    private static FileManagerBackend INSTANCE;
    private DataBaseManager dbManager;
    private DataBaseManager2 dbManager2;
    private List<Folder> folders;

    private FileManagerBackend(Context c)
    {
        dbManager = new DataBaseManager(c);
        dbManager2 = new DataBaseManager2(c);

        //SOLO PARA PRUEBA
        prueba();
    }

    public Folder getFolder(int index)
    {
        return folders.get(index);
    }

    //Patrón singleton para tener acceso global a la instancia
    public synchronized static FileManagerBackend getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new FileManagerBackend(mainActivity.getAppContext());
        }
        return INSTANCE;
    }

    public List<Folder> getFolders()
    {
        folders = dbManager.getDBFolders();
        return folders;
    }

    public List<FileM> getFiles(int id_folder)
    {
        return dbManager2.getDBFiles(id_folder);
    }

    public void prueba()
    {
        dbManager.insertDB("HOLA",0);
        dbManager.insertDB("CHAO",1);

        dbManager2.insertDB(0,0,"a","a");
    }
}
