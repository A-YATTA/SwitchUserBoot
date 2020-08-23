package com.example.switchuser;

import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.UserHandle;

import androidx.annotation.RequiresApi;

import java.util.List;

public class BootReceiver extends BroadcastReceiver {

    private static final String TAG = "Boot Receiver";
    private ComponentName compName;
    private DevicePolicyManager manager;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if (intent.getAction().equalsIgnoreCase(
                    Intent.ACTION_BOOT_COMPLETED)) {

                compName = new ComponentName(context, AppAdminReceiver.class);


                manager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);

                List<UserHandle> listUsers = manager.getSecondaryUsers(compName);

                manager.switchUser(compName, listUsers.get(0));
            }
        }
    }
}