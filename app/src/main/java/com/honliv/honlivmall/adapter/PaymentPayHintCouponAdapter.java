package com.honliv.honlivmall.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.bean.CouponInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PaymentPayHintCouponAdapter extends BaseAdapter {

	private List<CouponInfo> couponInfos;
	private Context context;
	private SimpleDateFormat df;
	private PaymentPayHintCouponAdapterCallBack adapterCallBack;
	
	public PaymentPayHintCouponAdapter(Context context, List<CouponInfo> couponInfos
			, PaymentPayHintCouponAdapterCallBack adapterCallBack){
		this.context = context;
		this.couponInfos = couponInfos;
		if (adapterCallBack!=null) {
			this.adapterCallBack = adapterCallBack;
		}
		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	@Override
	public int getCount() {
		
		return couponInfos.size();
	}

	@Override
	public Object getItem(int position) {
		
		return couponInfos.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	private List<RadioButton> radioButtons = new ArrayList<RadioButton>();
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Holder holder = null;
		View view = convertView;
		if (view == null) {
			holder = new Holder();
			view = View.inflate(context, R.layout.payment_list_coupon_item, null);
			
			holder.radioButton = (RadioButton) view.findViewById(R.id.payment_coupon_item_radio);
			holder.textTitle =  (TextView) view.findViewById(R.id.payment_coupon_item_text_title);
			holder.couponPrice =  (TextView) view.findViewById(R.id.payment_coupon_item_couponPrice);
			holder.limitPrice = (TextView) view.findViewById(R.id.payment_coupon_limitPrice);
//			holder.usedDate = (TextView) view.findViewById(R.id.payment_coupon_usedDate);
			holder.endDate = (TextView) view.findViewById(R.id.payment_coupon_endDate);
			radioButtons.add(holder.radioButton);
			holder.radioButton.setTag(couponInfos.get(position));
			
			view.setTag(holder);
			
			
			view.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					Log.i("logTest", "显示积分卷 onClick");
					Holder myHolder = (Holder) v.getTag();
					
					CouponInfo couponInfo = (CouponInfo) myHolder.radioButton.getTag();
					//实现单选	
					for (RadioButton radioButton : radioButtons) {
						if (radioButton != myHolder.radioButton) {
							radioButton.setChecked(false);
					
						}
					}

					//如果当前优惠被使用
					if (adapterCallBack!=null && !myHolder.radioButton.isChecked()) {
						if (!adapterCallBack.onCheckSuccess(couponInfo)) {
							return;
						}
					}else if (adapterCallBack!=null && myHolder.radioButton.isChecked()) {
//						adapterCallBack.onCheckCancel(couponInfo);
						if (!adapterCallBack.onCheckCancel(couponInfo)) {
							return;
						}
					}
					
					//是否选中当前的
					myHolder.radioButton.setChecked(!myHolder.radioButton.isChecked());
				}
			});
			
		}else{
			holder = (Holder) view.getTag();
		}
		
		CouponInfo couponInfo = couponInfos.get(position);
		
		holder.textTitle.setText(couponInfo.getCouponName());
		holder.couponPrice.setText("金        额："+ String.format("%.2f", couponInfo.getCouponPrice()));
		holder.limitPrice.setText( "最低消费："+ String.format("%.2f", couponInfo.getLimitPrice()));
//		holder.usedDate.setText("使用时间："+couponInfo.getUsedDate());
		holder.endDate.setText("截止时间："+ couponInfo.getEndDate().replace("9999-12-31"," 无限期"));
		
		Log.i("logTest", " position = "+position);
		
		return view;
	}
	
	public class Holder{
		public RadioButton radioButton;
		public TextView textTitle;
		public TextView couponPrice;
		public TextView limitPrice;
//		public TextView usedDate;
		public TextView endDate;
	}

	/**
	 * 设置一个点击回调事件
	 * @author Wangmf
	 * 日期：2014年5月28日
	 */
	public interface PaymentPayHintCouponAdapterCallBack{
		boolean onCheckSuccess(CouponInfo couponInfo);
		boolean onCheckCancel(CouponInfo couponInfo);
	}
}
