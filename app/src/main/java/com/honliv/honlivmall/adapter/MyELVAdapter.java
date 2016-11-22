package com.honliv.honlivmall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.honliv.honlivmall.R;
import com.honliv.honlivmall.bean.Category;

import java.util.List;

/**
 * Created by Rodin on 2016/11/22.
 */
public class MyELVAdapter extends BaseExpandableListAdapter {
    private final List<Category> groupList;
    private final Context context;

    public MyELVAdapter(Context context, List<Category> groupList) {
        this.context = context;
        this.groupList = groupList;
    }

    public int getGroupCount() {
        //return groupTitles.length;
        return groupList.size();
    }

    public int getChildrenCount(int groupPosition) {
        //return childTitle[groupPosition].length;
        return groupList.get(groupPosition).getChildlist().size();
    }

    public Object getGroup(int groupPosition) {
        return null;
    }

    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //
    //如果返回true表示子项和组的ID始终表示一个固定的组件对象
    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        //LinearLayout parentLayout=(LinearLayout)View.inflate(getApplicationContext(), R.layout.cateory2_parent_item, null);
        LayoutInflater inflater = LayoutInflater.from(context);
        LinearLayout parentLayout = (LinearLayout) inflater.inflate(R.layout.cateory2_parent_item, parent, false);

        TextView parentTextView = (TextView) parentLayout.findViewById(R.id.parentitem);

        // parentTextView.setText(""+groupTitles[groupPosition]);
        parentTextView.setText(groupList.get(groupPosition).getTitle());

        ImageView parentImageViw = (ImageView) parentLayout.findViewById(R.id.arrow_right);

        //判断isExpanded就可以控制是按下还是关闭，同时更换图片
        if (isExpanded) {
            //  parentImageViw.setBackgroundResource(R.drawable.arrow_down);
            parentImageViw.setBackgroundResource(R.drawable.arrow);
        } else {
            //   parentImageViw.setBackgroundResource(R.drawable.arrow_up);
            parentImageViw.setBackgroundResource(R.drawable.arrow_right);
        }
        return parentLayout;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        // LinearLayout childrenLayout=(LinearLayout)View.inflate(getApplicationContext(), R.layout.cateory2_children_item, null);

        LayoutInflater inflater = LayoutInflater.from(context);
        LinearLayout childrenLayout = (LinearLayout) inflater.inflate(R.layout.cateory2_children_item, parent, false);


        // childrenLayout.setPadding(40, 5, 3, 5) ;
        // childrenLayout.setPadding(40, 5, 3, 5) ;
        TextView childrenTextView = (TextView) childrenLayout.findViewById(R.id.childrenitem);
        // childrenTextView.setText(""+childTitle[groupPosition][childPosition]);
        childrenTextView.setText(groupList.get(groupPosition).getChildlist().get(childPosition).getTitle());

        return childrenLayout;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
