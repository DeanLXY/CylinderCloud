package com.example.cylindercloud.websocket.protocol;

import android.content.Context;

import com.example.cylindercloud.Constants;
import com.example.cylindercloud.websocket.protocol.annotation.Path;

/**
 * Created by wxj on 2015-9-21.
 */
@Path(Constants.Path.GETCHECKLISTPATH)
public class CheckedListResponse extends IRequest {
    private String[] args;

    public CheckedListResponse(Context context, IRequestListener listener, String... args) {
        super(context, listener, args);
        this.args = args;
    }

    @Override
    protected String prapareMsg() {
        //TODO
        return "{\"token\":\""+this.getToken()+"\",\" rfiduid \":\"3539539762\",\"method\":2}";
    }
}
