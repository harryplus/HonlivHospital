package com.honliv.honlivmall.bean;
/**
 * 首页banner图片信息
 * @author wang
 *
 */
public class HomeBanner {
	private int id; 
	private String title;
	private String pic;
	private String url;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "HomeBanner [id=" + id + ", title=" + title + ", pic=" + pic
				+ ", url=" + url + "]";
	}
	
}
