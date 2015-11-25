package com.mycompany.filemanager.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.mycompany.filemanager.General.FileManagerBackend;

/**
 * Created by Diego Avila on 25-11-2015.
 */
public class SingOutReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        FileManagerBackend.getInstance().resetTables();
    }
}
