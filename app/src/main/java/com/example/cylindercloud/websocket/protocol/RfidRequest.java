package com.example.cylindercloud.websocket.protocol;

import android.content.Context;

import com.example.cylindercloud.Constants;
import com.example.cylindercloud.websocket.protocol.annotation.Path;

/**
 * Created by wxj on 2015-9-21.
 */
@Path(Constants.Path.RFIDPATH)
public class RfidRequest extends IRequest {
    private String[] args;
    public RfidRequest(Context context, IRequestListener listener,String ...args) {
        super(context, listener,args);
        this.args = args;
    }

    @Override
    protected String prapareMsg() {
        return "rfid="+args[0];
    }
}
