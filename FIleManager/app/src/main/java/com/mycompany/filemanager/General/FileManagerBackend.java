package com.mycompany.filemanager.General;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;

import com.mycompany.filemanager.DataBase.DataBaseManager;
import com.mycompany.filemanager.DataBase.DataBaseManager2;
import com.mycompany.filemanager.mainActivity;

import java.io.File;
import java.util.List;

/**
 * Created by Diego Avila on 25-10-2015.
 */
public class FileManagerBackend {

    private static FileManagerBackend INSTANCE;
    private DataBaseManager dbManager;
    private DataBaseManager2 dbManager2;
    private List<Folder> folders;
    private List<FileM> files_temp;

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

    public FileM getFileM(int index)
    {
        return files_temp.get(index);
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
        files_temp = dbManager2.getDBFiles(id_folder);
        return files_temp;
    }

    public void prueba()
    {
        dbManager.insertDB("HOLA",0);
        dbManager.insertDB("CHAO", 1);

        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = Bitmap.createBitmap(100, 100, conf);
        bitmap.eraseColor(Color.GREEN);

        String path = Tool.saveToInternalStorage(bitmap, "hola");

        dbManager2.insertDB(0, 0, path, "hola.jpg", "IMAGE_1",1);

        Bitmap.Config conf2 = Bitmap.Config.ARGB_8888;
        Bitmap bitmap2 = Bitmap.createBitmap(100, 100, conf2);
        bitmap2.eraseColor(Color.RED);

        String path2 = Tool.saveToInternalStorage(bitmap2,"hola2");

        dbManager2.insertDB(1, 0, path2, "hola2.jpg", "IMAGE_2",1);

        Bitmap.Config conf3 = Bitmap.Config.ARGB_8888;
        Bitmap bitmap3 = Bitmap.createBitmap(100, 100, conf3);
        bitmap3.eraseColor(Color.YELLOW);

        String path3 = Tool.saveToInternalStorage(bitmap3,"hola3");

        dbManager2.insertDB(2, 1, path3, "hola3.jpg", "IMAGE_3",1);
    }
}
