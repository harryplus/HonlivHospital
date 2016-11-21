package com.honliv.honlivmall;

/**
 * Created by Rodin on 2016/11/10.
 */
public interface ConstantValue {
    /**
     * 图片地址前缀
     */
    String HOST_URL = "http://www.honlivmall.com/api/v1/"; //本app官方网址
    /**
     * 接口地址
     */
//    String URL = HOST_URL + "api/user/verifycode";

    String SecretKey = "honlivhp";

    public String PATH_CACHE = null;
    public static final String SP_NO_IMAGE = "no_image";

    public static final String SP_AUTO_CACHE = "auto_cache";


    /**
     * 图片地址前缀
     */
    String IMAGE_URL = "http://www.honlivmall.com"; //本app官方演示

    /**
     * 接口地址
     */
    String URL = IMAGE_URL + "/api/v1/shop.aspx";
    String HOST = IMAGE_URL;   //各个cookie缓冲所用的前缀
    /**
     * 联接服务器,超时时间
     */
    int CONNECTIONTIME = 60000;

    /**
     * 支付编码
     */
    String PAYCHARSET = "ISO8859-1";
    //    String CHARSET = "GBK";
    String CHARSET = "utf-8";
    //AppId
    String APP_ID = "wx2624d92e1ab18e4e";
    //微信统一下单地址
    String WeiXinPayUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    //商戶号
    String MCH_ID = "1383313902";
    //  API密钥，在商户平台设置
    String MCH_KEY = "WjWUALPGiRUW6aqWItHWhMTQAsYfLeM9";
    String APP_NAME = "宏力商城";
    // 商户PID
    String PARTNER = "2088911823990594";
    // 商户收款账号
    String SELLER = "hongliyishenghuo@163.com";
    // 商户私钥，pkcs8格式
    String RSA_PRIVATE = "MIICXAIBAAKBgQDDiAfEzjYxtGgypYB0we91+s+RfaSglRVSmDI0r+QXFL2117wd\n" +
            "GEgzATcNqqHJgCfwK70aJXHnMAdmFWzjRRErhFgpOyiKOrEp3Ics84M+qrv30UQG\n" +
            "/Qr+xln+6BoAH/PhwwzrF3c9D3E2P97J1/dVwZLvjgweHW1Drd1MVMokBwIDAQAB\n" +
            "AoGAA4qcMWlWu/G7jWdH+1Vj4+wmBEmVSL9KgpN1HVUuOWjLwtZkdtZwwFO+ToYP\n" +
            "phmnH4d7RlT1Si2phKTQsxEWLX55iAZ5hs+BC6usDQXWdju02kWAlB9WKuw6TLoM\n" +
            "A18KRGod8qL9A3KvEwIoe42PhZ5zZW/yAFQSJ5bvWNS7oAkCQQDvkSgu/ox20ri2\n" +
            "iv+oI+uIoYK+2sqyxSKAJfKsxmiUOrsBj//MoqwJ9An0cDF5J5+2FVUVP3QZ2UZM\n" +
            "5DlJvr6DAkEA0PGZTx5ImIzhkr4hPa4+m7j6tevL8cklu3rMGmKZQnk3EFhO8gzD\n" +
            "H0G265GYk6JhuVEzF6dLUcMUOHDSUzINLQJANMydOp5Hfvi6VSEVb41bH+XY1w+J\n" +
            "lRT1oaC7d3DTI7g99v4xtnwzxo3Ok90WSkl9Xbgd5Gzzva6xW8kBB0vO2QJAaOQg\n" +
            "VnuZbhurUgNopJJIk9w04AbThzm3W3q147kDvz8iyW3Lk++cNKDEt1WMBvFJpcKB\n" +
            "p6owQYdxKzRYIbPKsQJBAItf/Rx0WYVBoDPTg7510Czpxqc9NDEs02IswfsC8g8T\n" +
            "yuOrIi/+uVucwTTK3tRCAgyEbtKeuWdZnKWLOnZwB+k=";
}