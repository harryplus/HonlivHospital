package com.honliv.honlivmall.bean;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Product implements Serializable {
    /*
    private Categoroy category;*/
    private int _id;//商品ID

    private int userId;//购买者ID
    private int id;//商品ID
    private int favId;//商品收藏的ID
    private int number;//商品数量
    private int stockcount;//商品库存数量
    private String shopCarProductColor;
    private String shopCarColorKey;//属性一前缀
    private String shopCarSizekey;//属性二前缀
    private String shopCarKey3;//属性三前缀
    private String shopCarKey4;//属性四前缀
    private String shopCarValue3;
    private String shopCarValue4;
    private String shopCarProductSize;
    private String name;//商品名称
    private String sku;//商品sku	 关闭sku这里要显示sku
    private String productdesc;//商品描述

    //private List<String> pic;//商品图片URL
    private String pic;//商品图片URL

    private float vipPrice;//会员价
    private float promotionPrice;//促销价

    private String saleprice;//销售价
    private String marketprice;//市场价

    //	private float  limitPrice;//限时团购价
    private float shakePrice;//摇摇特价
    private String lefttime;//剩余时间，单位为秒
    private int commentCount; //评论数
    private boolean available; // //是否可售,比如有库存

    private float starLevel;//商品评分，星级
    private int buyLimit; //单品购买上限
    private List<String> bigPic;//大图

    private List<ProductProperty> productProperty; //商品属性“id”:1,"key":"颜色","value":"红色",

    private boolean isHotProduct;//是否是热门商品
    private boolean isNewProduct;  //是否是最新商品
    private boolean isgift; //是否是赠品
    private List<String> productProm; //享受促销信息

    private int maxCount;//限购数量
    private int groupCount;//团购满足数量
    private int limitQty;//单个商品限购数量
    private int totalCount;//商品列表总共有多少商品
    private String startDate;//开始时间，
    private String endDate;//剩余时间，
    private String createddate;//加入收藏时间，

    private boolean hasSKU;
    private boolean hasStock;
    private DefaultPrice defaultPrice;
    private Map<String, SkuData> skuData;
    private String openSkuStr;//存入购物车数据库的数据sku。

    private int countDownId;   //countDownId
    private int groupBuyid;
    private int price;//为了礼品优惠卷，提交定单处

    private int discountNum;   //最低优惠底数
    private float discountValue;//优惠的值
    private String limitStr;//限制条件
    private String xmltext;
    private boolean addFav; //加入收藏

    private List<DiscountRule> discountRule; //优惠信息


    public List<DiscountRule> getDiscountRule() {
        return discountRule;
    }

    public void setDiscountRule(List<DiscountRule> discountRule) {
        this.discountRule = discountRule;
    }

    public boolean isAddFav() {
        return addFav;
    }

    public void setAddFav(boolean addFav) {
        this.addFav = addFav;
    }

    public String getCreateddate() {
        return createddate;
    }

    public void setCreateddate(String createddate) {
        this.createddate = createddate;
    }

    public int getFavId() {
        return favId;
    }

    public void setFavId(int favId) {
        this.favId = favId;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public int getGroupCount() {
        return groupCount;
    }

    public void setGroupCount(int groupCount) {
        this.groupCount = groupCount;
    }

    public int getLimitQty() {
        return limitQty;
    }

    public void setLimitQty(int limitQty) {
        this.limitQty = limitQty;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLimitStr() {

        return limitStr;
    }

    public void setLimitStr(String limitStr) {
        this.limitStr = limitStr;
    }

    public int getDiscountNum() {
        return discountNum;
    }

    public void setDiscountNum(int discountNum) {
        this.discountNum = discountNum;
    }

    public float getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(float discountValue) {
        this.discountValue = discountValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCountDownId() {
        return countDownId;
    }

    public void setCountDownId(int countDownId) {
        this.countDownId = countDownId;
    }

    public int getGroupBuyid() {
        return groupBuyid;
    }

    public void setGroupBuyid(int groupBuyid) {
        this.groupBuyid = groupBuyid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockcount() {
        return stockcount;
    }

    public void setStockcount(int stockcount) {
        this.stockcount = stockcount;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getShopCarColorKey() {
        return shopCarColorKey;
    }

    public void setShopCarColorKey(String shopCarColorKey) {
        this.shopCarColorKey = shopCarColorKey;
    }

    public String getShopCarSizekey() {
        return shopCarSizekey;
    }

    public void setShopCarSizekey(String shopCarSizekey) {
        this.shopCarSizekey = shopCarSizekey;
    }

    public String getShopCarProductColor() {
        return shopCarProductColor;
    }

    public void setShopCarProductColor(String shopCarProductColor) {
        this.shopCarProductColor = shopCarProductColor;
    }

    public String getShopCarProductSize() {
        return shopCarProductSize;
    }

    public void setShopCarProductSize(String shopCarProductSize) {
        this.shopCarProductSize = shopCarProductSize;
    }

    public String getShopCarKey3() {
        return shopCarKey3;
    }

    public void setShopCarKey3(String shopCarKey3) {
        this.shopCarKey3 = shopCarKey3;
    }

    public String getShopCarKey4() {
        return shopCarKey4;
    }

    public void setShopCarKey4(String shopCarKey4) {
        this.shopCarKey4 = shopCarKey4;
    }

    public String getShopCarValue3() {
        return shopCarValue3;
    }

    public void setShopCarValue3(String shopCarValue3) {
        this.shopCarValue3 = shopCarValue3;
    }

    public String getShopCarValue4() {
        return shopCarValue4;
    }

    public void setShopCarValue4(String shopCarValue4) {
        this.shopCarValue4 = shopCarValue4;
    }

    public String getProductdesc() {
        return productdesc;
    }

    public void setProductdesc(String productdesc) {
        this.productdesc = productdesc;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getMarketprice() {
        return marketprice;
    }

    public void setMarketprice(String marketprice) {
        this.marketprice = marketprice;
    }

    public float getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(float vipPrice) {
        this.vipPrice = vipPrice;
    }

    public float getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(float promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    /*public float getLimitPrice() {
        return limitPrice;
    }
    public void setLimitPrice(float limitPrice) {
        this.limitPrice = limitPrice;
    }*/
    public float getShakePrice() {
        return shakePrice;
    }

    public void setShakePrice(float shakePrice) {
        this.shakePrice = shakePrice;
    }


    public String getLefttime() {
        return lefttime;
    }

    public void setLefttime(String lefttime) {
        this.lefttime = lefttime;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public float getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(float starLevel) {
        this.starLevel = starLevel;
    }

    public int getBuyLimit() {
        return buyLimit;
    }

    public void setBuyLimit(int buyLimit) {
        this.buyLimit = buyLimit;
    }

    public List<String> getBigPic() {
        return bigPic;
    }

    public void setBigPic(List<String> bigPic) {
        this.bigPic = bigPic;
    }

    public String getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(String saleprice) {
        this.saleprice = saleprice;
    }

    public List<ProductProperty> getProductProperty() {
        return productProperty;
    }

    public void setProductProperty(List<ProductProperty> productProperty) {
        this.productProperty = productProperty;
    }

    public boolean isHotProduct() {
        return isHotProduct;
    }

    public void setHotProduct(boolean isHotProduct) {
        this.isHotProduct = isHotProduct;
    }

    public boolean isNewProduct() {
        return isNewProduct;
    }

    public void setNewProduct(boolean isNewProduct) {
        this.isNewProduct = isNewProduct;
    }

    public boolean isIsgift() {
        return isgift;
    }

    public void setIsgift(boolean isgift) {
        this.isgift = isgift;
    }

    public List<String> getProductProm() {
        return productProm;
    }

    public void setProductProm(List<String> productProm) {
        this.productProm = productProm;
    }

    public boolean isHasSKU() {
        return hasSKU;
    }

    public void setHasSKU(boolean hasSKU) {
        this.hasSKU = hasSKU;
    }

    public boolean isHasStock() {
        return hasStock;
    }

    public void setHasStock(boolean hasStock) {
        this.hasStock = hasStock;
    }

    public DefaultPrice getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(DefaultPrice defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public Map<String, SkuData> getSkuData() {
        return skuData;
    }

    public void setSkuData(Map<String, SkuData> skuData) {
        this.skuData = skuData;
    }

    public String getOpenSkuStr() {
        return openSkuStr;
    }

    public void setOpenSkuStr(String openSkuStr) {
        this.openSkuStr = openSkuStr;
    }

    public String getXmltext() {
        return xmltext;
    }

    public void setXmltext(String xmltext) {
        this.xmltext = xmltext;
    }

    @Override
    public String toString() {
        return "Product [_id=" + _id + ", userId=" + userId + ", id=" + id
                + ", number=" + number + ", stockcount=" + stockcount
                + ", shopCarProductColor=" + shopCarProductColor
                + ", shopCarProductSize=" + shopCarProductSize + ", name="
                + name + ", sku=" + sku + ", productdesc=" + productdesc
                + ", pic=" + pic + ", vipPrice=" + vipPrice
                + ", promotionPrice=" + promotionPrice + ", saleprice="
                + saleprice + ", marketprice=" + marketprice + ", limitPrice="
                +/* limitPrice +*/ ", shakePrice=" + shakePrice + ", lefttime="
                + lefttime + ", commentCount=" + commentCount + ", available="
                + available + ", starLevel=" + starLevel + ", buyLimit="
                + buyLimit + ", bigPic=" + bigPic + ", productProperty="
                + productProperty + ", isHotProduct=" + isHotProduct
                + ", isNewProduct=" + isNewProduct + ", isgift=" + isgift
                + ", productProm=" + productProm + ", maxCount=" + maxCount
                + ", groupCount=" + groupCount + ", limitQty=" + limitQty
                + ", totalCount=" + totalCount + ", startDate=" + startDate
                + ", endDate=" + endDate + ", hasSKU=" + hasSKU + ", hasStock="
                + hasStock + ", defaultPrice=" + defaultPrice + ", skuData="
                + skuData + ", openSkuStr=" + openSkuStr + ", countDownId="
                + countDownId + ", groupBuyid=" + groupBuyid + ", price="
                + price + ", discountNum=" + discountNum + ", discountValue="
                + discountValue + ", limitStr=" + limitStr + ", xmltext="
                + xmltext + "]";
    }
}
