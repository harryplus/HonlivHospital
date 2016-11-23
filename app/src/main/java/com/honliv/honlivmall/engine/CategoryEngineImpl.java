package com.honliv.honlivmall.engine;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.honliv.honlivmall.ConstantValue;
import com.honliv.honlivmall.bean.Category;
import com.honliv.honlivmall.bean.Comment;
import com.honliv.honlivmall.bean.CouponInfo;
import com.honliv.honlivmall.bean.GifInfo;
import com.honliv.honlivmall.bean.OrderInfo;
import com.honliv.honlivmall.bean.PaymentInfo;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.bean.ProductListFilter;
import com.honliv.honlivmall.engine.CategoryEngine;
import com.honliv.honlivmall.net.HttpClientUtil;
import com.honliv.honlivmall.util.LogUtil;
import com.honliv.honlivmall.util.MyJSUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jsonrpc.JSONRPCClient;
import jsonrpc.JSONRPCParams;

public class CategoryEngineImpl implements CategoryEngine {

    private static final String TAG = "CategoryEngineImpl";

    @Override
    public List<Category> getServiceCategoryList() {
        List<Category> result;
        // 发送请求到服务器端
        JSONRPCClient client = JSONRPCClient.create(ConstantValue.URL, JSONRPCParams.Versions.VERSION_2);
        client.setConnectionTimeout(ConstantValue.CONNECTIONTIME);
        client.setSoTimeout(ConstantValue.CONNECTIONTIME);

        JSONObject parames = new JSONObject();
        try {
            JSONObject jsonObj = client.callJSONObject("CategoryList", parames);
            //JSONObject jsobj =  MyJSUtil.checkResponse(categoryStr);
            if (jsonObj != null && MyJSUtil.isSuccess(jsonObj)) {
                String resultContent = jsonObj.getString("result");
                result = JSON.parseArray(resultContent, Category.class);

                LogUtil.info("category=" + result.get(0).toString());
                /*JSONObject jsObj = new JSONObject(resultContent);//data_parent
                String resultContentParent = jsonObj.getString("data_parent");
				tempResult = JSON.parseArray(resultContentParent, Category.class);
				result.put("data_parent", tempResult);
				
				String resultContentChild = jsonObj.getString("data_child");
				tempResult = JSON.parseArray(resultContentChild, Category.class);
				result.put("data_parent", tempResult);*/
                // 如果list集合不为空，持久化到本地
                if (result != null && result.size() > 0) {
					/*
					 HelpDaoImpl daoImpl=new HelpDaoImpl(GloableParams.CONTEXT);
					 for(Help item:result)
					 {
					  	com.ms.tigo.dao.domain.Help m=new com.ms.tigo.dao.domain.Help();//BeanUtil:在android抛出异常
					 	m.setId(item.getId()+"");
					  	m.setTitle(item.getTitle());
					 	daoImpl.insert(m);
					  }
					 */
                }
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ProductListFilter getServiceProductList(int categoryId, String orderBy, int page, int pageNum) {
        ProductListFilter result;
        // 发送请求到服务器端
        JSONRPCClient client = JSONRPCClient.create(ConstantValue.URL, JSONRPCParams.Versions.VERSION_2);
        client.setConnectionTimeout(ConstantValue.CONNECTIONTIME);
        client.setSoTimeout(ConstantValue.CONNECTIONTIME);

        JSONObject parames = new JSONObject();
        try {
            parames.put("cId", categoryId);
            if (orderBy == null) {
                orderBy = "default";
            }
            parames.put("orderby", orderBy);
            parames.put("page", page);
            parames.put("pageNum", pageNum);

            JSONObject jsonObj = client.callJSONObject("ProductList", parames);
            //JSONObject jsobj =  MyJSUtil.checkResponse(categoryStr);
            if (jsonObj != null && MyJSUtil.isSuccess(jsonObj)) {
                String resultContent = jsonObj.getString("result");
                if ("null".equals(resultContent)) {  //访问成功，但是这id没有数据
                    ProductListFilter products = new ProductListFilter();
                    return products;
                }

                result = JSON.parseObject(resultContent, ProductListFilter.class);

                //LogUtil.info("商品列表的信息,进入商品列表="+result.toString());
						/*JSONObject jsObj = new JSONObject(resultContent);//data_parent
						
						String resultContentParent = jsonObj.getString("data_parent");
						tempResult = JSON.parseArray(resultContentParent, Category.class);
						result.put("data_parent", tempResult);
						
						String resultContentChild = jsonObj.getString("data_child");
						tempResult = JSON.parseArray(resultContentChild, Category.class);
						result.put("data_parent", tempResult);*/

                // 如果list集合不为空，持久化到本地
                if (result != null) {
							/*
							 HelpDaoImpl daoImpl=new HelpDaoImpl(GloableParams.CONTEXT);
							 for(Help item:result)
							 {
							  	com.ms.tigo.dao.domain.Help m=new com.ms.tigo.dao.domain.Help();//BeanUtil:在android抛出异常
							 	m.setId(item.getId()+"");
							  	m.setTitle(item.getTitle());
							 	daoImpl.insert(m);
							  }
							 */
                }
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product getServiceProductDetail(int productId, int userId) {
        Product result;
        // 发送请求到服务器端
        JSONRPCClient client = JSONRPCClient.create(ConstantValue.URL, JSONRPCParams.Versions.VERSION_2);
        client.setConnectionTimeout(ConstantValue.CONNECTIONTIME);
        client.setSoTimeout(ConstantValue.CONNECTIONTIME);

        JSONObject parames = new JSONObject();
        try {
            parames.put("pId", productId);
            parames.put("userId", userId);

            JSONObject jsonObj = client.callJSONObject("ProductDetail", parames);
            //JSONObject jsobj =  MyJSUtil.checkResponse(categoryStr);
            if (jsonObj != null && MyJSUtil.isSuccess(jsonObj)) {

                String resultContent = jsonObj.getString("result");
						
						/*if("null".equals(resultContent)){  //访问成功，但是这id没有数据
							Product product = new Product();
							return product;
						}
						JSONObject jso = new JSONObject(resultContent);
						resultContent = jso.getString("product");
						*/
                //LogUtil.info("resultContent解析前==="+resultContent);
						/*Gson gson = new Gson();
						Product product = gson.fromJson(resultContent, Product.class);*/

                result = JSON.parseObject(resultContent, Product.class);
						
						/*List<String> bigPices =  result.getBigPic();
						List<String> tempbigPices =  new ArrayList<String>();
						for(String big:bigPices){
							big = big.replace("{0}", "T300X390_");
							tempbigPices.add(big);
							LogUtil.info(big);
						}
						//StringUtils.replace(text, searchString, replacement);
						tempbigPices.add(result.getPic().replace("{0}", "T300X390_"));
						
						Collections.reverse(tempbigPices);
						result.setBigPic(tempbigPices);*/
                //LogUtil.info("商品的信息,要进入商品="+result.toString());

                // 如果list集合不为空，持久化到本地
						/*if (result != null ){
							LogUtil.info("result不为空;;productDetail...;");
							
							 HelpDaoImpl daoImpl=new HelpDaoImpl(GloableParams.CONTEXT);
							 for(Help item:result)
							 {
							  	com.ms.tigo.dao.domain.Help m=new com.ms.tigo.dao.domain.Help();//BeanUtil:在android抛出异常
							 	m.setId(item.getId()+"");
							  	m.setTitle(item.getTitle());
							 	daoImpl.insert(m);
							  }
							
						} */
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getServiceProuctDes(String productId) {
        String result;
        // 发送请求到服务器端
        HttpClientUtil clientUtil = new HttpClientUtil();
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("method", "version");
        String ProductStr = clientUtil.sendPost(ConstantValue.URL, params);

        try {
            JSONObject jsobj = MyJSUtil.checkResponse(ProductStr);
            if (jsobj != null) {
                String resultContent = jsobj.getString("result");
                result = JSON.parseObject(resultContent, String.class);

                // 如果list集合不为空，持久化到本地
                if (result != null) {
					/*
					 HelpDaoImpl daoImpl=new HelpDaoImpl(GloableParams.CONTEXT);
					 for(Help item:result)
					 {
					  	com.ms.tigo.dao.domain.Help m=new com.ms.tigo.dao.domain.Help();//BeanUtil:在android抛出异常
					 	m.setId(item.getId()+"");
					  	m.setTitle(item.getTitle());
					 	daoImpl.insert(m);
					  }
					*/
                }
                return result;
            } else {//得到错误的信息
                String errorMsg = MyJSUtil.getErrorMsg(ProductStr);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Comment> getServiceCommonList(int productId, int page, int pageNum) {
        List<Comment> result;
        // 发送请求到服务器端
        JSONRPCClient client = JSONRPCClient.create(ConstantValue.URL, JSONRPCParams.Versions.VERSION_2);
        client.setConnectionTimeout(ConstantValue.CONNECTIONTIME);
        client.setSoTimeout(ConstantValue.CONNECTIONTIME);

        JSONObject parames = new JSONObject();
        try {
            parames.put("pId", productId);
            parames.put("page", page);
            parames.put("pageNum", pageNum);

            JSONObject jsonObj = client.callJSONObject("productCommon", parames);
            if (jsonObj != null && MyJSUtil.isSuccess(jsonObj)) {
                String resultContent = jsonObj.getString("result");

                if ("null".equals(resultContent)) {  //访问成功，但是这id没有数据
                    result = new ArrayList<Comment>();
                    return result;
                }
                JSONObject jso = new JSONObject(resultContent);
                resultContent = jso.getString("commonlist");
                result = JSON.parseArray(resultContent, Comment.class);

                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String addProductToFavorites(int userId, int productId) {
        String result = " ";
        // 发送请求到服务器端
        JSONRPCClient client = JSONRPCClient.create(ConstantValue.URL, JSONRPCParams.Versions.VERSION_2);
        client.setConnectionTimeout(ConstantValue.CONNECTIONTIME);
        client.setSoTimeout(ConstantValue.CONNECTIONTIME);
        JSONObject parames = new JSONObject();
        try {
            parames.put("userId", userId);
            parames.put("productId", productId);
            JSONObject jsonObj = client.callJSONObject("ProductAddFav", parames);
            if (jsonObj != null && MyJSUtil.isSuccess(jsonObj)) {
                //String resultContent = jsonObj.getString("result");
                //result = JSON.parseObject(resultContent, UserInfo.class);
                //String result = jsonObj.getString("result");
                result = jsonObj.getString("result");
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<CouponInfo> getMyCouPonList(int userId,
                                            List<JSONObject> productList) {
        List<CouponInfo> result;
        // 发送请求到服务器端
        JSONRPCClient client = JSONRPCClient.create(ConstantValue.URL, JSONRPCParams.Versions.VERSION_2);
        client.setConnectionTimeout(ConstantValue.CONNECTIONTIME);
        client.setSoTimeout(ConstantValue.CONNECTIONTIME);

        JSONObject parames = new JSONObject();
        try {
            parames.put("userId", userId);
            parames.put("productList", new JSONArray(productList.toString()));

            JSONObject jsonObj = client.callJSONObject("GetCunpons", parames);
            //JSONObject jsobj =  MyJSUtil.checkResponse(CouponInfoStr);

            LogUtil.info("GetCunpons==" + parames);
            if (jsonObj != null && MyJSUtil.isSuccess(jsonObj)) {
                String resultContent = jsonObj.getString("result");
                result = JSON.parseArray(resultContent, CouponInfo.class);

                return result;
            } else {
                result = new ArrayList<CouponInfo>();
                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public GifInfo getServiceGifProduct(int userId, List<JSONObject> productList, float couponPrice) {
        GifInfo result;
        // 发送请求到服务器端
        JSONRPCClient client = JSONRPCClient.create(ConstantValue.URL, JSONRPCParams.Versions.VERSION_2);
        client.setConnectionTimeout(ConstantValue.CONNECTIONTIME);
        client.setSoTimeout(ConstantValue.CONNECTIONTIME);

        JSONObject parames = new JSONObject();
        try {
            parames.put("userId", userId);
            parames.put("coupPrice", couponPrice);
            parames.put("productList", new JSONArray(productList.toString()));
            LogUtil.info("GetGiftInfo==parames=" + parames);
            JSONObject jsonObj = client.callJSONObject("GetGiftInfo", parames);
            if (jsonObj != null && MyJSUtil.isSuccess(jsonObj)) {

                String resultContent = jsonObj.getString("result");
				
				/*if("null".equals(resultContent)){  //访问成功，但是这id没有数据
					Product product = new Product();
					return product;
				}
				JSONObject jso = new JSONObject(resultContent);
				resultContent = jso.getString("product");*/

                result = JSON.parseObject(resultContent, GifInfo.class);
                LogUtil.info("GetGiftInfo==result=" + result.toString());
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public OrderInfo getServiceSubmitOrder(int userId, int shipId, int regionId,
                                           int shipTypeId, int paymentId, float paymoney,
                                           List<JSONObject> productList, String remark, String couponCode, int proSaleId, int groupBuyId) {
        OrderInfo result;
        // 发送请求到服务器端
        JSONRPCClient client = JSONRPCClient.create(ConstantValue.URL, JSONRPCParams.Versions.VERSION_2);
        client.setConnectionTimeout(ConstantValue.CONNECTIONTIME);
        client.setSoTimeout(ConstantValue.CONNECTIONTIME);

        JSONObject parames = new JSONObject();
        try {

            Log.i(TAG, userId + "--userId--" + shipId + "--shipId--" + regionId + "--regionId--" + shipTypeId + "--shipTypeId--" +paymentId+ "--paymentId--" + proSaleId + "--proSaleId--" + groupBuyId+"--groupBuyId--" + new JSONArray(productList.toString()) + "--productList--" + remark + "--remark---" + couponCode + "--couponCode--");
            parames.put("userId", userId);
            parames.put("shipId", shipId);
            parames.put("regionId", regionId);
            parames.put("shipTypeId", shipTypeId);
            parames.put("paymentId", paymentId);
            parames.put("proSaleId", proSaleId);
            parames.put("groupBuyId", groupBuyId);
            parames.put("productList", new JSONArray(productList.toString()));
            parames.put("remark", remark);
            parames.put("couponCode", couponCode);

            LogUtil.info("SubmitOrder==parames=" + parames);
            JSONObject jsonObj = client.callJSONObject("SubmitOrder", parames);

            Log.i(TAG, (jsonObj == null) + "");

            if (jsonObj != null && MyJSUtil.isSuccess(jsonObj)) {

                Log.i(TAG, (jsonObj.toString()) + "");
                String resultContent = jsonObj.getString("result");
				/*if("null".equals(resultContent)){  //访问成功，但是这id没有数据
					Product product = new Product();
					return product;
				}*/
				/*JSONObject jso = new JSONObject(resultContent);
				resultContent = jso.getString("product");*/


                result = JSON.parseObject(resultContent, OrderInfo.class);
                // 如果list集合不为空，持久化到本地
//				if (result != null ){
//				}
                return result;
            } else {
                String resultContent = jsonObj.getString("result");//有商品库存不足
                if ("NOSHOPPINGCARTINFO".equals(resultContent)) {
                    result = new OrderInfo();
                    result.setErrMsg(resultContent);
                    return result;
                } else if ("PROSALEEXPIRED".equals(resultContent)) {
                    result = new OrderInfo();
                    result.setErrMsg(resultContent);
                    return result;
                } else if (resultContent.contains("stockcount")) {
                    List<OrderInfo> orderInfoList = JSON.parseArray(resultContent, OrderInfo.class);
                    if (orderInfoList != null) {
                        result = orderInfoList.get(0);
                        result.setErrMsg("NOSTOCK");
                        return result;
                    }
                }
                result = new OrderInfo();
                result.setErrMsg(resultContent);
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public float getFreight(int userId, int shipId, int shipTypeId,
                            List<JSONObject> productList, float allCouponMoney) {
        float freight = 0;

        JSONRPCClient client = JSONRPCClient.create(ConstantValue.URL, JSONRPCParams.Versions.VERSION_2);
        client.setConnectionTimeout(ConstantValue.CONNECTIONTIME);
        client.setSoTimeout(ConstantValue.CONNECTIONTIME);

        JSONObject parames = new JSONObject();
        try {
            parames.put("userId", userId);
            parames.put("shipId", shipId);
            parames.put("shipTypeId", shipTypeId);
//			parames.put("allCouponMoney", allCouponMoney);
            parames.put("productList", new JSONArray(productList.toString()));
            JSONObject jsonObj = client.callJSONObject("GetFreight", parames);
            if (jsonObj != null && MyJSUtil.isSuccess(jsonObj)) {

                String resultContent = jsonObj.getString("result");

                PaymentInfo freightObj = JSON.parseObject(resultContent, PaymentInfo.class);

                freight = freightObj.getFreight();
                LogUtil.info("GetFreight==freight=" + freight);
                return freight;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return freight;
    }

    @Override
    public float GetTotalPrice(int userId, int proSaleId, int groupBuyId,
                               List<JSONObject> productList) {
        float totalPrice = 0;

        JSONRPCClient client = JSONRPCClient.create(ConstantValue.URL, JSONRPCParams.Versions.VERSION_2);
        client.setConnectionTimeout(ConstantValue.CONNECTIONTIME);
        client.setSoTimeout(ConstantValue.CONNECTIONTIME);

        JSONObject parames = new JSONObject();
        try {
            parames.put("userId", userId);
            parames.put("groupBuyId", groupBuyId);
            parames.put("proSaleId", proSaleId);
            parames.put("productList", new JSONArray(productList.toString()));

            LogUtil.info("22222GetTotalPrice===" + parames);
            JSONObject jsonObj = client.callJSONObject("GetTotalPrice", parames);
            if (jsonObj != null && MyJSUtil.isSuccess(jsonObj)) {

//			String resultContent = jsonObj.getString("result");

//			PaymentInfo freightObj = JSON.parseObject(resultContent, PaymentInfo.class);

                totalPrice = jsonObj.getInt("result");
                LogUtil.info("22222totalPrice===" + totalPrice);
                return totalPrice;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalPrice;
    }


}
