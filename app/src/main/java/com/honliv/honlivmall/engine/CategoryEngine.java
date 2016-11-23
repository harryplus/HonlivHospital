package com.honliv.honlivmall.engine;

import com.honliv.honlivmall.bean.Category;
import com.honliv.honlivmall.bean.Comment;
import com.honliv.honlivmall.bean.CouponInfo;
import com.honliv.honlivmall.bean.GifInfo;
import com.honliv.honlivmall.bean.OrderInfo;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.bean.ProductListFilter;

import org.json.JSONObject;

import java.util.List;

/**
 * 分类
 * @author Administrator
 *
 */
public interface CategoryEngine {
	
	/**
	 * 拿到所有分类列表
	 * @return
	 */
	List<Category> getServiceCategoryList();
	
	/**
	 * 拿到分类商品列表
	 * @param categoryId
	 * @return
	 */
	ProductListFilter getServiceProductList(int categoryId, String orderBy, int page, int pageNum);

	/**
	 * 服务器的商品详情
	 * @param productId
	 * @return
	 */
	Product getServiceProductDetail(int productId, int userId);
	/**
	 * 拿到商品描述
	 * @param productId
	 * @return
	 */
	String getServiceProuctDes(String productId);
	
	/**
	 * 得到评论列表
	 * @param productId 商品id
	 * @return
	 */
	List<Comment> getServiceCommonList(int productId, int page, int pageNum);
	
	/**
	 * 收藏商品
	 * @param productId
	 * @return
	 */
	String addProductToFavorites(int userId, int productId);

	
	/**
	 * 服务器的赠品商品详情
	 * @return
	 */
	GifInfo getServiceGifProduct(int userId, List<JSONObject> productList, float allCouponMoney);

	/**
	 * 获取优惠劵
	 * @return 返回所有的优惠卷
	 */
	List<CouponInfo> getMyCouPonList(int userId, List<JSONObject> productList);

	/**
	 * 服务器的商品运费
	 * shipId:收货地址Id   int 
	 * @param shipTypeId:配送方式Id 
	 * @return
	 */
	float getFreight(int userId, int shipId, int shipTypeId, List<JSONObject> productList, float allCouponMoney);

	
	/**
	 * 获取应付金额
	 * @param userId
	 * @param proSaleId
	 * @param groupBuyId
	 * @param productList
	 * @return
	 */
	float GetTotalPrice(int userId, int proSaleId, int groupBuyId, List<JSONObject> productList);
	
	
	/**
	 * 
	 * @param userId 用户id
	 * @param shipId 地址id
	 * @param shipTypeId 快递id
	 * @param paymoney 支付金额
	 * @param productList 商品清单
	 * @param remark  留言
	 * @return
	 */
	OrderInfo getServiceSubmitOrder(int userId, int shipId, int regionId, int shipTypeId, int paymentId, float paymoney, List<JSONObject> productList, String remark, String couponCode,
									int proSaleId, int groupBuyId);

}
