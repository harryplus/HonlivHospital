package com.honliv.honlivmall.util;

import com.honliv.honlivmall.bean.DiscountRule;
import com.honliv.honlivmall.bean.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 抽取计算价格的方法
 *
 * @author Administrator
 */
public class MoneyUtils {
    /**
     * 应当付的钱数,根据商品
     *
     * @param productlist
     * @return
     */
    public static float getAllPayMoney(ArrayList<Product> productlist) {
        float allPrice = 0;
        for (int position = 0; position < productlist.size(); position++) {
            Product product = productlist.get(position);
            String priceStr = product.getSaleprice();
            float tempPrice = Float.parseFloat(priceStr);

            if (product.getDiscountRule() != null && product.getDiscountRule().size() > 0) {
                //有优惠信息
                if (product.getDiscountRule().get(0).getRuleMode() == 0) {////应用方式  0：单个商品 1：商品总计
                    int tempIndex = 0; //挑选最高的优惠
                    if (product.getDiscountRule().size() > 1) {
                        List<DiscountRule> discountRuleList = product.getDiscountRule();
                        for (int j = 0; j < discountRuleList.size(); j++) {
                            if (discountRuleList.get(j).getSalueRuleUnit() == 0 && product.getNumber() >= discountRuleList.get(j).getSalueUnit()) {
                                tempIndex = j;
                            } else if (discountRuleList.get(j).getSalueRuleUnit() == 1 && tempPrice * product.getNumber() >= discountRuleList.get(j).getSalueUnit()) {
                                tempIndex = j;
                            }
                        }
                    }
                    DiscountRule discountRule = product.getDiscountRule().get(tempIndex);

                    //打折优惠，购物数量大于优惠底数
//					 DiscountRule discountRule = product.getDiscountRule().get(0);
                    if (discountRule.getSaleType() == 0) { //0打折， 1减价  2固定价格
                        //打折优惠，购物数量大于优惠底数
                        if (discountRule.getSalueRuleUnit() == 0 && product.getNumber() >= discountRule.getSalueUnit()) {
                            allPrice += tempPrice * product.getNumber() * discountRule.getSalueValue() / 100;
                        } else if (discountRule.getSalueRuleUnit() == 1 && tempPrice * product.getNumber() >= discountRule.getSalueUnit()) {
                            allPrice += tempPrice * product.getNumber() * discountRule.getSalueValue() / 100;
                        } else {
                            allPrice += tempPrice * product.getNumber();
                        }
                    } else if (discountRule.getSaleType() == 1) {//0打折， 1减价  2固定价格
                        //优惠，购物数量大于优惠底数
                        if (discountRule.getSalueRuleUnit() == 0 && product.getNumber() >= discountRule.getSalueUnit()) {
                            allPrice = allPrice + tempPrice * product.getNumber() - discountRule.getSalueValue();
                        } else if (discountRule.getSalueRuleUnit() == 1 && tempPrice * product.getNumber() >= discountRule.getSalueUnit()) {
                            allPrice = allPrice + tempPrice * product.getNumber() - discountRule.getSalueValue();
                        } else {
                            allPrice += tempPrice * product.getNumber();
                        }
                    } else if (discountRule.getSaleType() == 2) {//0打折， 1减价  2固定价格
                        //优惠，购物数量大于优惠底数
                        if (discountRule.getSalueRuleUnit() == 0 && product.getNumber() >= discountRule.getSalueUnit()) {
                            allPrice = allPrice + (tempPrice - discountRule.getSalueValue()) * product.getNumber();
                        } else if (discountRule.getSalueRuleUnit() == 1 && tempPrice * product.getNumber() >= discountRule.getSalueUnit()) {
                            allPrice = allPrice + (tempPrice - discountRule.getSalueValue()) * product.getNumber();
                        } else {
                            allPrice += tempPrice * product.getNumber();
                        }
                    } else {
                        //其他优惠不计算先
                        allPrice += tempPrice * product.getNumber();
                    }
                } else {
                    //其他优惠不计算先
                    allPrice += tempPrice * product.getNumber();
                }
            } else {
                //没有优惠信息
                allPrice += tempPrice * product.getNumber();
            }
        }
        return allPrice;
    }

    public static String get2StrWithDouble(double totoprice) {
        BigDecimal totoB = new BigDecimal(totoprice);
        float totoPriceStr = totoB.setScale(2, BigDecimal.ROUND_HALF_UP)
                .floatValue();
        return String.format("%.2f", totoPriceStr) + "";
    }

    public static String getMoney(float shouldPayMoney) {
        return "￥" + String.format("%.2f", shouldPayMoney) + "元";
    }
}
