package com.honliv.honlivmall.fragment.fourth.child;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.honliv.honlivmall.ConstantValue;
import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.activity.MainActivity;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.contract.FourthContract;
import com.honliv.honlivmall.model.fourth.child.FourthMainModel;
import com.honliv.honlivmall.presenter.fourth.child.FourthMainPresenter;
import com.honliv.honlivmall.util.DelayTask;
import com.honliv.honlivmall.util.LogUtil;
import com.honliv.honlivmall.util.MoneyUtils;
import com.honliv.honlivmall.util.Utils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/10/26.
 */
public class FourthMainFragment extends BaseFragment<FourthMainPresenter, FourthMainModel> implements View.OnClickListener, FourthContract.FourthMainView {
    @BindView(R.id.shopcar_product_list)
    ListView shopcar_product_list;
    @BindView(R.id.shopcar_null_text)
    TextView shopcar_null_text;
    @BindView(R.id.shopcar_toBuy_text)
    TextView shopcar_toBuy_text;
    @BindView(R.id.shopcar_allprice_text)
    TextView allProductPriceTV;
    @BindView(R.id.shopcar_body_layout)
    LinearLayout shopcarLayout;//有内容时显示的控件
    @BindView(R.id.shopcar_bottom_rel)
    RelativeLayout shopcarBottomRel;
    @BindView(R.id.shopcar_delete_text)
    TextView headDeleteTV;
    @BindView(R.id.shopcar_default_nullimg)
    ImageView shopcar_default_img;

    ShopCartAdapter productAdapter;
    boolean[] flags;
//    int userId;
    float allProductPrice;//所有商品价格，要在listview设置完后才有值
    ArrayList<Product> productlist = null;//购物车的商品列表

