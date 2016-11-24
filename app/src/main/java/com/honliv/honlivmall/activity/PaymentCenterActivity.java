package com.honliv.honlivmall.activity;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.adapter.PaymentCenterAdapter;
import com.honliv.honlivmall.adapter.PaymentPayHintCouponAdapter;
import com.honliv.honlivmall.adapter.PaymentPayHintCouponAdapter.PaymentPayHintCouponAdapterCallBack;
import com.honliv.honlivmall.base.BaseLazyMainFragment;
import com.honliv.honlivmall.bean.AddressInfo;
import com.honliv.honlivmall.bean.CouponInfo;
import com.honliv.honlivmall.bean.GifInfo;
import com.honliv.honlivmall.bean.OrderInfo;
import com.honliv.honlivmall.bean.PayShip;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.contract.ActivityContract;
import com.honliv.honlivmall.model.activity.PaymentCenterModel;
import com.honliv.honlivmall.presenter.activity.PaymentCenterPresenter;
import com.honliv.honlivmall.task.GetCunPonListInfosTask;
import com.honliv.honlivmall.task.GetServiceSubmitOrderTask;
import com.honliv.honlivmall.util.LogUtil;
import com.honliv.honlivmall.util.MoneyUtils;
import com.honliv.honlivmall.util.PromptManager;
import com.honliv.honlivmall.view.PaymentDialog;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 提交订单
 *
 * @author Administrator
 */
public class PaymentCenterActivity extends CoreBaseActivity<PaymentCenterPresenter, PaymentCenterModel> implements BaseLazyMainFragment.OnBackToFirstListener, ActivityContract.PaymentCenterView {
    @BindView(R.id.payment_address_text)
    TextView paymentAddress;// 收货地址
    @BindView(R.id.payment_username_text)
    TextView paymentName;// 收货姓名
    @BindView(R.id.payment_phone_text)
    TextView paymentPhone;// 收货手机号
    @BindView(R.id.productLv)
    ListView productLV;// 商品清单
    @BindView(R.id.giftproductLv)
    ListView giftproductLv;// 赠送商品清单
    @BindView(R.id.payment_productprice_text)
    TextView allProductPriceTV;// 所有金额
    @BindView(R.id.payment_freight_text)
    TextView payment_freight_text;// 运费
    @BindView(R.id.payment_cuxiao_text)
    TextView payment_cuxiao_text;// 促销
    @BindView(R.id.payment_favorableProduct_text)
    TextView paymentfavorablePriceTV;// 优惠的价格
    @BindView(R.id.payment_order_bottom_money_text)
    TextView shouldPayPriceTV;// 应付金额
    @BindView(R.id.payment_invoiceHintValue_text)
    TextView paymentBottomPriceTV;//多余的应付金额
    @BindView(R.id.payment_lookProduct_coupon_IV)
    ImageView paymentLookProductCouponIV;
    @BindView(R.id.payment_payType_rel_useCoupon)
    RelativeLayout paymentPayTypeRelUseCoupon;
    @BindView(R.id.payment_lookProduct_IV)
    ImageView lookProductIV;// 商品
    @BindView(R.id.payment_lookGiftProduct_IV)
    ImageView lookGiftProductIV;// 赠品
    @BindView(R.id.payment_payHint_coupon_listview)
    ListView couPonListview;
    @BindView(R.id.payment_Gift_rel)
    RelativeLayout payment_Gift_rel; //赠品列表
    @BindView(R.id.giftproductBottom_TV)
    TextView giftproductBottom_TV;//赠品下面的灰色
    @BindView(R.id.couponBottom_TV)
    TextView couponBottom_TV;//优惠卷下面的灰色
    @BindView(R.id.content_paytype)
    TextView content_paytype;
    @BindView(R.id.text_allprice)
    TextView text_allprice;


    static final String TAGS = "PaymentCenterActivity";

