package com.example.cylindercloud.websocket.protocol;

import android.content.Context;

import com.example.cylindercloud.Constants;
import com.example.cylindercloud.websocket.protocol.annotation.Path;

/**
 * Created by Administrator on 2015-9-21.
 */
@Path(Constants.Path.TOKENPATH)
public class TokenRequest extends IRequest {
    private String deviceId;

    public TokenRequest(Context context, String deviceID, IRequestListener listener) {
        super(context, listener);
        this.deviceId = deviceID;
    }

    @Override
    protected String prapareMsg() {
        return  "deviceId="+deviceId;
    }
}
