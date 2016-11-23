package com.honliv.honlivmall.fragment.fifth.child;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.honliv.honlivmall.ConstantValue;
import com.honliv.honlivmall.R;
import com.honliv.honlivmall.activity.AboutActivity;
import com.honliv.honlivmall.base.BaseFragment;
import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.bean.UserInfo;
import com.honliv.honlivmall.bean.VersionInfo;
import com.honliv.honlivmall.contract.FifthContract;
import com.honliv.honlivmall.model.fifth.child.FifthSettingModel;
import com.honliv.honlivmall.presenter.fifth.child.FifthSettingPresenter;
import com.honliv.honlivmall.util.LogUtil;
import com.honliv.honlivmall.util.PromptManager;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import butterknife.BindView;

/**
 * Created by Rodin on 2016/11/16.
 */
public class FifthSettingFragment extends BaseFragment<FifthSettingPresenter, FifthSettingModel> implements FifthContract.FifthSettingView, View.OnClickListener {
    Intent intent;
    VersionInfo version;
    @BindView(R.id.my_logout_rl)
    RelativeLayout my_logout;
    @BindView(R.id.my_wipecache_rl)
    RelativeLayout wipecache;
    @BindView(R.id.my_help_rl)
    RelativeLayout myhelp_;
    @BindView(R.id.my_feedback_rl)
    RelativeLayout myfeedback;
    @BindView(R.id.my_about_rl)
    RelativeLayout myabout;
    @BindView(R.id.checkupdate)
    RelativeLayout checkupdate;
    @BindView(R.id.phone_ok)
    TextView callphone;
    @BindView(R.id.head_goback)
    TextView head_goback;


    public static FifthSettingFragment newInstance() {
        Bundle args = new Bundle();

        FifthSettingFragment fragment = new FifthSettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fifth_setting;
    }

