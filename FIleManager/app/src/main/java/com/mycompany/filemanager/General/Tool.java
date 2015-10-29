package com.mycompany.filemanager.General;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.mycompany.filemanager.mainActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Diego Avila on 26-10-2015.
 */
public class Tool {

    public static String saveToInternalStorage(Bitmap bitmapImage, String filename){
        ContextWrapper cw = new ContextWrapper(mainActivity.getAppContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("docDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory, filename+".jpg");

        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream(mypath);

            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return directory.getAbsolutePath();
    }

    public static Bitmap loadImageFromStorage(String filename, String folder_path)
    {
        Bitmap b = null;
        try {
            File f=new File(folder_path, filename+".jpg");
            b = BitmapFactory.decodeStream(new FileInputStream(f));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return b;

    }
}
