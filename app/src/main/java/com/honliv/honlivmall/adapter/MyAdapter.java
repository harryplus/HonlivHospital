package com.honliv.honlivmall.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.honliv.honlivmall.bean.AddressInfo;

import java.util.List;

public class MyAdapter extends BaseAdapter {
	
	private Context context;
	private List<AddressInfo> addressList;
	
	public MyAdapter(Context context, List<AddressInfo> addressList) {
		this.context = context; 
		this.addressList = addressList;
	}
	
	public int getCount() {
		return addressList.size(); 
	} 
	public Object getItem(int position) {
		return addressList.get(position);
	} 
	public long getItemId(int position) {
		return position;
	} 
	
	public View getView(int position, View convertView, ViewGroup parent)
	{ 
		AddressInfo myListItem = addressList.get(position);
		return new MyAdapterView(this.context, myListItem ); 
	}

	class MyAdapterView extends LinearLayout { 
		public static final String LOG_TAG = "MyAdapterView";
		
		public MyAdapterView(Context context, AddressInfo myListItem ) {
		super(context);
		this.setOrientation(HORIZONTAL); 
		
		LayoutParams params = new LayoutParams(200, LayoutParams.WRAP_CONTENT);
		params.setMargins(1, 1, 1, 1); 
		
		TextView name = new TextView( context ); 
		name.setText( myListItem.getName() ); 
		
//		LinearLayout.LayoutParams tVParams = new LinearLayout.LayoutParams(200, LayoutParams.WRAP_CONTENT); 
		name.setHeight(40);
		name.setGravity(Gravity.CENTER_VERTICAL);

		addView( name, params); 
		
		/*LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(200, LayoutParams.WRAP_CONTENT); 
		params2.setMargins(1, 1, 1, 1); 
		TextView pcode = new TextView(context); 
//		pcode.setText(myListItem.getPcode()); 
		pcode.setText(myListItem.getId()+""); 
		addView( pcode, params2); 
		pcode.setVisibility(GONE);*/

		}		 

		}

}