    PaymentCenterActivity TAG = PaymentCenterActivity.this;
    float allOldProductPrice = 0;//所有商品金额
    float shouldPayMoney;    // 应付金额
    float serviceShouldPayAllPrice = 0;      // 服务器返回的应付金额
    float returnShouldPayAllPrice = 0;      // 服务器返回的应付金额
    float totoprice;    // 优惠的价格
    float cuxiaoPrice;  // 促销的价格
    //     float preFreight = 0;
    float freight;
    ArrayList<Product> currentProductList;// 购物车的商品列表
    ArrayList<Product> currentGiftProductList;// 购物车的商品列表
    boolean isShowProduct = false;
    int shipId = 1;//配送id
    int payId = 0; //支付id
    PaymentCenterAdapter giftAdapter;
    //     Map<String, String> PrepayData;
    List<CouponInfo> couponInfos = null;
    boolean isShowGiftProduct = false;
    boolean isShowCoupon;
    GifInfo gifInfo;
    int regionId;
    Product favorableProduct; // 存放团购限时抢购商品的
    PaymentPayHintCouponAdapter payHintCouponAdapter;
    boolean isFavorable; // 是否从抢购和团购过来的
    List<PayShip> PayShipList;
    protected CouponInfo currentUseCouponInfo;
    List<AddressInfo> addressList;// 地址列表、获取默认地址
    AddressInfo addressInfo;// 地址
    AddressInfo preAddressInfo;// 地址
    List<JSONObject> productJsobList;

    void initSpinnerPay(List<PayShip> PayShipList) {
        this.PayShipList = PayShipList;
        PayShip payShip = PayShipList.get(0);
        content_paytype.setText(payShip.getName());
        payId = payShip.getId();
        PaymentDialog.initPayData(PayShipList);
    }

    /**
     * 获取积分卷信息
     */
    void getCunPonListInfos() {
        GetCunPonListInfosTask task = new GetCunPonListInfosTask(GloableParams.USERID, productJsobList, currentProductList);
        task.setListener(new GetCunPonListInfosTask.PostReslut() {

            @Override
            public void post(Object result) {
                if (result != null) {
                    couponInfos = (List<CouponInfo>) result;
                    // 不显示优惠劵
                    if (couponInfos == null || couponInfos.size() == 0) {
                        paymentPayTypeRelUseCoupon.setVisibility(View.GONE);
                        couponBottom_TV.setVisibility(View.GONE);
                    } else {
                        paymentPayTypeRelUseCoupon.setVisibility(View.VISIBLE);
                        couponBottom_TV.setVisibility(View.VISIBLE);
                    }
                } else {
                    paymentPayTypeRelUseCoupon.setVisibility(View.GONE);
                    couponBottom_TV.setVisibility(View.GONE);
                }
            }
        });
    }

