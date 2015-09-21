package com.example.cylindercloud.websocket;

import android.content.Context;

import com.example.cylindercloud.Constants;
import com.example.cylindercloud.ui.IActivity;
import com.example.cylindercloud.websocket.protocol.IRequestListener;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;

/**
 * Created by wxj on 2015-7-26.
 */
public class WebSocketManager {
    private static WebSocketManager manager = null;
    private final String WebSocket = Constants.WEBSOCKET;
    private WebSocketConnection mConnection;
    private WebSocketConnectListener connectListener;
    private Context context;
    private String message,path;
    private IRequestListener listener;

    public WebSocketManager(Context contxt, String path, String msg, IRequestListener listener) {
        this.context = contxt;
        this.path = path;
        this.message = msg;
        this.listener = listener;
        mConnection = new WebSocketConnection();
    }

//    public static WebSocketManager getManager(Context contxt, String msg, IRequestListener listener) {
//        this.message = msg;
//        this.listener = listener;
////        if (manager == null)
////            synchronized (WebSocketManager.class) {
////                if (manager == null)
//                    manager = new WebSocketManager(contxt);
////            }
//        return manager;
//    }

    public void connect() {
        if (!isConnect()) {
            connectListener = new WebSocketConnectListener(context,mConnection, message, listener);
            try {
                mConnection.connect(WebSocket+path, connectListener);
            } catch (WebSocketException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isConnect() {
        if (mConnection == null) return false;
        return mConnection.isConnected();
    }

    public void disConnect() {
        if (mConnection == null) return;
        mConnection.disconnect();
        mConnection = null;
        manager = null;
    }

    public void sendTextMessage(String sendText) {
        if (mConnection == null) {
            return;
        }
        connect();
        mConnection.sendTextMessage(sendText);
    }
}
