package com.mycompany.filemanager.General;

import android.graphics.Bitmap;

import java.net.URL;

/**
 * Created by Diego Avila on 25-10-2015.
 */
public class FileM {
    private String name;
    private Bitmap image;
    private String URL;
    private int is_image;

    public FileM(String Name, int is_image)
    {
        this.name = Name;
        this.is_image = is_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public int getIs_image() {
        return is_image;
    }

    public void setIs_image(int is_image) {
        this.is_image = is_image;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
