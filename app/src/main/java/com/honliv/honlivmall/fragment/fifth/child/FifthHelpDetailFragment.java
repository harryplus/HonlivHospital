package com.honliv.honlivmall.fragment.fifth.child;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.honliv.honlivmall.ConstantValue;
import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.Help;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.model.fifth.child.FifthHelpDetailModel;
import com.honliv.honlivmall.presenter.fifth.child.FifthHelpDetailPresenter;
import com.honliv.honlivmall.util.LogUtil;

import org.xml.sax.XMLReader;

import java.net.URL;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthHelpDetailFragment extends BaseFragment<FifthHelpDetailPresenter, FifthHelpDetailModel> implements FifthContract.FifthHelpDetailView, View.OnClickListener {
    int helpId;
    Help helpDetailDates;
    @BindView(R.id.help_detail_tv)
    TextView help_detailtitle_tv;
    @BindView(R.id.textDetail_tv)
    TextView textDetail_tv;

    Spanned result;
    String htmlStr;

    public static FifthHelpDetailFragment newInstance() {
        Bundle args = new Bundle();

        FifthHelpDetailFragment fragment = new FifthHelpDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fifth_helpdetail;
    }

    /**
     * 初始化控件
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        //        helpId = this.getIntent().getIntExtra("helpId", -1);
        if (helpId == -1) {
            showToast("获取失败，请您联系客服！");
            return;
        }
    }

    @Override
    public void initData() {
        mPresenter.getServiceHelpList(helpId);
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
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }


    public void initDate() {

        help_detailtitle_tv.setText(helpDetailDates.getTitle() + "");

        htmlStr = helpDetailDates.getContent();

        if (htmlStr == null) {
            return;
        }

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
                textDetail_tv.setMovementMethod(LinkMovementMethod.getInstance());
                textDetail_tv.setText(result);
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
                    spanStr.setSpan(new ForegroundColorSpan(Color.GREEN), 0, content.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
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
                url = new URL(source);
                drawable = Drawable.createFromStream(url.openStream(), "");  //获取网路图片  
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
            drawable.setBounds(0, 0, width, height);  
         /* drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable  
                  .getIntrinsicHeight()); 这是原来的设置*/
            return drawable;
        }
    };

    @Override
    public void updateView(Help result) {
        if (result != null) {
            //有返回东西 ,解析出来数据，设置给屏幕
            LogUtil.info((result).toString());
            helpDetailDates = (Help) result;
            if (helpDetailDates != null) {
                initDate();
            }
        } else {
            showToast("服务器忙，请稍后重试！！！");
        }
    }
}
