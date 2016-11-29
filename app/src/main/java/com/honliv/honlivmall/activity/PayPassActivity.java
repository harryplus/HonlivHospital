package com.honliv.honlivmall.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.CoreBaseActivity;
import com.honliv.honlivmall.bean.OrderInfo;
import com.honliv.honlivmall.task.AliPayTask;
import com.honliv.honlivmall.task.WeiXinPayTask;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/9/19.
 */
public class PayPassActivity extends CoreBaseActivity {
     OrderInfo orderInfo;
    @BindView(R.id.orderid_value_text)
     TextView orderIdTV;
    @BindView(R.id.paymoney_value_text)
     TextView paymoneyTV;
    @BindView(R.id.paytype_value_text)
     TextView payTypeTV;
     String paymenttype;    //支付类型
    @BindView(R.id.payicon)
     ImageView payicon;

    @Override
    public int getLayoutId() {
        return R.layout.activity_paypass;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        orderInfo = (OrderInfo) this.getIntent().getSerializableExtra("orderInfo");
        if (orderInfo != null) {
            orderIdTV.setText(orderInfo.getOrderCode() + "");
            paymoneyTV.setText("￥" + orderInfo.getAllprice() + "元");
            paymenttype = orderInfo.getPaymenttype();
            if (paymenttype == null) {
                paymenttype = orderInfo.getPaytypename();
            }

            if (paymenttype.equals(getString(R.string.text_weixin_pay))){
                payicon.setBackgroundResource(R.drawable.icon_weixinpay);
            }
            else if (paymenttype.equals(getString(R.string.text_ali_pay))) {
                payicon.setBackgroundResource(R.drawable.icon_alipay);
            }
            else if (paymenttype.equals(getString(R.string.text_hxt_pay))) {
                payicon.setBackgroundResource(R.drawable.icon_hxtpay);
            }
            else if (paymenttype.equals(getString(R.string.text_deliver_pay))) {
                payicon.setBackgroundResource(R.drawable.icon_deliverpay);
            }
            payTypeTV.setText(paymenttype + "");
        }
    }

    public void goPay(View v) {
        if (paymenttype.equals(getString(R.string.text_weixin_pay))) {
            WeiXinPayTask task = new WeiXinPayTask(PayPassActivity.this, orderInfo);
            task.execute();
        } else if (paymenttype.equals(getString(R.string.text_hxt_pay))) {
//            Intent intent = new Intent(PayPassActivity.this, MyHxtActivity.class);
//            intent.putExtra("orderInfo",orderInfo);
//            startActivity(intent);
        } else if (paymenttype.equals(getString(R.string.text_ali_pay))) {
            AliPayTask task = new AliPayTask(PayPassActivity.this);
            task.execute(orderInfo);
        } else if (paymenttype.equals(getString(R.string.text_deliver_pay))) {
            Intent intent = new Intent(PayPassActivity.this,
                    OrderSubmitOkActivity.class);
            intent.putExtra("result", 0);//设置成功标志
            intent.putExtra("orderInfo", orderInfo);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.tran_next_in,
                    R.anim.tran_next_out);
        }
    }

    public void goBack(View v) {
        this.finish();
    }
}
