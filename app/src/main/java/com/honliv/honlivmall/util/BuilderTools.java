package com.honliv.honlivmall.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.activity.MainActivity;
import com.honliv.honlivmall.activity.PayPassActivity;
import com.honliv.honlivmall.bean.OrderInfo;
import com.honliv.honlivmall.fragment.fifth.child.FifthMyOrderFragment;
import com.honliv.honlivmall.fragment.global.GlobalLoginFragment;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Rodin on 2016/11/4.
 */
public class BuilderTools {


    public static void showToPayDialog(final Context mContext, final OrderInfo orderInfo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {// 无法取消对话框
            public void onCancel(DialogInterface dialog) {
            }
        });
        builder.setTitle("支付确定");
        builder.setMessage("您确定要去支付吗？");
        builder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(mContext, PayPassActivity.class);
                        intent.putExtra("orderInfo", orderInfo);
                        mContext.startActivity(intent);
                    }
                });
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        builder.show();
    }

    public static void showSurePayDialog(final Context mContext, final OrderInfo orderInfo, final String cardNo, final String et_pwdText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {// 无法取消对话框
            public void onCancel(DialogInterface dialog) {
                // loadHomeActivity();// 取消对话框，进入主界面
//				LogUtil.info(" 取消对话框");
            }
        });
        builder.setTitle("支付确定");
        builder.setMessage("您确定要去支付吗？");
        builder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
//                        HXTPayTask task = new HXTPayTask(mContext, orderInfo);
//                        task.execute(cardNo, et_pwdText);
                    }
                });
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        builder.show();
    }

    public static void showLoginDialog(final Context mContext, SupportFragment fragment, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {// 无法取消对话框
            public void onCancel(DialogInterface dialog) {
            }
        });
        builder.setTitle(title);
        builder.setMessage("您确定要去登录吗？");
        builder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Bundle data = new Bundle();
                        data.putInt("position", MainActivity.FIRST);
                        data.putSerializable("fragment", fragment.getClass());
                        GlobalLoginFragment loginFragment = GlobalLoginFragment.newInstance(data);
                        fragment.start(loginFragment);
                    }
                });
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        builder.show();
    }
}
