package com.example.cylindercloud.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wxj on 2015/10/13.
 */
public class StreamUtils {
    public static void close(InputStream inputStream) {
        try {
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
