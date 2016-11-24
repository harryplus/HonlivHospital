package com.honliv.honlivmall.fragment.second.child;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.activity.MainActivity;
import com.honliv.honlivmall.adapter.HistoryAdapter;
import com.honliv.honlivmall.adapter.SearchAdapter;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.SearchKey;
import com.honliv.honlivmall.contract.SecondContract;
import com.honliv.honlivmall.listener.MyPageListener;
import com.honliv.honlivmall.model.second.child.SecondMainModel;
import com.honliv.honlivmall.presenter.second.child.SecondMainPresenter;
import com.honliv.honlivmall.util.DensityUtil;
import com.honliv.honlivmall.util.PromptManager;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/10/26.
 */
public class SecondMainFragment extends BaseFragment<SecondMainPresenter, SecondMainModel> implements SecondContract.SecondMainView, View.OnClickListener {
    @BindView(R.id.search_viewpager)
    ViewPager viewPager;
    @BindView(R.id.search_category_selector)
    ImageView selete;
    GridView hotList;
    @BindView(R.id.search_category_hot)
    TextView hotTitle;
    @BindView(R.id.search_category_history)
    TextView historyTitle;
    @BindView(R.id.seach_keyword)
    EditText seachkeywordET;
    GridView historyList;
    @BindView(R.id.search_del_TV)
    TextView searchDelTV;
    @BindView(R.id.search_x)
    ImageView search_x;
    @BindView(R.id.searchImageButton)
    ImageButton imageView_ok;

    //    int currentPage = 0;
    PagerAdapter pagerAdapter;
    HistoryAdapter historyAdapter;
    SearchAdapter hotAdapter;
    Vibrator vibrator = null;
    List<View> pagers;
    List<SearchKey> keyHistoryList;
    String[] hotNames;//
    String[] historyNames;
    ArrayList<String> historyTemp;
    boolean isHomeS;

    public static SecondMainFragment newInstance() {

        Bundle args = new Bundle();

        SecondMainFragment fragment = new SecondMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_second_main;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        vibrator = (Vibrator) getActivity().getSystemService(getContext().VIBRATOR_SERVICE);
        hotTitle.setOnClickListener(this);
        search_x.setOnClickListener(this);
        historyTitle.setOnClickListener(this);
        imageView_ok.setOnClickListener(this);
    }

