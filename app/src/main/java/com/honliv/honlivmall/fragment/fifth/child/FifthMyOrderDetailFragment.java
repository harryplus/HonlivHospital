package com.honliv.honlivmall.fragment.fifth.child;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.activity.PayPassActivity;
import com.honliv.honlivmall.adapter.MyOrderProductLVAdapter;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.OrderFollow;
import com.honliv.honlivmall.bean.OrderInfo;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.model.fifth.child.FifthMyOrderDetailModel;
import com.honliv.honlivmall.presenter.fifth.child.FifthMyOrderDetailPresenter;
import com.honliv.honlivmall.util.LogUtil;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthMyOrderDetailFragment extends BaseFragment<FifthMyOrderDetailPresenter, FifthMyOrderDetailModel> implements FifthContract.FifthMyOrderDetailView, View.OnClickListener {
    @BindView(R.id.productLv)
    ListView productLv;
    @BindView(R.id.orderusernameTV)
    TextView orderusernameTV;
    @BindView(R.id.payment_time_rel)
    RelativeLayout payment_time_rel;
    @BindView(R.id.payment_address_rel)
    RelativeLayout payment_address_rel;
    @BindView(R.id.orderuserphoneTV)
    TextView orderuserphoneTV;
    @BindView(R.id.payment_orderAddress_text)
    TextView payment_orderAddress_text;
    @BindView(R.id.payment_orderPrice_text)
    TextView payment_orderPrice_text;
    @BindView(R.id.orderaddressDetailTV)
    TextView orderaddressDetailTV;
    @BindView(R.id.order_follow1_LL)
    LinearLayout orderFollow1LL;
    @BindView(R.id.order_followtime1_TV)
    TextView order_followtime1_TV;
    @BindView(R.id.order_followoperation1_TV)
    TextView order_followoperation1_TV;
    @BindView(R.id.order_follow2_LL)
    LinearLayout orderFollow2LL;
    @BindView(R.id.order_followtime2_TV)
    TextView order_followtime2_TV;
    @BindView(R.id.order_followoperation2_TV)
    TextView order_followoperation2_TV;
    @BindView(R.id.order_follow3_LL)
    LinearLayout orderFollow3LL;
    @BindView(R.id.order_followtime3_TV)
    TextView order_followtime3_TV;
    @BindView(R.id.order_followoperation3_TV)
    TextView order_followoperation3_TV;
    @BindView(R.id.order_follow4_LL)
    LinearLayout orderFollow4LL;
    @BindView(R.id.order_followtime4_TV)
    TextView order_followtime4_TV;
    @BindView(R.id.order_followoperation4_TV)
    TextView order_followoperation4_TV;
    @BindView(R.id.order_productprice_text)
    TextView order_productprice_text;//订单价格
    @BindView(R.id.payment_carPrice_text)
    TextView payment_carPrice_text;//订单运费
    @BindView(R.id.payment_returnPrice_text)
    TextView payment_returnPrice_text;//订单返现
    @BindView(R.id.order_bottom_money_text)
    TextView order_bottom_money_text;//订单应付金额
    @BindView(R.id.order_invoiceHintValue_text)
    TextView order_invoiceHintValue_text;//订单应付金额
    @BindView(R.id.orderDetail_cancel_TV)
    TextView orderDetail_cancel_TV;//取消订单按钮
    @BindView(R.id.toPayOrder_bottom_text)
    TextView toPayOrder_bottom_text;//支付订单订单按钮
    @BindView(R.id.payment_lookProduct_IV)
    ImageView lookProductIV;
    @BindView(R.id.order_paytype_text)
    TextView orderpaytype;
    @BindView(R.id.lookProductList)
    RelativeLayout lookProductList;
    @BindView(R.id.toPayOrder)
    TextView toPayOrder;
    @BindView(R.id.useCoupon)
    TextView useCoupon;

    List<Product> currentProductList;
    int orderId;
    boolean isShowProduct = false;
    String OrderCode;
    String Allprice;
    OrderInfo ordereDetail;

    public static FifthMyOrderDetailFragment newInstance() {

        Bundle args = new Bundle();

        FifthMyOrderDetailFragment fragment = new FifthMyOrderDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fifth_my_order_detail;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        orderId = arguments.getInt("orderId", -1);
        OrderCode = arguments.getString("OrderCode");
        Allprice = arguments.getString("Allprice");
//        orderId = this.getIntent().getIntExtra("orderId", -1);
//        OrderCode = this.getIntent().getStringExtra("OrderCode");
//        Allprice = this.getIntent().getStringExtra("Allprice");
        if (orderId < 0) {
            showToast("订单信息错误，请重试");
        }
        lookProductList.setOnClickListener(this);
        toPayOrder.setOnClickListener(this);
        useCoupon.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mPresenter.getServiceOrderDetail(GloableParams.USERID, orderId);
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }


    protected void initDate(OrderInfo ordereDetail) {
        orderusernameTV.setText("姓名:" + ordereDetail.getAddress_info().getName());
        orderuserphoneTV.setText("手机号:" + ordereDetail.getAddress_info().getPhone());
        orderaddressDetailTV.setText("地址:" + ordereDetail.getAddress_info().getAddressArea() + " " + ordereDetail.getAddress_info().getAreaDetail());

        order_productprice_text.setText("￥" + ordereDetail.getPayment_info().getProductprice() + "元");
        payment_carPrice_text.setText("￥" + ordereDetail.getPayment_info().getYunfei() + "元");
        payment_returnPrice_text.setText("￥" + ordereDetail.getPayment_info().getReturnprice() + "元");
        String paymenttype = ordereDetail.getPaymenttype();
        if (paymenttype == null || paymenttype.equals("")) {
            paymenttype = ordereDetail.getPaytypename();
        }
        orderpaytype.setText(paymenttype);

        order_bottom_money_text.setText("￥" + ordereDetail.getPayment_info().getOrderprice() + "元");
        order_invoiceHintValue_text.setText("￥" + ordereDetail.getPayment_info().getOrderprice() + "元");

        List<OrderFollow> order_followList = ordereDetail.getOrder_follows();
        switch (order_followList.size()) {
            case 0:
                orderFollow1LL.setVisibility(View.GONE);
                orderFollow2LL.setVisibility(View.GONE);
                orderFollow3LL.setVisibility(View.GONE);
                orderFollow4LL.setVisibility(View.GONE);
                break;
            case 1:
                order_followtime1_TV.setText(order_followList.get(0).getTime());
                order_followoperation1_TV.setText(order_followList.get(0).getOperation());
                orderFollow2LL.setVisibility(View.GONE);
                orderFollow3LL.setVisibility(View.GONE);
                orderFollow4LL.setVisibility(View.GONE);
                break;
            case 2:
                order_followtime1_TV.setText(order_followList.get(0).getTime());
                order_followoperation1_TV.setText(order_followList.get(0).getOperation());
                order_followtime2_TV.setText(order_followList.get(1).getTime());
                order_followoperation2_TV.setText(order_followList.get(1).getOperation());
                orderFollow3LL.setVisibility(View.GONE);
                orderFollow4LL.setVisibility(View.GONE);
                break;
            case 3:
                order_followtime1_TV.setText(order_followList.get(0).getTime());
                order_followoperation1_TV.setText(order_followList.get(0).getOperation());
                order_followtime2_TV.setText(order_followList.get(1).getTime());
                order_followoperation2_TV.setText(order_followList.get(1).getOperation());
                order_followtime3_TV.setText(order_followList.get(2).getTime());
                order_followoperation3_TV.setText(order_followList.get(2).getOperation());
                orderFollow4LL.setVisibility(View.GONE);
                break;
            default:
                order_followtime1_TV.setText(order_followList.get(0).getTime());
                order_followoperation1_TV.setText(order_followList.get(0).getOperation());
                order_followtime2_TV.setText(order_followList.get(1).getTime());
                order_followoperation2_TV.setText(order_followList.get(1).getOperation());
                order_followtime3_TV.setText(order_followList.get(2).getTime());
                order_followoperation3_TV.setText(order_followList.get(2).getOperation());
                order_followtime4_TV.setText(order_followList.get(3).getTime());
                order_followoperation4_TV.setText(order_followList.get(3).getOperation());
                break;
        }
        //订单标识，1=>可删除可修改 2=>不可修改 3=>已完成
        if ("2".equals(ordereDetail.getFlag())) {
            orderDetail_cancel_TV.setClickable(false);
            orderDetail_cancel_TV.setText("订单不可修改");
            orderDetail_cancel_TV.setBackgroundResource(R.drawable.product_detail_shop_noselected);
        } else if ("3".equals(ordereDetail.getFlag())) {
            orderDetail_cancel_TV.setClickable(false);
            orderDetail_cancel_TV.setText("订单已完成");
            orderDetail_cancel_TV.setBackgroundResource(R.drawable.product_detail_shop_noselected);
        }
        if ("UnHandle".equals(ordereDetail.getStatus())) {
            // 可以支付的状态：Status=UnHandle, (payGateway != cod  &&   payGateway!=bank )
            if (!"cod".equals(ordereDetail.getPaygateway() + "") && !"bank".equals(ordereDetail.getPaygateway() + "")) {
                toPayOrder_bottom_text.setVisibility(View.VISIBLE);
            } else {
                toPayOrder_bottom_text.setVisibility(View.GONE);
            }
        } else {
            toPayOrder_bottom_text.setVisibility(View.GONE);
        }
    }


    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getServiceOrderDetail(GloableParams.USERID, orderId);
    }

    void showShopcarDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {// 无法取消对话框
            public void onCancel(DialogInterface dialog) {
                //loadHomeActivity();// 取消对话框，进入主界面
                LogUtil.info(" 取消对话框");
            }
        });
