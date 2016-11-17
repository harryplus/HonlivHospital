package com.honliv.honlivmall.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.adapter.BargainGridAdapter;
import com.honliv.honlivmall.adapter.HomeViewPagerAdapter;
import com.honliv.honlivmall.adapter.LikeGridAdapter;
import com.honliv.honlivmall.adapter.LimitGridAdapter;
import com.honliv.honlivmall.bean.HomeBanner;
import com.honliv.honlivmall.bean.HomeInfo;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.task.GetHomeBargainPicTask;
import com.honliv.honlivmall.task.GetHomeMarketPicTask;
import com.honliv.honlivmall.task.GetHomeRecommendPicTask;
import com.honliv.honlivmall.util.ImageOptionsUtils;
import com.honliv.honlivmall.util.PromptManager;
import com.honliv.honlivmall.view.LeftMenuListView;
import com.honliv.honlivmall.view.ScrollViewForViewPager;
import com.honliv.honlivmall.view.SlideMenu;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 首页
 *
 * @author Administrator
 */
public class HomeActivity extends BaseActivity implements OnClickListener {
    private static final String TAG = "HomeActivity";
    private static final String STATE_POSITION = "STATE_POSITION";
    public static Context mContext;
    private ArrayList<Product> likeProduct;
    private LikeGridAdapter likeAdapter;
    private List<Product> limitproduct;
    private List<Product> bargainproduct;
    private LimitGridAdapter limitAdapter;
    private BargainGridAdapter bargainAdapter;
    DisplayImageOptions options;
    private SlideMenu slideMenu;
    private MyPagerTask myPagerTask;
    private ViewPager viewPager;
    //    private TextView title;
    private int oldPosition = 0;// 上一次页面位置
    private int currentItem;
    private ScheduledExecutorService scheduledExecutor;
    private MyOnPageChangeListener listener;
    private ArrayList<View> dots;
    private Button seachkeywordET;
    private ImageView menuImg;
    private HomeViewPagerAdapter adapter;
    private List<HomeBanner> homeBanners;//首页banner
    private LeftMenuListView leftMenuLV;
    private int pagerPosition;
    private TextView more_limit;
    private TextView more_bargain;
//    private MSGallery limit;
//    private MSGallery bargain;
    private ScrollViewForViewPager relative_top2;
    //    private RelativeLayout loadmorelayout;
//    private MSGallery like;
    private Timer timer;
    private TimerTask task;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int resultNum = msg.what;
            if (resultNum == 20) {
                try {
                    for (int i = 0; i < limitproduct.size(); i++) {
//                        TextView timeTV = (TextView) limit.findViewWithTag(i);
//                        String dateStr = limitproduct.get(i).getLefttime();
//                        if (timeTV != null) {
//                            ViewUtils.updataTimeTV(timeTV, dateStr);
//                        }
                    }
                } catch (Exception e) {
                    Log.i(TAG, e.toString());
                }
            } else if (resultNum == 10) {
                viewPager.setCurrentItem(currentItem);
            }
        }
    };

    @Override
    protected void initCreate() {
        mContext = this;
        setContentView(R.layout.activity_home);
//        BottomManager.getInstanse().init(this);
        initView();
        initMotion();//初始化动作

        getServiceHomeAllInfo();//首页banner下载

        //增加的首页图片下载
        getServiceHomePic();
    }

    private void getServiceHomePic() {
        initPicData();

        GetHomeMarketPicTask getHomeAllPicTask = new GetHomeMarketPicTask();
        getHomeAllPicTask.setInitTimer(new GetHomeMarketPicTask.InitTimer() {

            @Override
            public void init() {
                timer = new Timer();
                task = new TimerTask() {
                    public void run() {
                        Message message = Message.obtain();
                        message.what = 20;
                        handler.sendMessage(message);
                    }
                };
                timer.schedule(task, 1000, 1000);
            }
        });

        getHomeAllPicTask.setUpdateHomeMarketing(new GetHomeMarketPicTask.UpdateHomeMarketing() {

            @Override
            public void update(List<Product> products) {
                if (products != null) {//有返回东西 ,解析出来数据，设置给屏幕
                    if (products.size() > 0) {
                        limitproduct.addAll(products);
                        limitAdapter.notifyDataSetChanged();
//                        limit.setSelection(100 * limitproduct.size() + 1);
                    }
                } else {
                    PromptManager.showToast(HomeActivity.mContext, "服务器忙，请稍后重试！！！");
                }
            }
        });
        getHomeAllPicTask.execute();
        GetHomeBargainPicTask getHomeBargainPicTask = new GetHomeBargainPicTask();
        getHomeBargainPicTask.setUpdateHomeBargain(new GetHomeBargainPicTask.UpdateHomeBargain() {

            @Override
            public void update(List<Product> products) {
                if (products != null) {//有返回东西 ,解析出来数据，设置给屏幕
                    if (products.size() > 0) {
                        bargainproduct.addAll(products);
                        bargainAdapter.notifyDataSetChanged();
//                        bargain.setSelection(100 * bargainproduct.size() + 1);
                    }
                } else {
                    PromptManager.showToast(HomeActivity.mContext, "服务器忙，请稍后重试！！！");
                }
            }
        });
        getHomeBargainPicTask.execute();
        GetHomeRecommendPicTask getHomeRecommendPicTask = new GetHomeRecommendPicTask();
        getHomeRecommendPicTask.setUpdateHomeRecommend(new GetHomeRecommendPicTask.UpdateHomeRecommend() {

            @Override
            public void update(List<Product> products) {
                if (products != null) {//有返回东西 ,解析出来数据，设置给屏幕
                    if (products.size() > 0) {
                        likeProduct.addAll(products);
                        likeAdapter.notifyDataSetChanged();
//                        like.setSelection(100 * likeProduct.size() + 1);
                    }
                } else {
                    PromptManager.showToast(HomeActivity.mContext, "服务器忙，请稍后重试！！！");
                }
            }
        });
        getHomeRecommendPicTask.execute();
    }

    private void initPicData() {
//        limit.setAdapter(limitAdapter);
//        limit.setOnItemClickListener(new GVItemListener(this, limitproduct, true));
//
//        bargain.setAdapter(bargainAdapter);
//        bargain.setOnItemClickListener(new GVItemListener(this, bargainproduct, false));
//
//        like.setAdapter(likeAdapter);
//        like.setOnItemClickListener(new GVItemListener(this, likeProduct, false));
    }

    private void initView() {
//        findViewById(R.id.imgHome)
//                .setBackgroundResource(R.drawable.bar_home_selected);// 主页
//        ((TextView) findViewById(R.id.tvHome)).setTextColor(getResources().getColor(R.color.background_red));

//        viewPager = (ViewPager) findViewById(R.id.vp_viewPager);
        more_limit = (TextView) findViewById(R.id.more_limit);
        more_bargain = (TextView) findViewById(R.id.more_bargain);

        seachkeywordET = (Button) findViewById(R.id.seach_keyword);

//        slideMenu = (SlideMenu) findViewById(R.id.slide_menu);
        menuImg = (ImageView) findViewById(R.id.title_bar_menu_btn);
        leftMenuLV = (com.honliv.honlivmall.view.LeftMenuListView) findViewById(R.id.category_leftMenu_LV);

//        limit = (MSGallery) findViewById(R.id.limit);
//        bargain = (MSGallery) findViewById(R.id.bargain);
//
//        relative_top2 = (ScrollViewForViewPager) findViewById(R.id.relative_top2);
//        like = (MSGallery) findViewById(R.id.like);
    }

    private void initMotion() {
        pagerPosition = 0;

        // 如果之前有保存用户数据
        if (savedInstanceState != null) {
            pagerPosition = savedInstanceState.getInt(STATE_POSITION);
        }

        options = ImageOptionsUtils.getHomeItemOption();

        relative_top2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        break;
                    case MotionEvent.ACTION_MOVE:
                        //检查滑动事件
                        View view = ((ScrollView) v).getChildAt(0);
                        if (view.getMeasuredHeight() <= v.getScrollY() + v.getHeight()) {
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        seachkeywordET.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
//                intent = new Intent(HomeActivity.this, SearchActivity.class);
//                intent.putExtra("isHomeS", true);
                startActivity(intent);
                overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
            }
        });

        more_limit.setOnClickListener(this);
        more_bargain.setOnClickListener(this);

        limitproduct = new ArrayList<Product>();
        bargainproduct = new ArrayList<Product>();
        likeProduct = new ArrayList<Product>();

        limitAdapter = new LimitGridAdapter(limitproduct, this);
        bargainAdapter = new BargainGridAdapter(bargainproduct, this, false, false);
        likeAdapter = new LikeGridAdapter(likeProduct, this, false, true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // 保存用户数据
        outState.putInt(STATE_POSITION, viewPager.getCurrentItem());
    }

    private void getServiceHomeAllInfo() {
        new MyHttpTask<Integer>() {
            @Override
            protected void onPreExecute() {
                PromptManager.showCommonProgressDialog(HomeActivity.this);
                super.onPreExecute();
            }

            @Override
            protected Object doInBackground(Integer... params) {
//                HomeEngine engine = BeanFactory.getImpl(HomeEngine.class);
//                return engine.getServiceHomeInfo("0");
                return null;
            }

            @Override
            protected void onPostExecute(Object result) {
                // TODO Auto-generated method stub
                super.onPostExecute(result);
                PromptManager.closeProgressDialog();
                if (result != null) {
                    //有返回东西 ,解析出来数据，设置给屏幕
                    HomeInfo homeInfo = (HomeInfo) result;
                    if (homeInfo == null) {
                        PromptManager.showToast(getApplicationContext(), "服务器忙，请稍后重试！！！");
                        return;
                    }
                    initData(homeInfo);
                } else {
                    PromptManager.showToast(getApplicationContext(), "服务器忙，请稍后重试！！！");
                }
            }
        }.executeProxy(1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_bar_menu_btn:
                if (slideMenu.isMainScreenShowing()) {
                    slideMenu.openMenu();
                    scheduledExecutor.shutdown();
                    //scheduledExecutor = null;
                } else {
                    slideMenu.closeMenu();
                    scheduledExecutor = Executors
                            .newSingleThreadScheduledExecutor();
                    if (myPagerTask == null) {
                        myPagerTask = new MyPagerTask();
                    }
                    scheduledExecutor.scheduleAtFixedRate(myPagerTask, 5, 5,
                            TimeUnit.SECONDS);
                }
                break;

            case R.id.more_limit:
//                startActivity(new Intent(this, MarketingActivity.class));
//                overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
                break;
            case R.id.more_bargain:
//                startActivity(new Intent(this, BargainActivity.class));
//                overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
                break;
        }
    }

    private void initData(HomeInfo homeInfo) {
        homeBanners = homeInfo.getHome_banner();//首页banner
//
        dots = new ArrayList<>();
        ArrayList<View> tempDots = new ArrayList<View>();
//        tempDots.add(findViewById(R.id.dot_0));
//        tempDots.add(findViewById(R.id.dot_1));
//        tempDots.add(findViewById(R.id.dot_2));
//        tempDots.add(findViewById(R.id.dot_3));
//        tempDots.add(findViewById(R.id.dot_4));
//        tempDots.add(findViewById(R.id.dot_5));
//        tempDots.add(findViewById(R.id.dot_6));
//        tempDots.add(findViewById(R.id.dot_7));
//        tempDots.add(findViewById(R.id.dot_8));
//        tempDots.add(findViewById(R.id.dot_9));
        if (homeBanners != null && homeBanners.size() > 0) {
            for (int i = 0; i < homeBanners.size(); i++) {
                dots.add(tempDots.get(i));
                tempDots.get(i).setVisibility(View.VISIBLE);
            }

            adapter = new HomeViewPagerAdapter(this, imageLoader, homeBanners, ImageOptionsUtils.getHomeItemOption());
            viewPager.setAdapter(adapter);

            listener = new MyOnPageChangeListener();
            viewPager.setOnPageChangeListener(listener);
        }

        /*******设置左菜单 的分类********/
//        leftMenuLV.setAdapter(new LeftMenuAdapter(homeInfo.getClassifies()));
//        leftMenuLV.setOnItemClickListener(new LeftMenuClickListener(homeInfo.getClassifies(), this));

        menuImg.setOnClickListener(this);//向左的左上角按钮
    }

    @Override
    protected void onResume() {
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

        if (myPagerTask == null) {
            myPagerTask = new MyPagerTask();
        }
        scheduledExecutor.scheduleAtFixedRate(myPagerTask, 5, 5,
                TimeUnit.SECONDS);
        super.onResume();
        initShopCarNumber();//
    }

    protected void onPause() {
        super.onPause();
        scheduledExecutor.shutdown();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {// 处理返回键
        if (keyCode == KeyEvent.KEYCODE_BACK) {// 返回值被点击了
            PromptManager.showExitSystem(this);
        }
        return true;
    }

    /**
     * 搜索 ,扫描二维码
     *
     * @param view
     */
    public void searchOK(View view) {
//        intent = new Intent(this, CaptureActivity.class);
//        startActivity(intent);
    }

    /**
     * 删除搜索
     *
     * @param view
     */
    public void searchX(View view) {
        String seachkeywordStr = seachkeywordET.getText().toString().trim();
        if (StringUtils.isBlank(seachkeywordStr)) {
            return;
        } else {
            seachkeywordET.setText("");
        }
    }

    private class MyOnPageChangeListener implements OnPageChangeListener {

        public void onPageScrollStateChanged(int arg0) {
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageSelected(int position) {
            dots.get(position % homeBanners.size()).setBackgroundResource(
                    R.drawable.dot_focused);
            dots.get(oldPosition % homeBanners.size()).setBackgroundResource(
                    R.drawable.dot_normal);
            oldPosition = position;
        }
    }

    private class MyPagerTask implements Runnable {
        public void run() {
            currentItem++;
            Message msg = new Message();
            msg.what = 10;
            handler.sendMessage(msg);
        }
    }
}

