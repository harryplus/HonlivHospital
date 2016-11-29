package com.honliv.honlivmall.activity;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.gc.materialdesign.views.Button;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.CoreBaseActivity;
import com.honliv.honlivmall.bean.OrderInfo;
import com.honliv.honlivmall.task.UpdateOrderTask;

import butterknife.BindView;

/**
 * 订单提交成功
 *
 * @author Administrator
 */
public class OrderSubmitOkActivity extends CoreBaseActivity {
    @BindView(R.id.orderid_value_text)
     TextView orderIdTV;
    @BindView(R.id.paymoney_value_text)
     TextView paymoneyTV;
    @BindView(R.id.paytype_value_text)
     TextView payTypeTV;
    @BindView(R.id.to_order_payment_text)
     Button toPayTV;
    @BindView(R.id.textOrderPhone)
     TextView textOrderPhone;
    @BindView(R.id.result)
    TextView resultTV;
    @BindView(R.id.result_detail)
    TextView result_detail;

    OrderInfo orderInfo;
    int result;


    /**
     * 继续购物
     */
    public void goShop(View view) {
        Intent intent = new Intent(OrderSubmitOkActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.tran_pre_in, R.anim.tran_pre_out);// 上个
        //overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
    }

    /**
     * 查看订单
     *
     * @param view
     */
    public void watchOrder(View view) {
//        Intent intent = new Intent(OrderSubmitOkActivity.this, MyOrderActivity.class);
//        startActivity(intent);
//        finish();
//        overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
    }

    /**
     * 去支付
     */
    public void paymentOrder(View view) {
        if (orderInfo == null) {
            return;
        }

        showToPayDialog();
    }

     void showToPayDialog() {
        Builder builder = new Builder(this);
        builder.setOnCancelListener(new OnCancelListener() {// 无法取消对话框
            public void onCancel(DialogInterface dialog) {
                // loadHomeActivity();// 取消对话框，进入主界面
//				LogUtil.info(" 取消对话框");
            }
        });
        builder.setTitle("支付确定");
        builder.setMessage("您确定要去支付吗？");
        builder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
//                        WeiXinPayTask myTask = new WeiXinPayTask(OrderSubmitOkActivity.this, orderInfo);
//                        myTask.execute();
                        Intent intent = new Intent(OrderSubmitOkActivity.this, PayPassActivity.class);
                        intent.putExtra("orderInfo", orderInfo);
                        startActivity(intent);
                        overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);//下个
                    }
                });
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        builder.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_submit_ok;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        orderInfo = (OrderInfo) this.getIntent().getSerializableExtra("orderInfo");
        result = this.getIntent().getIntExtra("result", -100);
        if (orderInfo == null) {
            return;
        }
        /**
         * 0成功 -2取消 其他失败
         */
        String paymenttype = orderInfo.getPaymenttype();
        if (paymenttype == null) {
            paymenttype = orderInfo.getPaytypename();
        }
        orderIdTV.setText(orderInfo.getOrderCode() + "");
        paymoneyTV.setText("￥" + orderInfo.getAllprice() + "元");
        payTypeTV.setText(paymenttype + "");
        if (result == -2) {//取消
            resultTV.setText(getString(R.string.pay_fail));
            result_detail.setText(getString(R.string.pay_cancel_detail));
            result_detail.setTextColor(getResources().getColor(R.color.black));
        } else if (result == 0) {//成功
            resultTV.setText(getString(R.string.pay_success));
            result_detail.setText(getString(R.string.pay_success_detail));
            result_detail.setTextColor(getResources().getColor(R.color.red));
            toPayTV.setVisibility(View.GONE);
//            GloableParams.CurrentOrder=orderInfo;
            new UpdateOrderTask(orderInfo).execute();
        } else {//失败
            resultTV.setText(getString(R.string.pay_fail));
            result_detail.setText(getString(R.string.pay_fail_detail));
            result_detail.setTextColor(getResources().getColor(R.color.black));
        }

        String phone = getString(R.string.servicesphone);
//        String phone = sp.getString("phone", "400 8888 8888");
        textOrderPhone.setText(phone);

        Double all = Double.parseDouble(orderInfo.getAllprice() + "");

        if (paymenttype.contains("货到") || all <= 0) {
            toPayTV.setVisibility(View.GONE);
        }
    }
}
