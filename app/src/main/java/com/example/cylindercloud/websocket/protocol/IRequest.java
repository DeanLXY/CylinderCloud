package com.example.cylindercloud.websocket.protocol;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

import com.example.cylindercloud.App;
import com.example.cylindercloud.R;
import com.example.cylindercloud.db.DBAdapter;
import com.example.cylindercloud.moudle.Token;
import com.example.cylindercloud.utils.JsonUtils;
import com.example.cylindercloud.utils.LogUtils;
import com.example.cylindercloud.utils.SnackbarUtils;
import com.example.cylindercloud.utils.SweetDialogUtils;
import com.example.cylindercloud.websocket.WebSocketManager;
import com.example.cylindercloud.websocket.protocol.annotation.Path;
import com.example.cylindercloud.websocket.protocol.module.TokenResponse;

/**
 * Created by Administrator on 2015-9-21.
 */
public abstract class IRequest {
    private Context context;
    private IRequestListener listener;
    private String path;
    private String tag;
    private Dialog pDialog;
    private String token;

    public IRequest(Context context, IRequestListener listener, String... args) {
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

    public void requestWithToken() {
        requestToken(this);
    }

    public void request() {
        String msg = prapareMsg();
        LogUtils.d("%s<>%s", getPath(), msg);
        WebSocketManager wsm = new WebSocketManager(context, getPath(), msg, new IRequestListener() {
            @Override
            public void onSuccess(String payload) {
                if (pDialog != null && pDialog.isShowing()) {
                    pDialog.dismiss();
                    pDialog = null;
                }
                if (listener != null) {
                    listener.onSuccess(payload);
                }
            }

            @Override
            public void onClose(int code, String reason) {
                if (pDialog != null && pDialog.isShowing()) {
                    pDialog.dismiss();
                    pDialog = null;
                }
                if (listener != null) {
                    listener.onClose(code, reason);
                }
            }
        });
//        wsm.sendTextMessage(msg);
        pDialog = SweetDialogUtils.show(context, SweetDialogUtils.AlertType.PROGRESS_TYPE, "正在努力获取");
        wsm.connect();
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public synchronized void requestToken(final IRequest request) {
        Token token = App.getInstance().getToken();
        if (token == null)
            token = DBAdapter.getInstance().getToken();
        if (token == null) {
            IRequest tokenRequest = new TokenRequest(context, App.getInstance().getDeviceId(), new IRequestListener() {
                @Override
                public void onSuccess(String payload) {
                    TokenResponse response = JsonUtils.fromJson(payload, TokenResponse.class);
                    if (response.isRequestSuccssful()) {
                        DBAdapter.getInstance().addToken(response.toToken());
                        App.getInstance().setToken(response.toToken());
                        setToken(response.toToken().getToken());
                        request.request();
                    } else {
                        SnackbarUtils.show((Activity) context, R.string.device_unauth);
                    }
                }

                @Override
                public void onClose(int code, String reason) {
                }
            });
            tokenRequest.request();
        } else {
            if (token == null) {
                return;
            }
            if (token.isWorked()) {
                App.getInstance().setToken(token);
            } else {
                IRequest tokenRequest = new TokenRequest(context, App.getInstance().getDeviceId(), new IRequestListener() {
                    @Override
                    public void onSuccess(String payload) {
                        TokenResponse response = JsonUtils.fromJson(payload, TokenResponse.class);
                        if (response.isRequestSuccssful()) {
                            DBAdapter.getInstance().addToken(response.toToken());
                            App.getInstance().setToken(response.toToken());
                            setToken(response.toToken().getToken());
                            request.request();
                        } else {
                            SnackbarUtils.show((Activity) context, R.string.device_unauth);
                        }
                    }

                    @Override
                    public void onClose(int code, String reason) {
                    }
                });
                tokenRequest.request();
            }
        }

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
