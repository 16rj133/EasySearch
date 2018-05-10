package com.android.wordsmanagesystem.konwledge;

import java.util.ArrayList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.wordsmanagesystem.R;
import com.android.wordsmanagesystem.konwledge.Pro_type_adapter;


public class Fragment_pro_type extends Fragment {
	private ArrayList<Type> list;
	private ImageView hint_img;
	private ListView listView;
	private Pro_type_adapter adapter;
	private Type type;
	private ProgressBar progressBar;
	private String typename;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_pro_type, null);
		progressBar=(ProgressBar) view.findViewById(R.id.progressBar);
		hint_img=(ImageView) view.findViewById(R.id.hint_img);
		listView = (ListView) view.findViewById(R.id.listView);
		typename=getArguments().getString("typename");
		((TextView)view.findViewById(R.id.toptype)).setText(typename);
		GetTypeList();
		adapter=new Pro_type_adapter(getActivity(), list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {


			}
		});

		return view;
	}


	private void GetTypeList() {
		list=new ArrayList<Type>();
		/*for(int i=1;i<35;i++){
			type=new Type(i, typename+i, "");
			list.add(type);
		}*/
		for (int i = 0; i < 5; i++) {
			Type type1 = new Type();
			type1.typename = "地产及城市建设规划\n" + "金融投资项目计划书";
			type1.des = "word流程 金融";
			type1.typeiconurl = getActivity().getResources().getDrawable(R.drawable.jinrong1);
			list.add(type1);

			Type type2 = new Type();
			type2.typename = "证券股票投资\n" + "金融投资项目计划书";
			type2.des = "ppt模板 金融";
			type2.typeiconurl = getActivity().getResources().getDrawable(R.drawable.jinrong3);
			list.add(type2);

			Type type3 = new Type();
			type3.typename = "金融行业工作会议\n" + "报告与反思";
			type3.des = "pdf文档 金融";
			type3.typeiconurl = getActivity().getResources().getDrawable(R.drawable.jinrong4);
			list.add(type3);
		}
		progressBar.setVisibility(View.GONE);
	}



}
