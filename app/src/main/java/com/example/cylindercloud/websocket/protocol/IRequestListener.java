package com.example.cylindercloud.websocket.protocol;

/**
 * Created by Administrator on 2015-9-21.
 */
public interface IRequestListener {
    void onSuccess(String payload);
    void onClose(int code, String reason);
}
