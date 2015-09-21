package com.example.cylindercloud.ui;

import android.os.Bundle;
import android.view.View;

import com.example.cylindercloud.R;
import com.example.cylindercloud.utils.SweetDialogUtils;
import com.example.cylindercloud.websocket.WebSocketManager;
import com.example.cylindercloud.websocket.protocol.BottleCarBRequest;
import com.example.cylindercloud.websocket.protocol.IRequest;
import com.example.cylindercloud.websocket.protocol.IRequestListener;
import com.example.cylindercloud.websocket.protocol.RfidRequest;


public class PortalActivity extends IActivity implements View.OnClickListener {
    private View btnConfirmInspection;
    private View btnCylinderInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnConfirmInspection = findViewById(R.id.btn_confirm_inspection);
        btnConfirmInspection.setOnClickListener(this);
        btnCylinderInfo = findViewById(R.id.btn_cylinder_info);
        btnCylinderInfo.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        if (view == btnConfirmInspection) {
            IRequest request = new RfidRequest(this, new IRequestListener() {
                @Override
                public void onSuccess(String payload) {
                    SweetDialogUtils.show(PortalActivity.this, payload);
                }

                @Override
                public void onClose(int code, String reason) {
                    SweetDialogUtils.show(PortalActivity.this, reason);
                }
            }, "3539539762");
            request.request();
        } else if (view == btnCylinderInfo) {
            IRequest request = new BottleCarBRequest(this, new IRequestListener() {
                @Override
                public void onSuccess(String payload) {
                    SweetDialogUtils.show(PortalActivity.this, payload);
                }

                @Override
                public void onClose(int code, String reason) {
                    SweetDialogUtils.show(PortalActivity.this, reason);
                }
            });
            request.request();
        }
    }

}
