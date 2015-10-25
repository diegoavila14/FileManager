package com.mycompany.filemanager;

/**
 * Created by Diego Avila on 25-10-2015.
 */
public class Folder {
    private String name;

    public Folder(String Name)
    {
        this.name = Name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
