package com.honliv.honlivmall.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.honliv.honlivmall.ConstantValue;
import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.bean.OrderInfo;
import com.honliv.honlivmall.util.MD5;
import com.honliv.honlivmall.util.Utils;
import com.tencent.mm.sdk.modelpay.PayReq;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Rodin on 2016/9/10.
 */
public class WeiXinPayTask extends AsyncTask<Void, Void, String> {
    private static final String TAGS = "WeiXinPayTask";
    private final OrderInfo orderInfo;
    private Context mContext;

    public WeiXinPayTask(Context mContext, OrderInfo orderInfo) {
        this.mContext = mContext;
        this.orderInfo = orderInfo;
    }

    @Override
    protected String doInBackground(Void... params) {
        String url = String.format(ConstantValue.WeiXinPayUrl);
        String entity = genProductArgs();
        try {
            entity = new String(entity.getBytes(), ConstantValue.PAYCHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] buf = Utils.httpPost(url, entity);
        if (buf != null) {
            String content = new String(buf);
            return content;
        } else {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String content) {
        try {
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(new ByteArrayInputStream(content.getBytes()), ConstantValue.CHARSET);//设置数据源编码
            int evnType = parser.getEventType();//获取事件类型

            PayReq payreq = new PayReq();
            while (evnType != XmlPullParser.END_DOCUMENT) {
                switch (evnType) {
                    case XmlPullParser.START_TAG:
                        String name = parser.getName();
                        if (!name.equalsIgnoreCase("xml")) {
                            String text = parser.nextText();
                            if ("prepay_id".equalsIgnoreCase(name)) {
                                payreq.prepayId = text;
                            } else if ("return_msg".equalsIgnoreCase(name) && !text.equalsIgnoreCase("ok")) {
                                Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
                            } else if ("err_code_des".equalsIgnoreCase(name)) {
                                Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                    default:
                        break;
                }
                evnType = parser.next();
            }
            payreq.appId = ConstantValue.APP_ID;
            payreq.partnerId = ConstantValue.MCH_ID;
            payreq.packageValue = "Sign=WXPay";
            payreq.nonceStr = genNonceStr();
            payreq.timeStamp = String.valueOf(genTimeStamp());

            List<NameValuePair> signParams = new LinkedList<NameValuePair>();
            signParams.add(new BasicNameValuePair("appid", payreq.appId));
            signParams.add(new BasicNameValuePair("noncestr", payreq.nonceStr));
            signParams.add(new BasicNameValuePair("package", payreq.packageValue));
            signParams.add(new BasicNameValuePair("partnerid", payreq.partnerId));
            signParams.add(new BasicNameValuePair("prepayid", payreq.prepayId));
            signParams.add(new BasicNameValuePair("timestamp", payreq.timeStamp));

            payreq.sign = genAppSign(signParams);
            GloableParams.api.registerApp(ConstantValue.APP_ID);
            GloableParams.api.sendReq(payreq);
        } catch (Exception e) {
        }
    }

    private String genProductArgs() {
        StringBuilder xml = new StringBuilder();
        try {
            String nonceStr = genNonceStr();
            xml.append("</xml>");
            List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
            packageParams.add(new BasicNameValuePair("appid", ConstantValue.APP_ID));
            packageParams.add(new BasicNameValuePair("attach", ConstantValue.APP_NAME));
            packageParams.add(new BasicNameValuePair("body", ConstantValue.APP_NAME));
            packageParams.add(new BasicNameValuePair("mch_id", ConstantValue.MCH_ID));
            packageParams.add(new BasicNameValuePair("nonce_str", nonceStr));
            packageParams.add(new BasicNameValuePair("notify_url", "http://www.honlivmall.com/pay/payment/notify_url.aspx"));//接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
            if (orderInfo == null) {
                return null;
            }
            packageParams.add(new BasicNameValuePair("out_trade_no", orderInfo.getOrderCode()));
            GloableParams.CurrentOrder = orderInfo;
            packageParams.add(new BasicNameValuePair("spbill_create_ip", "127.0.0.1"));//用户端实际ip

            String allprice = orderInfo.getAllprice();
            if (allprice == null) {
                allprice = orderInfo.getPayment_info().getOrderprice();
            }
            allprice = ((int) Double.parseDouble(allprice) * 100) + "";
//            allprice="1";

            packageParams.add(new BasicNameValuePair("total_fee", allprice));

            packageParams.add(new BasicNameValuePair("trade_type", "APP"));
            String sign = genPackageSign(packageParams);
            packageParams.add(new BasicNameValuePair("sign", sign));

            String xmlstring = toXml(packageParams);
            return xmlstring;
        } catch (Exception e) {
        }
        return null;
    }

    private String genNonceStr() {
        Random random = new Random();
        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }

    /**
     * 生成签名
     */
    private String genPackageSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(ConstantValue.MCH_KEY);
        String packageSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
        return packageSign;
    }

    private String toXml(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        for (int i = 0; i < params.size(); i++) {
            sb.append("<" + params.get(i).getName() + ">");
            sb.append(params.get(i).getValue());
            sb.append("</" + params.get(i).getName() + ">");
        }
        sb.append("</xml>");
        return sb.toString();
    }

    private String genAppSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(ConstantValue.MCH_KEY);
        String value = sb.toString();
        String
                appSign = MD5.getMessageDigest(value.getBytes()).toUpperCase();
        return appSign;
    }

    private long genTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }
}