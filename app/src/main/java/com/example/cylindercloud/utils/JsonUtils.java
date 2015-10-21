package com.example.cylindercloud.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by wxj on 2015-9-20.
 */
public class JsonUtils {
    public static <T> T fromJson(String json, Class<T> clazz) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        Gson gson = new Gson();
        if (json.startsWith("{")) {
            return gson.fromJson(json, clazz);
        } else if (json.startsWith("[")) {
            return gson.fromJson(json, new TypeToken<List<T>>() {
            }.getType());
        }

        return null;
    }

}