    public static FourthMainFragment newInstance() {
        Bundle args = new Bundle();
        FourthMainFragment fragment = new FourthMainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fourth_main;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        productlist=new ArrayList<>();
        productAdapter = new ShopCartAdapter();
        shopcar_product_list.setAdapter(productAdapter);
        shopcar_product_list.setOnItemClickListener(new ProductItemListener());
        shopcar_toBuy_text.setOnClickListener(this);
//        showToast(GloableParams.USERID+"--GloableParams.USERID");
//        mPresenter.getNativeAllShopCart(GloableParams.USERID);
        initShopCarNumber();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shopcar_toBuy_text:
                ((MainActivity)getActivity()).onBackToFirstFragment();
                break;
        }
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }


    public void onResume() {
        super.onResume();
//        mPresenter.getNativeAllShopCart(userId);
    }

    /**
     * 设置顶部的价格和积分
     */
    void initTotalPrice() {
        if (productlist.size() != 0) {
            allProductPrice = 0;
            allProductPrice = MoneyUtils.getAllPayMoney(productlist);
            allProductPriceTV.setText("￥" + MoneyUtils.get2StrWithDouble(allProductPrice) + "元");
        }
    }

    @Override
    public void initData() {

    }
    @Override
    public void updataAllShopCart(ArrayList<Product> result) {
        initShopCarNumber();
        if (result != null) {
//            productlist.addAll(result);

            if (productlist.size() == 0) {
                //没有数据
                shopcarLayout.setVisibility(View.GONE);
                shopcar_null_text.setVisibility(View.VISIBLE);
                shopcar_toBuy_text.setVisibility(View.VISIBLE);
                shopcar_default_img.setVisibility(View.VISIBLE);
                headDeleteTV.setVisibility(View.GONE);
                shopcarBottomRel.setVisibility(View.GONE);
            } else {
                shopcarLayout.setVisibility(View.VISIBLE);
                headDeleteTV.setVisibility(View.VISIBLE);
                shopcarBottomRel.setVisibility(View.VISIBLE);

                shopcar_null_text.setVisibility(View.GONE);
                shopcar_toBuy_text.setVisibility(View.GONE);
                shopcar_default_img.setVisibility(View.GONE);

                flags = new boolean[productlist.size()];
                for (int i = 0; i < flags.length; i++) {
                    flags[i] = true;
                }
                initShopCarNumber();//初始化底部小球的数量
                initTotalPrice();//初始化顶部的价格和积分
            }
        } else {
            shopcarLayout.setVisibility(View.GONE);
            shopcar_null_text.setVisibility(View.VISIBLE);
            shopcar_toBuy_text.setVisibility(View.VISIBLE);
            shopcar_default_img.setVisibility(View.VISIBLE);
            headDeleteTV.setVisibility(View.GONE);
            shopcarBottomRel.setVisibility(View.GONE);
        }
    }

    @Override
    public void updateStart() {

    }

    class ShopCartAdapter extends BaseAdapter {
        public int getCount() {
            showLog("----"+productlist.size());
            return productlist.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        ViewHolder holder = null;

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = null;
            if (convertView == null) {
                holder = new ViewHolder();
                view = View.inflate(getContext(), R.layout.shopping_car_listitem, null);
                holder.imgIcon = (ImageView) view.findViewById(R.id.shopcar_item_prodImage_img);
                holder.item_describe = (TextView) view
                        .findViewById(R.id.shopcar_item_prodName_text);

                holder.productNum = (TextView) view.findViewById(R.id.shopcar_item_prodCount_text);
                holder.productColor = (TextView) view.findViewById(R.id.shopcar_item_prodColor_text);
                holder.productSize = (TextView) view.findViewById(R.id.shopcar_item_prodSize_text);
                holder.productColorKey = (TextView) view.findViewById(R.id.shopcar_item_prodColor_key_text);
                holder.productSizeKey = (TextView) view.findViewById(R.id.shopcar_item_prodSize_key_text);
                holder.productPrice = (TextView) view.findViewById(R.id.shopcar_item_prodPrice_text);
                holder.prodImage_delete = (ImageView) view.findViewById(R.id.shopcar_item_prodImage_delete);

//				holder.prodImage_selete.setTag(position);
                view.setTag(holder);
            } else {
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }
            holder.item_describe.setText("" + productlist.get(position).getName());
            //int prodNum = cartes.get(position).getProductNum();
            holder.productNum.setText(productlist.get(position).getNumber() + "");
            if (productlist.get(position).getShopCarProductColor() == null) {
                holder.productColor.setText("无");
            } else {
                holder.productColorKey.setText(productlist.get(position).getShopCarColorKey() + "：");
                holder.productColor.setText(productlist.get(position).getShopCarProductColor());
            }
            if (productlist.get(position).getShopCarProductSize() == null) {
                holder.productSize.setText("无");
            } else {
                holder.productSizeKey.setText(productlist.get(position).getShopCarSizekey() + "：");
                holder.productSize.setText(productlist.get(position).getShopCarProductSize());
            }
            holder.productPrice.setText("￥" + productlist.get(position).getSaleprice());

            final TextView tvNum = holder.productNum;
            holder.productNum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showEditNumDialog(position, tvNum);
                }
            });
            holder.prodImage_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DbUtils db = DbUtils.create(getContext());
                    try {
                        db.delete(productlist.get(position));
                        productlist.remove(position);
                        productAdapter.notifyDataSetChanged();
                        if (productlist.size() == 0) {
                            shopcarLayout.setVisibility(View.GONE);
                            shopcar_null_text.setVisibility(View.VISIBLE);
                            shopcar_toBuy_text.setVisibility(View.VISIBLE);
                            shopcar_default_img.setVisibility(View.VISIBLE);
                            headDeleteTV.setVisibility(View.GONE);
                            shopcarBottomRel.setVisibility(View.GONE);
                        }

                        initShopCarNumber();
                        initTotalPrice();
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                }
            });
            Glide.with(mContext).load(Utils.checkImagUrl(Utils.checkImagUrl(productlist.get(position).getPic() + ""))).crossFade().placeholder(R.mipmap.picture_no).into(holder.imgIcon);
            return view;
        }
    }

    static class ViewHolder {
        TextView item_describe;//标题描述
        TextView productNum;//商品数量
        TextView productColor;//商品颜色
        TextView productSize;//商品尺码
        TextView productColorKey;//商品颜色前缀
        TextView productSizeKey;//商品尺码前缀
        TextView productPrice;//商品价格
        ImageView imgIcon;//图片
//		ImageView prodImage_selete;//前面选择图片

        ImageView prodImage_delete;//后面删除图片
    }

    protected void showEditNumDialog(int position, final TextView tvNum) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = View.inflate(getContext(), R.layout.dialog_enter_number, null);
        final AlertDialog dialog = builder.create();
        dialog.setView(view, 0, 0, 0, 0);
        final EditText productNumET = (EditText) view.findViewById(R.id.edit_productNum_ET);
        TextView productPriceTV = (TextView) view.findViewById(R.id.edit_productNum_price_TV);
        final TextView productNum = (TextView) view.findViewById(R.id.edit_productNum_Number_TV);
        final Button subNumBT = (Button) view.findViewById(R.id.edit_productNum_subNum_BT);
        final Button aaNumBT = (Button) view.findViewById(R.id.edit_productNum_addNum_BT);

        final Product selProduct = productlist.get(position);
        productPriceTV.setText("￥" + selProduct.getSaleprice());
        productNum.setText("x " + selProduct.getNumber());
        productNumET.setText(selProduct.getNumber() + "");
        Editable b = productNumET.getText();
        productNumET.setSelection(b.length());

        if (selProduct.getNumber() == 1) {
            subNumBT.setBackgroundResource(R.drawable.edit_product_num_des_no_enable);
            subNumBT.setClickable(false);
        }
        productNumET.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                String productNumStr = productNumET.getText().toString().trim();
                if ("".equals(productNumStr)) {
                    return;
                }
                int num = 1;
                try {
                    num = Integer.parseInt(s.toString());
                } catch (Exception e) {
                    num = 1;
                }
                if (num > selProduct.getStockcount()) {
                    showToast( "商品可售库存仅为(" + selProduct.getStockcount() + ")！");
                    productNumET.removeTextChangedListener(this);
                    productNumET.setText(selProduct.getStockcount() + "");
                    productNum.setText("x " + selProduct.getStockcount());
                    productNumET.addTextChangedListener(this);

                    aaNumBT.setBackgroundResource(R.drawable.edit_product_num_add_no_enable);
                    aaNumBT.setClickable(false);
                } else {
                    productNumET.removeTextChangedListener(this);
                    productNumET.setText(num + "");
                    productNum.setText("x " + num);
                    productNumET.addTextChangedListener(this);

                    aaNumBT.setBackgroundResource(R.drawable.button_add_number);
                    aaNumBT.setClickable(true);
                }
                Editable b = productNumET.getText();
                productNumET.setSelection(b.length());

                if (num == 1) {
                    subNumBT.setBackgroundResource(R.drawable.edit_product_num_des_no_enable);
                    subNumBT.setClickable(false);
                } else {
                    subNumBT.setBackgroundResource(R.drawable.button_sub_number);
                    subNumBT.setClickable(true);
                }

                if (num > 998) {
                    aaNumBT.setBackgroundResource(R.drawable.edit_product_num_add_no_enable);
                    aaNumBT.setClickable(false);
                    return;
                }
            }
        });
        subNumBT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {//减数量按钮
                String productNumStr = productNumET.getText().toString().trim();
                int num = 1;
                try {
                    num = Integer.parseInt(productNumStr);
                } catch (Exception e) {
                    num = 1;
                }

                num = num - 1;
                if (num == 0) {
                    showToast("亲,数量不能再减少了～");
                    return;
                }
                if (num == 1) {
                    subNumBT.setBackgroundResource(R.drawable.edit_product_num_des_no_enable);
                    subNumBT.setClickable(false);
                }
                productNumET.setText(num + "");
                productNum.setText("x " + num);
                Editable b = productNumET.getText();
                productNumET.setSelection(b.length());

                if (num < selProduct.getStockcount()) {
                    aaNumBT.setBackgroundResource(R.drawable.button_add_number);
                    aaNumBT.setClickable(true);
                }
            }
        });
        aaNumBT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {//增加数量按钮
                String productNumStr = productNumET.getText().toString().trim();
                int num = 1;
                try {
                    num = Integer.parseInt(productNumStr);
                } catch (Exception e) {
                    num = 1;
                }
                num = num + 1;
                if (num > selProduct.getStockcount()) {
                    showToast("商品可售库存仅为(" + selProduct.getStockcount() + ")");
                    aaNumBT.setBackgroundResource(R.drawable.edit_product_num_add_no_enable);
                    aaNumBT.setClickable(false);
                    return;
                }

                productNumET.setText(num + "");
                productNum.setText("x " + num);
                Editable b = productNumET.getText();
                productNumET.setSelection(b.length());

                if (num > 1) {
                    subNumBT.setBackgroundResource(R.drawable.button_sub_number);
                    subNumBT.setClickable(true);
                }
                if (num < selProduct.getStockcount()) {
                    aaNumBT.setBackgroundResource(R.drawable.button_add_number);
                    aaNumBT.setClickable(true);
                }

                if (num > 998) {
                    aaNumBT.setBackgroundResource(R.drawable.edit_product_num_add_no_enable);
                    aaNumBT.setClickable(false);
                    return;
                }
            }
        });

        view.findViewById(R.id.editproductNum_cancle_TV).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {//取消按钮
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.edit_productNum_ok_TV).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {//确定按钮
                String productNumStr = productNumET.getText().toString().trim();
                int num = 1;
                try {
                    num = Integer.parseInt(productNumStr);
                } catch (Exception e) {
                    num = 1;
                }

                selProduct.setNumber(num);
                DbUtils db = DbUtils.create(getContext());

                if (GloableParams.USERID > 0) {
                    selProduct.setUserId(GloableParams.USERID);
                } else {
                    selProduct.setUserId(-100);
                }
                try {
                    db.update(selProduct);
                } catch (DbException e) {
                    e.printStackTrace();
                }
                initTotalPrice();//初始化顶部的价格和积分
                initShopCarNumber();
                tvNum.setText(num + "");
                dialog.dismiss();
            }
        });
        dialog.show();

        //弹出输入框。
        timer.schedule(new TimerTask() {
            public void run() {
                InputMethodManager inputManager =
                        (InputMethodManager) productNumET.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.showSoftInput(productNumET, 0);
            }
        }, 150);
    }

    Timer timer = new Timer();


    class ProductItemListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, final int position,
                                long id) {
            int pId = productlist.get(position).getId();

//            Intent intent = new Intent(getContext(), ProductDetailActivity.class);
//            intent.putExtra("pId", pId);
//            GloableParams.PRODUCTID2 = pId;//为了第二次进商品详情有更新
//            startActivity(intent);
        }
    }

    /**
     * 长按点击事件
     *
     * @author Administrator
     */
    class ProductItemLongListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view,
                                       final int position, long id) {
            int index = position + 1;
            String url = ConstantValue.HOST + "/MShop/ShoppingCart/RemoveItem";
            String postDate = "ItemIds=" + index;

            //这是点击item向右移动删除的正确代码，可使用。
            TranslateAnimation ta = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, 0,
                    Animation.RELATIVE_TO_SELF, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0,
                    Animation.RELATIVE_TO_SELF, 0);
            ta.setDuration(800);
            view.startAnimation(ta);

            DelayTask task = new DelayTask() {
                @Override
                protected void runOnUiThread() {
                    for (int i = 0; i < flags.length; i++) {
                        flags[i] = false;
                    }
                    flags[position] = true;
                    deleteProduct();
                }
            };
            task.execute(800);
            return true;
        }
    }

    void showLoginDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {// 无法取消对话框
            public void onCancel(DialogInterface dialog) {
            }
        });
        builder.setTitle("需要登录");
        builder.setMessage("您确定要去登录吗？");
        builder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
