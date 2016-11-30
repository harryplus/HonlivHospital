package com.honliv.honlivmall.activity;

import android.os.Bundle;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseLazyMainFragment;
import com.honliv.honlivmall.base.CoreBaseActivity;
import com.honliv.honlivmall.fragment.fifth.FifthFragment;
import com.honliv.honlivmall.fragment.fifth.child.FifthHomeFragment;
import com.honliv.honlivmall.fragment.first.FirstFragment;
import com.honliv.honlivmall.fragment.first.child.FirstHomeFragment;
import com.honliv.honlivmall.fragment.fourth.FourthFragment;
import com.honliv.honlivmall.fragment.fourth.FourthMainFragment;
import com.honliv.honlivmall.fragment.second.SecondFragment;
import com.honliv.honlivmall.fragment.second.child.SecondMainFragment;
import com.honliv.honlivmall.fragment.third.ThirdFragment;
import com.honliv.honlivmall.fragment.third.child.ThirdMainFragment;
import com.honliv.honlivmall.view.BottomBar;
import com.honliv.honlivmall.view.BottomBarTab;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class MainActivity extends CoreBaseActivity implements BaseLazyMainFragment.OnBackToFirstListener {

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;
    public static final int FIFTH = 4;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;

    private SupportFragment[] mFragments = new SupportFragment[5];

    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        return super.onCreateFragmentAnimator();
    }

    @Override
    public void onBackPressedSupport() {
        // 对于 4个类别的主Fragment内的回退back逻辑,已经在其onBackPressedSupport里各自处理了
        super.onBackPressedSupport();
    }

    @Override
    public void onBackToFirstFragment() {
        mBottomBar.setCurrentItem(0);
    }

    /**
     * 这里暂没实现,忽略
     */
//    @Subscribe
//    public void onHiddenBottombarEvent(boolean hidden) {
//        if (hidden) {
//            mBottomBar.hide();
//        } else {
//            mBottomBar.show();
//        }
//    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            mFragments[FIRST] = FirstFragment.newInstance();
            mFragments[SECOND] = SecondFragment.newInstance();
            mFragments[THIRD] = ThirdFragment.newInstance();
            mFragments[FOURTH] = FourthMainFragment.newInstance();
            mFragments[FIFTH] = FifthFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH],
                    mFragments[FIFTH]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用,也可以通过getSupportFragmentManager.getFragments()自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[FIRST] = findFragment(FirstFragment.class);
            mFragments[SECOND] = findFragment(SecondFragment.class);
            mFragments[THIRD] = findFragment(ThirdFragment.class);
            mFragments[FOURTH] = findFragment(FourthMainFragment.class);
            mFragments[FIFTH] = findFragment(FifthFragment.class);
        }

        mBottomBar.addItem(new BottomBarTab(this, R.mipmap.bottom_layout_home, R.string.bottom_layout_main))
                .addItem(new BottomBarTab(this, R.mipmap.bottom_layout_search, R.string.bottom_layout_search))
                .addItem(new BottomBarTab(this, R.mipmap.bottom_layout_class, R.string.bottom_layout_class))
                .addItem(new BottomBarTab(this, R.mipmap.bottom_layout_shoping, R.string.bottom_layout_shopping))
                .addItem(new BottomBarTab(this, R.mipmap.bottom_layout_center, R.string.bottom_layout_center));
        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                SupportFragment currentFragment = mFragments[position];
                int count = currentFragment.getChildFragmentManager().getBackStackEntryCount();
                // 如果不在该类别Fragment的主页,则回到主页;
                if (count > 1) {
                    if (currentFragment instanceof FirstFragment) {
                        currentFragment.popToChild(FirstHomeFragment.class, false);
                    } else if (currentFragment instanceof SecondFragment) {
                        currentFragment.popToChild(SecondMainFragment.class, false);
                    } else if (currentFragment instanceof ThirdFragment) {
                        currentFragment.popToChild(ThirdMainFragment.class, false);
                    } else if (currentFragment instanceof FourthMainFragment) {
//                        currentFragment.popToChild(FourthMainFragment.class, false);
                    } else if (currentFragment instanceof FifthFragment) {
                        currentFragment.popToChild(FifthHomeFragment.class, false);
                    }
                    return;
                }

                // 这里推荐使用EventBus来实现 -> 解耦
//                if (count == 1) {
//                    // 在FirstPagerFragment中接收, 因为是嵌套的孙子Fragment 所以用EventBus比较方便
//                    // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
//                    EventBus.getDefault().post(new TabSelectedEvent(position));
//                }
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Bundle bundle = getIntent().getExtras();
//        showToast("onRestart main  "+(bundle==null));
        if (bundle != null) {
            Class fragment = (Class) bundle.getSerializable("fragment");
            int position = bundle.getInt("position");
            startAssignFragment(position, fragment);
        }
    }

    public void startAssignFragment(int position, Class fragmentClass) {
        mBottomBar.setCurrentItem(position);
        SupportFragment currentFragment = mFragments[position];
        currentFragment.popToChild(fragmentClass, false);
    }
}