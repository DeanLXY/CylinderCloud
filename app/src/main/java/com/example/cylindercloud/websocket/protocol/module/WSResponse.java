package com.example.cylindercloud.websocket.protocol.module;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015-9-18.
 */
public class WSResponse implements Serializable{
    private int result;
    private String message;
    private List<CylinderInfo> data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CylinderInfo> getData() {
        return data;
    }

    public void setData(List<CylinderInfo> data) {
        this.data = data;
    }
}
