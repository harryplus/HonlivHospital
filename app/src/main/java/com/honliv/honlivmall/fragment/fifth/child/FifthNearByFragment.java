package com.honliv.honlivmall.fragment.fifth.child;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.honliv.honlivmall.ConstantValue;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.model.fifth.child.FifthAboutModel;
import com.honliv.honlivmall.model.fifth.child.FifthNearByModel;
import com.honliv.honlivmall.presenter.fifth.child.FifthAboutPresenter;
import com.honliv.honlivmall.presenter.fifth.child.FifthNearByPresenter;

import butterknife.BindView;
import im.delight.android.webview.AdvancedWebView;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthNearByFragment extends BaseFragment<FifthNearByPresenter, FifthNearByModel> implements FifthContract.FifthNearByView, View.OnClickListener, AdvancedWebView.Listener {
    @BindView(R.id.webview)
    AdvancedWebView mWebView;

    public static FifthNearByFragment newInstance() {
        Bundle args = new Bundle();
        FifthNearByFragment fragment = new FifthNearByFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fifth_nearby;
    }

    /**
     * 初始化控件
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        String imageUrl = ConstantValue.IMAGE_URL;
//        webview.loadDataWithBaseURL(null, imageUrl +"/MShop/MAlifeLocal", "text/html", "utf-8", null);
//        webview.loadData(imageUrl +"MShop/MAlifeLocal");
//        webview.loadMarkdownFile(imageUrl +"/MShop/MAlifeLocal");
        mWebView.setListener(getActivity(), this);
        mWebView.loadUrl(imageUrl + "/MShop/MAlifeLocal");
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

    @SuppressLint("NewApi")
    @Override
    public void onResume() {
        super.onResume();
        mWebView.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    public void onPause() {
        mWebView.onPause();
        // ...
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mWebView.onDestroy();
        // ...
        super.onDestroy();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        super.onActivityResult(requestCode, resultCode, intent);
//        mWebView.onActivityResult(requestCode, resultCode, intent);
//        // ...
//    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {
    }

    @Override
    public void onPageFinished(String url) {
    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {
    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {
    }

    @Override
    public void onExternalPageRequest(String url) {
    }


}
