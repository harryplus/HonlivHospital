package com.honliv.honlivhospital.base;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.honliv.honlivhospital.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Rodin on 2016/10/25.
 */
public class BaseFragment extends SupportFragment {
    protected void initToolbarNav(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });

//        initToolbarMenu(toolbar);
    }
}