//                        intent = new Intent(getContext(), LoginActivity.class);
//                        startActivity(intent);
                        GloableParams.toLoginActivity = getContext().getClass();
                    }
                });
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // loadHomeActivity();
//						LogUtil.info(" 回到界面");
                    }
                });
        builder.show();
    }

    /**
     * 去结算
     */
//    public void goSubmit(View view) {
//
//        if (GloableParams.USERID < 0) {
////			PromptManager.showToast(this, "登陆状态错误")
//            showLoginDialog();
//            return;
//        }
//
//        ArrayList<Product> TempCI = new ArrayList<Product>();
//        int shopNum = 0;
//        for (int i = 0; i < productlist.size(); i++) {
//
//            if (flags[i]) {
//                if (productlist.get(i).getNumber() > productlist.get(i).getStockcount()) {
//                    String proName = productlist.get(i).getName();
//                    PromptManager.showToast(getContext(), proName + "可售库存仅为(" + productlist.get(i).getStockcount() + ")！");
//                    return;
//                }
//                shopNum += productlist.get(i).getNumber();
//                TempCI.add(productlist.get(i));
//            }
//        }
//        if (TempCI.size() == 0) {
//            PromptManager.showToast(getContext(), "您还没有选择商品");
//            return;
//        }
//        int limitNum = sp.getInt("limitNum", 0);
//        if (shopNum < limitNum) {
//            PromptManager.showToast(getContext(), "您所购买的商品数量少于(" + limitNum + ")件,请增加数量再试！");
//            return;
//        }
////        intent = new Intent(getContext(), PaymentCenterActivity.class);
////        intent.putExtra("productlist", TempCI);
////        startActivity(intent);
//    }

    /**
     * 删除清空商品
     */
    public void deleteProduct(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {// 无法取消对话框
            public void onCancel(DialogInterface dialog) {
                //loadHomeActivity();// 取消对话框，进入主界面
                LogUtil.info(" 取消对话框");
            }
        });
        builder.setMessage("您确定要清空购物车？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                DbUtils db = DbUtils.create(getContext());
                try {
                    db.dropTable(Product.class);
                    shopcarLayout.setVisibility(View.GONE);
                    shopcar_null_text.setVisibility(View.VISIBLE);
                    shopcar_toBuy_text.setVisibility(View.VISIBLE);
                    shopcar_default_img.setVisibility(View.VISIBLE);
                    headDeleteTV.setVisibility(View.GONE);
                    shopcarBottomRel.setVisibility(View.GONE);
                } catch (DbException e) {

                    e.printStackTrace();
                }

                initShopCarNumber();
                initTotalPrice();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                LogUtil.info(" 回到界面");
            }
        });
        builder.show();
    }

    /**
     * 为了删除一个商品
     */
    public void deleteProduct() {
    }

    @Override
    public boolean onBackPressedSupport() {
        ((MainActivity)getActivity()).onBackToFirstFragment();
        return true;
    }
}
