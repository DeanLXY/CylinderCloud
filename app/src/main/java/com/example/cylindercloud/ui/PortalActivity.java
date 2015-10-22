package com.example.cylindercloud.ui;

import android.os.Bundle;
import android.view.View;

import com.example.cylindercloud.App;
import com.example.cylindercloud.R;
import com.example.cylindercloud.utils.SweetDialogUtils;
import com.example.cylindercloud.websocket.WebSocketManager;
import com.example.cylindercloud.websocket.protocol.BottleCarBRequest;
import com.example.cylindercloud.websocket.protocol.BottleCarCRequest;
import com.example.cylindercloud.websocket.protocol.BottleCheckedListRequest;
import com.example.cylindercloud.websocket.protocol.BottleCheckedUpdateRequest;
import com.example.cylindercloud.websocket.protocol.BottleRequest;
import com.example.cylindercloud.websocket.protocol.BottleWarningRequest;
import com.example.cylindercloud.websocket.protocol.CheckedListResponse;
import com.example.cylindercloud.websocket.protocol.IRequest;
import com.example.cylindercloud.websocket.protocol.IRequestListener;
import com.example.cylindercloud.websocket.protocol.RfidRequest;
import com.example.cylindercloud.websocket.protocol.SyncDataResquest;
import com.example.cylindercloud.websocket.protocol.TokenRequest;


public class PortalActivity extends IActivity implements View.OnClickListener {
    private View btnConfirmInspection;
    private View btnCylinderInfo;
    private View btnTransfer;
    private View btnWarningReminder;
    private View btnInspectionRenewal;
    private View btnDataSyncData;
    private View btnCheckAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnConfirmInspection = findViewById(R.id.btn_confirm_inspection);
        btnConfirmInspection.setOnClickListener(this);
        btnCylinderInfo = findViewById(R.id.btn_cylinder_info);
        btnCylinderInfo.setOnClickListener(this);
        btnTransfer = findViewById(R.id.transfer);
        btnTransfer.setOnClickListener(this);
        btnWarningReminder = findViewById(R.id.btn_warning_reminder);
        btnWarningReminder.setOnClickListener(this);
        btnInspectionRenewal = findViewById(R.id.btn_inspection_renewal);
        btnInspectionRenewal.setOnClickListener(this);
        btnDataSyncData = findViewById(R.id.btn_data_syncData);
        btnDataSyncData.setOnClickListener(this);
        btnCheckAccount = findViewById(R.id.btn_check_account);
        btnCheckAccount.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        if (view == btnConfirmInspection) {
            IRequest request = new BottleCarBRequest(this, new IRequestListener() {
                @Override
                public void onSuccess(String payload) {
                    SweetDialogUtils.show(PortalActivity.this, payload);
                }

                @Override
                public void onClose(int code, String reason) {
                    SweetDialogUtils.show(PortalActivity.this, String.format("错误代码%d %s", code, reason));
                }
            });
            request.requestWithToken();
        } else if (view == btnCylinderInfo) {
            IRequest request = new BottleCarCRequest(this, new IRequestListener() {
                @Override
                public void onSuccess(String payload) {
                    SweetDialogUtils.show(PortalActivity.this, payload);
                }

                @Override
                public void onClose(int code, String reason) {
                    SweetDialogUtils.show(PortalActivity.this, String.format("错误代码%d %s", code, reason));
                }
            });
            request.requestWithToken();
        } else if (view == btnTransfer) {
            IRequest request = new BottleRequest(this, new IRequestListener() {
                @Override
                public void onSuccess(String payload) {
                    SweetDialogUtils.show(PortalActivity.this, payload);
                }

                @Override
                public void onClose(int code, String reason) {
                    SweetDialogUtils.show(PortalActivity.this, String.format("错误代码%d %s", code, reason));
                }
            });
            request.requestWithToken();
        } else if (view == btnInspectionRenewal) {
            IRequest request = new BottleCheckedUpdateRequest(this, new IRequestListener() {
                @Override
                public void onSuccess(String payload) {
                    SweetDialogUtils.show(PortalActivity.this, payload);
                }

                @Override
                public void onClose(int code, String reason) {
                    SweetDialogUtils.show(PortalActivity.this, String.format("错误代码%d %s", code, reason));
                }
            });
            request.requestWithToken();
        } else if (view == btnDataSyncData) {

            IRequest request = new SyncDataResquest(this, new IRequestListener() {
                @Override
                public void onSuccess(String payload) {
                    SweetDialogUtils.show(PortalActivity.this, payload);
                }

                @Override
                public void onClose(int code, String reason) {
                    SweetDialogUtils.show(PortalActivity.this, String.format("错误代码%d %s", code, reason));
                }
            });
            request.requestWithToken();
        } else if (view == btnCheckAccount) {
            IRequest request = new CheckedListResponse(this, new IRequestListener() {
                @Override
                public void onSuccess(String payload) {
                    SweetDialogUtils.show(PortalActivity.this, payload);
                }

                @Override
                public void onClose(int code, String reason) {
                    SweetDialogUtils.show(PortalActivity.this, String.format("错误代码%d %s", code, reason));
                }
            });
            request.requestWithToken();
        } else if (view == btnWarningReminder) {

            IRequest request = new BottleWarningRequest(this, new IRequestListener() {
                @Override
                public void onSuccess(String payload) {
                    SweetDialogUtils.show(PortalActivity.this, payload);
                }

                @Override
                public void onClose(int code, String reason) {
                    SweetDialogUtils.show(PortalActivity.this, String.format("错误代码%d %s", code, reason));
                }
            });
            request.requestWithToken();
        }
    }

}
