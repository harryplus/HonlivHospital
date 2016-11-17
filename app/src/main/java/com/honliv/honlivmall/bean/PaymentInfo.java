package com.honliv.honlivmall.bean;

import java.io.Serializable;

public class PaymentInfo implements Serializable {

    private String yunfei;//运费
    private float freight;//运费
    private float allprice;//价格
    private String productprice;//商品金额
    private String orderprice;//应付金额
    private String returnprice;//返回金额


    public float getAllprice() {
        return allprice;
    }

    public void setAllprice(float allprice) {
        this.allprice = allprice;
    }

    public float getFreight() {
        return freight;
    }

    public void setFreight(float freight) {
        this.freight = freight;
    }

    public String getYunfei() {
        return yunfei;
    }

    public void setYunfei(String yunfei) {
        this.yunfei = yunfei;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    public String getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(String orderprice) {
        this.orderprice = orderprice;
    }

    public String getReturnprice() {
        return returnprice;
    }

    public void setReturnprice(String returnprice) {
        this.returnprice = returnprice;
    }

    @Override
    public String toString() {
        return "PaymentInfo [yunfei=" + yunfei + ", freight=" + freight
                + ", productprice=" + productprice + ", orderprice="
                + orderprice + ", returnprice=" + returnprice + "]";
    }
}