    /**
     * 初始化控件
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        my_logout.setOnClickListener(this);
        wipecache.setOnClickListener(this);
        myhelp_.setOnClickListener(this);
        myfeedback.setOnClickListener(this);
        myabout.setOnClickListener(this);
        checkupdate.setOnClickListener(this);
        callphone.setOnClickListener(this);
        head_goback.setOnClickListener(this);
        String phone = sp.getString("phone", "400 8888 8888");
        callphone.setText("电话：" + phone);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onClick(View view) {

        if (intent == null) {
            intent = new Intent();
        }
        switch (view.getId()) {
            case R.id.my_logout_rl://注销
                mPresenter.getServiceLogOut("-1");
                break;
            case R.id.my_wipecache_rl://清理缓存
                cleanAll();
                break;
            case R.id.my_help_rl://帮助
                start(FifthHelpListFragment.newInstance());
                break;
            case R.id.my_feedback_rl://意见反馈
                start(FifthFeedBackFragment.newInstance());
                break;
            case R.id.my_about_rl://关于
                intent.setClass(getContext(), AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.checkupdate://检查更新
//			PromptManager.showToastTest(getContext(), "功能稍后开放");
                mPresenter.checkVersion();
                break;
            case R.id.phone_ok:
                String phoneNumber = callphone.getText().toString().split("：")[1];
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);//拨号界面，不是马上打电话
                intent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
                break;
            case R.id.head_goback:

                break;
        }
    }


    public void cleanAll() {

        //利用系统的一个特性，当申请的内存超过现有空闲内存时，系统会自动清理掉缓存

        PromptManager.showToast(getContext(), "正在清理中...");

        PackageManager pm = getContext().getPackageManager();
        Method[] methods = PackageManager.class.getMethods();
        try {
            for (Method method : methods) {
                if ("freeStorageAndNotify".equals(method.getName())) {
//                    method.invoke(pm, Integer.MAX_VALUE, new IPackageDataObserver.Stub() {
//                        public void onRemoveCompleted(String packageName, boolean succeeded)
//                                throws RemoteException {
//                            handler.sendEmptyMessage(1);
//                        }
//                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (1 == msg.what) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                DbUtils db = DbUtils.create(getContext());
                try {
                    db.dropTable(Product.class);
                } catch (DbException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                initShopCarNumber();
                PromptManager.showToast(getContext(), "恭喜你，缓存已经全部清除，手机达到最佳状态");
            }
        }
    };

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }


    protected void initUpDate() {
        String versionStr = getAppVersions();
        String versionServiceStr = version.getVersionNum();
        if (versionServiceStr.compareTo(versionStr) > 0) {
            showUpdateDialog();
        } else {
            showToast("当前版本 V" + versionStr + " 为最新版本");
        }
    }

    String getAppVersions() {// 当前应用的版本号
        PackageManager manager = getContext().getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(getContext().getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // can't run
            e.printStackTrace();
            return null;
        }
    }

    HttpHandler updateHandler;

    void showUpdateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {//无法取消对话框
            public void onCancel(DialogInterface dialog) {
            }
        });
        builder.setTitle("更新提醒");
        String tempDes = version.getDescr();
        if (tempDes != null) { //换行
            tempDes = tempDes.replace("|", "\n");
        }
        if (version.getDescr() != null) {
            tempDes = tempDes.replace("{version}", getAppVersions() + "");
            builder.setMessage(tempDes);
        }
        //判断是否包含[update]，如果包含不升级就强制退出
//		tempDes = "我爱你[update]";
//		version.setDescr(tempDes+"");
        if (version.getDescr() != null && tempDes.contains("[update]")) {
            tempDes = tempDes.replace("[update]", "");
            builder.setMessage(tempDes);

            //强制退出
            builder.setNegativeButton("下次再说", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
//					loadHomeActivity();
                    dialog.dismiss();
//					android.os.Process.killProcess(android.os.Process.myPid());
//					finish();
                }
            });
        } else {
            builder.setNegativeButton("下次再说", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
//					loadHomeActivity();
                }
            });
        }

        builder.setPositiveButton("立即升级", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                            File file = new File(Environment.getExternalStorageDirectory() + "/dyupdate", "DYAndroidupdate.apk");

                            if (!file.exists()) {
                                try {
                                    file.createNewFile();
                                } catch (IOException e) {
//								e.printStackTrace();
                                    LogUtil.info("eeee===" + e.toString());
                                }
                            }
                            HttpUtils finalHttp = new HttpUtils();

                            LogUtil.info("下载地址===" + ConstantValue.IMAGE_URL + version.getVersionUrl());

                            String tempUpdate = version.getVersionUrl();
                            if (!tempUpdate.contains("http:")) {
                                tempUpdate = ConstantValue.IMAGE_URL + tempUpdate;
                            }
                            updateHandler = finalHttp.download(tempUpdate, file.getAbsolutePath(),
                                    true, true,
                                    new RequestCallBack<File>() {
                                        @Override
                                        public void onStart() {
//								 updateTV.setVisibility(View.VISIBLE);

                                            showUpdataProcess();
                                            updateTV.setText("连接中...");
                                        }

                                        @Override
                                        public void onLoading(long total, long current, boolean isUploading) {
                                            int process = (int) (current * 100 / total);
                                            updateTV.setText("进度:" + process + "%");
                                            pb.setMax((int) total);
                                            pb.setProgress((int) current);
                                        }

                                        @Override
                                        public void onFailure(HttpException arg0,
                                                              String arg1) {
                                            if (upDatadialog != null) {
                                                upDatadialog.dismiss();
                                            }
//								updateTV.setVisibility(View.GONE);
                                            Toast.makeText(getContext(), "下载失败...", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onSuccess(ResponseInfo<File> responseInfo) {
//								 updateTV.setVisibility(View.GONE);
                                            if (upDatadialog != null) {
                                                upDatadialog.dismiss();
                                            }
                                            // == 下载成功，替换安装 ==
//								String cmd = "chmod 777 " + t.getParent();
//								Logger.i(TAG, cmd);
//								try {
//									Runtime.getRuntime().exec(cmd);
//								} catch(Exception e) {
//									e.printStackTrace();
//								}

                                            Intent intent = new Intent();
                                            intent.setAction("android.intent.action.VIEW");
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);        // 加这个
                                            intent.addCategory("android.intent.category.DEFAULT");
                                            intent.setDataAndType(Uri.fromFile(responseInfo.result), "application/vnd.android.package-archive");
                                            startActivity(intent);
                                        }
                                    });
                        } else {
                            Toast.makeText(getContext(), "SD卡不可用,请检查", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        builder.show();
    }

    AlertDialog upDatadialog;
    TextView updateTV;
    ProgressBar pb;

    void showUpdataProcess() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        View view = View.inflate(getContext(), R.layout.dialog_enter_pwd, null);
        upDatadialog = builder.create();
        upDatadialog.setView(view, 0, 0, 0, 0);
        pb = (ProgressBar) view.findViewById(R.id.pb_updata);
        updateTV = (TextView) view.findViewById(R.id.tv_update_stats);
        view.findViewById(R.id.bt_hide).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //隐藏窗口
                upDatadialog.dismiss();
            }
        });

        view.findViewById(R.id.bt_cancleok).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //取消升级
                //调用stop()方法停止下载
                updateHandler.stop();
                upDatadialog.dismiss();
            }
        });
        upDatadialog.show();
    }

    @Override
    public void updateVersion(VersionInfo result) {
        if (result != null) {
            version = (VersionInfo) result;
            if (version != null) {
                //有返回东西 ,解析出来数据，设置给屏幕
                initUpDate();
            }
        } else {
//					loadHomeActivity();//取消对话框，进入主界面
//					PromptManager.showMyToast(SplashActivity.this, "服务器忙，请稍后重试！！！");
        }
    }

    @Override
    public void updateLogOut(Boolean result) {
        if (result != null && (Boolean) result) {

            DbUtils db = DbUtils.create(getContext());
            try {
                db.dropTable(UserInfo.class);
            } catch (DbException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            PromptManager.showToast(getContext(), "注销成功!");
            pop();
            //有返回东西 ,解析出来数据，设置给屏幕
//					LogUtil.info(((List)result).toString());
        } else {
            PromptManager.showToast(getContext(), "服务器忙，请稍后重试！！！");
        }
    }
}
