package com.honliv.honlivmall.contract;

import com.honliv.honlivmall.activity.CoreBaseView;
import com.honliv.honlivmall.bean.BaseBean;
import com.honliv.honlivmall.bean.BaseInfo;
import com.honliv.honlivmall.bean.BaseResult;
import com.honliv.honlivmall.bean.HomeInfo;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.model.CoreBaseModel;
import com.honliv.honlivmall.presenter.CoreBasePresenter;

import java.util.List;

import okhttp3.ResponseBody;
import rx.Observable;


/**
 * Created by Rodin on 2016/11/15.
 */
public interface FirstContract {
    interface FirstModel extends CoreBaseModel {
    }

    interface FirstView extends CoreBaseView {
    }

    interface FirstProfessorView extends CoreBaseView {
    }

    interface FirstDetailView extends CoreBaseView {
    }

    interface FirstHomeView extends CoreBaseView {
        void updataHomeInfo(HomeInfo info);

        void updataHomeMarketing(List<Product> result);
    }

    interface FirstGuideView extends CoreBaseView {
    }

    interface FirstSelectDoctorView extends CoreBaseView {
    }

    interface FirstDetailModel extends CoreBaseModel {
    }

    interface FirstProfessorModel extends CoreBaseModel {
    }

    interface FirstGuideModel extends CoreBaseModel {
    }

    interface FirstSelectDoctorModel extends CoreBaseModel {
    }

    abstract class FirstPresenter extends CoreBasePresenter<FirstModel, FirstView> {
    }

    abstract class FirstDetailPresenter extends CoreBasePresenter<FirstDetailModel, FirstDetailView> {
    }

    abstract class FirstHomePresenter extends CoreBasePresenter<FirstHomeModel, FirstHomeView> {
        public abstract void  getServiceHomeInfo(String s);

        public abstract void getServiceHomeMarketing();
    }

    interface FirstHomeModel extends CoreBaseModel {
        Observable<HomeInfo> getServiceHomeInfo(String s);

        Observable<List<Product>> getServiceHomeMarketing();
    }

    abstract class FirstProfessorPresenter extends CoreBasePresenter<FirstProfessorModel, FirstProfessorView> {
    }

    abstract class FirstGuidePresenter extends CoreBasePresenter<FirstGuideModel, FirstGuideView> {
    }

    abstract class FirstSelectDoctorPresenter extends CoreBasePresenter<FirstSelectDoctorModel, FirstSelectDoctorView> {
    }
}
