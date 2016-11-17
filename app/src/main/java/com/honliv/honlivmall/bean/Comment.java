package com.honliv.honlivmall.bean;

public class Comment {
/*
	"grade":"5",                  //评分数
    "username":"用户",            //评论用户
    "commentime":"2013-11-11 23:00:00"    //评论时间
    "buytime":"2013-11-01 23:00:00"    //购买时间
    "commoncontent":"评论内容详细描述"    //评论内容心得
    "color":"清新红"    //颜色 
*/
	private String gradeNum;
	private String name;
	private String commontime;
	private String buyTime;
	private String commoncontent;
	private String color;
	
	private int commentFlags;   //1, 好评  2 中评 3差评

	private int userId; //评论人id
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getGradeNum() {
		return gradeNum;
	}

	public void setGradeNum(String gradeNum) {
		this.gradeNum = gradeNum;
	}

	public String getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCommontime() {
		return commontime;
	}

	public void setCommontime(String commontime) {
		this.commontime = commontime;
	}

	public String getCommoncontent() {
		return commoncontent;
	}

	public void setCommoncontent(String commoncontent) {
		this.commoncontent = commoncontent;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getCommentFlags() {
		return commentFlags;
	}

	public void setCommentFlags(int commentFlags) {
		this.commentFlags = commentFlags;
	}
}
