package com.mycompany.filemanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mycompany.filemanager.General.ApiComunicator;
import com.mycompany.filemanager.General.CustomGrid;
import com.mycompany.filemanager.General.FileManagerBackend;



public class mainActivity extends Activity {

    private static Context context;
    private FileManagerBackend fileManagerBackend;
    private ApiComunicator apiComunicator;

    private ProgressBar progressBar;
    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActionBar().hide();

        apiComunicator = new ApiComunicator(this.getApplicationContext());
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);

        mainActivity.context = getApplicationContext();
        fileManagerBackend = FileManagerBackend.getInstance();

        textView = (TextView) findViewById(R.id.text);


    }

    @Override
    protected void onResume() {
        super.onResume();

        GridView gridview = (GridView) findViewById(R.id.gridview);
        CustomGrid adapter = new CustomGrid(mainActivity.this, fileManagerBackend.getFolders() );
        gridview.setAdapter(adapter);


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Intent intent = new Intent(mainActivity.getAppContext(), DisplayFolderActivity.class);
                intent.putExtra("folder_position", position);
                startActivity(intent);
            }
        });

        if ( apiComunicator.getPreference() )
        {
            textView.setVisibility(View.GONE);
        }
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
        if (id == R.id.action_update) {

            if ( !hasInternetConnection() )
            {
                Toast.makeText(getApplicationContext(),"Error: No hay conexión a internet.",Toast.LENGTH_LONG).show();
                return false;
            }

            apiComunicator.update();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static Context getAppContext() {
        return mainActivity.context;
    }

    private boolean hasInternetConnection()
    {
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = false;
        if (activeNetwork != null)
        {

            isConnected = activeNetwork.isConnectedOrConnecting();
        }
        return isConnected;
    }
}
