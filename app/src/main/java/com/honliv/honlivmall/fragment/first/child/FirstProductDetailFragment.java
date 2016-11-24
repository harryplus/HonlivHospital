package com.honliv.honlivmall.fragment.first.child;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.Html;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.honliv.honlivmall.ConstantValue;
import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.activity.PaymentCenterActivity;
import com.honliv.honlivmall.adapter.ProductViewPagerAdapter;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.bean.ProductProperty;
import com.honliv.honlivmall.bean.SkuData;
import com.honliv.honlivmall.contract.FirstContract;
import com.honliv.honlivmall.listener.ProductTextWatcher;
import com.honliv.honlivmall.model.first.child.FirstProductDetailModel;
import com.honliv.honlivmall.presenter.first.child.FirstProductDetailPresenter;
import com.honliv.honlivmall.util.BuilderTools;
import com.honliv.honlivmall.util.MoneyUtils;
import com.honliv.honlivmall.util.ViewUtils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.rd.PageIndicatorView;

import org.apache.commons.lang3.StringUtils;
import org.xml.sax.XMLReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/10/29.
 */
public class FirstProductDetailFragment extends BaseFragment<FirstProductDetailPresenter, FirstProductDetailModel> implements SwipeRefreshLayout.OnRefreshListener, FirstContract.FirstProductDetailView, View.OnClickListener {
    @BindView(R.id.prodNumValue)
    EditText prodNumValue;
    @BindView(R.id.viewPager)
    ViewPager productViewPager;
    @BindView(R.id.productHtmlTV)
    TextView productHtmlTV; //产品的html富文本描述
    @BindView(R.id.ProductHtmlRL)
    RelativeLayout productHtmlRL;//产品的html富文本描述
    //    @BindView(R.id.textProductSize)
//    TextView textProductSize;
//    @BindView(R.id.clothesSizeValue)
//    TextView clothesSizeValueTV;//产品尺寸
//    @BindView(R.id.textProductColor)
//    TextView textProductColor;
//    @BindView(R.id.textProductColorValue)
//    TextView clothesColorValueTV;//产品颜色
    @BindView(R.id.textProductNameValue)
    TextView textProductNameValue;//商品名字
    @BindView(R.id.textMarketPriceValue)
    TextView textMarketPriceValue;//市场价
    @BindView(R.id.textPriceValue)
    TextView textPriceValue;//售出价
    @BindView(R.id.textProductCommentNum)
    TextView textProductCommentNum; //评论数
    //    @BindView(R.id.prod_property)
//    RelativeLayout prod_property;//商品属性控件一
//    @BindView(R.id.prod_property2)
//    RelativeLayout prod_property2;//商品属性控件二
    @BindView(R.id.num_layout)
    LinearLayout num_layout;
    @BindView(R.id.priceLayout)
    RelativeLayout priceLayout;
    @BindView(R.id.marketing_product_time)
    TextView marketingProductTimeTV;//倒记时
    //    @BindView(R.id.textProductProperty3Key)
//    TextView textProductProperty3Key; //三个四个属性选择
//    @BindView(R.id.textProductProperty3Value)
//    TextView textProductProperty3Value;
//    @BindView(R.id.textProductProperty4Key)
//    TextView textProductProperty4Key;
    @BindView(R.id.textPrice)
    TextView textPrice;
    @BindView(R.id.subNum_BT)
    Button subNumBT;
    @BindView(R.id.add_Num_BT)
    Button addNumBT;
    //    @BindView(R.id.textProductProperty4Value)
//    TextView textProductProperty4Value;
    @BindView(R.id.shopcar_prdtoFav_text)
    TextView addFavTV;
    @BindView(R.id.textPutIntoShopcar)
    TextView textPutIntoShopcar;  //加入购物车
    @BindView(R.id.head_goback)
    TextView head_goback;
    @BindView(R.id.shopcar_prdtobuy_text)
    TextView shopcar_prdtobuy_text;
    @BindView(R.id.pageIndicatorView)
    PageIndicatorView pageIndicatorView;

