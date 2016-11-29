package com.honliv.honlivmall.task;

import android.os.AsyncTask;

import com.honliv.honlivmall.bean.Product;
import com.honliv.honlivmall.util.BeanFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Rodin on 2016/11/6.
 * 获取积分卷信息
 */
public class GetCunPonListInfosTask extends AsyncTask<Integer, Void, Object> {

    private List<HashMap<String,Object>> productJsobList;
    private ArrayList<Product> currentProductList;
    private int userID;

    public GetCunPonListInfosTask(int userID, List<HashMap<String,Object>> productJsobList, ArrayList<Product> currentProductList) {
        this.userID = userID;
        this.productJsobList = productJsobList;
        this.currentProductList = currentProductList;
    }

    public void setListener(PostReslut listener) {
        this.listener = listener;
    }

    private PostReslut listener;

    public interface PostReslut {
        void post(Object result);
    }

    @Override
    protected Object doInBackground(Integer... params) {

//        CategoryEngine engine = BeanFactory.getImpl(CategoryEngine.class);
//
//        if (productJsobList == null) {
//            productJsobList = new ArrayList<JSONObject>();
//            // allPrice = 0;
//            JSONObject jsobj;
//            for (int i = 0; i < currentProductList.size(); i++) {
//                Product product = currentProductList.get(i);
//                float itemPrice = Float.parseFloat(product.getSaleprice());
//                jsobj = new JSONObject();
//                try {
//                    jsobj.put("SKU", product.getOpenSkuStr());
//                    jsobj.put("Count", product.getNumber());
//                    productJsobList.add(jsobj);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return engine.getMyCouPonList(userID, productJsobList);

        return null;
    }

    @Override
    protected void onPostExecute(Object result) {
        listener.post(result);
    }
}
