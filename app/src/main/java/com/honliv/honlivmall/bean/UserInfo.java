package com.honliv.honlivmall.bean;


import java.io.Serializable;

public class UserInfo implements Serializable {
	private int _id;
	
	private int userId; 
	private int point;//会员积分
	private String level; //会员等级， "VIP"
	
	private String nickName;//昵称
	private String trueName;
	private String userName;
	private String phone;//手机
	private String headImage;
	private String password;
	private String newpassword;
	
	private String addressDetail;
	private String email;//邮箱
	
	private String tel;//固定电话
	private String birthday;//生日
	private String province;//区域选择
	private String sex;//性别 1代表男,0代表女
	private int regionId; //选中的区域
	
	private String departmentID;
	private String fileNo;
	//private String bonus;//会员积分
	private String userSession;   //MD5
	private String orderCount;//所下订单数
	private String favoritesCount;//收藏总数
	
	/***收货地址存放在本地***///省市区
	private String provinceAddress;//省市区
	private String detailAddress;//详细地址
	private String postcode;//邮编
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDepartmentID() {
		return departmentID;
	}
	public void setDepartmentID(String departmentID) {
		this.departmentID = departmentID;
	}
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/*public String getBonus() {
		return bonus;
	}
	public void setBonus(String bonus) {
		this.bonus = bonus;
	}*/
	
	
	public String getLevel() {
		return level;
	}
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getUserSession() {
		return userSession;
	}
	public void setUserSession(String userSession) {
		this.userSession = userSession;
	}
	public String getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(String orderCount) {
		this.orderCount = orderCount;
	}
	public String getFavoritesCount() {
		return favoritesCount;
	}
	public void setFavoritesCount(String favoritesCount) {
		this.favoritesCount = favoritesCount;
	}
	public String getProvinceAddress() {
		return provinceAddress;
	}
	public void setProvinceAddress(String provinceAddress) {
		this.provinceAddress = provinceAddress;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	@Override
	public String toString() {
		return "UserInfo [_id=" + _id + ", userId=" + userId + ", point="
				+ point + ", level=" + level + ", nickName=" + nickName
				+ ", trueName=" + trueName + ", userName=" + userName
				+ ", phone=" + phone + ", headImage=" + headImage
				+ ", password=" + password + ", newpassword=" + newpassword
				+ ", addressDetail=" + addressDetail + ", email=" + email
				+ ", tel=" + tel + ", birthday=" + birthday + ", province="
				+ province + ", sex=" + sex + ", regionId=" + regionId
				+ ", departmentID=" + departmentID + ", fileNo=" + fileNo
				+ ", userSession=" + userSession + ", orderCount=" + orderCount
				+ ", favoritesCount=" + favoritesCount + ", provinceAddress="
				+ provinceAddress + ", detailAddress=" + detailAddress
				+ ", postcode=" + postcode + "]";
	}
}
