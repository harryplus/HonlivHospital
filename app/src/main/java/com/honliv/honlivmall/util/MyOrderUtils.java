package com.honliv.honlivmall.util;

/**
 * Created by Rodin on 2016/10/25.
 */
public class MyOrderUtils {

//    public static void getServiceOrderList(final MyOrderActivity mContext, int userId) {
//        new AsyncTask<Integer, Void, Object>() {
//            @Override
//            protected void onPreExecute() {
//                PromptManager.showCommonProgressDialog(mContext);
//                super.onPreExecute();
//            }
//
//            @Override
//            protected Object doInBackground(Integer... params) {
//                OrderEngine engine = BeanFactory.getImpl(OrderEngine.class);
//                return engine.getServiceOrderList(params[0], mContext.start, 30);
//            }
//
//            protected void onPostExecute(Object result) {
//                super.onPostExecute(result);
//                PromptManager.closeProgressDialog();
//                if (result != null) {
//                    mContext.ordereList = (List<OrderInfo>) result;
//                    if (mContext.isUpload) {
//                        //加载更多
//                        if (mContext.ordereList.size() == 0) {
//                            PromptManager.showToast(mContext, "暂无更多内容");
//                            mContext.mPullToRefreshView.setEnablePullLoadMoreDataStatus(false);
//                        } else {
//                            mContext.currentOrderList.addAll(mContext.ordereList);
//
//                            GalleryItem item = null;
//                            for (int i = 0; i < mContext.ordereList.size(); i++) {
//                                item = new GalleryItem(mContext, mContext.ordereList.get(i));
//                                item.initAdapter(mContext);
//                                mContext.galleryitemList.add(item);
//                            }
//                            mContext.orderAdapter.notifyDataSetChanged();
//                        }
//                        mContext.isUpload = false;
//                    } else {
//                        if (mContext.ordereList.size() == 0) {
//                            mContext.orderListView.setVisibility(View.GONE);
//                            mContext.orderNullTV.setVisibility(View.VISIBLE);
//                            mContext.mPullToRefreshView.setVisibility(View.GONE);
//                        } else {
//                            //有返回东西 ,解析出来数据，设置给屏幕
//                            mContext.currentOrderList = mContext.ordereList;
//                            mContext.initDate(mContext.currentOrderList);
//                        }
//                    }
//                    /*
//                    if(orderes.size()>0){
//						//有返回东西 ,解析出来数据，设置给屏幕
//						initDate(orderes);
//					}else{
//						//返回数据为空的集合
//						orderListView.setVisibility(View.GONE);
//						orderNullTV.setVisibility(View.VISIBLE);
//					}*/
//                } else {
//                    PromptManager.showToast(mContext, "服务器忙，请稍后重试！！！");
//                }
//            }
//        }.execute(userId);
//    }
}
