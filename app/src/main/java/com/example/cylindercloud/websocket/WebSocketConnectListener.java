package com.example.cylindercloud.websocket;

import android.content.Context;
import android.util.Log;
;

import com.example.cylindercloud.R;
import com.example.cylindercloud.ui.IActivity;
import com.example.cylindercloud.utils.LogUtils;
import com.example.cylindercloud.utils.SnackbarUtils;
import com.example.cylindercloud.utils.SweetDialogUtils;

import de.tavendo.autobahn.WebSocketConnectionHandler;

/**
 * Created by wxj on 2015-7-26.
 */
public class WebSocketConnectListener extends WebSocketConnectionHandler {
    private Context context;

    public WebSocketConnectListener(Context context) {
        this.context = context;
    }

    @Override
    public void onOpen() {
        super.onOpen();
        LogUtils.d("%s", "WebSocketConnectListener connect");
        if(this.context instanceof IActivity)
            SnackbarUtils.show((IActivity)context, R.string.connect_success);
    }

    @Override
    public void onClose(int code, String reason) {
        super.onClose(code, reason);
        LogUtils.d("%s", "WebSocketConnectListener lost");
        if(this.context instanceof IActivity)
            SnackbarUtils.show((IActivity)context, R.string.connect_lost);
    }

    @Override
    public void onTextMessage(String payload) {
        super.onTextMessage(payload);
        LogUtils.d("%s","payload = "+payload);
        SweetDialogUtils.show(context,payload);
    }

}