    void getProductListAndGifProduct() {
        currentProductList = (ArrayList<Product>) this.getIntent()
                .getSerializableExtra("productlist");
        isFavorable = this.getIntent().getBooleanExtra("isFavorable", false);

        if (currentProductList == null || currentProductList.size() == 0) {
            PromptManager.showToast(this, "没有购物清单");
            return;
        }
        //转换成sku传送给服务器的
        productJsobList = new ArrayList<JSONObject>();
        JSONObject jsobj;
        for (int i = 0; i < currentProductList.size(); i++) {
            Product product = currentProductList.get(i);
            jsobj = new JSONObject();
            try {
                jsobj.put("SKU", product.getSku());
                jsobj.put("Count", product.getNumber());
                productJsobList.add(jsobj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

//        mPresenter.getServiceTotalPrice(favorableProduct != null ? favorableProduct
//                        .getCountDownId() : 0,
//                favorableProduct != null ? favorableProduct
//                        .getGroupBuyid() : 0, productJsobList);//获取服务器的应付金额

//        if (isFavorable) {
//            favorableProduct = currentProductList.get(0);
//        } else {
//            mPresenter.getServiceGifInfo(productJsobList, 0);
//        }
//
//        // 获取积分卷信息,使用优惠卷
//        getCunPonListInfos();
    }

//     void getServiceGifInfo(final float couponPrice) {
//        new MyHttpTask<Integer>() {
//            @Override
//            protected Object doInBackground(Integer... params) {
//                CategoryEngine engine = BeanFactory
//                        .getImpl(CategoryEngine.class);
//
//                return engine.getServiceGifProduct(GloableParams.USERID,
//                        productJsobList, couponPrice);
//            }
//
//            protected void onPostExecute(Object result) {
//                super.onPostExecute(result);
//                if (result != null) {
//                    gifInfo = (GifInfo) result;
//                    if (gifInfo != null && (gifInfo.getProductList() != null || gifInfo.getCouponList() != null)) {
//                        if (currentGiftProductList != null && currentGiftProductList.size() > 0) {//说明前面有赠品
//                            currentGiftProductList.clear();
//                        } else {
//                            currentGiftProductList = new ArrayList<Product>();
//                        }
//                        currentGiftProductList = (ArrayList<Product>) gifInfo.getProductList();
//                        if (gifInfo.getCouponList() != null && gifInfo.getCouponList().size() > 0) {
//                            if (currentGiftProductList == null) {
//                                currentGiftProductList = new ArrayList<Product>();
//                            }
//                            currentGiftProductList.addAll(gifInfo.getCouponList());
//                        }
//
//                        if (currentGiftProductList != null && currentGiftProductList.size() > 0) {
//                            payment_Gift_rel.setVisibility(View.VISIBLE);
//                            giftproductBottom_TV.setVisibility(View.VISIBLE);
//                        } else {
//                            payment_Gift_rel.setVisibility(View.GONE);
//                            giftproductBottom_TV.setVisibility(View.GONE);
//                            if (giftAdapter != null) {
//                                giftAdapter.notifyDataSetChanged();
//                            }
//                        }
//                    } else {
//                        payment_Gift_rel.setVisibility(View.GONE);
//                        giftproductBottom_TV.setVisibility(View.GONE);
//
//                        if (giftAdapter != null) {
//                            giftAdapter.notifyDataSetChanged();
//                        }
//                    }
//                } else {
//                    payment_Gift_rel.setVisibility(View.GONE);
//                    giftproductBottom_TV.setVisibility(View.GONE);
//                }
//            }
//        }.executeProxy(0);
//    }

    /**
     * 初始化价格的方法
     *
     * @param freight
     */
    void initAllPrice(float freight) {

        float allProductPrice = 0; //商品销售金额总计
        //所有商品旧金额
        allOldProductPrice = 0;
        // 服务器返回的应付金额
        serviceShouldPayAllPrice = 0;
        if (!isFavorable) {
            allProductPrice = MoneyUtils.getAllPayMoney(currentProductList);
        }

        for (int position = 0; position < currentProductList.size(); position++) {
            Product product = currentProductList.get(position);
            String priceStr = product.getSaleprice();
            float tempPrice = Float.parseFloat(priceStr);

            if (isFavorable) {//从团购抢购来的
                float marketPrice = Float.parseFloat(product.getMarketprice());
                allOldProductPrice += marketPrice * product.getNumber();

                allProductPrice += marketPrice * product.getNumber();
                serviceShouldPayAllPrice += tempPrice * product.getNumber();

                cuxiaoPrice = allOldProductPrice - serviceShouldPayAllPrice;
            } else {
                //普通商品
                float marketPrice = Float.parseFloat(product.getMarketprice());
                allOldProductPrice += marketPrice * product.getNumber();

                if (returnShouldPayAllPrice >= 0) {
                    serviceShouldPayAllPrice = returnShouldPayAllPrice;
                }
                cuxiaoPrice = allProductPrice - serviceShouldPayAllPrice;
            }
        }
        cuxiaoPrice = Math.abs(cuxiaoPrice);

        if (serviceShouldPayAllPrice <= 0) {
            shouldPayPriceTV.setText("请重新获取金额");
            text_allprice.setText("请重新获取金额");
            return;
        }


        // 所有商品金额
        allProductPriceTV.setText(MoneyUtils.getMoney(allProductPrice));
        // 促销的
        payment_cuxiao_text.setText("-" + MoneyUtils.getMoney(cuxiaoPrice));
        // 运费
        payment_freight_text.setText(MoneyUtils.getMoney(freight));
        // 优惠的价
        paymentfavorablePriceTV.setText("-" + MoneyUtils.getMoney(totoprice));

        shouldPayMoney = serviceShouldPayAllPrice - totoprice + freight;

        if (shouldPayMoney < 0) {
            shouldPayMoney = 0;
        }
        // 最后应付金额
        shouldPayPriceTV.setText(MoneyUtils.getMoney(shouldPayMoney));
        text_allprice.setText(MoneyUtils.getMoney(shouldPayMoney));
    }

//     void getServiceAddressList() {
//        new MyHttpTask<Integer>() {
//            @Override
//            protected void onPreExecute() {
//                PromptManager
//                        .showCommonProgressDialog(PaymentCenterActivity.this);
//                super.onPreExecute();
//            }
//
//            @Override
//            protected Object doInBackground(Integer... params) {
//                AddressEngine engine = BeanFactory
//                        .getImpl(AddressEngine.class);
//                return engine.getServiceAddressList(GloableParams.USERID, 1, 50);
//            }
//
//            protected void onPostExecute(Object result) {
//                super.onPostExecute(result);
//                PromptManager.closeProgressDialog();
//
//
//            }
//        }.executeProxy(0);
//    }

    protected void initAddressData() {
        for (int i = 0; i < addressList.size(); i++) {
            if (addressList.get(i).isHasDefault()) {
                addressInfo = addressList.get(i);
            }
        }

        if (addressList.size() == 1) {
            addressInfo = addressList.get(0);
        }
        if (addressInfo != null) {
        }
    }

    /**
     * 提交定单
     */
    public void goSubmit(View view) {// submitOK

        if (addressInfo == null) {
            PromptManager.showToast(this, "请选择地址,再提交");
            return;
        }
        regionId = addressInfo.getRegionId();
        if (regionId == 0) {
            PromptManager.showToast(this, "请重新选择地址,再提交");
            return;
        }
        if (payId == 0 || shipId == 0) {
            PromptManager.showToast(this, "请先选择支付配送方式");
            return;
        }
        showShopcarDialog();
    }

    void showShopcarDialog() {
        Builder builder = new Builder(this);
        builder.setOnCancelListener(new OnCancelListener() {// 无法取消对话框
            public void onCancel(DialogInterface dialog) {
            }
        });
        builder.setTitle("提交订单");
        builder.setMessage("您确定要提交订单吗？");// 您确定要提交订单吗？”
        builder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        getServiceSubmitOrder();
                    }
                });
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        builder.show();
    }