    boolean scanning;//扫描
    ArrayList<ImageView> images;
    ScheduledExecutorService scheduledExecutor;
    Product product;//展示的是本商品的详情
    static final String STATE_POSITION = "STATE_POSITION";
    int pagerPosition;
    ArrayList<String> productPropertyList;//商品属性的个数集合，颜色，尺码
    ArrayList<String> productProperty1;//商品属性，如颜色的具体
    ArrayList<String> productProperty2;//商品属性，如尺码的具体
    ArrayList<String> productProperty3;//商品属性，如尺码的具体
    ArrayList<String> productProperty4;//商品属性，如尺码的具体
    int userId;//登陆用户的id
    String[] imagevpes; // 顶部banner图片
    ArrayList<View> dots;
    int oldPosition = 0;// 上一次页面位置
    int currentItem;
    Spanned result;
    String htmlStr;
    int colorId = -1;
    int sizeId = -1;
    int key3Id = -1;
    MyPagerTask myPagerTask;
    int key4Id = -1;
    ArrayList<String> productImgs;//传送到下一个界面的图片url集合
    String[] clothesSizeValues;
    String[] clothesColorValues;
    String[] prodPropertyValues3;
    String[] prodPropertyValues4;
    int pId;
    Product privilegeProduct;//团购，抢购界面传送过来的商品
    Timer timer;
    TimerTask task;

    public static FirstProductDetailFragment newInstance(Bundle args) {
        FirstProductDetailFragment instance = new FirstProductDetailFragment();
        instance.setArguments(args);
        return instance;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_first_product_detail;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        textPutIntoShopcar.setVisibility(View.VISIBLE);
        textPrice.setVisibility(View.VISIBLE);
        textMarketPriceValue.setVisibility(View.VISIBLE);
        priceLayout.setVisibility(View.VISIBLE);
        num_layout.setVisibility(View.VISIBLE);
        textPriceValue.setTextSize(16);
        head_goback.setOnClickListener(this);
        pageIndicatorView.setViewPager(productViewPager);
        addNumBT.setOnClickListener(this);
        subNumBT.setOnClickListener(this);
        addFavTV.setOnClickListener(this);
        textPutIntoShopcar.setOnClickListener(this);
        shopcar_prdtobuy_text.setOnClickListener(this);
    }

    @Override
    public void onRefresh() {

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
        Bundle bundle = getArguments();
        pId = bundle.getInt("pId", -1);
        scanning = bundle.getBoolean("scanning", false);
        privilegeProduct = (Product) bundle.getSerializable("privilegeProduct");
//        pId = this.getIntent().getIntExtra("pId", -1);
//        scanning = getIntent().getBooleanExtra("scanning", false);

//        privilegeProduct = (Product) this.getIntent().getSerializableExtra("privilegeProduct");
//            pId = 4;
//        showToast(pId+"-----");
        if (pId == -1) {
            showToast("获取商品id失败");
            return;
        }
        mPresenter.getServiceProductDetail(pId);
    }

    @Override
    public void onResume() {
        super.onResume();
        initTimer();
        if (GloableParams.PRODUCTID2 <= 0 || GloableParams.PRODUCTID2 == pId) {
            return;
        }
        mPresenter.getServiceProductDetail(GloableParams.PRODUCTID2);
        GloableParams.PRODUCTID2 = 0;

        initShopCarNumber();
    }

