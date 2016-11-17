package com.honliv.honlivmall.bean;

import java.util.List;


public class HomeInfo {
    private List<HomeBanner> home_banner; //轮转大图
    private List<LeftMenu> classifies; ///首页左菜单
    private List<HomeBrand> brandlist; //品牌
    private List<GalleryProduct> galleryproduct;//首页gallery产品

    public List<HomeBanner> getHome_banner() {
        return home_banner;
    }

    public void setHome_banner(List<HomeBanner> home_banner) {
        this.home_banner = home_banner;
    }

    public List<LeftMenu> getClassifies() {
        return classifies;
    }

    public void setClassifies(List<LeftMenu> classifies) {
        this.classifies = classifies;
    }

    public List<GalleryProduct> getGalleryproduct() {
        return galleryproduct;
    }

    public void setGalleryproduct(List<GalleryProduct> galleryproduct) {
        this.galleryproduct = galleryproduct;
    }

    public List<HomeBrand> getBrandlist() {
        return brandlist;
    }

    public void setBrandlist(List<HomeBrand> brandlist) {
        this.brandlist = brandlist;
    }

    @Override
    public String toString() {
        return "HomeInfo [home_banner=" + home_banner + ", classifies="
                + classifies + ", galleryproduct=" + galleryproduct
                + ", brandlist=" + brandlist + "]";
    }
}
