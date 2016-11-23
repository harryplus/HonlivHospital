package com.honliv.honlivmall.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.honliv.honlivmall.ConstantValue;
import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.activity.OrderSubmitOkActivity;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by Rodin on 2016/9/5.
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final String TAG = "WXPayEntryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GloableParams.api = WXAPIFactory.createWXAPI(this, ConstantValue.APP_ID);
        GloableParams.api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        GloableParams.api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        Log.i(TAG, "onReq");
    }

    @Override
    public void onResp(final BaseResp resp) {
        Log.i(TAG, "resp.getType()----" + resp.getType());
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            Intent intent = new Intent(this, OrderSubmitOkActivity.class);
            intent.putExtra("orderInfo", GloableParams.CurrentOrder);
            intent.putExtra("result", resp.errCode);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.tran_next_in,
                    R.anim.tran_next_out);
        }
    }
}
