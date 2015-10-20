package com.example.cylindercloud.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

import java.util.UUID;

/**
 * Created by Administrator on 2015/10/20 0020.
 */
public class DeviceUtils {
    public static String getDeviceId(Context context) {
        String deviceId = null;
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = tm.getDeviceId();
            if (deviceId == null) {
                deviceId = UUID.randomUUID().toString();
            }
        } catch (Exception e) {
        }

        return deviceId;
    }
}
