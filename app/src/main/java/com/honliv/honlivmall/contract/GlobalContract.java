package com.honliv.honlivmall.contract;


import com.honliv.honlivmall.activity.CoreBaseView;
import com.honliv.honlivmall.model.CoreBaseModel;
import com.honliv.honlivmall.presenter.CoreBasePresenter;

/**
 * Created by Rodin on 2016/11/15.
 */
public interface GlobalContract {
    public interface GlobalOfficeSelectModel extends CoreBaseModel {
    }
    interface GlobalOfficeSelectView extends CoreBaseView {
    }
    interface ContentView extends CoreBaseView {
    }
    interface MenuListView extends CoreBaseView {
    }

    public interface ContentModel extends CoreBaseModel{
    }

    public interface MenuListModel extends CoreBaseModel {
    }

    abstract class GlobalOfficeSelectPresenter  extends CoreBasePresenter<GlobalOfficeSelectModel, GlobalOfficeSelectView> {
    }

    abstract class ContentPresenter  extends CoreBasePresenter<ContentModel, ContentView>{
    }

    abstract class MenuListPresenter  extends CoreBasePresenter<MenuListModel,MenuListView>{
    }
}
