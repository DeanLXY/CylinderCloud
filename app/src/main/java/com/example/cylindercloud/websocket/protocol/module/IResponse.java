package com.example.cylindercloud.websocket.protocol.module;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/10/21 0021.
 */
public class IResponse implements Serializable {
    private String message;
    private int errorCode;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public boolean isRequestSuccssful(){
        if(0 == this.errorCode){
            return true;
        }
        return false;
    }
}
