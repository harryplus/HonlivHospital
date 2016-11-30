package com.honliv.honlivmall.fragment.fifth.child;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.CouponInfo;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.model.fifth.child.FifthMyCouponModel;
import com.honliv.honlivmall.presenter.fifth.child.FifthMyCouponPresenter;
import com.honliv.honlivmall.util.DensityUtil;
import com.honliv.honlivmall.util.LogUtil;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthMyCouponFragment extends BaseFragment<FifthMyCouponPresenter, FifthMyCouponModel> implements FifthContract.FifthMyCouponView, View.OnClickListener {
    @BindView(R.id.search_category_selector)
    ImageView selete;
    @BindView(R.id.viewpager_VP)
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    @BindView(R.id.category_unused_TV)
    TextView categoryUnusedTV;
    @BindView(R.id.category_used_TV)
    TextView categoryUsedTV;
    @BindView(R.id.nullProductTV)
    TextView nullProductTV;
    @BindView(R.id.contentLV)
    TextView contentLV;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    int start = 1;
    int userId;
    List<CouponInfo> myCouponList;
    List<CouponInfo> currentUsedCouponList;
    List<CouponInfo> currentUnusedCouponList;
    ListView UnusedList;
    UnUsedAdapter unUsedAdapter;
    ListView UsedList;
    UsedAdapter usedAdapter;
    List<View> pagers;
    TextView historyTV;

    public static FifthMyCouponFragment newInstance() {
        Bundle args = new Bundle();

        FifthMyCouponFragment fragment = new FifthMyCouponFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fifth_my_coupon;
    }

    /**
     * 初始化控件
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        initToolbar(toolbar,getString(R.string.text_my_coupon),true);
        initImage();///处理标题下划线位置信息
        categoryUsedTV.setOnClickListener(this);
        categoryUnusedTV.setOnClickListener(this);
        categoryUnusedTV.setTextColor(Color.RED);
//        titleBack.setOnClickListener(this);
    }

    @Override
    public void showError(String msg) {

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.category_unused_TV:
                viewPager.setCurrentItem(0);
                categoryUnusedTV.setBackgroundResource(R.drawable.android_horizontal_button_4_selected);
                categoryUsedTV.setBackgroundResource(R.drawable.android_horizontal_button_4);
                break;
            case R.id.category_used_TV:
                categoryUsedTV.setBackgroundResource(R.drawable.android_horizontal_button_4_selected);
                categoryUnusedTV.setBackgroundResource(R.drawable.android_horizontal_button_4);
                viewPager.setCurrentItem(1);
                break;
        }
    }

    /**
     * 初始化标题下划线位置
     */
    void initImage() {
        myCouponList = new ArrayList<>();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.id_category_selector);
        int BitmapWidth = bitmap.getWidth();
        int left = (GloableParams.WINDOW_WIDTH / 2 - BitmapWidth) / 2;
        Matrix matrix = new Matrix();
        matrix.postTranslate(left, 0);
        selete.setImageMatrix(matrix);
    }

    public void initData() {
        mPresenter.getServiceCoupon1(userId, start, 30, 1);
        initPager();
        pagerAdapter = new ViewAdapter();
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);//初始化时显示哪个界面
        viewPager.setOnPageChangeListener(new MyPageListener());
    }


    void initPager() {
        pagers = new ArrayList<View>();
        if (currentUnusedCouponList != null && currentUnusedCouponList.size() > 0) {
            UnusedList = new ListView(getContext());
            UnusedList.setSelector(android.R.color.transparent);//设置点击 背景无色
            UnusedList.setDivider(null);
            UnusedList.setDividerHeight(DensityUtil.dip2px(getContext(), 6L));
            unUsedAdapter = new UnUsedAdapter();
            UnusedList.setAdapter(unUsedAdapter);
            pagers.add(UnusedList);

//			HotOnItemClickListener hotOnItemListener = new HotOnItemClickListener();
//			hotList.setOnItemClickListener(hotOnItemListener);
        } else {
            TextView hotTV = new TextView(getContext());
            hotTV.setGravity(Gravity.CENTER);
            hotTV.setText("暂 无 未 使 用 优 惠 券");
            //hotTV.setBackgroundResource(R.drawable.search_del);
            hotTV.setTextColor(getResources().getColor(R.color.black));
            hotTV.setTextSize(18);
            pagers.add(hotTV);
        }
        if (currentUsedCouponList != null && currentUsedCouponList.size() > 0) {//historyNames
            UsedList = new ListView(getContext());
            UsedList.setSelector(android.R.color.transparent);
            usedAdapter = new UsedAdapter();

            UsedList.setDivider(null);
            UsedList.setDividerHeight(DensityUtil.dip2px(getContext(), 6L));

            UsedList.setAdapter(usedAdapter);
            pagers.add(UsedList);
//			HistoryOnItemClickListener historyOnItemListener = new HistoryOnItemClickListener();
//			historyList.setOnItemClickListener(historyOnItemListener);
        } else {
            historyTV = new TextView(getContext());
            historyTV.setGravity(Gravity.CENTER);
            historyTV.setText("暂 无 已 使 用 优 惠 券");
            //historyTV.setBackgroundResource(R.drawable.search_del);
            historyTV.setTextColor(getResources().getColor(R.color.black));
            historyTV.setTextSize(18);
            pagers.add(historyTV);
        }
    }

    @Override
    public void updateView2(List<CouponInfo> result) {
        if (result != null) {
            //有返回东西 ,解析出来数据，设置给屏幕
            myCouponList.clear();
            myCouponList.addAll(result);
            if (myCouponList != null && myCouponList.size() > 0) {
                currentUsedCouponList = myCouponList;
                //获取成功
            } else {
                //获取失败
                nullProductTV.setVisibility(View.VISIBLE);
                contentLV.setVisibility(View.GONE);
            }
            pagerAdapter.notifyDataSetChanged();
//            initData();
        } else {
            showToast("服务器忙，请稍后重试！！！");
        }
    }

    @Override
    public void updateView1(List<CouponInfo> result) {
        if (result != null) {
            //有返回东西 ,解析出来数据，设置给屏幕
            myCouponList.clear();
            myCouponList.addAll(result);
            if (myCouponList != null && myCouponList.size() > 0) {
                currentUnusedCouponList = myCouponList;
            } else {
                //获取失败
                nullProductTV.setVisibility(View.VISIBLE);
                contentLV.setVisibility(View.GONE);
            }
            mPresenter.getServiceCoupon2(userId, start, 30, 2);
        } else {
            showToast("服务器忙，请稍后重试！！！");
        }
    }

    class ViewAdapter extends PagerAdapter {
        public int getCount() {
            return 2;
        }

        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        public Object instantiateItem(ViewGroup container, int position) {
            View view = pagers.get(position);

            container.addView(view);

            return view;
        }

        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(pagers.get(position));
        }
    }

    class UnUsedAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return currentUnusedCouponList.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ListViewHolder holder = null;
            if (convertView == null) {
                holder = new ListViewHolder();
                convertView = View.inflate(getContext(), R.layout.my_coupon_item, null);
                holder.couponCodeTV = (TextView) convertView.findViewById(R.id.coupon_code_TV);
                holder.couponClassTV = (TextView) convertView.findViewById(R.id.coupon_class_TV);
                holder.couponPriceTV = (TextView) convertView.findViewById(R.id.coupon_price_TV);
                holder.couponLimitDescTV = (TextView) convertView.findViewById(R.id.coupon_limitDesr_TV);
                holder.couponLimitPriceTV = (TextView) convertView.findViewById(R.id.coupon_limitprice_TV);
                holder.couponEndDateTV = (TextView) convertView.findViewById(R.id.coupon_endDate_TV);
                convertView.setTag(holder);
            } else {
                holder = (ListViewHolder) convertView.getTag();
            }
            holder.couponCodeTV.setText(currentUnusedCouponList.get(position).getCouponCode() + "");
            holder.couponClassTV.setText(currentUnusedCouponList.get(position).getClassName() + "");
            holder.couponLimitDescTV.setText(StringUtils.isEmpty(currentUnusedCouponList.get(position).getLimitStr()) ? "无" : currentUnusedCouponList.get(position).getLimitStr());
            holder.couponPriceTV.setText("￥" + String.format("%.2f", currentUnusedCouponList.get(position).getCouponPrice()));
            holder.couponLimitPriceTV.setText("￥" + String.format("%.2f", currentUnusedCouponList.get(position).getLimitPrice()));
            holder.couponEndDateTV.setText(currentUnusedCouponList.get(position).getEndDate().replace("9999-12-31", " 无限期") + "");

            return convertView;
        }
    }

    class UsedAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return currentUsedCouponList.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            ListViewHolder holder = null;
            if (convertView == null) {
                holder = new ListViewHolder();
                convertView = View.inflate(getContext(), R.layout.my_coupon_item, null);
                holder.couponCodeTV = (TextView) convertView.findViewById(R.id.coupon_code_TV);
                holder.couponClassTV = (TextView) convertView.findViewById(R.id.coupon_class_TV);
                holder.couponPriceTV = (TextView) convertView.findViewById(R.id.coupon_price_TV);
                holder.couponLimitPriceTV = (TextView) convertView.findViewById(R.id.coupon_limitprice_TV);
                holder.couponLimitDescTV = (TextView) convertView.findViewById(R.id.coupon_limitDesr_TV);
                holder.couponEndDateTV = (TextView) convertView.findViewById(R.id.coupon_endDate_TV);
                convertView.setTag(holder);
            } else {
                holder = (ListViewHolder) convertView.getTag();
            }
            holder.couponCodeTV.setText(currentUsedCouponList.get(position).getCouponCode() + "");
            holder.couponClassTV.setText(currentUsedCouponList.get(position).getClassName() + "");

            LogUtil.info("holder.couponLimitDescTV=" + holder.couponLimitDescTV + ",,,currentUsedCouponList.get(position).getLimitStr()==" + currentUsedCouponList.get(position).getLimitStr());

            holder.couponLimitDescTV.setText(StringUtils.isEmpty(currentUsedCouponList.get(position).getLimitStr()) ? "无" : currentUsedCouponList.get(position).getLimitStr());
            holder.couponPriceTV.setText("￥" + String.format("%.2f", currentUsedCouponList.get(position).getCouponPrice()));
            holder.couponLimitPriceTV.setText("￥" + String.format("%.2f", currentUsedCouponList.get(position).getLimitPrice()));
            holder.couponEndDateTV.setText(currentUsedCouponList.get(position).getEndDate().replace("9999-12-31", " 无限期") + "");

            return convertView;
        }
    }

    int currentPage = 0;

    class MyPageListener implements ViewPager.OnPageChangeListener {

        public void onPageScrollStateChanged(int position) {
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageSelected(int position) {
            categoryUnusedTV.setTextColor(Color.BLACK);
            categoryUsedTV.setTextColor(Color.BLACK);

            switch (position) {
                case 0:
                    categoryUnusedTV.setTextColor(Color.RED);
                    categoryUnusedTV.setBackgroundResource(R.drawable.android_horizontal_button_4_selected);
                    categoryUsedTV.setBackgroundResource(R.drawable.android_horizontal_button_4);
                    break;
                case 1:
                    categoryUsedTV.setTextColor(Color.RED);
                    categoryUsedTV.setBackgroundResource(R.drawable.android_horizontal_button_4_selected);
                    categoryUnusedTV.setBackgroundResource(R.drawable.android_horizontal_button_4);
                    break;
            }
            TranslateAnimation animation = new TranslateAnimation(currentPage * GloableParams.WINDOW_WIDTH / 2, position * GloableParams.WINDOW_WIDTH / 2, 0, 0);
            animation.setFillAfter(true);
            animation.setDuration(300);
            selete.startAnimation(animation);
            currentPage = position;
        }
    }

    static class ListViewHolder {
        TextView couponCodeTV;//编号
        TextView couponClassTV;//类别
        TextView couponPriceTV;//金额
        TextView couponLimitPriceTV;//最低消费金额
        TextView couponLimitDescTV;//消费条件
        TextView couponEndDateTV;//最后使用时间
    }
}
