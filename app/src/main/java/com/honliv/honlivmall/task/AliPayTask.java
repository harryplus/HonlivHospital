package com.honliv.honlivmall.task;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.honliv.honlivmall.ConstantValue;
import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.bean.OrderInfo;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.util.PayResult;
import com.honliv.honlivmall.util.SignUtils;

import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Rodin on 2016/10/24.
 */
public class AliPayTask extends AsyncTask<OrderInfo, Void, String> {
    private static final String TAG = "AliPayTask";
    private Activity mActivity;
    private String OutTradeNo;
    private OrderInfo orderInfo;

    public AliPayTask(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    protected String doInBackground(OrderInfo... params) {
        orderInfo = params[0];
        OutTradeNo = params[0].getOrderCode();
        List<Product> productlist = params[0].getProductlist();
        Product product = null;
        if (productlist != null && productlist.size() > 0) {
            product = productlist.get(0);
        }
        String allprice = params[0].getAllprice();
        if (allprice == null) {
            allprice = params[0].getPayment_info().getOrderprice();
        }
        String productName = null;
        String productdesc = null;
        if (product != null) {
            productName = product.getName();
            productdesc = product.getProductdesc();
        } else {
            productName = mActivity.getString(R.string.app_name);
            productdesc = mActivity.getString(R.string.app_name);
        }
        String orderInfo = getOrderInfo(productName, productdesc, allprice);
//        String orderInfo = getOrderInfo(productName, productdesc, "0.01");
//        String orderInfo = getOrderInfo("测试的商品", "该测试商品的详细描述", "0.01");
        /**
         * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
         */
        String sign = sign(orderInfo);
        Log.i(TAG, "null--" + (sign == null));
        try {
            /**
             * 仅需对sign 做URL编码
             */
            sign = URLEncoder.encode(sign, ConstantValue.CHARSET);
        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }

        /**
         * 完整的符合支付宝参数规范的订单信息
         */
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

        // 构造PayTask 对象
        PayTask alipay = new PayTask(mActivity);
        // 调用支付接口，获取支付结果

        String result = alipay.pay(payInfo, true);

        return result;
    }

    @Override
    protected void onPostExecute(String str) {
        PayResult payResult = new PayResult(str);
        /**
         * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
         * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
         * docType=1) 建议商户依赖异步通知
         */
        String resultInfo = payResult.getResult();// 同步返回需要验证的信息

        String resultStatus = payResult.getResultStatus();
        // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
        int result = -1;
        if (TextUtils.equals(resultStatus, "9000")) {
//            Toast.makeText(mActivity, "支付成功", Toast.LENGTH_SHORT).show();
            result = 0;
        } else {
            // 判断resultStatus 为非"9000"则代表可能支付失败
            // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
            if (TextUtils.equals(resultStatus, "8000")) {
                result = -1;
            } else {
                // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                result = -2;
            }
        }
        Log.i(TAG, "payresult---" + result);
        GloableParams.CurrentOrder = orderInfo;
//        Intent intent = new Intent(mActivity, OrderSubmitOkActivity.class);
//        intent.putExtra("orderInfo", orderInfo);

//        intent.putExtra("result", result);
//        mActivity.startActivity(intent);
//        mActivity.finish();
    }

    /**
     * 商品名称 详情 价格
     */
    private String getOrderInfo(String subject, String body, String price) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + ConstantValue.PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + ConstantValue.SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + OutTradeNo + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        return orderInfo;
    }

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     */
//    private String getOutTradeNo() {
//        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
//        Date date = new Date();
//        String key = format.format(date);
//
//        Random r = new Random();
//        key = key + r.nextInt();
//        key = key.substring(0, 15);
//        return key;
//    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content 待签名订单信息
     */
    private String sign(String content) {
        return SignUtils.sign(content, ConstantValue.RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     */
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }
}
