package com.honliv.honlivmall.fragment.third.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.adapter.MyCategoryAdapter;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.Category;
import com.honliv.honlivmall.contract.ThirdContract;
import com.honliv.honlivmall.listener.MyCategoryItemClickListener;
import com.honliv.honlivmall.model.third.child.ThirdMainModel;
import com.honliv.honlivmall.presenter.third.child.ThirdMainPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Rodin on 2016/10/26.
 */
public class ThirdMainFragment extends BaseFragment<ThirdMainPresenter, ThirdMainModel> implements ThirdContract.ThirdMainView {
    @BindView(R.id.category1List)
     ListView category1List;
     ArrayList<Category> categorys;//一级分类.里面有所有的分类信息
     MyCategoryAdapter myCategoryAdapter;
     MyCategoryItemClickListener myCategoryItemClickListener;

    public static ThirdMainFragment newInstance() {

        Bundle args = new Bundle();

        ThirdMainFragment fragment = new ThirdMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_category1;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {

    }
    @Override
    public void showError(String msg) {

    }

    @Override
    public void updateView(List<Category> result) {
                        if(result != null){
                    categorys = (ArrayList)result;
                    GloableParams.hasCategory = true;
                    GloableParams.categoryInfos = categorys;
                    myCategoryItemClickListener.setData(categorys);
                    initData();
                }
    }


    /**
     *拿到服务器的分类列表
     */
//     void getServiceCategoryList(){
//        new BaseActivity.MyHttpTask<Integer>() {
//            protected void onPreExecute() {
//                PromptManager.showCommonProgressDialog(CategoryActivity1.this);
//                super.onPreExecute();
//            }
//            @Override
//            protected Object doInBackground(Integer... params) {
//                CategoryEngine engine = BeanFactory.getImpl(CategoryEngine.class);
//                return engine.getServiceCategoryList();
//            }
//            protected void onPostExecute(Object result) {
//                super.onPostExecute(result);
//                PromptManager.closeProgressDialog();
//                if(result != null){
//                    categorys = (ArrayList)result;
//                    GloableParams.hasCategory = true;
//                    GloableParams.categoryInfos = categorys;
//                    myCategoryItemClickListener.setData(categorys);
//                    initData();
//                }else{
//                    PromptManager.showToast(getApplicationContext(), "服务器忙，请稍后重试！！！");
//                }
//            }
//        }.executeProxy(1);
//    }

    // 初始化数据
    public void initData() {
        if(GloableParams.hasCategory){//已经联网访问过分类数据
            categorys = GloableParams.categoryInfos;
            initData();
        }else{
            mPresenter.getServiceCategoryList();
        }
        if(myCategoryItemClickListener==null){
            myCategoryItemClickListener = new MyCategoryItemClickListener((SupportFragment) getParentFragment());
        }
        category1List.setOnItemClickListener(myCategoryItemClickListener);
    }
}
