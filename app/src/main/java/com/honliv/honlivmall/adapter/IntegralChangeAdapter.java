package com.honliv.honlivmall.adapter;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.bean.CuponRuleInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据适配器
 * @author Wangmf
 * 日期：2014年5月28日
 */
public class IntegralChangeAdapter extends BaseAdapter {

	private List<CuponRuleInfo> currentIntegrales;
	private Context context;
	private IntegralChangeAdapterCallBack adapterCallBack;
	private List<RadioButton> radioButtons = new ArrayList<RadioButton>();

	public IntegralChangeAdapter(Context context, List<CuponRuleInfo> currentIntegrales
			, IntegralChangeAdapterCallBack adapterCallBack){
		this.context = context;
		this.currentIntegrales = currentIntegrales;
		if (adapterCallBack!=null) {
			this.adapterCallBack = adapterCallBack;
		}
	}
	@Override
	public int getCount() {

		return currentIntegrales.size();
	}

	@Override
	public Object getItem(int position) {
	
		return currentIntegrales.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = convertView;
		Holder holder = null;
		if (view == null) {
			holder = new Holder();
			view = View.inflate(context, R.layout.my_integral_change_item, null);
			
			holder.radioButton = (RadioButton) view.findViewById(R.id.my_integral_change_item_radiobutton);
			holder.name = (TextView) view.findViewById(R.id.my_integral_change_item_name);
			holder.needPonit = (TextView) view.findViewById(R.id.my_integral_change_item_needpoint);
			
			radioButtons.add(holder.radioButton);
			holder.radioButton.setTag(currentIntegrales.get(position));
			
			view.setTag(holder);
			
			view.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					Holder myHolder = (Holder) v.getTag();
					
					CuponRuleInfo cuponRuleInfo = (CuponRuleInfo) myHolder.radioButton.getTag();
					//取消全部选择	
					for (RadioButton radioButton : radioButtons) {
						if (radioButton != myHolder.radioButton) {
							radioButton.setChecked(false);
						}
					}
				

		
					if (adapterCallBack!=null && !myHolder.radioButton.isChecked()) {
						if (!adapterCallBack.onCheckSuccess(cuponRuleInfo)) {
							return;
						}
					}else if (adapterCallBack!=null && myHolder.radioButton.isChecked()) {
//						adapterCallBack.onCheckCancel(couponInfo);
						if (!adapterCallBack.onCheckCancel(cuponRuleInfo)) {
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
		
		CuponRuleInfo ruleInfo = currentIntegrales.get(position);
		
		holder.name.setText(ruleInfo.getName());
		holder.needPonit.setText("需要积分："+ruleInfo.getNeedpoint()+" 分");
		
		return view;
	}
	
	private class Holder{
		public RadioButton radioButton;
		public TextView name;
		public TextView needPonit;
	}


	/**
	 * 设置一个点击回调事件
	 * @author Wangmf
	 * 日期：2014年5月28日
	 */
	public interface IntegralChangeAdapterCallBack{
		boolean onCheckSuccess(CuponRuleInfo couponInfo);
		boolean onCheckCancel(CuponRuleInfo couponInfo);
	}


	/**
	 * 取消全部选择
	 * @author Wangmf
	 * 日期：2014年5月28日
	 */
	public void cancelAllCheck() {
		
		for (RadioButton radioButton : radioButtons) {
				
			if (radioButton.isChecked()) {
				radioButton.setChecked(false);
			}
			
		}
		
	}
}