    void nDate() {
        // TODO Auto-generated method stub
        if (privilegeProduct != null) {//团购限时购买过来的
            textPutIntoShopcar.setVisibility(View.GONE);

            product.setMarketprice(privilegeProduct.getMarketprice() + "");
            product.setSaleprice(privilegeProduct.getSaleprice() + "");

            marketingProductTimeTV.setVisibility(View.VISIBLE);
            initTimer();

        }
        if (product.isAddFav()) {
            addFavTV.setClickable(false);
            addFavTV.setBackgroundResource(R.drawable.product_detail_shop_noselected);
        }


        initProductEditNumListener();
        textMarketPriceValue.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线
        textMarketPriceValue.getPaint().setAntiAlias(true);// 抗锯齿
        textProductNameValue.setText(product.getName());
        textProductCommentNum.setText(product.getCommentCount() + "人");

        if (product.getStockcount() > 0 || privilegeProduct != null) {//当有库存或者为抢购时
            textMarketPriceValue.setText("￥" + product.getMarketprice());
            textPriceValue.setText("￥" + product.getSaleprice());
        } else {
            textPrice.setVisibility(View.GONE);
            textMarketPriceValue.setVisibility(View.GONE);
            textPriceValue.setText(getResources().getString(R.string.commodity_null));
            textPriceValue.setTextSize(20);
            priceLayout.setVisibility(View.GONE);
            num_layout.setVisibility(View.GONE);
        }

        if (product.getXmltext() != null) {
            productHtmlRL.setVisibility(View.VISIBLE);
            initproductHtmlTV();
        } else {
            productHtmlRL.setVisibility(View.GONE);
            productHtmlTV.setVisibility(View.GONE);
        }
        SharedPreferences.Editor editor = sp.edit();//初始化
        editor.putInt("clothesColorWhich", 0);
        editor.putInt("clothesSizeWhich", 0);
        editor.putInt("prodPropertyValues3", 0);
        editor.putInt("prodPropertyValues4", 0);
        editor.commit();

        if (product.getProductProperty() == null) {
//            prod_property.setVisibility(View.GONE);
//            prod_property2.setVisibility(View.GONE);
            clothesColorValues = new String[0];
        } else {
            productProperty1 = new ArrayList<String>();
            productProperty2 = new ArrayList<String>();
            productProperty3 = new ArrayList<String>();
            productProperty4 = new ArrayList<String>();
            productPropertyList = new ArrayList<String>();

            List<ProductProperty> productPropertyes = product.getProductProperty();
            for (int i = 0; i < productPropertyes.size(); i++) {
                if (!productPropertyList.contains(productPropertyes.get(i).getKey())) {
                    productPropertyList.add(productPropertyes.get(i).getKey());
                }
            }
            for (int i = 0; i < productPropertyes.size(); i++) {
                if (productPropertyList.get(0).equals(productPropertyes.get(i).getKey())) {
                    productProperty1.add(productPropertyes.get(i).getValue());
                }
                if (productPropertyList.size() > 1 && productPropertyList.get(1).equals(productPropertyes.get(i).getKey())) {
                    productProperty2.add(productPropertyes.get(i).getValue());
                }
                if (productPropertyList.size() > 2 && productPropertyList.get(2).equals(productPropertyes.get(i).getKey())) {
                    productProperty3.add(productPropertyes.get(i).getValue());
                }
                if (productPropertyList.size() > 3 && productPropertyList.get(3).equals(productPropertyes.get(i).getKey())) {
                    productProperty4.add(productPropertyes.get(i).getValue());
                }
            }
            clothesColorValues = productProperty1.toArray(new String[productProperty1.size()]);            //al.toArray(new String[al.size()]);
//            textProductColor.setText("" + productPropertyList.get(0));
//            clothesColorValueTV.setText(clothesColorValues[sp.getInt("clothesColorWhich", 0)]);

//            if (productPropertyList.size() == 1) {
//                //只有一个选择
////                clothesSizeValueTV.setVisibility(View.GONE);
////                textProductSize.setVisibility(View.GONE);
//            }
            if (productPropertyList.size() > 1) {//第二个属性选择
                clothesSizeValues = productProperty2.toArray(new String[productProperty2.size()]);
//                textProductSize.setText("" + productPropertyList.get(1));
//                clothesSizeValueTV.setText(clothesSizeValues[sp.getInt("clothesSizeWhich", 0)]);
            }
            if (productPropertyList.size() > 2) {//第三个属性选择
//                prod_property2.setVisibility(View.VISIBLE);
                prodPropertyValues3 = productProperty3.toArray(new String[productProperty3.size()]);
//                textProductProperty3Key.setText("" + productPropertyList.get(2));
//                textProductProperty3Value.setText(prodPropertyValues3[sp.getInt("prodPropertyValues3", 0)]);
            }
            if (productPropertyList.size() > 3) {//第四个属性选择
//                textProductProperty4Key.setVisibility(View.VISIBLE);
//                textProductProperty4Value.setVisibility(View.VISIBLE);
                prodPropertyValues4 = productProperty4.toArray(new String[productProperty4.size()]);
//                textProductProperty4Key.setText("" + productPropertyList.get(3));
//                textProductProperty4Value.setText(prodPropertyValues4[sp.getInt("prodPropertyValues4", 0)]);
            }
        }
        List<String> bigPices = new ArrayList<String>();
        bigPices.addAll(product.getBigPic());
        imagevpes = new String[bigPices.size()];
        for (int i = 0; i < bigPices.size(); i++) {
            imagevpes[i] = ConstantValue.IMAGE_URL + bigPices.get(i);
        }

        ProductViewPagerAdapter productViewAdapter = new ProductViewPagerAdapter(getContext(), imagevpes);
        productViewPager.setAdapter(productViewAdapter);
        productViewPager.setCurrentItem(pagerPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // 保存用户数据
        outState.putInt(STATE_POSITION, productViewPager.getCurrentItem());
    }

    void initproductHtmlTV() {
        htmlStr = product.getXmltext();
        htmlStr = htmlStr.replaceAll("/Upload", ConstantValue.IMAGE_URL + "/Upload");
        htmlStr = htmlStr.replaceAll("/ueditor/net/upload", ConstantValue.IMAGE_URL + "/ueditor/net/upload");
        new Thread() {
            public void run() {
                result = Html.fromHtml(htmlStr, imgGetter, tagHandler);//imgGetter  imageGetter
                Message msg = Message.obtain();
                msg.what = 20;
                xmlHandler.sendMessage(msg);
            }

        }.start();
    }

    Handler xmlHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 20) {
                productHtmlTV.setMovementMethod(LinkMovementMethod.getInstance());
                productHtmlTV.setText(result);
            }
        }
    };

    //处理未知标签,通常是系统默认不能处理的标签
    final Html.TagHandler tagHandler = new Html.TagHandler() {
        int contentIndex = 0;

        /**
         * opening : 是否为开始标签
         * tag: 标签名称
         * output:输出信息，用来保存处理后的信息
         * xmlReader: 读取当前标签的信息，如属性等
         */
        public void handleTag(boolean opening, String tag, Editable output,
                              XMLReader xmlReader) {
            if ("mytag".equals(tag)) {
                if (opening) {//获取当前标签的内容开始位置
                    contentIndex = output.length();
                    try {
                        final String color = (String) xmlReader.getProperty("color");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    final int length = output.length();
                    String content = output.subSequence(contentIndex, length).toString();
                    SpannableString spanStr = new SpannableString(content);
                    spanStr.setSpan(new ForegroundColorSpan(Color.GREEN), 0, content.length(),
                            Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                    output.replace(contentIndex, length, spanStr);
                }
            }
            System.out.println("opening:" + opening + ",tag:" + tag + ",output:" + output);
        }
    };


    Html.ImageGetter imgGetter = new Html.ImageGetter() {
        public Drawable getDrawable(String source) {
            Drawable drawable = null;
            URL url;
            try {
                url = new URL(source);   //获取网路图片
                drawable = Drawable.createFromStream(url.openStream(), "");
            } catch (Exception e) {
                return null;
            }
            if (drawable == null) { ///如果url没有图片资源，返回
                return drawable;
            }

            int width = drawable.getIntrinsicWidth() * 6;
            int height = drawable.getIntrinsicHeight() * 6;

            if (width > GloableParams.WINDOW_WIDTH) {
                height = (GloableParams.WINDOW_WIDTH * height) / width;
                width = GloableParams.WINDOW_WIDTH;
            }
            drawable.setBounds(0, 0, width - 36, height);
            return drawable;
        }
    };


    boolean CheckNum(DbUtils db, int productNumber) throws DbException {
        Product dbproduct = null;

        dbproduct = db.findFirst(Selector.from(Product.class).where("openSkuStr", "=", product.getOpenSkuStr())/*.and("id","=",product.getId())*/.and("userId", "=", userId));
        if (dbproduct != null) {

            if ((productNumber + dbproduct.getNumber()) > product.getStockcount()) {
                showToast("商品可售库存仅为(" + product.getStockcount() + ")！");

                dbproduct.setNumber(product.getStockcount());
                db.update(dbproduct);
                return true;
            }
            dbproduct.setNumber(productNumber + dbproduct.getNumber());
            //	db.update(dbproduct,"number");
            db.update(dbproduct);
        } else {
            if (productNumber > product.getStockcount()) {
                showToast("商品可售库存仅为(" + product.getStockcount() + ")！");
                return true;
            }
            product.setNumber(productNumber);
            db.save(product);
        }
        return false;
    }

    boolean IsAvailable() {
        colorId = -1;
        sizeId = -1;
        key3Id = -1;
        key4Id = -1;
        if (productPropertyList != null && productPropertyList.size() > 0) {//有sku
            if (productPropertyList.size() == 1) {//有一个属性选择
                if (clothesColorValues.length > 0) { //至少有一个属性选择
                    String color = clothesColorValues[sp.getInt("clothesColorWhich", 0)];
                    product.setShopCarProductColor(color);
                    product.setShopCarColorKey(productPropertyList.get(0));
                }
                List<ProductProperty> productPropertyes = product.getProductProperty();
                for (int i = 0; i < productPropertyes.size(); i++) {
                    if (product.getShopCarProductColor().equals(productPropertyes.get(i).getValue())) {
                        colorId = productPropertyes.get(i).getId();
                        break;
                    }
                }
                SkuData skuData = product.getSkuData().get(colorId + "");
                if (skuData != null) {
                    product.setOpenSkuStr(skuData.getSku() + "");
                    product.setStockcount(skuData.getCount());
                    product.setSaleprice(MoneyUtils.get2StrWithDouble(skuData.getPrice()));
                } else {
                    return true;
                }
            } else if (productPropertyList.size() == 2) {//有二个属性选择
                if (clothesColorValues.length > 0) { //至少有一个属性选择
                    String color = clothesColorValues[sp.getInt("clothesColorWhich", 0)];
                    product.setShopCarProductColor(color);
                    product.setShopCarColorKey(productPropertyList.get(0));
                }
                List<ProductProperty> productPropertyes = product.getProductProperty();
                for (int i = 0; i < productPropertyes.size(); i++) {
                    if (product.getShopCarProductColor().equals(productPropertyes.get(i).getValue())) {
                        colorId = productPropertyes.get(i).getId();
                        break;
                    }
                }
                if (clothesSizeValues.length > 0) { //有二个属性选择
                    String size = clothesSizeValues[sp.getInt("clothesSizeWhich", 0)];
                    product.setShopCarProductSize(size);
                    product.setShopCarSizekey(productPropertyList.get(1));
                    for (int i = 0; i < productPropertyes.size(); i++) {
                        if (size.equals(productPropertyes.get(i).getValue())) {
                            sizeId = productPropertyes.get(i).getId();
                            break;
                        }
                    }
                }

                SkuData skuData = product.getSkuData().get(colorId + "," + sizeId);
                if (skuData != null) {
                    product.setOpenSkuStr(skuData.getSku() + "");
                    product.setStockcount(skuData.getCount());
                    product.setSaleprice(MoneyUtils.get2StrWithDouble(skuData.getPrice()));
                } else {
                    return true;
                }
            } else if (productPropertyList.size() == 3) {//有三个属性选择
                if (clothesColorValues.length > 0) { //至少有一个属性选择
                    String color = clothesColorValues[sp.getInt("clothesColorWhich", 0)];
                    product.setShopCarProductColor(color);
                    product.setShopCarColorKey(productPropertyList.get(0));
                }
                List<ProductProperty> productPropertyes = product.getProductProperty();
                for (int i = 0; i < productPropertyes.size(); i++) {
                    if (product.getShopCarProductColor().equals(productPropertyes.get(i).getValue())) {
                        colorId = productPropertyes.get(i).getId();
                        break;
                    }
                }
                if (clothesSizeValues.length > 0) { //有二个属性选择
                    String size = clothesSizeValues[sp.getInt("clothesSizeWhich", 0)];
                    product.setShopCarProductSize(size);
                    product.setShopCarSizekey(productPropertyList.get(1));
                    for (int i = 0; i < productPropertyes.size(); i++) {
                        if (size.equals(productPropertyes.get(i).getValue())) {
                            sizeId = productPropertyes.get(i).getId();
                            break;
                        }
                    }
                }
                if (prodPropertyValues3.length > 0) { //有三个属性选择
                    String value3 = clothesSizeValues[sp.getInt("prodPropertyValues3", 0)];
                    product.setShopCarValue3(value3);
                    product.setShopCarKey3(productPropertyList.get(2));
                    for (int i = 0; i < productPropertyes.size(); i++) {
                        if (value3.equals(productPropertyes.get(i).getValue())) {
                            key3Id = productPropertyes.get(i).getId();
                            break;
                        }
                    }
                }
                SkuData skuData = product.getSkuData().get(colorId + "," + sizeId + "," + key3Id);
                if (skuData != null) {
                    product.setOpenSkuStr(skuData.getSku() + "");
                    product.setStockcount(skuData.getCount());
                    product.setSaleprice(MoneyUtils.get2StrWithDouble(skuData.getPrice()));
                } else {
                    return true;
                }
            } else if (productPropertyList.size() == 4) {//有四个属性选择
                if (clothesColorValues.length > 0) { //至少有一个属性选择
                    String color = clothesColorValues[sp.getInt("clothesColorWhich", 0)];
                    product.setShopCarProductColor(color);
                    product.setShopCarColorKey(productPropertyList.get(0));
                }
                List<ProductProperty> productPropertyes = product.getProductProperty();
                for (int i = 0; i < productPropertyes.size(); i++) {
                    if (product.getShopCarProductColor().equals(productPropertyes.get(i).getValue())) {
                        colorId = productPropertyes.get(i).getId();
                        break;
                    }
                }
                if (clothesSizeValues.length > 0) { //有二个属性选择
                    String size = clothesSizeValues[sp.getInt("clothesSizeWhich", 0)];
                    product.setShopCarProductSize(size);
                    product.setShopCarSizekey(productPropertyList.get(1));
                    for (int i = 0; i < productPropertyes.size(); i++) {
                        if (size.equals(productPropertyes.get(i).getValue())) {
                            sizeId = productPropertyes.get(i).getId();
                            break;
                        }
                    }
                }
                if (prodPropertyValues3.length > 0) { //有三个属性选择
                    String value3 = prodPropertyValues3[sp.getInt("prodPropertyValues3", 0)];
                    product.setShopCarValue3(value3);
                    product.setShopCarKey3(productPropertyList.get(2));
                    for (int i = 0; i < productPropertyes.size(); i++) {
                        if (value3.equals(productPropertyes.get(i).getValue())) {
                            key3Id = productPropertyes.get(i).getId();
                            break;
                        }
                    }
                }
                if (prodPropertyValues4.length > 0) { //有四个属性选择
                    String value4 = prodPropertyValues4[sp.getInt("prodPropertyValues4", 0)];
                    product.setShopCarValue4(value4);
                    product.setShopCarKey4(productPropertyList.get(3));
                    for (int i = 0; i < productPropertyes.size(); i++) {
                        if (value4.equals(productPropertyes.get(i).getValue())) {
                            key4Id = productPropertyes.get(i).getId();
                            break;
                        }
                    }
                }
                SkuData skuData = product.getSkuData().get(colorId + "," + sizeId + "," + key3Id + "," + key4Id);
                if (skuData != null) {
                    product.setOpenSkuStr(skuData.getSku() + "");
                    product.setStockcount(skuData.getCount());
                    product.setSaleprice(MoneyUtils.get2StrWithDouble(skuData.getPrice()));
                } else {
                    return true;
                }
            }
        } else {//没有sku选择的情况，库存自动返回
            product.setOpenSkuStr(product.getSku() + "");
        }
        return false;
    }

    @Override
    public void updateView(Product result) {
        if (result != null) {
            product = (Product) result;
            if (product != null) {
                //有返回东西 ,解析出来数据，设置给屏幕
                nDate();
            }
        } else {
            showToast("服务器忙，请稍后重试！！！");
        }
    }

    @Override
    public void updateAddProductFavView(String result) {
        if (result != null) {
            String isSuccess = (String) result;
            if ("success".equals(isSuccess)) {
                showToast("收藏成功");
                addFavTV.setClickable(false);
                addFavTV.setBackgroundResource(R.drawable.product_detail_shop_noselected);
            } else if ("hasFav".equals(isSuccess)) {
                showToast("亲,商品已收藏");
                addFavTV.setBackgroundResource(R.drawable.product_detail_shop_noselected);
                addFavTV.setClickable(false);
            } else {
                showToast("收藏失败");
            }
        } else {
            showToast("服务器忙，请稍后重试！！！");
        }
    }

    @Override
    public void updateStartView(Product arg) {
//        showToast("卡埃看    打回电话");
        showToast(arg.getName() + "------");
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        DbUtils db = DbUtils.create(getContext());
        String prodNumValueStr;
        int productNumber;
        switch (v.getId()) {
            case R.id.head_goback:
                pop();
                break;
            case R.id.add_Num_BT: {
                if (product == null) {
                    return;
                }

                prodNumValueStr = prodNumValue.getText().toString().trim();
                try {
                    Integer.parseInt(prodNumValueStr);
                } catch (Exception e) {
                    showToast("输入框字符非法");
                    return;
                }

                if (StringUtils.isNumericSpace(prodNumValueStr) && prodNumValueStr != null) {//判断字符是数字
                    productNumber = Integer.parseInt(prodNumValueStr);

                    productNumber = productNumber + 1;

                    if (privilegeProduct != null && privilegeProduct.getLimitQty() != 0) {
                        if (productNumber > privilegeProduct.getLimitQty()) {
                            showToast("限购数量为(" + privilegeProduct.getLimitQty() + ")");
                            return;
                        }
                    }

                    prodNumValue.setText(productNumber + "");
                    Editable etext = prodNumValue.getText();
                    int position = etext.length();
                    Selection.setSelection(etext, position);
                }

            }
            break;
            case R.id.subNum_BT:
                if (product == null) {
                    return;
                }
                prodNumValueStr = prodNumValue.getText().toString().trim();

                try {
                    Integer.parseInt(prodNumValueStr);
                } catch (Exception e) {
                    showToast("输入框字符非法");
                    return;
                }

                if (StringUtils.isNumericSpace(prodNumValueStr) && prodNumValueStr != null) {//判断字符是数字
                    productNumber = Integer.parseInt(prodNumValueStr);

                    if (productNumber <= 1) {
                        showToast("亲，数量不能再减了");
                        return;
                    } else {
                        productNumber = productNumber - 1;
                        prodNumValue.setText(productNumber + "");
                        Editable etext = prodNumValue.getText();
                        int position = etext.length();
                        Selection.setSelection(etext, position);
                    }
                }
                break;
            case R.id.shopcar_prdtoFav_text:
                if (product == null) {
                    showToast("没有商品信息");
                    return;
                }

                if (GloableParams.USERID < 0) {
                    BuilderTools.showLoginDialog(getContext(), getPreFragment(), "需要登录");
                } else {
                    mPresenter.addProductFav(product.getId());
                }
                break;
            case R.id.textPutIntoShopcar:
                if (product == null) {
                    return;
                }

                //拿到数量
                prodNumValueStr = prodNumValue.getText().toString().trim();
                productNumber = 1;
                try {
                    Integer.parseInt(prodNumValueStr);
                } catch (Exception e) {
                    showToast("输入框字符非法");
                    return;
                }
                if (StringUtils.isNumericSpace(prodNumValueStr) && prodNumValueStr != null) {//判断字符是数字
                    productNumber = Integer.parseInt(prodNumValueStr);
                }

                userId = GloableParams.USERID;
                if (userId < 0) {
                    userId = -100;
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putInt("isLogin", userId);
                    edit.commit();
                }
                product.setUserId(userId);


                if (IsAvailable()) {
                    showToast("本规格没有库存,请更换规格购买");
                    return;
                }

                try {
                    if (CheckNum(db, productNumber)) return;
                    showToast("商品成功加入购物车");
                } catch (DbException e) {
                    e.printStackTrace();
                    try {
                        db.dropTable(Product.class);
                    } catch (DbException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    showToast("加入购物车失败，请重试!");
                } finally {
                    initShopCarNumber();//初始化底部球的数量
                }
                break;

            case R.id.shopcar_prdtobuy_text:
                if (GloableParams.USERID < 0) {
                    BuilderTools.showLoginDialog(getContext(), getPreFragment(), "需要登录");
                    return;
                }

                if (product == null) {
                    return;
                }
                //拿到数量
                prodNumValueStr = prodNumValue.getText().toString().trim();
                productNumber = 1;
                try {
                    Integer.parseInt(prodNumValueStr);
                } catch (Exception e) {
                    showToast("输入框字符非法");
                    return;
                }
                if (StringUtils.isNumericSpace(prodNumValueStr) && prodNumValueStr != null) {//判断字符是数字
                    productNumber = Integer.parseInt(prodNumValueStr);
                }

                userId = GloableParams.USERID;

                if (userId < 0) {
                    userId = -100;
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putInt("isLogin", userId);
                    edit.commit();
                }
                product.setUserId(userId);

                if (privilegeProduct != null) {//购物和抢购过来的
                    if (privilegeProduct.getCountDownId() > 0) {
                        product.setCountDownId(privilegeProduct.getCountDownId());
                    }
                    if (privilegeProduct.getGroupBuyid() > 0) {
                        product.setGroupBuyid(privilegeProduct.getGroupBuyid());
                    }
                    if (privilegeProduct.getLimitQty() != 0 && productNumber > privilegeProduct.getLimitQty()) {
                        showToast("限购数量为(" + privilegeProduct.getLimitQty() + ")");
                        prodNumValue.setText(privilegeProduct.getLimitQty() + "");
                        addNumBT.setBackgroundResource(R.drawable.edit_product_num_add_no_enable);
                        addNumBT.setClickable(false);
                        return;
                    }
                }

                if (IsAvailable()) {
                    showToast("本规格没有库存,请更换规格购买");
                    return;
                }

                try {
                    if (CheckNum(db, productNumber)) return;

                    Intent intent = new Intent();
                    ArrayList<Product> pds = new ArrayList<Product>();
                    product.setNumber(productNumber);

                    pds.add(product);
                    intent.setClass(getContext(), PaymentCenterActivity.class);
                    Log.i(TAG, pds.toString());
                    intent.putExtra("productlist", pds);
                    startActivity(intent);
                } catch (DbException e) {
                    e.printStackTrace();
                    try {
                        db.dropTable(Product.class);
                    } catch (DbException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    showToast(getResources().getString(R.string.buy_fail));
                }
                break;
        }
    }


    class MyPagerTask implements Runnable {
        public void run() {
            currentItem++;
            handler.sendEmptyMessage(0);
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            productViewPager.setCurrentItem(currentItem);
        }
    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        scheduledExecutor.shutdown();
    }


    @Override
    public void onStart() {
        super.onStart();
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

        if (myPagerTask == null) {
            myPagerTask = new MyPagerTask();
        }
        scheduledExecutor.scheduleAtFixedRate(myPagerTask, 6, 6,
                TimeUnit.SECONDS);
    }


    void initProductEditNumListener() {
        ProductTextWatcher watcher = new ProductTextWatcher(getContext(), prodNumValue, privilegeProduct, addNumBT, subNumBT);
        prodNumValue.addTextChangedListener(watcher);
    }

    Handler timeHandler = new Handler() {

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int resultNum = msg.what;

            if (resultNum == 20) {  // products.size()

                String dateStr = privilegeProduct.getLefttime();
                if (marketingProductTimeTV != null) {
                    ViewUtils.updataTimeTV(marketingProductTimeTV, dateStr);
                }
            }
        }
    };

    void initTimer() {
        if (privilegeProduct == null) {
            return;
        }

        timer = new Timer();

        task = new TimerTask() {
            public void run() {
                Message message = Message.obtain();
                message.what = 20;
                timeHandler.sendMessage(message);
            }
        };
        timer.schedule(task, 1000, 1000);
    }

    @Override
    public void onDestroy() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        super.onDestroy();
    }
}
