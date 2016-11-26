package com.honliv.honlivmall.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.bean.OrderInfo;
import com.honliv.honlivmall.util.BuilderTools;
import com.honliv.honlivmall.util.LogUtil;
import com.honliv.honlivmall.util.ToastUtils;
import com.honliv.honlivmall.view.GalleryItem;

import java.util.List;

/**
 * Created by Rodin on 2016/10/26.
 */
public class MyOrderAdapter extends BaseAdapter {
    private static final String TAG = "MyOrderAdapter";
    private final Context context;
    boolean temptoPay;
    private List<OrderInfo> ordereList;
    private List<GalleryItem> galleryitemList;
    private List<OrderInfo> currentOrderList;//商品显示列表,已经不使用，无法加载更多
    private Intent intent;

    public MyOrderAdapter(Context context, List<GalleryItem> galleryitemList,
                          List<OrderInfo> currentOrderList, List<OrderInfo> ordereList) {
        this.context = context;
        this.galleryitemList = galleryitemList;
        this.currentOrderList = currentOrderList;
        this.ordereList = ordereList;
    }

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
            view = View.inflate(context.getApplicationContext(), R.layout.my_order_listitem, null);

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

        holder.orderIDTV.setText(context.getString(R.string.order_num) + currentOrderList.get(position).getOrderCode() + "");
        holder.textOrderPrice.setText(
                context.getString(R.string.order_money) + currentOrderList.get(position).getAllprice() + "元");
        holder.textTimeIcon.setText(context.getString(R.string.order_day) + currentOrderList.get(position).getTime());

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

        GalleryItem items = this.galleryitemList.get(position);
        holder.gallery.setAdapter(items.adapter);
        if (currentOrderList.get(position).getPics().size() > 1) {
            holder.gallery.setSelection(1);
        }

//        holder.OrderDetailTV.setOnClickListener(new OrderDetailListener(position));
//        view.setOnClickListener(new OrderDetailListener(position));

//        holder.CancelOrderTV.setOnClickListener(new CancelOrderListener(position));
//        holder.toPayTV.setOnClickListener(new toPayListener(position));
        return view;
    }


//    private void showShopcarDialog(final int position) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {// 无法取消对话框
//            public void onCancel(DialogInterface dialog) {
//                //loadHomeActivity();// 取消对话框，进入主界面
//                LogUtil.info(" 取消对话框");
//            }
//        });
////		builder.setTitle("添加成功 !");
//        builder.setMessage("您确定要取消这件商品？");
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                getServiceCancelOrder(position);
//            }
//        });
//        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                //loadHomeActivity();
//                LogUtil.info(" 回到界面");
//            }
//        });
//        builder.show();
//    }

    public void getServiceCancelOrder(final int position) {
        new AsyncTask<Integer, Void, Object>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Object doInBackground(Integer... params) {
                return null;
//                OrderEngine engine = BeanFactory.getImpl(OrderEngine.class);
//                return engine.getServiceCancelOrder(GloableParams.USERID, currentOrderList.get(params[0]).getOrderid());
            }

            protected void onPostExecute(Object result) {
                super.onPostExecute(result);
                if (result != null) {
                    Boolean isSuccess = (Boolean) result;
                    if (isSuccess) {
//                        MyOrderUtils.OrderList(context, context.userId);
//							initCancelOrder(position);
                    } else {
                        ToastUtils.showShortToast("取消订单失败！！！");
                    }
                } else {
                    ToastUtils.showShortToast("服务器忙，请稍后重试！！！");
                }
            }
        }.execute(position);
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

//    private class CancelOrderListener implements View.OnClickListener {
//        int position;
//
//        public CancelOrderListener(int position) {
//            super();
//            this.position = position;
//        }
//
//        public void onClick(View v) {
//            String flag = currentOrderList.get(position).getFlag();
//            if ("1".equals(flag)) {
//                showShopcarDialog(position);
//            } else {
//                ToastUtils.showShortToast("订单状态不可取消");
//            }
//        }
//    }

//    private class toPayListener implements View.OnClickListener {
//        int position;
//
//        public toPayListener(int position) {
//            super();
//            this.position = position;
//        }
//
//        public void onClick(View v) {
//            BuilderTools.showToPayDialog(context, ordereList.get(position));
//        }
//    }

//    private class OrderDetailListener implements View.OnClickListener {
//        int position;
//
//        public OrderDetailListener(int position) {
//            super();
//            this.position = position;
//        }
//
//        public void onClick(View v) {
////            intent = new Intent(context.getApplicationContext(), MyOrderDetailActivity.class);
////            intent.putExtra("orderId", currentOrderList.get(position).getOrderid());
////            intent.putExtra("OrderCode", ordereList.get(position).getOrderCode());
////            intent.putExtra("Allprice", ordereList.get(position).getAllprice());
////            Log.i(TAG, ordereList.get(position).getOrderCode() + "---getOrderCode");
////            context.startActivity(intent);
//        }
//    }
}
