package com.example.switchuser;

import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class AppAdminReceiver extends DeviceAdminReceiver {

    private static final String TAG = "AppAdminReceiver";
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onProfileProvisioningComplete(Context context, Intent intent) {
        // Enable the profile
        DevicePolicyManager manager =
                (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);

        ComponentName componentName = this.getComponentName(context);

        manager.setProfileName(componentName, "SwitchUser");

        manager.setLockTaskPackages(componentName, new String[]{"com.example.switchuser"});
    }

    /**
     * @return A newly instantiated {@link ComponentName} for this
     * DeviceAdminReceiver.
     */
    public static ComponentName getComponentName(Context context) {
        return new ComponentName(context.getApplicationContext(), AppAdminReceiver.class);
    }

    @Override
    public void onEnabled(Context context, Intent intent) {
        Toast.makeText(context, "Device Admin : enabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        Toast.makeText(context, "Device Admin : disabled", Toast.LENGTH_SHORT).show();
    }
}

