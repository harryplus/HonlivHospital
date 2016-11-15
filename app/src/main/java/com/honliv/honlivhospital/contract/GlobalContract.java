package com.honliv.honlivhospital.contract;


import com.honliv.honlivhospital.activity.CoreBaseView;
import com.honliv.honlivhospital.model.CoreBaseModel;
import com.honliv.honlivhospital.presenter.CoreBasePresenter;

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
