package com.honliv.honlivhospital.fragment.first.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.honliv.honlivhospital.R;
import com.honliv.honlivhospital.adapter.MainPagerAdapter;
import com.honliv.honlivhospital.base.BaseFragment;
import com.rd.PageIndicatorView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by YoKeyword on 16/6/5.
 */
public class FirstHomeFragment extends BaseFragment implements View.OnClickListener {

    private ViewPager viewPager;
    private View main_body_detail;
    private PageIndicatorView pageIndicatorView;
    private View main_body_professor;
    private View main_body_guide;
    private View main_body_registrate_day;
    private View main_body_registrate_recommend;
    private View main_body_report;
    private View main_body_cost;
    private View main_body_office;
    private View main_body_handbook;
    private View main_body_question;

    public static FirstHomeFragment newInstance() {

        Bundle args = new Bundle();

        FirstHomeFragment fragment = new FirstHomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_home, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        List<String> viewpaperData = new ArrayList<>();
        viewpaperData.add("drawable://" + R.drawable.ic_launcher);
        viewpaperData.add("drawable://" + R.drawable.ic_launcher);
        viewpaperData.add("drawable://" + R.drawable.ic_launcher);
        viewpaperData.add("drawable://" + R.drawable.ic_launcher);
//        viewpaperData.add("http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E9%A3%8E%E6%99%AF&step_word=&hs=0&pn=1&spn=0&di=156503313810&pi=0&rn=1&tn=baiduimagedetail&is=&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=1965601872%2C1719965004&os=4214844996%2C2283441075&simid=0%2C0&adpicid=0&ln=1996&fr=&fmq=1477633088314_R&fm=index&ic=undefined&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=11&oriquery=&objurl=http%3A%2F%2Fdesk.fd.zol-img.com.cn%2Ft_s960x600c5%2Fg5%2FM00%2F01%2F0E%2FChMkJ1bKwhGIUJlGAA6OD8_97jgAALGiQIKBdAADo4n874.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3F1jfh_z%26e3Bz5s_z%26e3Bv54_z%26e3BvgAzdH3FktzitAzdH3Fcd8_90ba_c_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0");
//        viewpaperData.add("http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E9%A3%8E%E6%99%AF&step_word=&hs=0&pn=2&spn=0&di=149744190670&pi=0&rn=1&tn=baiduimagedetail&is=&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=1930598182%2C3212953064&os=2275872397%2C1639929149&simid=0%2C0&adpicid=0&ln=1996&fr=&fmq=1477633088314_R&fm=index&ic=undefined&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=11&oriquery=&objurl=http%3A%2F%2Fimg4.duitang.com%2Fuploads%2Fitem%2F201409%2F17%2F20140917143512_y8NEt.thumb.700_0.jpeg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B17tpwg2_z%26e3Bv54AzdH3Fks52AzdH3F%3Ft1%3Dm9cl8balb&gsm=0&rpstart=0&rpnum=0");
//        viewpaperData.add("http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E9%A3%8E%E6%99%AF&step_word=&hs=0&pn=3&spn=0&di=195887212430&pi=0&rn=1&tn=baiduimagedetail&is=&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=1205727766%2C1693092955&os=2492370718%2C1872074550&simid=0%2C0&adpicid=0&ln=1996&fr=&fmq=1477633088314_R&fm=index&ic=undefined&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=11&oriquery=&objurl=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201609%2F12%2F20160912192529_SXazQ.thumb.700_0.jpeg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B17tpwg2_z%26e3Bv54AzdH3Fks52AzdH3F%3Ft1%3Dmnmlll80n&gsm=0&rpstart=0&rpnum=0");
//        viewpaperData.add("http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E9%A3%8E%E6%99%AF&step_word=&hs=0&pn=4&spn=0&di=12245285400&pi=0&rn=1&tn=baiduimagedetail&is=&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=3394900025%2C4220965317&os=2658675833%2C934238386&simid=0%2C0&adpicid=0&ln=1996&fr=&fmq=1477633088314_R&fm=index&ic=undefined&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=11&oriquery=&objurl=http%3A%2F%2Fpic1.win4000.com%2Fmobile%2F7%2F58115e58d31ab.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Botg9aaa_z%26e3Bv54AzdH3Fowssrwrj6_1jpwts_88c800_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0");
//        viewpaperData.add("http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E9%A3%8E%E6%99%AF&step_word=&hs=0&pn=5&spn=0&di=121071701330&pi=0&rn=1&tn=baiduimagedetail&is=&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=3949446977%2C234975750&os=3154301756%2C2858679189&simid=0%2C0&adpicid=0&ln=1996&fr=&fmq=1477633088314_R&fm=index&ic=undefined&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=11&oriquery=&objurl=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fwallpaper%2F1609%2F27%2Fc0%2F27587202_1474952311163_800x600.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fowssrwrj6_z%26e3Brv5gstgj_z%26e3Bv54_z%26e3BvgAzdH3FrtvAzdH3F9nc89_d_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0");
//        viewpaperData.add("http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E9%A3%8E%E6%99%AF&step_word=&hs=0&pn=6&spn=0&di=825431570&pi=0&rn=1&tn=baiduimagedetail&is=&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=3018439128%2C2891843102&os=1227731852%2C4131073256&simid=0%2C0&adpicid=0&ln=1996&fr=&fmq=1477633088314_R&fm=index&ic=undefined&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=11&oriquery=&objurl=http%3A%2F%2Fimage101.360doc.com%2FDownloadImg%2F2016%2F10%2F2719%2F83195451_8.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bnma15v_z%26e3Bv54AzdH3Fv5gpjgpAzdH3F8mAzdH3F8ad0AzdH3F8lAzdH3F8nan8cl0_ma8blc989_z%26e3Bfip4s&gsm=0&rpstart=0&rpnum=0");
        MainPagerAdapter viewpaperAdapter = new MainPagerAdapter(getContext(), viewpaperData);
        viewPager.setAdapter(viewpaperAdapter);
////instance of android.support.v4.view.PagerAdapter adapter
//
        pageIndicatorView.setViewPager(viewPager);

        main_body_detail.setOnClickListener(this);
        main_body_guide.setOnClickListener(this);
        main_body_registrate_day.setOnClickListener(this);
        main_body_registrate_recommend.setOnClickListener(this);
        main_body_report.setOnClickListener(this);
        main_body_cost.setOnClickListener(this);
        main_body_office.setOnClickListener(this);
        main_body_handbook.setOnClickListener(this);
        main_body_question.setOnClickListener(this);
    }

    private void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        pageIndicatorView = (PageIndicatorView) view.findViewById(R.id.pageIndicatorView);
        main_body_detail = view.findViewById(R.id.main_body_detail);
        main_body_professor = view.findViewById(R.id.main_body_professor);
        main_body_guide = view.findViewById(R.id.main_body_guide);
        main_body_registrate_day = view.findViewById(R.id.main_body_registrate_day);
        main_body_registrate_recommend = view.findViewById(R.id.main_body_registrate_recommend);
        main_body_report = view.findViewById(R.id.main_body_report);
        main_body_cost = view.findViewById(R.id.main_body_cost);
        main_body_office = view.findViewById(R.id.main_body_office);
        main_body_handbook = view.findViewById(R.id.main_body_handbook);
        main_body_question = view.findViewById(R.id.main_body_question);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_body_detail:
                FirstDetailFragment fragment = FirstDetailFragment.newInstance();
                start(fragment);
                break;
            case R.id.main_body_professor:

                break;
            case R.id.main_body_guide:

                break;
            case R.id.main_body_registrate_day:

                break;
            case R.id.main_body_registrate_recommend:

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
}