    void getServiceSubmitOrder() {
        GetServiceSubmitOrderTask task = new GetServiceSubmitOrderTask(PaymentCenterActivity.this, addressInfo, regionId, shipId, payId, productJsobList, currentUseCouponInfo, favorableProduct);
        task.setListener(new GetServiceSubmitOrderTask.PostMotion() {
            @Override
            public void motion(Object result) {
                if (result != null) {
                    OrderInfo orderInfo = (OrderInfo) result;
                    if (orderInfo == null) {
                        PromptManager.showToast(PaymentCenterActivity.this,
                                "提交订单失败,请重试");
                    } else {
                        if (orderInfo.getErrMsg() != null) {
                            if ("NOSTOCK".equals(orderInfo.getErrMsg())) {// NOSHOPPINGCARTINFO
                                String name = orderInfo.getName();

                                if (name.length() > 15) {
                                    name = name.substring(0, 25) + "..";
                                }
                                PromptManager.showToast(
                                        PaymentCenterActivity.this,
                                        name + "库存不足");
                            } else if ("NOSHOPPINGCARTINFO".equals(orderInfo
                                    .getErrMsg())) {
                                PromptManager.showToast(
                                        PaymentCenterActivity.this,
                                        "有商品库存不足,请减少数量或者联系客服！");
                            } else if ("PROSALEEXPIRED".equals(orderInfo
                                    .getErrMsg())) {
                                PromptManager.showToast(
                                        PaymentCenterActivity.this, "商品已经过期！");
                            } else {
                                PromptManager.showToast(
                                        PaymentCenterActivity.this,
                                        "提交订单失败,请联系客服");
                            }
                        } else {
                            try {
                                GloableParams.SUBMITOK = true;
                                DbUtils db = DbUtils
                                        .create(getApplicationContext());
                                List<Product> productlist = (ArrayList) db
                                        .findAll(Selector.from(Product.class)
                                                .where("userId", "=",
                                                        GloableParams.USERID));
                                db.deleteAll(productlist);
//                                initShopCarNumber();

                                Intent intent = new Intent(PaymentCenterActivity.this,
                                        PayPassActivity.class);
                                intent.putExtra("orderInfo", orderInfo);
                                startActivity(intent);
                                finish();
                                overridePendingTransition(R.anim.tran_next_in,
                                        R.anim.tran_next_out);
                            } catch (DbException e) {
                            }
                        }


                    }
                }
            }
        });
        task.execute(0);
    }

