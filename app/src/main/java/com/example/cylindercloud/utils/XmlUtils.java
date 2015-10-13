package com.example.cylindercloud.utils;

import android.text.TextUtils;
import android.util.Xml;

import com.example.cylindercloud.App;
import com.example.cylindercloud.R;

import org.apache.http.protocol.HTTP;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wxj on 2015/10/13.
 */
public class XmlUtils {
    private final static int CNGSAPPDICTRES = R.raw.cngsappdict;

    public static String fromXML(String xml, String element) {
        InputStream inputStream = App.getInstance().getResources().openRawResource(CNGSAPPDICTRES);
        XmlPullParser pullParser = Xml.newPullParser();
        try {
            pullParser.setInput(inputStream, HTTP.UTF_8);
            int eventType = pullParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {

                    case XmlPullParser.START_DOCUMENT:

                        //DO NOTHING
                        break;
                    case XmlPullParser.START_TAG:
                        if (TextUtils.equals(element, pullParser.getName())) {
                            return pullParser.nextText();
                        }
                        break;
                }
                eventType = pullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            StreamUtils.close(inputStream);
        }
        return null;
    }
}
