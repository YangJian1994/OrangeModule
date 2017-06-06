package com.example.orangemodule.broad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.orangemodule.MainActivity;
import com.example.orangemodule.utils.SystemUtils;

public class OpenReceiver extends BroadcastReceiver {
    public OpenReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (SystemUtils.isAppAlive(context, "com.example.orangemodule")) {
            Log.i("OrangeModule", "the app process is alive");
            Log.i("OrangeModule", context.getPackageName());
            Intent startIntent = new Intent(context, MainActivity.class);
            context.startActivity(startIntent);
        } else {
            Log.i("OrangeModule", "the app process is dead");
            Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage("com.example.orangemodule");
            launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            context.startActivity(launchIntent);
        }
    }
}