    /**
     * 送货地址
     *
     * @param view
     */
    public void paymentAddress(View view) {
        Intent intent = new Intent();

//        intent.setClass(getApplicationContext(), AddressListActivity.class);
//        startActivityForResult(intent, 100);
//        overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (20 == resultCode) {
            addressInfo = (AddressInfo) data
                    .getSerializableExtra("addressInfo");
            if (addressInfo != null) {
                paymentName.setText(addressInfo.getName());
                paymentAddress.setText(addressInfo.getAddressArea() + " "
                        + addressInfo.getAreaDetail());
                paymentPhone.setText(addressInfo.getPhone());

                if (shipId != 0 && addressInfo != preAddressInfo) {
                    preAddressInfo = addressInfo;
                    mPresenter.getServiceFreight(addressInfo.getId(), shipId, productJsobList, 0);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
        if (GloableParams.SETDEFAULTADDRESS) {
            mPresenter.getServiceAddressList(1, 50);
            GloableParams.SETDEFAULTADDRESS = false;
        }
    }

    /**
     * 是否显示通用卷
     *
     * @param view
     */
    public void useCoupon(View view) {
        isShowCoupon = !isShowCoupon;
        if (isShowCoupon) {
            paymentLookProductCouponIV.setBackgroundResource(R.drawable.arrow);
            couponBottom_TV.setVisibility(View.VISIBLE);
            if (couponInfos != null) {
                if (payHintCouponAdapter == null) {
                    payHintCouponAdapter = new PaymentPayHintCouponAdapter(
                            this, couponInfos,
                            new PaymentPayHintCouponAdapterCallBack() {
                                @Override
                                public boolean onCheckSuccess(
                                        CouponInfo couponInfo) {
                                    if (couponInfo.getLimitPrice() > shouldPayMoney) {
                                        Builder builder = new Builder(
                                                TAG);
                                        builder.setMessage("您选中的优惠劵不满足最低消费金额")
                                                .setPositiveButton("确定", null)
                                                .create().show();
                                        paymentfavorablePriceTV.setText(MoneyUtils.getMoney(totoprice));

                                        // 设置新的应付金额
                                        shouldPayPriceTV.setText(MoneyUtils.getMoney(shouldPayMoney));
                                        text_allprice.setText(MoneyUtils.getMoney(shouldPayMoney));
                                        paymentBottomPriceTV.setText(MoneyUtils.getMoney(shouldPayMoney));
                                        currentUseCouponInfo = null;
                                        return false;
                                    }
                                    paymentfavorablePriceTV.setText(MoneyUtils.getMoney((couponInfo.getCouponPrice() + totoprice)));
                                    float shouldPayAllMoney = shouldPayMoney
                                            - couponInfo.getCouponPrice();
                                    if (shouldPayAllMoney < 0) {
                                        shouldPayAllMoney = 0;
                                    }
                                    shouldPayPriceTV.setText(MoneyUtils.getMoney(shouldPayAllMoney));
                                    text_allprice.setText(MoneyUtils.getMoney(shouldPayAllMoney));
                                    paymentBottomPriceTV.setText(MoneyUtils.getMoney(shouldPayAllMoney));
                                    currentUseCouponInfo = couponInfo;
                                    // 隐藏优惠劵列表
                                    hideCouponList();

                                    if (!isFavorable) {
                                        mPresenter.getServiceGifInfo(productJsobList, couponInfo.getCouponPrice());
                                    }

                                    return true;
                                }

                                @Override
                                public boolean onCheckCancel(
                                        CouponInfo couponInfo) {
                                    // 设置新的应付金额
                                    shouldPayPriceTV.setText(MoneyUtils.getMoney(shouldPayMoney));
                                    text_allprice.setText(MoneyUtils.getMoney(shouldPayMoney));
                                    paymentBottomPriceTV.setText(MoneyUtils.getMoney(shouldPayMoney));
                                    currentUseCouponInfo = null;

                                    if (!isFavorable) {
                                        mPresenter.getServiceGifInfo(productJsobList, 0);
                                    }
                                    return true;
                                }

                            });

                    couPonListview.setAdapter(payHintCouponAdapter);
                }
                couPonListview.setVisibility(View.VISIBLE);
            }

        } else {
            couPonListview.setVisibility(View.GONE);
            paymentLookProductCouponIV
                    .setBackgroundResource(R.drawable.arrow_right);
        }
    }

    /**
     * 隐藏优惠劵列表
     */
    void hideCouponList() {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        useCoupon(null);
                        break;
                    default:
                        break;
                }
            }
        };
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        }, 300);
    }

    /**
     * @param view
     */
    public void lookProductList(View view) {
        isShowProduct = !isShowProduct;
        if (isShowProduct) {
            productLV.setAdapter(new PaymentCenterAdapter(this, currentProductList));
            lookProductIV.setBackgroundResource(R.drawable.arrow);
            productLV.setVisibility(View.VISIBLE);
        } else {
            productLV.setVisibility(View.GONE);
            lookProductIV.setBackgroundResource(R.drawable.arrow_right);
        }
    }

    /**
     * 查看赠品列表
     *
     * @param view
     */
    public void lookGiftProductList(View view) {

        isShowGiftProduct = !isShowGiftProduct;
        if (isShowGiftProduct) {
            giftAdapter = new PaymentCenterAdapter(this, currentGiftProductList);
            giftproductLv.setAdapter(giftAdapter);
            giftproductLv.setVisibility(View.VISIBLE);
            giftproductBottom_TV.setVisibility(View.VISIBLE);

        } else {
            giftproductLv.setVisibility(View.GONE);
            lookGiftProductIV.setBackgroundResource(R.drawable.arrow_right);
        }
    }

    /**
     * 返回
     */
    public void goBack(View view) {
        finish();
        overridePendingTransition(R.anim.tran_pre_in, R.anim.tran_pre_out);
    }

    public void selectPaySendType(View v) {

        PaymentDialog dialog = new PaymentDialog();
        dialog.setLisener(new PaymentDialog.OnclickListener() {

            @Override
            public void payClick(int position) {
                PayShip payShip = PayShipList.get(position);
                payId = payShip.getId();
                content_paytype.setText(payShip.getName());
            }

            @Override
            public void sendClick(int position) {
                shipId = position;
            }
        });

        dialog.showDialog(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_payment_center;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        getProductListAndGifProduct();
    }

    @Override
    public void onBackToFirstFragment() {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void updateServiceTotalPrice(Float result) {
        if (result != null) {
            returnShouldPayAllPrice = result;
//					这里要初始化价格
            initAllPrice(freight);
            mPresenter.getServiceAddressList(1, 50);
        } else {
            PromptManager.showToast(getApplicationContext(), "服务器忙，请稍后重试！！！");
        }
    }

    @Override
    public void updateServiceFreight(Float result) {
        if (result != null) {
            freight = (Float) result;
//					这里要初始化价格
            initAllPrice(freight);
        }
    }

    @Override
    public void updateServiceGifInfo(GifInfo result) {

    }

    @Override
    public void updateServiceAddressList(List<AddressInfo> result) {
        if (result != null) {
            addressList = result;
            if (addressList.size() > 0) {
                initAddressData();

                for (int i = 0; i < addressList.size(); i++) {
                    if (addressList.get(i).isHasDefault()) {
                        addressInfo = addressList.get(i);
                    }
                }
                if (addressList.size() == 1) {
                    addressInfo = addressList.get(0);
                }
                if (addressInfo != null) {
                    paymentAddress.setText(addressInfo.getAddressArea() + " " + addressInfo.getAreaDetail());
                    paymentName.setText(addressInfo.getName() + "");
                    paymentPhone.setText(addressInfo.getPhone() + "");

                    preAddressInfo = addressInfo;
                    //获取运费
                    mPresenter.getServiceFreight(addressInfo.getId(), shipId, productJsobList, 0);
                }
            }
        }
        mPresenter.getServicePayList();//加载付款方式
    }

    @Override
    public void updateServicePayList(List<PayShip> result) {
        if (result != null) {
            //有返回东西 ,解析出来数据，设置给屏幕
            List<PayShip> PayShipList = (List<PayShip>) result;
            if (PayShipList != null && PayShipList.size() > 0) {
                initSpinnerPay(PayShipList);
            } else {
                PromptManager.showToast(getApplicationContext(), "服务器忙，请稍后重试！！！");
            }
        } else {
            PromptManager.showToast(getApplicationContext(), "服务器忙，请稍后重试！！！");
        }
    }

    @Override
    public void onBackPressedSupport() {
//        Intent intent=new Intent(this,MainActivity.class);
//        startActivity(intent);
    }
}
