package com.honliv.honlivmall.bean;
/**
 * 账号中心专用
 * @author Administrator
 *
 */
public class Version {
	/**
	 * 版本号
	 */
	private boolean isNew;
	private String version;
	//private boolean isForce;//
	private String url;
	
	
	
	public boolean isNew() {
		return isNew;
	}
	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	/*public boolean isForce() {
		return isForce;
	}
	public void setForce(boolean isForce) {
		this.isForce = isForce;
	}*/
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Version [isNew=" + isNew + ", version=" + version
				+ ", isForce=" + /*isForce+ */ ", url=" + url + "]";
	}
	
}
