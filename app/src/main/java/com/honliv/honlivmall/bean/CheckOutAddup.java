package com.honliv.honlivmall.bean;
/**
 * 总计
 * @author vinice
 *
 */

@Deprecated
public class CheckOutAddup {
	
	/**
	 * 商品金额总计
	 */
	private float totalPrice;
	
	/**
	 *优惠
	 */
	private float favorableprice;
	
	/**
	 * 运费
	 */
	private float freight;
	
	/**
	 * 促销减钱
	 */
	private float promCut;
	
	/**
	 * 实际支付价格
	 */
	private float payprice;

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public float getFavorableprice() {
		return favorableprice;
	}

	public void setFavorableprice(float favorableprice) {
		this.favorableprice = favorableprice;
	}

	public float getFreight() {
		return freight;
	}

	public void setFreight(float freight) {
		this.freight = freight;
	}

	public float getPromCut() {
		return promCut;
	}

	public void setPromCut(float promCut) {
		this.promCut = promCut;
	}

	public float getPayprice() {
		return payprice;
	}

	public void setPayprice(float payprice) {
		this.payprice = payprice;
	}
}
