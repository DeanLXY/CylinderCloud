package com.example.cylindercloud.websocket.protocol;

import android.content.Context;

import com.example.cylindercloud.websocket.WebSocketManager;
import com.example.cylindercloud.websocket.protocol.annotation.Path;

/**
 * Created by Administrator on 2015-9-21.
 */
public abstract class IRequest {
    private Context context;
    private IRequestListener listener;
    private String path;
    private String tag;

    public IRequest(Context context, IRequestListener listener,String ...args) {
        this.context = context;
        this.listener = listener;
        Path path = this.getClass().getAnnotation(Path.class);
        if (path != null) {
            String val = path.value();
            setPath(val);
        }
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void request() {
        String msg = prapareMsg();
        WebSocketManager wsm = new WebSocketManager(context,getPath(), msg, listener);
//        wsm.sendTextMessage(msg);
        wsm.connect();
    }

    public void cancel() {
        QueueManager.getManager().getQueue().remove(this);
    }

    public IRequestListener getListener() {
        return listener;
    }

    protected abstract String prapareMsg();

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IRequest iRequest = (IRequest) o;

        if (path != null ? !path.equals(iRequest.path) : iRequest.path != null) return false;
        return !(tag != null ? !tag.equals(iRequest.tag) : iRequest.tag != null);

    }

    @Override
    public int hashCode() {
        int result = path != null ? path.hashCode() : 0;
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        return result;
    }
}
