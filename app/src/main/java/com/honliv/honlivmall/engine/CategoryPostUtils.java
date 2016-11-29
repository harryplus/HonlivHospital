package com.honliv.honlivmall.engine;

import android.util.Log;

import com.google.gson.Gson;
import com.honliv.honlivmall.ConstantValue;
import com.honliv.honlivmall.api.MainApi;
import com.honliv.honlivmall.bean.Category;
import com.honliv.honlivmall.bean.CouponInfo;
import com.honliv.honlivmall.bean.GifInfo;
import com.honliv.honlivmall.bean.OrderInfo;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.bean.ProductListFilter;
import com.honliv.honlivmall.util.RxService;
import com.honliv.honlivmall.util.RxUtil;
import com.honliv.honlivmall.util.Utils;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by Rodin on 2016/11/21.
 */
public class CategoryPostUtils {
    private static final String TAG = "CategoryPostUtils";

    public static Observable<List<Category>> CategoryList(HashMap<String, Object> map) {
        HashMap<String, Object> postMap = Utils.getBaseMap("CategoryList");
        postMap.put("params", map);

        return RxService.createApi(MainApi.class).ListCategory(postMap).map(bean -> {
//            Log.i(TAG,new Gson().toJson(bean).toString());
            return bean.getResult().getResult();
        }).compose(RxUtil.rxSchedulerHelper());
//        return RxService.createApi(MainApi.class).ListCategory(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());

//        return Observable.just(map).map(m -> {
//            CategoryEngine engine = BeanFactory.getImpl(CategoryEngine.class);
//            return engine.getServiceCategoryList();
//        }).compose(RxUtil.rxSchedulerHelper());


//        return RxService.createApi(MainApi.class).postBody(postMap).map(bean -> {
//            Type type = new TypeToken<List<Category>>() {
//            }.getType();
//            try {
//                Type t = new TypeToken<BaseBean<BaseInfo<String>>>() {
//                }.getType();
//                BaseBean<BaseInfo<String>> r = new Gson().fromJson(bean.string(), t);
//                Log.i(TAG, bean.string());
//                Log.i(TAG, new Gson().toJson(r).toString());
//            } catch (IOException e) {
//                e.printStackTrace();
//                Log.i(TAG,e.toString());
//            }
//            List<Category> result = null;
//            return result;
//        }).compose(RxUtil.rxSchedulerHelper());
//        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
//            Type type = new TypeToken<List<Category>>() {
//            }.getType();
//            List<Category> result = null;
//            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
//                Log.i(TAG,bean.getResult().getResult());
//                result = new Gson().fromJson(bean.getResult().getResult(), type);
//            }
//            return result;
//        }).compose(RxUtil.rxSchedulerHelper());
    }

