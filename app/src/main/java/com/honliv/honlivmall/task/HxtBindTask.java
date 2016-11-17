package com.honliv.honlivmall.task;

import android.os.AsyncTask;

import com.loopj.android.http.SyncHttpClient;

/**
 * Created by Rodin on 2016/11/2.
 */
public class HxtBindTask extends AsyncTask<String, Void, Boolean> {
    private static final String TAG = "HxtBindTask";
    private BindFace bindFace;
    SyncHttpClient client = new SyncHttpClient();

    public void setBindFace(BindFace bindFace) {
        this.bindFace = bindFace;
    }

    public interface BindFace {
        void bind(Boolean flag);
    }

    @Override
    protected Boolean doInBackground(String... params) {
//        HXTEngine engine = new HXTEngineImpl();
//        return engine.bind(params[0], params[1]);
        return null;
    }

    @Override
    protected void onPostExecute(Boolean flag) {
        bindFace.bind(flag);
    }
}
