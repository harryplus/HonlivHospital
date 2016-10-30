package com.honliv.honlivhospital.fragment.first.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.honliv.honlivhospital.R;
import com.honliv.honlivhospital.adapter.MainPagerAdapter;
import com.honliv.honlivhospital.base.BaseFragment;
import com.honliv.honlivhospital.fragment.global.GlobalOfficeSelectFragment;
import com.rd.PageIndicatorView;
import com.rd.animation.AnimationType;

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
    private Toolbar mToolbar;

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
        MainPagerAdapter viewpaperAdapter = new MainPagerAdapter(getContext(), viewpaperData);
        viewPager.setAdapter(viewpaperAdapter);
        pageIndicatorView.setViewPager(viewPager);
        pageIndicatorView.setUnselectedColor(R.color.background_gray);
        pageIndicatorView.setInteractiveAnimation(true);
        pageIndicatorView.setSelection(0);
        pageIndicatorView.setAnimationType(AnimationType.WORM);
        pageIndicatorView.setAnimationDuration(1000);
//        pageIndicatorView.setSelectedColor(R.color.white);

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
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.app_name));
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
}
