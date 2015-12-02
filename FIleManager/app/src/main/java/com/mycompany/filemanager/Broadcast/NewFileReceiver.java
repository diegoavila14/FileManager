package com.mycompany.filemanager.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.mycompany.filemanager.General.ApiComunicator;

/**
 * Created by Diego Avila on 01-12-2015.
 */
public class NewFileReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        //TODO
        ApiComunicator apiComunicator = new ApiComunicator(context);
        apiComunicator.update();

    }
}
