package com.example.cylindercloud;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.example.cylindercloud.utils.ConfigManager;
import com.example.cylindercloud.utils.DeviceUtils;

/**
 * Created by wxj on 2015/10/13.
 */
public class App extends Application {
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }

    public String getDeviceId() {
        String deviceId = ConfigManager.getString(this, ConfigManager.Config.DEVICEID, null);
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = DeviceUtils.getDeviceId(this);
        }
        ConfigManager.put(this, ConfigManager.Config.DEVICEID, deviceId);
        return deviceId;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
