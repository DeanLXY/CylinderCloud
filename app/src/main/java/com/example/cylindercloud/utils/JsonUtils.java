package com.example.cylindercloud.utils;

import com.google.gson.Gson;

/**
 * Created by wxj on 2015-9-20.
 */
public class JsonUtils {
    public static <T> T fromJson(String json, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }

}