    private void initView() {
        historyTemp = new ArrayList<String>();
        pagerAdapter = new ViewAdapter();
        viewPager.setAdapter(pagerAdapter);

        DbUtils db = DbUtils.create(getContext());
        try {//通过类型查找
            keyHistoryList = db.findAll(SearchKey.class);
            if (keyHistoryList != null) {
                for (int i = 0; i < keyHistoryList.size(); i++) {
                    historyTemp.add(keyHistoryList.get(i).getKey());
                }
            } else {
                searchDelTV.setVisibility(View.GONE);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        Collections.reverse(historyTemp);
        historyNames = historyTemp.toArray(new String[]{});//历史

        initPager();

        initImage();///处理标题下划线位置信息

        viewPager.setCurrentItem(0);//初始化时显示哪个界面
        hotTitle.setTextColor(Color.RED);

        viewPager.setOnPageChangeListener(new MyPageListener(hotTitle, historyTitle, searchDelTV, selete, historyNames));

        seachkeywordET.requestFocus();

        timerET.schedule(new TimerTask() {
            public void run() {
                if (isHomeS) {
                    InputMethodManager inputManager =
                            (InputMethodManager) seachkeywordET.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.showSoftInput(seachkeywordET, 0);
                }
            }
        }, 350);
        seachkeywordET.requestFocus();
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void initData() {
        historyTemp = new ArrayList<String>();
//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);//得到屏幕的宽度,设置到参数里面
//        GloableParams.WINDOW_WIDTH = metrics.widthPixels;
        if (GloableParams.hasHotKey) {// true 为已经联网获取过关键词
            hotNames = GloableParams.hotKeys;
            initView();
        } else {// false 为没有联网获取过关键词，过去联网获取
            hotNames = new String[]{};
            mPresenter.getSeaviceSearchKeyList();
        }
    }

    Timer timerET = new Timer();

    void initImage() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.id_category_selector);
        int BitmapWidth = bitmap.getWidth();

        int left = (GloableParams.WINDOW_WIDTH / 2 - BitmapWidth) / 2;

        Matrix matrix = new Matrix();
        matrix.postTranslate(left, 0);

        selete.setImageMatrix(matrix);
    }

    @Override
    public void onDestroyView() {
        if (vibrator != null) {
            vibrator.cancel();
            vibrator = null;
        }
        super.onDestroyView();
    }

    void initPager() {
        pagers = new ArrayList<View>();

        if (historyNames != null) {

            //热门
            hotList = new GridView(getContext());
            hotList.setNumColumns(4);
            hotList.setVerticalSpacing(DensityUtil.dip2px(getContext(), 4L));
            /*categoryList.setDivider(null);
        categoryList.setFadingEdgeLength(0);*/

            hotAdapter = new SearchAdapter(getContext(), hotNames);
            hotList.setAdapter(hotAdapter);
            pagers.add(hotList);

            HotOnItemClickListener hotOnItemListener = new HotOnItemClickListener();
            hotList.setOnItemClickListener(hotOnItemListener);
        } else {
            TextView hotTV = new TextView(getContext());
            hotTV.setGravity(Gravity.CENTER);
            hotTV.setText("暂 无 热 门 关 键 词");
            hotTV.setTextColor(getResources().getColor(R.color.font_gray));
            hotTV.setTextSize(18);
            pagers.add(hotTV);
        }
        if (historyNames != null && historyNames.length > 0) {
            historyList = new GridView(getContext());
            historyList.setNumColumns(4);
            historyList.setVerticalSpacing(DensityUtil.dip2px(getContext(), 4L));

            historyAdapter = new HistoryAdapter(getContext(), historyNames);
            historyList.setAdapter(historyAdapter);
            pagers.add(historyList);

            HistoryOnItemClickListener historyOnItemListener = new HistoryOnItemClickListener();
            historyList.setOnItemClickListener(historyOnItemListener);
        } else {
            TextView historyTV = new TextView(getContext());
            historyTV.setGravity(Gravity.CENTER);
            historyTV.setText("暂 无 搜 索 关 键 词");
            historyTV.setTextColor(getResources().getColor(R.color.font_gray));
            historyTV.setTextSize(18);
            searchDelTV.setVisibility(View.GONE);
            pagers.add(historyTV);
        }
    }

    @Override
    public void updataSearchKeyList(List<String> result) {
        if (result != null) {
            hotNames = (String[]) ((List) result).toArray(new String[((List) result).size()]);
            //hotNames = (ArrayList)result;
            GloableParams.hasHotKey = true;
            GloableParams.hotKeys = hotNames;
            initView();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_x:
                String seachkeywordStr = seachkeywordET.getText().toString().trim();
                if (StringUtils.isBlank(seachkeywordStr)) {
                    //PromptManager.showToast(getContext(), "搜索内容不能为空");
                    //Animation loadAnimation = AnimationUtils.loadAnimation(
                    //		getContext(), R.anim.shake);// 输入框振动
                    //seachkeywordET.setAnimation(loadAnimation);
                    return;
                } else {
                    seachkeywordET.setText("");
                }
                break;
            case R.id.search_category_hot:
                //热门被点了
                viewPager.setCurrentItem(0);
                hotTitle.setBackgroundResource(R.drawable.android_horizontal_button_4_selected);
                historyTitle.setBackgroundResource(R.drawable.android_horizontal_button_4);
                searchDelTV.setVisibility(View.GONE);
                break;

            case R.id.search_category_history:
                //历史被点了
                historyTitle.setBackgroundResource(R.drawable.android_horizontal_button_4_selected);
                hotTitle.setBackgroundResource(R.drawable.android_horizontal_button_4);
                viewPager.setCurrentItem(1);
                if (historyNames != null && historyNames.length > 0) {
                    searchDelTV.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.searchImageButton:
                //搜索被点了
                //PromptManager.showToast(this, "正在搜索ok.........");
//                showToast("正在搜索ok.........");
                searchBTOK();
                break;
            default:
                break;

        }
    }


    //清空历史搜索

    public void clearKey(View view) {
        DbUtils db = DbUtils.create(getContext());
        try {
            db.deleteAll(SearchKey.class);
            historyNames = new String[]{};
            historyAdapter.notifyDataSetChanged();
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


    void searchBTOK() {//搜索被点了
        String seachkeywordStr = seachkeywordET.getText().toString().trim();

        if (StringUtils.isBlank(seachkeywordStr)) {
            vibrator = (Vibrator) getActivity().getSystemService(getContext().VIBRATOR_SERVICE);
            showToast("搜索内容不能为空");
            Animation loadAnimation = AnimationUtils.loadAnimation(
                    getContext(), R.anim.shake);// 输入框振动
            seachkeywordET.setAnimation(loadAnimation);

            seachkeywordET.setFocusable(true);
            vibrator.vibrate(200);// 振动手机
            return;
        } else {
            searchKey(seachkeywordStr);
        }
    }

    void searchKey(String searchKey) {
        if (searchKey == null) {
            showToast("搜索内容不能为空");
            return;
        }
        DbUtils db = DbUtils.create(getContext());
        SearchKey key = new SearchKey();
        key.setKey(searchKey);
        try {
            SearchKey entity = db.findFirst(key);//通过entity的属性查找
            if (entity != null) {
                //查找到内容,不为空说明在历史数据中有
            } else {
                db.save(key);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }

        /******************************/
        //DbUtils db = DbUtils.create(this);
        try {//通过类型查找
            keyHistoryList = db.findAll(SearchKey.class);
            if (keyHistoryList != null) {
                historyTemp.clear();
                for (int i = 0; i < keyHistoryList.size(); i++) {
                    historyTemp.add(keyHistoryList.get(i).getKey());
                }
            } else {
                searchDelTV.setVisibility(View.GONE);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        Collections.reverse(historyTemp);
        historyNames = historyTemp.toArray(new String[]{});//历史
        if (historyAdapter == null) {
            historyAdapter = new HistoryAdapter(getContext(), historyNames);
        }
        historyAdapter.notifyDataSetChanged();
        /*******************加的历史搜索看看有没有效果*****************/
        Bundle data = new Bundle();
        data.putString("searchKey", searchKey);
        start(SecondSearchResultFragment.newInstance(data));
//        intent = new Intent();
//        intent.putExtra("searchKey", searchKey);
//
//        Log.i(TAG, searchKey);
//        intent.setClass(getContext(), SearchResultActivity.class);
//        startActivityForResult(intent, 20);
//        overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);//下个
        //finish();
    }


    class HotOnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view,
                                int position, long id) {
            //设置条目的摇动
//            view.startAnimation(AnimationUtils.loadAnimation(SearchActivity.this, R.anim.ia_ball_shake));
//            searchKey(hotNames[position]);

				/*for(int i=0;i<hotNames.length;i++){
                    if(position == i){
						PromptManager.showToast(getContext(),hotNames[position] );
					}
				}*/
        }
    }

    class HistoryOnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view,
                                int position, long id) {
            // TODO Auto-generated method stub
            //设置条目的摇动
//            view.startAnimation(AnimationUtils.loadAnimation(SearchActivity.this, R.anim.ia_ball_shake));
//            searchKey(historyNames[position]);
                /*for(int i=0;i<historyNames.length;i++){
                    if(position == i){
						PromptManager.showToast(getContext(),historyNames[position]+position );
					}
				}*/
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        ((MainActivity) getActivity()).onBackToFirstFragment();
        return true;
    }
}
