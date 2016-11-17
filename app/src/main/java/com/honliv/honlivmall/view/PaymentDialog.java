package com.honliv.honlivmall.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.adapter.PaymentDialogAdapter;
import com.honliv.honlivmall.bean.PayShip;
import com.honliv.honlivmall.util.BeanFactory;

import java.util.List;

/**
 * Created by Rodin on 2016/11/6.
 */
public class PaymentDialog {
    private static final String TAG = "PaymentDialog";
    private static List<PayShip> payList;
    private static Activity mActivity;
    private static List<PayShip> ShipList;
    private View layout_send;
//    private MyListView gridview_sendtype;

    public void setLisener(OnclickListener lisener) {
        this.lisener = lisener;
    }

    private OnclickListener lisener;

    public static void initPayData(List<PayShip> payList) {
        PaymentDialog.payList = payList;
    }

    public interface OnclickListener {
        void payClick(int position);

        void sendClick(int position);
    }

    public void showDialog(final Activity mActivity) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        try {
            PaymentDialog.mActivity = mActivity;
            final Dialog dialog = new Dialog(mActivity, R.style.BottomDialog);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
            dialog.setContentView(R.layout.activity_dialog_paytype);
            dialog.setCanceledOnTouchOutside(true); // 外部点击取消
            // 设置宽度为屏宽, 靠近屏幕底部。
            final Window window = dialog.getWindow();
            window.setWindowAnimations(R.style.AnimBottom);
            final WindowManager.LayoutParams lp = window.getAttributes();
            lp.gravity = Gravity.BOTTOM; // 紧贴底部
            lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
            lp.height = mActivity.getWindowManager().getDefaultDisplay().getHeight() * 3 / 5;
            window.setAttributes(lp);
            MyListView gridview_paytype = (MyListView) dialog.findViewById(R.id.gridview_paytype);
            final PaymentDialogAdapter payAdapter = new PaymentDialogAdapter(mActivity, payList);

//            gridview_sendtype = (MyListView) dialog.findViewById(R.id.gridview_sendtype);
            layout_send = dialog.findViewById(R.id.layout_send);

            gridview_paytype.setAdapter(payAdapter);
            gridview_paytype.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int payId = payList.get(position).getId();
                    initShipSpinner(payId);
                    lisener.payClick(position);
                    List<View> viewList = payAdapter.getListView();
                    Log.i(TAG, "viewList.size()---" + viewList.size());
                    for (int i = 0; i < viewList.size(); i++) {
                        View item = viewList.get(i);
                        CheckBox radiobutton = (CheckBox) item.findViewById(R.id.radiobutton);
                        TextView content = (TextView) item.findViewById(R.id.content);
                        Log.i(TAG, "position---" + position + "--i--" + i);
//                        radiobutton.setChecked(true);
//                        content.setTextColor(mActivity.getResources().getColor(R.color.background_red));
                        if (i == position) {
                            radiobutton.setChecked(true);
                            content.setTextColor(mActivity.getResources().getColor(R.color.background_red));
                        } else {
                            radiobutton.setChecked(false);
                            content.setTextColor(mActivity.getResources().getColor(R.color.black));
                        }
                    }
                }
            });

            int payId = payList.get(0).getId();
            initShipSpinner(payId);

//            Button btn_confirm_pay = (Button) dialog.findViewById(R.id.btn_confirm_pay);
//            btn_confirm_pay.setRippleSpeed(500);
//            ImageView close_one = (ImageView) dialog.findViewById(R.id.close_one);
//            close_one.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                }
//            });
//
//            btn_confirm_pay.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                }
//            });
//            dialog.show();
        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
    }

    private void initShipSpinner(final int payId) {
        new AsyncTask<Integer, Void, Object>() {

            @Override
            protected Object doInBackground(Integer... params) {
//                AddressEngine engine = BeanFactory.getImpl(AddressEngine.class);
//                return engine.getShipList(payId);
                return null;
            }

            @Override
            protected void onPostExecute(Object result) {
                if (result != null) {
                    List<PayShip> ShipList = (List<PayShip>) result;
                    if (ShipList != null && ShipList.size() > 0) {
                        PaymentDialog.ShipList = ShipList;
                        initSpinnerShip(ShipList);
                    }
                }
            }
        }.execute(payId);
    }

    private void initSpinnerShip(List<PayShip> PayShipList) {
        int shipId = payList.get(0).getId();
        initShipSpinner(shipId);
        CheckBox radiobutton = (CheckBox) layout_send.findViewById(R.id.radiobutton);
        TextView content = (TextView) layout_send.findViewById(R.id.content);
        radiobutton.setChecked(true);
        content.setTextColor(mActivity.getResources().getColor(R.color.background_red));
    }
}
