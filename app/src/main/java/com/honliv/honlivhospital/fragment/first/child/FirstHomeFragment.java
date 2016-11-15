package com.honliv.honlivhospital.fragment.first.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.honliv.honlivhospital.R;
import com.honliv.honlivhospital.adapter.MainPagerAdapter;
import com.honliv.honlivhospital.base.BaseFragment;
import com.honliv.honlivhospital.contract.FirstContract;
import com.honliv.honlivhospital.fragment.global.GlobalOfficeSelectFragment;
import com.honliv.honlivhospital.model.first.child.FirstHomeModel;
import com.honliv.honlivhospital.presenter.first.child.FirstHomePresenter;
import com.rd.PageIndicatorView;
import com.rd.animation.AnimationType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by YoKeyword on 16/6/5.
 */
public class FirstHomeFragment extends BaseFragment<FirstHomePresenter, FirstHomeModel> implements View.OnClickListener, FirstContract.FirstHomeView {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.main_body_detail)
    View main_body_detail;
    @BindView(R.id.pageIndicatorView)
    PageIndicatorView pageIndicatorView;
    @BindView(R.id.main_body_professor)
    View main_body_professor;
    @BindView(R.id.main_body_guide)
    View main_body_guide;
    @BindView(R.id.main_body_registrate_day)
    View main_body_registrate_day;
    @BindView(R.id.main_body_registrate_recommend)
    View main_body_registrate_recommend;
    @BindView(R.id.main_body_report)
    View main_body_report;
    @BindView(R.id.main_body_cost)
    View main_body_cost;
    @BindView(R.id.main_body_office)
    View main_body_office;
    @BindView(R.id.main_body_handbook)
    View main_body_handbook;
    @BindView(R.id.main_body_question)
    View main_body_question;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    public static FirstHomeFragment newInstance() {
        Bundle args = new Bundle();

        FirstHomeFragment fragment = new FirstHomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_first_home;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        mToolbar.setTitle(getString(R.string.app_name));
        List<String> viewpaperData = new ArrayList<>();
        viewpaperData.add("res:///" + R.drawable.ic_launcher);
        viewpaperData.add("res:///" + R.mipmap.ic_back);
        viewpaperData.add("http://image.baidu.com/search/detail?ct=503316480&z=&tn=baiduimagedetail&ipn=d&word=%E5%94%AF%E7%BE%8E%E6%91%84%E5%BD%B1&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=-1&cs=1733827750,3545185613&os=3414688886,2175489473&simid=4175591317,578912154&pn=2&rn=1&di=62836027210&ln=1992&fr=&fmq=1470223098862_R_D&fm=&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&is=0,0&istype=2&ist=&jit=&bdtype=0&adpicid=0&pi=0&gsm=0&objurl=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fwallpaper%2F1209%2F10%2Fc1%2F13758581_1347257278695.jpg&rpstart=0&rpnum=0&adpicid=0&ctd=1478767443834^3_1263X899%1");
        viewpaperData.add("http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%94%AF%E7%BE%8E%E6%91%84%E5%BD%B1&step_word=&hs=0&pn=3&spn=0&di=39692495150&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=4102858049%2C1987224197&os=282371456%2C3522944046&simid=3431034695%2C144573878&adpicid=0&ln=1992&fr=&fmq=1470223098862_R_D&fm=&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fimg2.zjolcdn.com%2Fpic%2F0%2F13%2F66%2F56%2F13665652_914292.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fs1gjof_z%26e3Bz35s_z%26e3Bv54_z%26e3BvgAzdH3Fs1gjofAzdH3F3vprAzdH3F&gsm=0&rpstart=0&rpnum=0");
        viewpaperData.add("http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%94%AF%E7%BE%8E%E6%91%84%E5%BD%B1&step_word=&hs=0&pn=4&spn=0&di=19685571620&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=3704303780%2C566844591&os=208990820%2C1672163501&simid=4013659575%2C439894315&adpicid=0&ln=1992&fr=&fmq=1470223098862_R_D&fm=&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fimg3.3lian.com%2F2013%2Fv9%2F58%2Fd%2F25.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bnstwg_z%26e3Bv54AzdH3F2tuAzdH3Fda8nAzdH3Fa0-dlAzdH3F9aadc_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0");
        viewpaperData.add("http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%94%AF%E7%BE%8E%E6%91%84%E5%BD%B1&step_word=&hs=0&pn=5&spn=0&di=19125531150&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=1848868758%2C346045617&os=17440529%2C1557301382&simid=4029559481%2C536642675&adpicid=0&ln=1992&fr=&fmq=1470223098862_R_D&fm=&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fimg3.3lian.com%2F2013%2Fs1%2F09%2Fd%2F58.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bnstwg_z%26e3Bv54AzdH3F2tuAzdH3Fda8nAzdH3F8a-n8AzdH3F99dnd_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0");
        viewpaperData.add("http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%94%AF%E7%BE%8E%E6%91%84%E5%BD%B1&step_word=&hs=0&pn=6&spn=0&di=192618682850&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=4287269756%2C1566780773&os=1883607464%2C2830231528&simid=25463841%2C837277717&adpicid=0&ln=1992&fr=&fmq=1470223098862_R_D&fm=&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fsc.sinaimg.cn%2F2011%2F1125%2FU7326P841DT20111125162036.bmp&fromurl=ippr_z2C%24qAzdH3FAzdH3Ffv_z%26e3Bftgw_z%26e3Bv54_z%26e3BvgAzdH3FgjofAzdH3FrAzdH3Fda88-88-dcAzdH3Fdcl-b9d0n_d_z%26e3Bip4s%23rw2j_rtv&gsm=0&rpstart=0&rpnum=0");
        MainPagerAdapter viewpaperAdapter = new MainPagerAdapter(getContext(), viewpaperData);

        viewPager.setAdapter(viewpaperAdapter);
        pageIndicatorView.setViewPager(viewPager);
        pageIndicatorView.setInteractiveAnimation(true);
        pageIndicatorView.setSelection(0);
        pageIndicatorView.setAnimationType(AnimationType.WORM);
        pageIndicatorView.setAnimationDuration(1000);

        main_body_detail.setOnClickListener(this);
        main_body_guide.setOnClickListener(this);
        main_body_registrate_day.setOnClickListener(this);
        main_body_registrate_recommend.setOnClickListener(this);
        main_body_report.setOnClickListener(this);
        main_body_cost.setOnClickListener(this);
        main_body_office.setOnClickListener(this);
        main_body_handbook.setOnClickListener(this);
        main_body_question.setOnClickListener(this);
        main_body_professor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_body_detail:
                FirstDetailFragment detailFragment = FirstDetailFragment.newInstance();
                start(detailFragment);
                break;
            case R.id.main_body_professor:
                FirstProfessorFragment professorFragment = FirstProfessorFragment.newInstance();
                start(professorFragment);
                break;
            case R.id.main_body_guide:
                FirstGuideFragment guideFragment = FirstGuideFragment.newInstance();
                start(guideFragment);
                break;
            case R.id.main_body_registrate_day:
                GlobalOfficeSelectFragment selectFragment1 = GlobalOfficeSelectFragment.newInstance();
                start(selectFragment1);
                break;
            case R.id.main_body_registrate_recommend:
                GlobalOfficeSelectFragment selectFragment2 = GlobalOfficeSelectFragment.newInstance();
                start(selectFragment2);
                break;
            case R.id.main_body_report:

                break;
            case R.id.main_body_cost:

                break;
            case R.id.main_body_office:

                break;
            case R.id.main_body_handbook:

                break;
            case R.id.main_body_question:

                break;
        }
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public boolean onBackPressedSupport() {
//        loadRootFragment(R.id.fl_container, FirstHomeFragment.newInstance());
        showToast(getString(R.string.again_exit));
        return true;
    }
}
