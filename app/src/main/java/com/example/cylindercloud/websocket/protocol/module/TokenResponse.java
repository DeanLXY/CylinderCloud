package com.example.cylindercloud.websocket.protocol.module;

import com.example.cylindercloud.moudle.Token;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/10/21 0021.
 */
public class TokenResponse extends IResponse {
    //    {
//        "message": "Success",
//            "result": {
//        "token": "rPeDS7jzftEYTDuAX0Tll3548B3LcCGbsI6g766ZRaT+THarZBVFfVYJ4cfDCn1D/zxdWXy/bXo="
//    },
//        "errorCode": 0
//    }

    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result implements Serializable {
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    public Token toToken() {
        Token token = new Token(this.result.getToken(), System.currentTimeMillis(), -1);
        return token;
    }
}
