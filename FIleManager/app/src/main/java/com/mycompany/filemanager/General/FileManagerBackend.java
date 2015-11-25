package com.mycompany.filemanager.General;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;

import com.mycompany.filemanager.DataBase.DataBaseManager;
import com.mycompany.filemanager.mainActivity;

import java.util.List;

import cl.medapp.medappwebapi.Document;

/**
 * Created by Diego Avila on 25-10-2015.
 */
public class FileManagerBackend {

    private static FileManagerBackend INSTANCE;
    private DataBaseManager dbManager;
    private List<Folder> folders;
    private List<FileM> files_temp;

    private FileManagerBackend(Context c)
    {
        dbManager = new DataBaseManager(c);

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
        files_temp = dbManager.getDBFiles(id_folder);
        return files_temp;
    }

    public void prueba()
    {
        dbManager.insertDB("Tareas",0);
        dbManager.insertDB("Materiales", 1);

        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = Bitmap.createBitmap(100, 100, conf);
        bitmap.eraseColor(Color.GREEN);

        String path = Tool.saveToInternalStorage(bitmap, "hola");

        dbManager.insertDB(0, 0, path, "hola.jpg", "IMAGE_1",1);

        Bitmap.Config conf2 = Bitmap.Config.ARGB_8888;
        Bitmap bitmap2 = Bitmap.createBitmap(100, 100, conf2);
        bitmap2.eraseColor(Color.RED);

        String path2 = Tool.saveToInternalStorage(bitmap2,"hola2");

        dbManager.insertDB(1, 0, path2, "hola2.jpg", "IMAGE_2",1);

        Bitmap.Config conf3 = Bitmap.Config.ARGB_8888;
        Bitmap bitmap3 = Bitmap.createBitmap(100, 100, conf3);
        bitmap3.eraseColor(Color.YELLOW);

        String path3 = Tool.saveToInternalStorage(bitmap3,"hola3");

        dbManager.insertDB(2, 1, path3, "hola3.jpg", "IMAGE_3",1);

        dbManager.insertDB(3,1,"http://www.adobe.com/content/dam/Adobe/en/devnet/acrobat/pdfs/pdf_open_parameters.pdf",""
                ,"PDFPrueba",0);
    }

    public void proccessFolder(cl.medapp.medappwebapi.Folder folder)
    {
        dbManager.insertDB(folder.getName(),folder.getId());

        for (Document doc: folder.getDocuments()) {
            //TODO (Diego) Si el doc no es una imagen no tiene que setearse el bitmap. El path tiene que cambiarse y ser la URL y el 1
            //cambiarse por 0
            Bitmap bitmap = doc.getImage();
            int id_doc = doc.getId();
            String filename = Integer.toString(id_doc)+"_doc";
            String path = Tool.saveToInternalStorage(bitmap,filename);
            dbManager.insertDB(id_doc,folder.getId(),path,filename,doc.getName(),1);
        }
    }

    public void resetTables()
    {
        dbManager.resetTables();
    }
}
