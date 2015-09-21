package com.example.cylindercloud.websocket.protocol;

import android.content.Context;

import com.example.cylindercloud.Constants;
import com.example.cylindercloud.websocket.protocol.annotation.Path;

/**
 * Created by wxj on 2015-9-21.
 */
@Path(Constants.Path.PUTBOTTLECHECKUPDATAPATH)
public class BottleCheckedUpdateRequest extends IRequest {
    private String[] args;

    public BottleCheckedUpdateRequest(Context context, IRequestListener listener, String... args) {
        super(context, listener, args);
        this.args = args;
    }

    @Override
    protected String prapareMsg() {
        //TODO
        return "{\"token\":\"rPeDS7jzftEYTDuAX0Tll3548B3LcCGbsI6g766ZRaT+THarZBVFfVYJ4cfDCn1D/zxdWXy/bXo\",\" rfiduid \":\"3539539762\",\"method\":2}";
    }
}
