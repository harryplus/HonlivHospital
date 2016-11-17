package com.honliv.honlivmall.bean;

import java.io.Serializable;

/**
 * 初始化参数
 * @author Administrator
 *
 */
public class DefaultParas implements Serializable {
	
	
	private String phone; //手机号
	private int defaultShipId; //快递id 默认为
	private int defaultPayId; //支付id 默认为货到付款
	private int supplierId; //
	private int wechatPayId;  //微信支付的id
	private int minCount;//去结算的每次最少数量
	private float minOrderAmount; //优惠券大于商品金额，显示这个
	private boolean emay_SMS_IsOpen; //是否打开sms短信注册
	
	public float getMinOrderAmount() {
		return minOrderAmount;
	}
	public void setMinOrderAmount(float minOrderAmount) {
		this.minOrderAmount = minOrderAmount;
	}
	public int getDefaultShipId() {
		return defaultShipId;
	}
	public void setDefaultShipId(int defaultShipId) {
		this.defaultShipId = defaultShipId;
	}
	public int getDefaultPayId() {
		return defaultPayId;
	}
	public void setDefaultPayId(int defaultPayId) {
		this.defaultPayId = defaultPayId;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public int getWechatPayId() {
		return wechatPayId;
	}
	public void setWechatPayId(int wechatPayId) {
		this.wechatPayId = wechatPayId;
	}
	public int getMinCount() {
		return minCount;
	}
	public void setMinCount(int minCount) {
		this.minCount = minCount;
	}
	public boolean isEmay_SMS_IsOpen() {
		return emay_SMS_IsOpen;
	}
	public void setEmay_SMS_IsOpen(boolean emay_SMS_IsOpen) {
		this.emay_SMS_IsOpen = emay_SMS_IsOpen;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "DefaultParas [phone=" + phone + ", defaultShipId="
				+ defaultShipId + ", defaultPayId=" + defaultPayId
				+ ", supplierId=" + supplierId + ", wechatPayId=" + wechatPayId
				+ ", minCount=" + minCount + ", minOrderAmount="
				+ minOrderAmount + ", emay_SMS_IsOpen=" + emay_SMS_IsOpen + "]";
	}
}
