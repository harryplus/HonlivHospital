package com.honliv.honlivmall.bean;



@Deprecated
public class CheckOut {

	/**
	 * 收货人信息
	 */
	private AddressInfo addressInfo;
	
	/**
	 * 支付方式
	 */
	private PaymentInfo paymentInfo;
	
	/**
	 * 送货时间
	 */
	private DeliveryInfo deliveryInfo;
	
	/**
	 * 总计
	 */
	private CheckOutAddup checkOutAddup;

	public AddressInfo getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(AddressInfo addressInfo) {
		this.addressInfo = addressInfo;
	}

	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}

	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public DeliveryInfo getDeliveryInfo() {
		return deliveryInfo;
	}

	public void setDeliveryInfo(DeliveryInfo deliveryInfo) {
		this.deliveryInfo = deliveryInfo;
	}

	public CheckOutAddup getCheckOutAddup() {
		return checkOutAddup;
	}

	public void setCheckOutAddup(CheckOutAddup checkOutAddup) {
		this.checkOutAddup = checkOutAddup;
	}
}
