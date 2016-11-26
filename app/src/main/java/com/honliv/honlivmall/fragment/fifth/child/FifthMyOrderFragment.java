package com.honliv.honlivmall.fragment.fifth.child;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.OrderInfo;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.model.fifth.child.FifthMyOrderModel;
import com.honliv.honlivmall.presenter.fifth.child.FifthMyOrderPresenter;
import com.honliv.honlivmall.util.BuilderTools;
import com.honliv.honlivmall.util.LogUtil;
import com.honliv.honlivmall.util.ToastUtils;
import com.honliv.honlivmall.view.GalleryItem;
import com.honliv.honlivmall.view.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthMyOrderFragment extends BaseFragment<FifthMyOrderPresenter, FifthMyOrderModel> implements FifthContract.FifthMyOrderView, View.OnClickListener, PullToRefreshView.OnHeaderRefreshListener, PullToRefreshView.OnFooterRefreshListener {
    boolean isUpload = false;//是否是加载
    @BindView(R.id.main_pull_refresh_view)
    PullToRefreshView mPullToRefreshView;
    int start = 1;
    List<OrderInfo> ordereList;//所有商品的集合
    List<OrderInfo> currentOrderList;//商品显示列表
    @BindView(R.id.my_order_list)
    ListView orderListView;
    int userId = -1;
    @BindView(R.id.my_order_null_text)
    TextView orderNullTV;
    @BindView(R.id.head_back_text)
    TextView head_back_text;
    MyOrderAdapter orderAdapter;
    List<GalleryItem> galleryitemList = new ArrayList<GalleryItem>();

    public static FifthMyOrderFragment newInstance() {
        Bundle args = new Bundle();
        FifthMyOrderFragment fragment = new FifthMyOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fifth_my_order;
    }

    /**
     * 初始化控件
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        head_back_text.setOnClickListener(this);
        mPullToRefreshView.setOnHeaderRefreshListener(this);
        mPullToRefreshView.setOnFooterRefreshListener(this);
        if (userId == -1) {
            userId = GloableParams.USERID;
        }
        Log.i(TAG, "getUserId--" + userId);
        if (userId < 0) {
            showToast("登录状态错误，请重新登录！");
            pop();
            return;
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }

    @Override
    public void showError(String msg) {

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_back_text:
                pop();
                break;
        }
    }

    @Override
    public void initData() {
        mPresenter.getServiceOrderList(userId, start, 30);
    }

    public void nDate(List<OrderInfo> orderes) {
        GalleryItem item = null;
        galleryitemList = new ArrayList<GalleryItem>();
        for (int i = 0; i < orderes.size(); i++) {
            item = new GalleryItem(getContext(), orderes.get(i));
            item.initAdapter(getContext());
            galleryitemList.add(item);
        }
        orderAdapter = new MyOrderAdapter();
        orderListView.setAdapter(orderAdapter);
    }

    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        mPullToRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("上拉加载");
                start = start + 1;
                isUpload = true;
//                MyOrderUtils.OrderList((MyOrderActivity) mContext, userId);
                mPullToRefreshView.onFooterRefreshComplete();
            }
        }, 1);
    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        mPullToRefreshView.postDelayed(new Runnable() {
            public void run() {
                // 设置更新时间
                // mPullToRefreshView.onHeaderRefreshComplete("最近更新:01-23 12:01");
                System.out.println("下拉更新");
                start = 1;
//                MyOrderUtils.OrderList((MyOrderActivity) mContext, userId);
                mPullToRefreshView.onHeaderRefreshComplete();
                mPullToRefreshView.setEnablePullLoadMoreDataStatus(true);
            }
        }, 1);
    }

    @Override
    public void onResume() {
        start = 1;
        if (GloableParams.USERID <= 0) {
            showToast("登陆状态错误");
            return;
        }
//        MyOrderUtils.OrderList((MyOrderActivity) mContext, GloableParams.USERID);
        super.onResume();
    }

    @Override
    public void updateView(List<OrderInfo> result) {
        if (result != null) {
            ordereList = (List<OrderInfo>) result;
            if (isUpload) {
                //加载更多
                if (ordereList.size() == 0) {
                    showToast("暂无更多内容");
                    mPullToRefreshView.setEnablePullLoadMoreDataStatus(false);
                } else {
                    currentOrderList.addAll(ordereList);

                    GalleryItem item = null;
                    for (int i = 0; i < ordereList.size(); i++) {
                        item = new GalleryItem(mContext, ordereList.get(i));
                        item.initAdapter(mContext);
                        galleryitemList.add(item);
                    }
                    orderAdapter.notifyDataSetChanged();
                }
                isUpload = false;
            } else {
                if (ordereList.size() == 0) {
                    orderListView.setVisibility(View.GONE);
                    orderNullTV.setVisibility(View.VISIBLE);
                    mPullToRefreshView.setVisibility(View.GONE);
                } else {
                    //有返回东西 ,解析出来数据，设置给屏幕
                    currentOrderList = ordereList;
                    nDate(currentOrderList);
                }
            }
        } else {
            showToast("服务器忙，请稍后重试！！！");
        }
    }

    @Override
    public void updateServiceCancelOrder(Boolean result, int position) {
                            if (result != null) {
                        Boolean isSuccess = (Boolean) result;
                        if (isSuccess) {
                        mPresenter.getServiceOrderList(userId, start, 30);
//							initCancelOrder(position);
                        } else {
                            showToast("取消订单失败！！！");
                        }
                    } else {
                                showToast("服务器忙，请稍后重试！！！");
                    }
    }

  private   class MyOrderAdapter extends BaseAdapter {
        boolean temptoPay;

        @Override
        public int getCount() {
            return currentOrderList.size();
        }

        @Override
        public Object getItem(int position) {
            return currentOrderList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                view = View.inflate(getContext(), R.layout.my_order_listitem, null);

                holder.orderIDTV = (TextView) view.findViewById(R.id.textOrderIDIcon);
                holder.textOrderPrice = (TextView) view.findViewById(R.id.textOrderPrice);
                holder.textTimeIcon = (TextView) view.findViewById(R.id.textTimeIcon);
                holder.textStateIcon = (TextView) view.findViewById(R.id.textStateValue);
                holder.CancelOrderTV = (TextView) view.findViewById(R.id.textCancelOrder);
                holder.OrderDetailTV = (TextView) view.findViewById(R.id.textOrderDetail);
                holder.toPayTV = (TextView) view.findViewById(R.id.toPaytext);
                holder.gallery = (GalleryItem) view.findViewById(R.id.item_gallery);

                view.setTag(holder);
            } else {
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }

            holder.orderIDTV.setText(getString(R.string.order_num) + currentOrderList.get(position).getOrderCode() + "");
            holder.textOrderPrice.setText(
                    getString(R.string.order_money) + currentOrderList.get(position).getAllprice() + "元");
            holder.textTimeIcon.setText(getString(R.string.order_day) + currentOrderList.get(position).getTime());

            String orderStatus = currentOrderList.get(position).getStatus();

            if ("UnHandle".equals(orderStatus)) {
                // 可以支付的状态：Status=UnHandle, (payGateway != cod  &&   payGateway!=bank )
                temptoPay = (!"cod".equals(currentOrderList.get(position).getPaygateway() + ""))
                        && (!"bank".equals(currentOrderList.get(position).getPaygateway() + ""));
                if (temptoPay) {
                    holder.toPayTV.setVisibility(View.VISIBLE);
                } else {
                    holder.toPayTV.setVisibility(View.GONE);
                }
            } else {
                holder.toPayTV.setVisibility(View.GONE);
            }

            Double price = Double.parseDouble(currentOrderList.get(position).getAllprice());
            if (price <= 0) {
                holder.toPayTV.setVisibility(View.GONE);
            }

            if ("Handling".equals(orderStatus)) {
                orderStatus = "正在进行";
            } else if ("UnHandle".equals(orderStatus)) {
                orderStatus = "等待处理"; // 可以支付的状态：Status=UnHandle, (payGateway != cod  &&   payGateway!=bank )
            } else if ("Complete".equals(orderStatus)) {
                orderStatus = "订单完成";
            } else if ("Cancel".equals(orderStatus)) {
                orderStatus = "订单已取消";
            } else if ("UserLock".equals(orderStatus)) {
                orderStatus = "用户锁定";
            } else if ("SystemLock".equals(orderStatus)) {
                orderStatus = "订单锁定";
            } else if ("AdminLock".equals(orderStatus)) {
                orderStatus = "订单锁定";
            } else {
                orderStatus = "等待处理";
            }

            holder.textStateIcon.setText(orderStatus + "");

            GalleryItem items = galleryitemList.get(position);
            holder.gallery.setAdapter(items.adapter);
            if (currentOrderList.get(position).getPics().size() > 1) {
                holder.gallery.setSelection(1);
            }

        holder.OrderDetailTV.setOnClickListener(new OrderDetailListener(position));
        view.setOnClickListener(new OrderDetailListener(position));

        holder.CancelOrderTV.setOnClickListener(new CancelOrderListener(position));
        holder.toPayTV.setOnClickListener(new toPayListener(position));
            return view;
        }

        private class ViewHolder {
            TextView orderIDTV;//订单编号
            TextView textOrderPrice;//订单价格
            TextView textTimeIcon;//订单时间
            TextView textStateIcon;//订单状态
            TextView CancelOrderTV;//取消订单
            TextView OrderDetailTV;//订单详情
            TextView toPayTV;//订单支付
            GalleryItem gallery; //滑动图片
        }

    private class CancelOrderListener implements View.OnClickListener {
        int position;

        public CancelOrderListener(int position) {
            super();
            this.position = position;
        }

        public void onClick(View v) {
            String flag = currentOrderList.get(position).getFlag();
            if ("1".equals(flag)) {
              showShopcarDialog(position);
            } else {
                ToastUtils.showShortToast("订单状态不可取消");
            }
        }
    }

private void showShopcarDialog( final int position) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {// 无法取消对话框
                public void onCancel(DialogInterface dialog) {
                }
            });
            builder.setMessage("您确定要取消这件商品？");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                   mPresenter. getServiceCancelOrder(GloableParams.USERID, currentOrderList.get(position).getOrderid(),position);
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.show();
        }

    private class toPayListener implements View.OnClickListener {
        int position;

        public toPayListener(int position) {
            super();
            this.position = position;
        }

        public void onClick(View v) {
            BuilderTools.showToPayDialog(getContext(), ordereList.get(position));
        }
    }

    private class OrderDetailListener implements View.OnClickListener {
        int position;

        public OrderDetailListener(int position) {
            this.position = position;
        }

        public void onClick(View v) {
            Bundle data=new Bundle();
            data.putInt("orderId", currentOrderList.get(position).getOrderid());
            data.putString("OrderCode", ordereList.get(position).getOrderCode());
            data.putString("Allprice", ordereList.get(position).getAllprice());
            start(FifthMyOrderDetailFragment.newInstance(data));
        }
    }
    }
}
