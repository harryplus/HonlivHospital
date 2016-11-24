package com.honliv.honlivmall.fragment.fifth.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.Help;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.model.fifth.child.FifthHelpListModel;
import com.honliv.honlivmall.presenter.fifth.child.FifthHelpListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthHelpListFragment extends BaseFragment<FifthHelpListPresenter, FifthHelpListModel> implements FifthContract.FifthHelpListView, View.OnClickListener {

    @BindView(R.id.helpListView)
    ListView helpListView;
    List<Help> helpDates;

    public static FifthHelpListFragment newInstance() {
        Bundle args = new Bundle();

        FifthHelpListFragment fragment = new FifthHelpListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fifth_help_list;
    }

    /**
     * 初始化控件
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        helpDates = new ArrayList<>();
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }

    @Override
    public void initData() {
        mPresenter.getServiceHelpList(12);
        helpListView.setAdapter(new HelpListAdapter());
        helpListView.setOnItemClickListener(new HelpItemListener());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.helpListView:
                start(FifthHelpDetailFragment.newInstance());
                break;
            default:
                break;
        }
    }

    @Override
    public void updateView(List<Help> result) {
        if (result != null) {
            //有返回东西 ,解析出来数据，设置给屏幕
            helpDates.clear();
            helpDates.addAll(result);
        } else {
            showToast("服务器忙，请稍后重试！！！");
        }
    }

    class HelpListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return helpDates.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                view = View.inflate(getContext(), R.layout.help_activity_item, null);

                holder.helpTitleTV = (TextView) view.findViewById(R.id.helpTitleTV);

                view.setTag(holder);
            } else {
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }
            holder.helpTitleTV.setText(helpDates.get(position).getTitle());

            return view;
        }
    }

    static class ViewHolder {
        TextView helpTitleTV;//商品标题
    }

    class HelpItemListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            String helptitle = helpDates.get(position).getTitle();
            ArrayList<Help> helpList = (ArrayList<Help>) helpDates.get(position).getChildlist();

            start(FifthHelpList2Fragment.newInstance());
//            intent = new Intent(HelpListActivity.this, HelpList2Activity.class);
//            intent.putExtra("helpList", helpList);
//            intent.putExtra("helptitle", helptitle);
//            startActivity(intent);
//            //finish();
//            overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
        }
    }
}
