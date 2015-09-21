package com.example.cylindercloud.websocket;

import android.content.Context;
;

import com.example.cylindercloud.R;
import com.example.cylindercloud.ui.IActivity;
import com.example.cylindercloud.utils.LogUtils;
import com.example.cylindercloud.utils.SnackbarUtils;
import com.example.cylindercloud.utils.SweetDialogUtils;
import com.example.cylindercloud.websocket.protocol.IRequestListener;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketConnectionHandler;

/**
 * Created by wxj on 2015-7-26.
 */
public class WebSocketConnectListener extends WebSocketConnectionHandler {
    private Context context;
    private WebSocketConnection connection;
    private String message;
    private IRequestListener listener;

    public WebSocketConnectListener(Context context, WebSocketConnection mConnection, String message, IRequestListener listener) {
        this.context = context;
        this.connection = mConnection;
        this.message = message;
        this.listener = listener;
    }

    @Override
    public void onOpen() {
        super.onOpen();
        LogUtils.d("%s", "WebSocketConnectListener connect");
        connection.sendTextMessage(message);
    }

    @Override
    public void onClose(int code, String reason) {
        super.onClose(code, reason);
        LogUtils.d("%s", "WebSocketConnectListener lost");
        if (this.listener != null) {
            this.listener.onClose(code, reason);
        }
    }

    @Override
    public void onTextMessage(String payload) {
        super.onTextMessage(payload);
        LogUtils.d("%s", "payload = " + payload);
        if (this.listener != null) {
            this.listener.onSuccess(payload);
        }
    }

}
