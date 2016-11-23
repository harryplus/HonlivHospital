package com.honliv.honlivmall.fragment.fifth.child;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
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
import com.honliv.honlivmall.model.fifth.child.FifthHelpList2Model;
import com.honliv.honlivmall.model.fifth.child.FifthHelpListModel;
import com.honliv.honlivmall.presenter.fifth.child.FifthHelpList2Presenter;
import com.honliv.honlivmall.presenter.fifth.child.FifthHelpListPresenter;
import com.honliv.honlivmall.util.PromptManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthHelpList2Fragment extends BaseFragment<FifthHelpList2Presenter, FifthHelpList2Model> implements FifthContract.FifthHelpList2View, View.OnClickListener {


    String helptitle;
    List<Help> helpDates;
    @BindView(R.id.helpListView)
    ListView helpdetailListView;
    ArrayList<Help> preHelpList;
    @BindView(R.id.helpList2_title)
    TextView helpList2_title;

    public static FifthHelpList2Fragment newInstance() {
        Bundle args = new Bundle();

        FifthHelpList2Fragment fragment = new FifthHelpList2Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fifth_help_list2;
    }

    /**
     * 初始化控件
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
//        helptitle = this.getIntent().getStringExtra("helptitle");
//        preHelpList = (ArrayList<Help>) this.getIntent().getSerializableExtra("helpList");
        if (helptitle == null) {
            showToast("获取失败，请您联系客服！");
            return;
        }
        helpList2_title.setText(helptitle + "");
        if (preHelpList != null && preHelpList.size() > 0) {
            helpDates = preHelpList;
            helpdetailListView.setAdapter(new HelpListAdapter());
            helpdetailListView.setOnItemClickListener(new HelpItemListener());
        }
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

    }


    class HelpItemListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            int helpId = helpDates.get(position).getId();
            start(FifthHelpDetailFragment.newInstance());
//            intent.putExtra("helpId", helpId);
//            startActivity(intent);
            //finish();
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
        TextView helpTitleTV;//标题

    }
}
