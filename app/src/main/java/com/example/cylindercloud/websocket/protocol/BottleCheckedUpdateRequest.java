package com.example.cylindercloud.websocket.protocol;

import android.content.Context;

import com.example.cylindercloud.Constants;
import com.example.cylindercloud.websocket.protocol.annotation.Path;

/**
 * Created by wxj on 2015-9-21.
 */
@Path(Constants.Path.PUTBOTTLECHECKUPDATAPATH)
public class BottleCheckedUpdateRequest extends IRequest {
    private String[] args;

    public BottleCheckedUpdateRequest(Context context, IRequestListener listener, String... args) {
        super(context, listener, args);
        this.args = args;
    }

    @Override
    protected String prapareMsg() {
        //TODO
        return "rfiduid=9d14b61f&xjDate=2015-10-12&pd=合格&licend=2020-01-01&licenseNO=TS2210A87-2015&token=" + this.getToken() + "_cccc_cphm_djbh_cpxh_cjh_fdjh_syxz_color_regUnit_gd_zzgb_jybh_zzdm_zzxkzbh_ccrq_zcbh_xjrq_bfrq_azdw_cqjz_CNGID_unitName_licenseNO_licEnd_updateTime";
    }
}
