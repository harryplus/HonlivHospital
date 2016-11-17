package com.honliv.honlivmall.bean;

import java.io.Serializable;

/**
 * 优惠卷
 * @author Administrator
 *
 */
public class CouponInfo implements Serializable {
		
	private int id; //优惠卷id
	private int classId; //优惠卷类别
	private String className;//优惠卷类别
	private int userId; //用户id
	private String couponCode; //优惠卷编号
	private String couponName;//名称
	private int status;      //状态
	private int limitstatus;      //状态
	private float couponPrice;//金额
	private float limitPrice;//最低消费金额
	private String endDate;//最后使用时间
	private String usedDate;//使用时间
	private String generateTime;//创建时间
	
	private int needPoint;  //兑换需要积分
	
	private String limitStr;//限制条件
	private String couponPwd;//密码
	private int isPwd;  //是否需要密码，0不需要，1需要
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getLimitStr() {
		return limitStr;
	}
	public void setLimitStr(String limitStr) {
		this.limitStr = limitStr;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getLimitstatus() {
		return limitstatus;
	}
	public void setLimitstatus(int limitstatus) {
		this.limitstatus = limitstatus;
	}
	public float getCouponPrice() {
		return couponPrice;
	}
	public void setCouponPrice(float couponPrice) {
		this.couponPrice = couponPrice;
	}
	public float getLimitPrice() {
		return limitPrice;
	}
	public void setLimitPrice(float limitPrice) {
		this.limitPrice = limitPrice;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getUsedDate() {
		return usedDate;
	}
	public void setUsedDate(String usedDate) {
		this.usedDate = usedDate;
	}
	public String getGenerateTime() {
		return generateTime;
	}
	public void setGenerateTime(String generateTime) {
		this.generateTime = generateTime;
	}
	public int getNeedPoint() {
		return needPoint;
	}
	public void setNeedPoint(int needPoint) {
		this.needPoint = needPoint;
	}
	public String getCouponPwd() {
		return couponPwd;
	}
	public void setCouponPwd(String couponPwd) {
		this.couponPwd = couponPwd;
	}
	public int getIsPwd() {
		return isPwd;
	}
	public void setIsPwd(int isPwd) {
		this.isPwd = isPwd;
	}
	@Override
	public String toString() {
		return "CouponInfo [id=" + id + ", classId=" + classId + ", className="
				+ className + ", userId=" + userId + ", couponCode="
				+ couponCode + ", couponName=" + couponName + ", status="
				+ status + ", couponPrice=" + couponPrice + ", limitPrice="
				+ limitPrice + ", endDate=" + endDate + ", usedDate="
				+ usedDate + ", generateTime=" + generateTime + ", needPoint="
				+ needPoint + ", couponPwd=" + couponPwd + ", isPwd=" + isPwd
				+ "]";
	}
}
