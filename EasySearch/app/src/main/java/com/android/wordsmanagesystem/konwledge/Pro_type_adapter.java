package com.android.wordsmanagesystem.konwledge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import com.android.wordsmanagesystem.R;

public class Pro_type_adapter extends BaseAdapter {
	// ����Context
		private LayoutInflater mInflater;
	    private ArrayList<Type> list;
	    private Context context;
	    private Type type;
		public Pro_type_adapter(Context context,ArrayList<Type> list){
			mInflater=LayoutInflater.from(context);
			this.list=list;
			this.context=context;
		}
		
		@Override
		public int getCount() {
			if(list!=null&&list.size()>0)
				return list.size();
			else
			    return 0;
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			final MyView view;
			if(convertView==null){
				view=new MyView();
				convertView=mInflater.inflate(R.layout.list_pro_type_item, null);
				view.icon=(ImageView)convertView.findViewById(R.id.typeicon);
				view.name=(TextView)convertView.findViewById(R.id.typetitle);
				view.des = (TextView)convertView.findViewById(R.id.typedes);
				convertView.setTag(view);
			}else{
				view=(MyView) convertView.getTag();
			}
			if(list!=null&&list.size()>0)
			{
				type=list.get(position);
				view.name.setText(type.typename);
				view.des.setText(type.des);
				if(type.typename.contains("金融")){
					view.icon.setImageDrawable(type.typeiconurl);
				}
				else if(type.typename.contains("通讯")){
					view.icon.setImageDrawable(type.typeiconurl);
				}
				else if(type.typename.contains("医疗")){
					view.icon.setImageDrawable(type.typeiconurl);
				}
				else if(type.typename.contains("管理")){
					view.icon.setImageDrawable(type.typeiconurl);
				}
				else if(type.typename.contains("教育")){
					view.icon.setImageDrawable(type.typeiconurl);
				}
				else if(type.typename.contains("科技")){
					view.icon.setImageDrawable(type.typeiconurl);
				}
				else if(type.typename.contains("设计")){
					view.icon.setImageDrawable(type.typeiconurl);
				}
				else if(type.typename.contains("保险")){
					view.icon.setImageDrawable(type.typeiconurl);
				}
				else if(type.typename.contains("娱乐")){
					view.icon.setImageDrawable(type.typeiconurl);
				}
				else if(type.typename.contains("通知")){
					view.icon.setImageDrawable(type.typeiconurl);
				}
				else if(type.typename.contains("财务分析")){
					view.icon.setImageDrawable(type.typeiconurl);
				}
				else if(type.typename.contains("财务数据")){
					view.icon.setImageDrawable(type.typeiconurl);
				}
				else{
					view.icon.setImageDrawable(type.typeiconurl);
				}
			}
			
	        return convertView;
		}

		
		private class MyView{
			private ImageView icon;		
			private TextView name;
			private TextView des;
		}
		
}
