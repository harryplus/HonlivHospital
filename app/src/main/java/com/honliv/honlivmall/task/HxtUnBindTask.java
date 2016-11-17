package com.honliv.honlivmall.task;

import android.os.AsyncTask;


/**
 * Created by Rodin on 2016/11/2.
 */
public class HxtUnBindTask extends AsyncTask<String, Void, Boolean> {
    public void setListener(ResultListener listener) {
        this.listener = listener;
    }

    ResultListener listener;

    public interface ResultListener {
        void result(Boolean aBoolean);
    }

    @Override
    protected Boolean doInBackground(String... params) {
//        HXTEngine engine = new HXTEngineImpl();
//        boolean flag = engine.unbind(params[0]);
//        return flag;
        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        listener.result(aBoolean);
    }
}
