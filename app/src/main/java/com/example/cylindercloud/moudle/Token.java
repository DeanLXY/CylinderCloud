package com.example.cylindercloud.moudle;

import android.content.ContentValues;

import com.example.cylindercloud.db.DBAdapter;
import com.example.cylindercloud.db.DBHelper;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/10/21 0021.
 */
public class Token implements Serializable {
    private String token;
    private long createTime;
    private long endTime;

    public Token(String token, long createTime, long endTime) {
        this.token = token;
        this.createTime = createTime;
        this.endTime = endTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token1 = (Token) o;

        return !(token != null ? !token.equals(token1.token) : token1.token != null);

    }

    @Override
    public int hashCode() {
        return token != null ? token.hashCode() : 0;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put("token", this.token);
        values.put("create_time", System.currentTimeMillis());
        values.put("end_time", -1);
        return values;
    }


    public boolean isWorked() {
        if (this.endTime == -1) {
            long currentTime = System.currentTimeMillis();
            long dTime = currentTime - createTime;
            if (dTime > 24 * 60 * 60) { // 大于1天
                return false;
            } else {
                ContentValues values = toContentValues();
                values.put("end_time", currentTime);
                DBAdapter.getInstance().update(DBHelper.TABLE_TOKEN, values, "token=?", new String[]{this.token});
                return true;
            }
        } else {
            long currentTime = System.currentTimeMillis();
            long dTime = currentTime - createTime;
            if (dTime > 24 * 60 * 60) { // 大于1天
                return false;
            } else {
                return true;
            }
        }
    }
}
