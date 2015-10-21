package com.example.cylindercloud.websocket.protocol;

import android.content.Context;

import com.example.cylindercloud.Constants;
import com.example.cylindercloud.websocket.protocol.annotation.Path;

/**
 * Created by wxj on 2015-9-21.
 */
@Path(Constants.Path.GETBOTTLECARBPATH)
public class BottleCarBRequest extends IRequest {
    public BottleCarBRequest(Context context, IRequestListener listener, String... args) {
        super(context, listener, args);
    }

    @Override
    protected String prapareMsg() {
        return "rfiduid=3da3b81f&token=" + this.getToken() + "_cccc_cphm_djbh_cpxh_cjh_fdjh_syxz_color_regUnit_gd_zzgb_jybh_zzdm_zzxkzbh_ccrq_zcbh_xjrq_bfrq_azdw_cqjz_CNGID_unitName_licenseNO_licEnd_updateTime";
//        return "token="+this.getToken()+",rfiduid=3da3b81f,infotab=_color,method=0";
    }
}
