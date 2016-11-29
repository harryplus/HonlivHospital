package com.google.zxing.activity;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.camera.CameraManager;
import com.google.zxing.decoding.CaptureActivityHandler;
import com.google.zxing.decoding.InactivityTimer;
import com.google.zxing.view.ViewfinderView;
import com.honliv.honlivmall.GloableParams;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.base.CoreBaseActivity;
import com.honliv.honlivmall.activity.MainActivity;
import com.honliv.honlivmall.contract.ActivityContract;
import com.honliv.honlivmall.fragment.fifth.child.FifthMyOrderDetailFragment;
import com.honliv.honlivmall.fragment.global.GlobalProductDetailFragment;
import com.honliv.honlivmall.fragment.global.GlobalLoginFragment;
import com.honliv.honlivmall.model.activity.CaptureModel;
import com.honliv.honlivmall.presenter.activity.CapturePresenter;
import com.honliv.honlivmall.util.LogUtil;

import java.io.IOException;
import java.util.Vector;

public class CaptureActivity extends CoreBaseActivity<CapturePresenter, CaptureModel> implements Callback, ActivityContract.CaptureView {


    private CaptureActivityHandler handler;
    private ViewfinderView viewfinderView;
    private boolean hasSurface;
    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    private TextView txtResult;
    private InactivityTimer inactivityTimer;
    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private static final float BEEP_VOLUME = 0.10f;
    private boolean vibrate;
    private String contentStr;
    private int orderId = -1;

    @Override
    protected void onResume() {
        super.onResume();
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if (hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        decodeFormats = null;
        characterSet = null;
        playBeep = true;
        AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        initBeepSound();
        vibrate = true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        CameraManager.init(getApplication());
        viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
        txtResult = (TextView) findViewById(R.id.txtResult);
        hasSurface = false;
        inactivityTimer = new InactivityTimer(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        super.onDestroy();
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
        } catch (IOException ioe) {
            return;
        } catch (RuntimeException e) {
            return;
        }
        if (handler == null) {
            handler = new CaptureActivityHandler(this, decodeFormats,
                    characterSet);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;

    }

    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return handler;
    }

    public void drawViewfinder() {
        viewfinderView.drawViewfinder();

    }

    public void handleDecode(Result obj, Bitmap barcode) {
        inactivityTimer.onActivity();
        viewfinderView.drawResultBitmap(barcode);
        playBeepSoundAndVibrate();
        txtResult.setText(obj.getBarcodeFormat().toString() + ":"
                + obj.getText());

        contentStr = obj.getText();

//		contentStr = "P20140604175459509";

        if (contentStr.contains("http://")) {//如果是网址
            Bundle data = new Bundle();
            data.putInt("position", MainActivity.FIRST);
            data.putSerializable("fragment", GlobalProductDetailFragment.class);
//			Intent intent = new Intent(this, ProductDetailActivity.class);
            contentStr = contentStr.substring(contentStr.lastIndexOf("/") + 1);
            try {

                LogUtil.info("contentStr = " + contentStr);

                int productID = Integer.parseInt(contentStr);

                if (productID > 0) {
                    data.putInt("pId", productID);
                    data.putBoolean("scanning", true);
//					intent.putExtra("pId", productID);
//					intent.putExtra("scanning", true);
                } else {
                    showToast("扫描解析错误");
                    return;
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
                showToast("扫描解析错误");
                return;
            }
            startActivity(MainActivity.class, data);
//			startActivity(intent);
            finish();
//			overridePendingTransition(R.anim.tran_pre_out, R.anim.tran_next_in);
            overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);

        } else {//不是网址，默认就是订单

            //判断用户是否登录，如果没有登录就引导用户登录
            if (GloableParams.USERID <= 0) {//没有登录
                Bundle data = new Bundle();
                data.putInt("position", MainActivity.FIFTH);
                data.putSerializable("fragment", GlobalLoginFragment.class);
                showToast("请先登录！！");
                startActivity(MainActivity.class, data);
//                Intent intent = new Intent(CaptureActivity.this, LoginActivity.class);
//                startActivity(intent);
                finish();
//				overridePendingTransition(R.anim.tran_pre_out, R.anim.tran_next_in);
                overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);

            } else {
                mPresenter.getOrderId(contentStr);
            }
        }
    }

    private void initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            // The volume on STREAM_SYSTEM is not adjustable, and users found it
            // too loud,
            // so we now play on the music stream.
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(beepListener);

            AssetFileDescriptor file = getResources().openRawResourceFd(
                    R.raw.beep);
            try {
                mediaPlayer.setDataSource(file.getFileDescriptor(),
                        file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mediaPlayer.prepare();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        }
    }

    private static final long VIBRATE_DURATION = 200L;

    private void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }

    /**
     * When the beep has finished playing, rewind to queue up another one.
     */
    private final OnCompletionListener beepListener = new OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };


    @Override
    public void updateView(Integer result) {
        if (result != null) {
            if (((Integer) result) != -1) {

                orderId = (Integer) result;
                Bundle data = new Bundle();
                data.putInt("position", MainActivity.FIRST);
                data.putSerializable("fragment", FifthMyOrderDetailFragment.class);
                data.putInt("orderId", orderId);
                startActivity(MainActivity.class, data);
//                Intent intent = new Intent(CaptureActivity.this, MyOrderDetailActivity.class);
//                intent.putExtra("orderId", orderId);
//                startActivity(intent);
                finish();
                //				overridePendingTransition(R.anim.tran_pre_out, R.anim.tran_next_in);
                overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
            } else {
                showToast("解析状态错误！");
                return;
            }
        } else {
            showToast("服务器忙，请稍后重试！！！");
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showError(String msg) {
        showToast(msg);
    }
}