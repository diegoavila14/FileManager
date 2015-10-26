package com.mycompany.filemanager.General;

/**
 * Created by Diego Avila on 25-10-2015.
 */
public class Folder {
    private String name;
    private int id;

    public Folder(String Name, int Id)
    {
        this.name = Name;
        this.id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
