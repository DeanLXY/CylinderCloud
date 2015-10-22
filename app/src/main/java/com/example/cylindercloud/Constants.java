package com.example.cylindercloud;

/**
 * Created by Administrator on 2015-9-18.
 */
public class Constants {
    public static final String WS_ADDRESS = "58.215.2.84";
    public static final String WS_POET = "9000";
    public static final String WEBSOCKET = "ws://qp.hystudio.cn:9000";
//    public static  final String WEBSOCKET = "ws://58.215.2.84:9000/cngs_getToken";

   public class Path {
       public static final String RFIDPATH = "/getBottle";
        /**
         * 获取token<br/>
         * {
         * DeviceID
         * }
         */
        public static final String TOKENPATH = "/cngs_getToken";
        /**
         * 通过气瓶RFID UID获取气瓶信息和对应所在车辆信息<br/>
         * {
         * "token":"rPeDS7jzftEYTDuAX0Tll3548B3LcCGbsI6g766ZRaT+THarZBVFfVYJ4cfDCn1D/zxdWXy/bXo",
         * "rfiduid":"3da3b81f",
         * "method":0
         * }
         */
        public static final String GETBOTTLECARBPATH = "/cngs_getBottleCarB";
        public static final String GETBOTTLECARCPATH = "/cngs_getBottleCarC";
        public static final String GETBOTTLEPATH = "/cngs_getBottle";
        public static final String  GETBOTTLECHECKLISTPATH = "/cngs_getCheckList";
        public static final String  PUTBOTTLECHECKUPDATAPATH = "/cngs_putBottleCheckUpdata";
        public static final String  GETCHECKLISTPATH = "/cngs_GetCheckList";
        public static final String  SYNCDATAPATH = "/cngs_syncData";
        public static final String  GETBOTTLEWARNINGPATH = "/cngs_getBottleWarning";

    }
}
