package com.honliv.honlivmall.bean;


import java.io.Serializable;

/**
 * 打折信息
 *
 * @author Administrator
 */
public class DiscountRule implements Serializable {

    private int _id;

    private int saleType;  //0打折， 1减价  2固定价格

    private String saleName; //规则名字
    private int salueUnit;   //单位数值，比如100个，，100，100元，
    private int salueRuleUnit;//规格单位，0代表个，，，1代表元
    private int salueValue; ///优惠的数值   如 95

    private int ruleMode; /////应用方式  0：单个商品 1：商品总计

    public int getRuleMode() {
        return ruleMode;
    }

    public void setRuleMode(int ruleMode) {
        this.ruleMode = ruleMode;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getSaleType() {
        return saleType;
    }

    public void setSaleType(int saleType) {
        this.saleType = saleType;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public int getSalueUnit() {
        return salueUnit;
    }

    public void setSalueUnit(int salueUnit) {
        this.salueUnit = salueUnit;
    }

    public int getSalueRuleUnit() {
        return salueRuleUnit;
    }

    public void setSalueRuleUnit(int salueRuleUnit) {
        this.salueRuleUnit = salueRuleUnit;
    }

    public int getSalueValue() {
        return salueValue;
    }

    public void setSalueValue(int salueValue) {
        this.salueValue = salueValue;
    }

    @Override
    public String toString() {
        return "DiscountRule [_id=" + _id + ", saleType=" + saleType
                + ", saleName=" + saleName + ", salueUnit=" + salueUnit
                + ", salueRuleUnit=" + salueRuleUnit + ", salueValue="
                + salueValue + ", ruleMode=" + ruleMode + "]";
    }
}
