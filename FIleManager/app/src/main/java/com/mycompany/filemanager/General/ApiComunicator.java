package com.mycompany.filemanager.General;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.mycompany.filemanager.*;
import com.mycompany.filemanager.DataBase.DataBaseManager;

import java.util.List;

import cl.medapp.medappwebapi.*;
import cl.medapp.medappwebapi.Folder;

/**
 * Created by Diego Avila on 01-12-2015.
 */
public class ApiComunicator {

    MedappApi api;
    DataBaseManager dbManager;
    Context context;

    public ApiComunicator(Context context) {
        this.api = new MedappApi(context);
        dbManager = new DataBaseManager(context);
        this.context = context;
    }

    public void update()
    {
        api.getPatientHospitalization(new OnTaskCompleted<Patient>() {
            @Override
            public void onTaskCompleted(Patient patient) {

                api.getPatientFolders(patient, new OnTaskCompleted<List<cl.medapp.medappwebapi.Folder>>() {
                    @Override
                    public void onTaskCompleted(List<cl.medapp.medappwebapi.Folder> folders) {
                        int n_documents = 0;
                        for (cl.medapp.medappwebapi.Folder folder : folders ) {
                            n_documents += folder.getDocuments().size();
                            proccessFolder(folder);
                        }
                        String msg;
                        switch (n_documents) {
                            case 1:
                                msg = "1 documento actualizado";
                                break;
                            default:
                                msg = n_documents+" documentos actualizados";
                        }
                        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void proccessFolder(Folder folder)
    {
        dbManager.insertDB(folder.getName(), folder.getId());

        for (Document doc: folder.getDocuments()) {
            //TODO (Diego) Si el doc no es una imagen no tiene que setearse el bitmap. El path tiene que cambiarse y ser la URL y el 1 debe ser 0
            //cambiarse por 0
            Bitmap bitmap = doc.getImage();
            int id_doc = doc.getId();
            String filename = Integer.toString(id_doc)+"_doc";
            String path = Tool.saveToInternalStorage(bitmap,filename);
            dbManager.insertDB(id_doc,folder.getId(),path,filename+".jpg",doc.getName(),1);
        }
    }

    public void resetTables()
    {
        dbManager.resetTables();
    }
}