//		builder.setTitle("添加成功 !");
        builder.setMessage("您确定要取消这件商品？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mPresenter.getServiceCancelOrder(GloableParams.USERID, orderId);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //loadHomeActivity();
                LogUtil.info(" 回到界面");
            }
        });
        builder.show();
    }


    @Override
    public void updateServiceOrderDetail(OrderInfo result) {
        if (result != null) {
            ordereDetail = (OrderInfo) result;
            if (ordereDetail != null) {
                //有返回东西 ,解析出来数据，设置给屏幕
                ordereDetail.setOrderCode(OrderCode);
                ordereDetail.setAllprice(Allprice);
                initDate(ordereDetail);
            } else {
                showToast("获取定单信息失败");
            }
        } else {
            showToast("服务器忙，请稍后重试！！！");
        }
    }

    @Override
    public void updateServiceCancelOrder(Boolean result) {
        if (result != null) {
            Boolean isSuccess = (Boolean) result;
            if (isSuccess) {
                orderDetail_cancel_TV.setText("订单已取消");
                orderDetail_cancel_TV.setBackgroundResource(R.drawable.product_detail_shop_noselected);
                orderDetail_cancel_TV.setClickable(false);
                toPayOrder_bottom_text.setVisibility(View.GONE);
            } else {
                showToast("取消订单失败！！！");
            }
        } else {
            showToast("服务器忙，请稍后重试！！！");
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lookProductList:
                if (ordereDetail == null) {
                    return;
                }
                isShowProduct = !isShowProduct;
                if (isShowProduct) {
                    currentProductList = ordereDetail.getProductlist();
                    if (currentProductList.size() > 0) {
                        Log.i(TAG, "MyOrderProductLVAdapter");
                        productLv.setAdapter(new MyOrderProductLVAdapter(getContext(), currentProductList));
                    }
                    lookProductIV.setBackgroundResource(R.drawable.arrow);
                    productLv.setVisibility(View.VISIBLE);
                } else {
                    productLv.setVisibility(View.GONE);
                    lookProductIV.setBackgroundResource(R.drawable.arrow_right);
                }
                break;
            case R.id.toPayOrder:
                if (orderId < 0) {
                    return;
                }
                Intent intent = new Intent(getContext(), PayPassActivity.class);
                intent.putExtra("orderInfo", ordereDetail);
                startActivity(intent);
                break;
            case R.id.useCoupon:
                String flag = ordereDetail.getFlag();
                if ("1".equals(flag)) {
                    showShopcarDialog(orderId);
                } else {
                    showToast("订单状态不可取消");
                }
                break;
        }
    }
}
