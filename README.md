
# Introduction
Switch to secondary user after boot of a rooted Android 10.

# Instructions
To enable the application as Device Owner in order have permissions to change the user:
```
adb root
adb shell dpm set-device-owner com.example.switchuser/.AppAdminReceiver
```

# User restrictions
## Hardening:
1. Dump `/data/system/users/10.xml` where 10 is the id of the user. The file looks like this:
```
<?xml version='1.0' encoding='utf-8' standalone='yes' ?>
<user id="10" serialNumber="10" flags="16" created="1598034338552" lastLoggedIn="1598035769294" lastLoggedInFingerprint="Android/sdk_phone_x86_64/generic_x86_64:10/QPP6.190730.005.B1/5775370:userdebug/test-keys" icon="/data/system/users/10/photo.png" profileBadge="0">
    <name>New user</name>
    <restrictions no_sms="true" no_outgoing_calls="true" />
</user>
```
2. Add more restrictions. The list of restrictions can be founded on [UserManager Documentation][1]
3. push the changes
```
adb push 10.xml /data/system/users/10.xml
```
# Reboot 
Once the device start the application switch the user to the secondary user.

# Tests
The app was tested on AVD (Android Virtual Device)


  [1]: https://developer.android.com/reference/android/os/UserManager
