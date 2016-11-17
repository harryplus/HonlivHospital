package com.honliv.honlivmall.util;

import com.alibaba.fastjson.JSON;
import com.honliv.honlivmall.bean.ErrorInfo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 判断网络返回的js字符串
 * @author wang
 */
public abstract  class MyJSUtil {
	/**
	 * 判断是否有正确的数据返回，成功为true
	 * @param jsonObject
	 * @return
	 */
	public static boolean isSuccess(JSONObject jsonObject) {
		try {
			String status = jsonObject.getString("status");
			if ("success".equals(status)) {
				return true;
				//PromptManager.showToastTest(getContext(), err.getMessage());
			} 
			if("failed".equals(status)){
				//拿到错误信息，提醒用户
				String errorMsg = jsonObject.getString("result");
				JSONObject jsobj = new JSONObject(errorMsg);
				LogUtil.info(MyJSUtil.class, "到达=failed");
				ToastUtil.showShortToast(jsobj.getString("message"));
				return false;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}
	// 错误时: {"id":793,"result":
	// {"status":"failed","result":{"error_code":"40","message":"登录失败，请确认用户名或密码是否正确。"}}}
	/**
	 * 检查网络连接返回的js数据
	 * @param String 类型  可转成JSONObject 
	 * @return 返回一个 JSONObject，如果没有数据，那么返回空
	 */
	static String errorMsg;
	public static JSONObject checkResponse(String jsonObjectStr){
		JSONObject jsResult = null;
		if(jsonObjectStr==null){
			LogUtil.info("参数jsonObjectStr为空");
			errorMsg =  "网络连接错误或服务器忙!!!";
			return jsResult;
		}
		try {
			JSONObject jsonObject = new JSONObject(jsonObjectStr);
			String outterResult = jsonObject.getString("result");
			// 最外层result
			JSONObject outterjsobj = new JSONObject(outterResult);

			String status = outterjsobj.getString("status");
			String innerResult = outterjsobj.getString("result");
			if("failed".equals(status)){
				ErrorInfo err = JSON.parseObject(innerResult, ErrorInfo.class);
				LogUtil.info(MyJSUtil.class,"1==="+err.getMessage());
				//LogUtil.info(MyJSUtil.class,"2="+new JSONObject(innerResult).getString("message"));
				errorMsg = err.getMessage();
				//showTOUser(err.getMessage());
				//PromptManager.showToastTest(getContext(), err.getMessage());
			}else{
				//返回里面那个resutl对象
				jsResult = new JSONObject(innerResult);
			}
		} catch (JSONException e){
			e.printStackTrace();
		}
		return jsResult;
	}
	
	public static String checkResponseString(String jsonObjectStr){
		
		LogUtil.info(MyJSUtil.class,"网络返回的总的数据=="+jsonObjectStr);
		String jsResult = null;
		if(jsonObjectStr==null){
			LogUtil.info("参数jsonObjectStr为空");
			errorMsg =  "网络连接错误或服务器忙!!!";
			return jsResult;
		}
		try {
			JSONObject jsonObject = new JSONObject(jsonObjectStr);
			String outterResult = jsonObject.getString("result");
			// 最外层result
			JSONObject outterjsobj = new JSONObject(outterResult);
			
			String status = outterjsobj.getString("status");
			String innerResult = outterjsobj.getString("result");
			if("failed".equals(status)){
				ErrorInfo err = JSON.parseObject(innerResult, ErrorInfo.class);
				LogUtil.info(MyJSUtil.class,"1==="+err.getMessage());
				//LogUtil.info(MyJSUtil.class,"2="+new JSONObject(innerResult).getString("message"));
				errorMsg = err.getMessage();
				//showTOUser(err.getMessage());
				//PromptManager.showToastTest(getContext(), err.getMessage());
			}else{
				//返回里面那个resutl对象
				jsResult = innerResult; 
			}
		} catch (JSONException e){
			e.printStackTrace();
		}
		return jsResult;
	}
	
	//public abstract void showTOUser(String errorMessage);
	/**
	 * 获取解析错误信息
	 * @param jsonObjectStr
	 * @return
	 */
	public static String getErrorMsg(String jsonObjectStr){
		if(errorMsg==null){
			checkResponse(jsonObjectStr);
		}
		return errorMsg;
	}
	/*
	// 错误时: {"id":793,"result":
	// {"status":"failed","result":{"error_code":"40","message":"登录失败，请确认用户名或密码是否正确。"}}}
	private boolean checkResponse(JSONObject jsonObject) {
		try {
			String outResult = jsonObject.getString("result");
			;// 最外层result
			JSONObject jsobj = new JSONObject(outResult);
			String status = jsobj.getString("status");
			if ("failed".equals(status)) {
				// 提示用户,解析出message里面的内容
				String innerResult = jsobj.getString("result");
				ErrorInfo err = JSON.parseObject(innerResult, ErrorInfo.class);

				PromptManager.showToastTest(getContext(), err.getMessage());
			} else {
				return true;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}
	*
	*/
}
