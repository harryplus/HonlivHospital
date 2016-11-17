package com.honliv.honlivmall.bean;

import java.io.Serializable;
import java.util.List;


public class OrderInfo implements Serializable {

    private int id;
    private int orderid;  //这个要改成id
    private String orderCode;   //订单编号
    private String allprice;   //订单总金额
    private String status;   //订单状态status
    private String statusstr;   //订单状态status
    private String time;   //订单下单时间
    private String paymenttype;////完成订单时的支付方式
    private String paytypename; //支付方式         "paytypename": "支付宝手机支付",
    private String paygateway;//支付网关名称       "paygateway": "alipaywap",
    private String pic;   //订单图片
    private List<String> pics;   //订单图片列表
    private String flag;   ////订单标识，1=>可删除可修改 2=>不可修改 3=>已完成

    private String errMsg;   ////错误信息
    private List<OrderFollow> order_follows;//订单跟踪
    private AddressInfo address_info;//配送地址
    private PaymentInfo payment_info; //支付信息
    private List<Product> productlist;
    //	private int defaultShipId; //快递id 默认为无忧蚂蚁
//	private int defaultPayId; //支付id 默认为货到付款
    private boolean isreturn;  //订单是否可以退货

    /////退货订单
    private int returnid;
    private String returncode;

    private List<OrderInfo> suborders;

    private String name;//为了提交订单返回库存不足
    private String sku;//为了提交订单返回库存不足
    private int stockcount;//为了提交订单返回库存不足

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getStockcount() {
        return stockcount;
    }

    public void setStockcount(int stockcount) {
        this.stockcount = stockcount;
    }

    public List<OrderInfo> getSuborders() {
        return suborders;
    }

    public void setSuborders(List<OrderInfo> suborders) {
        this.suborders = suborders;
    }

    public int getReturnid() {
        return returnid;
    }

    public void setReturnid(int returnid) {
        this.returnid = returnid;
    }

    public String getReturncode() {
        return returncode;
    }

    public void setReturncode(String returncode) {
        this.returncode = returncode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusstr() {
        return statusstr;
    }

    public void setStatusstr(String statusstr) {
        this.statusstr = statusstr;
    }

    public boolean isIsreturn() {
        return isreturn;
    }

    public void setIsreturn(boolean isreturn) {
        this.isreturn = isreturn;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public String getAllprice() {
        return allprice;
    }

    public void setAllprice(String allprice) {
        this.allprice = allprice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPaytypename() {
        return paytypename;
    }

    public void setPaytypename(String paytypename) {
        this.paytypename = paytypename;
    }

    public String getPaygateway() {
        return paygateway;
    }

    public void setPaygateway(String paygateway) {
        this.paygateway = paygateway;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<OrderFollow> getOrder_follows() {
        return order_follows;
    }

    public void setOrder_follows(List<OrderFollow> order_follows) {
        this.order_follows = order_follows;
    }

    public AddressInfo getAddress_info() {
        return address_info;
    }

    public void setAddress_info(AddressInfo address_info) {
        this.address_info = address_info;
    }

    public PaymentInfo getPayment_info() {
        return payment_info;
    }

    public void setPayment_info(PaymentInfo payment_info) {
        this.payment_info = payment_info;
    }

    public List<Product> getProductlist() {
        return productlist;
    }

    public void setProductlist(List<Product> productlist) {
        this.productlist = productlist;
    }

    @Override
    public String toString() {
        return "OrderInfo [id=" + id + ", orderid=" + orderid + ", orderCode="
                + orderCode + ", allprice=" + allprice + ", status=" + status
                + ", statusstr=" + statusstr + ", time=" + time
                + ", paymenttype=" + paymenttype + ", paytypename="
                + paytypename + ", paygateway=" + paygateway + ", pic=" + pic
                + ", pics=" + pics + ", flag=" + flag + ", errMsg=" + errMsg
                + ", order_follows=" + order_follows + ", address_info="
                + address_info + ", payment_info=" + payment_info
                + ", productlist=" + productlist + ", isreturn=" + isreturn
                + ", returnid=" + returnid + ", returncode=" + returncode
                + ", suborders=" + suborders + ", name=" + name + ", sku="
                + sku + ", stockcount=" + stockcount + "]";
    }
}
