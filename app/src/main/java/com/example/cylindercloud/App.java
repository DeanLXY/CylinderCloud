package com.example.cylindercloud;

import android.app.Application;
import android.content.Context;

/**
 * Created by wxj on 2015/10/13.
 */
public class App extends Application {
    private static Context instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getInstance() {
        return instance;
    }
}
