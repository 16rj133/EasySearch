package com.android.wordsmanagesystem;

import android.app.Activity;
import android.app.usage.UsageEvents;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.wordsmanagesystem.activity.FileShowDownActivity;
import com.android.wordsmanagesystem.base.BaseAndroidFragment;
import com.android.wordsmanagesystem.base.NewsBean;
import com.android.wordsmanagesystem.base.NewsUtils;
import com.android.wordsmanagesystem.bean.File;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by 杨婷 on 2018/3/4.
 */

public class ConlusionActivity extends Activity{
    private ListView mListView;
    private ArrayList<NewsBean> mList;
    private ArrayList<NewsBean> newList;
    private String account;
    private ListAdapter adapter;
    private ArrayList<File> fileArrayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_file_mulpulication);
        mListView = (ListView)findViewById(R.id.afm_lv);
        //mList = NewsUtils.getAllNews(ConlusionActivity.this);
        //newList = new ArrayList<NewsBean>();
        //fileArrayList = new ArrayList<File>();
        //ArrayList<File> fileArrayList = (ArrayList<File>) getIntent().getSerializableExtra("resultList");
        //Intent intent = getIntent();
        fileArrayList = (ArrayList<File>)getIntent().getSerializableExtra("resultList");
        /*if(fileArrayList.size()==0)
        {
            Toast.makeText(ConlusionActivity.this, "长度为0", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(ConlusionActivity.this,"长度有的",Toast.LENGTH_SHORT).show();
        }*/
        //mList = NewsUtils.getAllNews(ConlusionActivity.this);
        /*for(int i=0;i<mList.size();i++)
        {
            NewsBean tmp = mList.get(i);
            if(tmp.main.toString().contains(account)||tmp.des.toString().contains(account)||tmp.title.toString().contains(account))
            {
                newList.add(tmp);
            }
        }*/
        initData();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int data = position;
                if (data==0) {
                    startActivity(new Intent(ConlusionActivity.this, FileShowDownActivity.class));
                } else if (data==1) {
                    startActivity(new Intent(ConlusionActivity.this, FileShowDownActivity.class));
                } else if (data==2) {
                    startActivity(new Intent(ConlusionActivity.this, FileShowDownActivity.class));
                }
                else{
                Toast.makeText(ConlusionActivity.this, "不存在该结果", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //这个和之前的适配器不同，是搜索完成后listview
    }
   public void initData()
    {
        adapter = new NewsAdapter1();
        mListView.setAdapter(adapter);
    }
    private class NewsAdapter1 extends BaseAdapter implements Filterable{
        @Override
        public Filter getFilter() {
            return null;
        }

        @Override
        public int getCount() {
            return fileArrayList.size();
        }

        @Override
        public File getItem(int position) {
            return fileArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder1 holder;
            if (convertView == null) {
                holder = new ViewHolder1();
                convertView = View.inflate(ConlusionActivity.this, R.layout.listview_item, null);
                holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
                holder.tv_des = (TextView) convertView.findViewById(R.id.tv_des);
                holder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
                holder.tv_main = (TextView) convertView.findViewById(R.id.tv_main);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder1) convertView.getTag();
            }
            File item = getItem(position);
            holder.tv_title.setText(item.title);
            holder.tv_des.setText(item.content);
            /*if(item.type == "pdf")
            {
               holder.iv_icon.setImageResource(R.mipmap.ic_pdf);
            }
            else if(item.type == "pdf")
            {
                holder.iv_icon.setImageResource(R.mipmap.ic_pdf);
            }*/
            holder.iv_icon.setImageResource(R.mipmap.ic_doc);
            holder.tv_main.setText(item.tag);//这些功能都是对的
            return convertView;
        }
    }

    private static class ViewHolder1 {
        TextView tv_title;
        TextView tv_des;
        ImageView iv_icon;
        TextView tv_main;
    }
}