    public static Observable<Float> GetFreight(int userId, int shipId, int shipTypeId,
                                               List<HashMap<String, Object>> productList, float allCouponMoney) {
        HashMap<String, Object> postMap = Utils.getBaseMap("GetFreight");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("userId", userId);
        parames.put("shipId", shipId);
        parames.put("shipTypeId", shipTypeId);
//			parames.put("allCouponMoney", allCouponMoney);
        parames.put("productList", productList);
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).PaymentInfo(postMap).map(bean -> bean.getResult().getResult().getFreight()).compose(RxUtil.rxSchedulerHelper());
//        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
//            Float result = null;
//            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
//                result = Float.valueOf(bean.getResult().getResult());
//            }
//            return result;
//        }).compose(RxUtil.rxSchedulerHelper());
    }

    public static Observable<Float> GetTotalPrice(int userid, int proSaleId, int groupBuyId, List<HashMap<String, Object>> productList) {
        HashMap<String, Object> postMap = Utils.getBaseMap("GetTotalPrice");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("userId", userid);
        parames.put("groupBuyId", groupBuyId);
        parames.put("proSaleId", proSaleId);
//        Log.i(TAG, productList.toString());
        parames.put("productList", productList);
        Log.i(TAG, parames.toString());
        postMap.put("params", parames);

//        Log.i(TAG, "---"+postMap.toString());
        String str = new Gson().toJson(postMap).toString();
////        str.replaceAll("\"\\[","[");
////        str.replaceAll("]\"","]");
//        Log.i(TAG,"---"+ str);
//        BaseReq reqBean = new BaseReq();
//        reqBean.id = UUID.randomUUID().hashCode();
//        reqBean.jsonrpc = "2.0";
//        reqBean.method = "GetTotalPrice";
//        reqBean.params.groupBuyId = groupBuyId;
//        reqBean.params.productList = productList;
//        reqBean.params.userId = userid;
//        reqBean.params.proSaleId = proSaleId;
//        Log.i(TAG,"---"+  new Gson().toJson(reqBean).toString());

        return RxService.createApi(MainApi.class).String(postMap).map(bean -> {
            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
                return Float.valueOf(bean.getResult().getResult());
            }
            return null;
        }).compose(RxUtil.rxSchedulerHelper());
    }

    public static Observable<GifInfo> GetGiftInfo(int userid, List<HashMap<String, Object>> productList, float couponPrice) {
        HashMap<String, Object> postMap = Utils.getBaseMap("GetGiftInfo");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("userId", userid);
        parames.put("coupPrice", couponPrice);
        parames.put("productList", productList);
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).GifInfo(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());
//        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
//            Type type = new TypeToken<GifInfo>() {
//            }.getType();
//            GifInfo result = null;
//            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
//                result = new Gson().fromJson(bean.getResult().getResult(), type);
//            }
//            return result;
//        }).compose(RxUtil.rxSchedulerHelper());
    }

    public static Observable<Product> ProductDetail(int pId, int userid) {
        HashMap<String, Object> postMap = Utils.getBaseMap("ProductDetail");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("pId", pId);
        parames.put("userId", userid);
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).Product(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());
//        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
//            Type type = new TypeToken<Product>() {
//            }.getType();
//            Product result = null;
//            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
//                result = new Gson().fromJson(bean.getResult().getResult(), type);
//            }
//            return result;
//        }).compose(RxUtil.rxSchedulerHelper());

    }

    public static Observable<String> ProductAddFav(int userid, int pid) {
        HashMap<String, Object> postMap = Utils.getBaseMap("ProductAddFav");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("userId", userid);
        parames.put("productId", pid);
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).String(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());
//        return RxService.createApi(MainApi.class).post(postMap).map(bean -> {
//            String result = null;
//            if (bean.getResult().getStatus().equals(ConstantValue.SUCCESS)) {
//                result = bean.getResult().getResult();
//            }
//            return result;
//        }).compose(RxUtil.rxSchedulerHelper());
    }

    public static Observable<ProductListFilter> ProductList(int categoryId, String orderBy, int page, int pageNum) {
        HashMap<String, Object> postMap = Utils.getBaseMap("ProductList");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("cId", categoryId);
        if (orderBy == null) {
            orderBy = "default";
        }
        parames.put("orderby", orderBy);
        parames.put("page", page);
        parames.put("pageNum", pageNum);
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).ProductListFilter(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());
    }

    public static Observable<CouponInfo> GetCunpons(int userid, List<HashMap<String, Object>> productJsobList) {
        HashMap<String, Object> postMap = Utils.getBaseMap("GetCunpons");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("userId", userid);
        parames.put("productList", productJsobList);
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).CouponInfo(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());
    }

    public static Observable<OrderInfo> SubmitOrder(int userId, int shipId, int regionId, int shipTypeId, int paymentId, float paymoney, List<HashMap<String, Object>> productList, String remark, String couponCode, int proSaleId, int groupBuyId) {
        HashMap<String, Object> postMap = Utils.getBaseMap("SubmitOrder");
        HashMap<String, Object> parames = new HashMap<>();
        parames.put("userId", userId);
        parames.put("shipId", shipId);
        parames.put("regionId", regionId);
        parames.put("shipTypeId", shipTypeId);
        parames.put("paymentId", paymentId);
        parames.put("proSaleId", proSaleId);
        parames.put("groupBuyId", groupBuyId);
        parames.put("productList", productList);
        parames.put("remark", remark);
        parames.put("couponCode", couponCode);
        postMap.put("params", parames);
        return RxService.createApi(MainApi.class).OrderInfo(postMap).map(bean -> bean.getResult().getResult()).compose(RxUtil.rxSchedulerHelper());
    }
}
