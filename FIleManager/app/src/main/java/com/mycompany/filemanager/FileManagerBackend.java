package com.mycompany.filemanager;

import android.content.Context;

import com.mycompany.filemanager.DataBase.DataBaseManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diego Avila on 25-10-2015.
 */
public class FileManagerBackend {

    private static FileManagerBackend INSTANCE;
    private DataBaseManager dbManager;

    private FileManagerBackend(Context c)
    {
        dbManager = new DataBaseManager(c);

        //SOLO PARA PRUEBA
        prueba();
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
        return dbManager.getDBFolders();
    }

    public void prueba()
    {
        dbManager.insertDB("HOLA",0);
        dbManager.insertDB("CHAO",1);
    }
}
