package com.example.cylindercloud.ui;

import android.os.Bundle;
import android.view.View;

import com.example.cylindercloud.App;
import com.example.cylindercloud.R;
import com.example.cylindercloud.utils.SweetDialogUtils;
import com.example.cylindercloud.websocket.WebSocketManager;
import com.example.cylindercloud.websocket.protocol.BottleCarBRequest;
import com.example.cylindercloud.websocket.protocol.BottleCarCRequest;
import com.example.cylindercloud.websocket.protocol.IRequest;
import com.example.cylindercloud.websocket.protocol.IRequestListener;
import com.example.cylindercloud.websocket.protocol.RfidRequest;
import com.example.cylindercloud.websocket.protocol.TokenRequest;


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
            IRequest request = new TokenRequest(this, App.getInstance().getDeviceId(), new IRequestListener() {
                @Override
                public void onSuccess(String payload) {
                    SweetDialogUtils.show(PortalActivity.this, payload);
                }

                @Override
                public void onClose(int code, String reason) {
                    SweetDialogUtils.show(PortalActivity.this, String.format("错误代码%d %s",code,reason));
                }
            });
            request.request();
        }
    }

}
