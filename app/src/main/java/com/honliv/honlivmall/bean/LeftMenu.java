package com.honliv.honlivmall.bean;
/**
 * 左侧分类菜单
 * @author Administrator
 *
 */
public class LeftMenu{
	
	/*
	 * 
	 *  {
                "id": 61,
                "name": "童装",
                
                "pic": "/Content/themes/base/Shop/images/none.png",
               
            },

	 * */
	private int id;//分类id
	private String name;
	private String pic;
	private String des;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	@Override
	public String toString() {
		return "LeftMenu [id=" + id + ", name=" + name + ", pic=" + pic + "]";
	}
}
