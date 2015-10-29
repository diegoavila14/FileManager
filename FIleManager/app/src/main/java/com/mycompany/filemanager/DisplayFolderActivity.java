package com.mycompany.filemanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.mycompany.filemanager.General.CustomGrid;
import com.mycompany.filemanager.General.CustomGridFiles;
import com.mycompany.filemanager.General.FileM;
import com.mycompany.filemanager.General.FileManagerBackend;
import com.mycompany.filemanager.General.Folder;

public class DisplayFolderActivity extends Activity {

    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_folder);

        DisplayFolderActivity.context = getApplicationContext();

        Intent intent = getIntent();
        int folder_position = intent.getIntExtra("folder_position",-1);

        Folder folder = FileManagerBackend.getInstance().getFolder(folder_position);

        setTitle(folder.getName());

        GridView gridview = (GridView) findViewById(R.id.gridview);
        CustomGridFiles adapter = new CustomGridFiles(DisplayFolderActivity.this, FileManagerBackend.getInstance().getFiles(folder.getId()) );
        gridview.setAdapter(adapter);

        //gridview.setAdapter(new CustomGrid(this));


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id)
            {
                Toast.makeText(DisplayFolderActivity.this, "" + position,Toast.LENGTH_SHORT).show();

                FileM fileM = FileManagerBackend.getInstance().getFileM(position);

                Intent intent;

                if (fileM.getIs_image() == 1)
                {
                    intent = new Intent(DisplayFolderActivity.getAppContext() , DisplayImageActivity.class);
                }
                else
                {
                    intent = new Intent(DisplayFolderActivity.getAppContext() , DisplayPDFActivity.class);
                }

                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dispaly_folder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_update) {
            int a = 2;
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public static Context getAppContext() {
        return DisplayFolderActivity.context;
    }
}
