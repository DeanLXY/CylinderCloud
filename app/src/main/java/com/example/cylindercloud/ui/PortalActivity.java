package com.example.cylindercloud.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android.nfc.ScanActivity;
import com.example.cylindercloud.R;
import com.example.cylindercloud.websocket.WebSocketManager;


public class PortalActivity extends AppCompatActivity implements View.OnClickListener {
    private View btnConfirmInspection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnConfirmInspection = findViewById(R.id.btn_confirm_inspection);
        WebSocketManager.getManager(PortalActivity.this).connect();
        btnConfirmInspection.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, ScanActivity.class));
    }

}
