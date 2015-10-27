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
import com.mycompany.filemanager.General.FileManagerBackend;
import com.mycompany.filemanager.General.Folder;

public class mainActivity extends Activity {

    private static Context context;
    private FileManagerBackend fileManagerBackend;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity.context = getApplicationContext();
        fileManagerBackend = FileManagerBackend.getInstance();

        GridView gridview = (GridView) findViewById(R.id.gridview);
        CustomGrid adapter = new CustomGrid(mainActivity.this, fileManagerBackend.getFolders() );
        gridview.setAdapter(adapter);

        //gridview.setAdapter(new CustomGrid(this));


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(mainActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();

                //Folder folder = FileManagerBackend.getInstance().getFolder(position);

                Intent intent = new Intent(mainActivity.getAppContext(), DisplayFolderActivity.class);
                intent.putExtra("folder_position",position);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_file_browser, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static Context getAppContext() {
        return mainActivity.context;
    }
}
