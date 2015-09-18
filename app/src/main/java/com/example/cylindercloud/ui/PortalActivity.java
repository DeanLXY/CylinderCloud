package com.example.cylindercloud.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.cylindercloud.R;
import com.example.cylindercloud.websocket.WebSocketManager;

public class PortalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread() {
            @Override
            public void run() {
                super.run();
                WebSocketManager.getManager(PortalActivity.this).connect();
            }
        }.start();

    }
}
