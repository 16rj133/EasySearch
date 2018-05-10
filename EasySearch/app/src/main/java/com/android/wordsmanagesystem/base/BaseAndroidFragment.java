package com.android.wordsmanagesystem.base;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;

import com.android.wordsmanagesystem.BaseFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.wordsmanagesystem.MainActivity;
import com.android.wordsmanagesystem.R;
import com.android.wordsmanagesystem.SearchActivity;
import android.widget.ImageView;
import java.util.ArrayList;

/**
 * Created by 杨婷 on 2018/2/4.
 */

public class BaseAndroidFragment extends BaseFragment {
    private ListView mListView;
    private String[] datas;
    private ArrayList<NewsBean> mList;
    private BaseAndroidFragmentAdapter adapter;
    private ImageView search;
    private int[] images;
    private static final String TAG = BaseAndroidFragment.class.getSimpleName();//"BaseAndroidFragment"

    @Override
    protected View initView() {
        View view = View.inflate(mContext,R.layout.first_frame,null);
        mListView = (ListView) view.findViewById(R.id.lv_f);
        mList = NewsUtils.getAllNews(mContext);
        search = (ImageView) view.findViewById(R.id.searchauto) ;
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, SearchActivity.class));
            }
        });
        /*mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                *//*String data = datas[position];
                if (data.toLowerCase().equals("webview")) {
                    startActivity(new Intent(mContext, WebViewMainActivity.class));
                } else if (data.toLowerCase().equals("listview")) {
                    startActivity(new Intent(mContext, ListViewMainActivity.class));
                } else if (data.toLowerCase().equals("recyclerview")) {
                    startActivity(new Intent(mContext, MyRecyclerViewMainActivity.class));
                } else if (data.equals("UI基础组件")) {
                    startActivity(new Intent(mContext, MyBaseUIMainActivity.class));
                } else if (data.equals("事件处理机制")) {
                    startActivity(new Intent(mContext, EventMainActivity.class));
                } else if (data.equals("动画")) {
                    startActivity(new Intent(mContext, MyAnimationMainActivity.class));
                }*//*
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(mList.get(position).news_url));
                startActivity(intent);
            }
        });*/
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Uri uri = Uri.parse("http://www.dzwww.com/xinwen/guojixinwen/201802/t20180220_17060967.htm");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);*/
                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("url",mList.get(position).news_url);
                startActivity(intent);
                /*Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(mList.get(position).news_url));
                startActivity(intent);*/
            }
        });
        return view;
    }
    @Override
    protected void initData() {
        /*super.initData();
        //准备数据
        datas = new String[]{"UI基础组件", "ListView", "RecyclerView", "WebView", "事件处理机制", "动画",
                ".....", "动画", "动画", "动画", "动画", "动画", "动画"};
        images = new int[]{R.drawable.ff_yellow_folder};
        //设置适配器
        adapter = new BaseAndroidFragmentAdapter(mContext, datas, images);*/
        mListView.setAdapter(new NewsAdapter());
    }
    private class NewsAdapter extends BaseAdapter implements Filterable{
        @Override
        public Filter getFilter() {
            return null;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public NewsBean getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(mContext, R.layout.listview_item, null);
                holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
                holder.tv_des = (TextView) convertView.findViewById(R.id.tv_des);
                holder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
                holder.tv_main = (TextView) convertView.findViewById(R.id.tv_main);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            NewsBean item = getItem(position);
            holder.tv_title.setText(item.title);
            holder.tv_des.setText(item.des);
            holder.iv_icon.setImageDrawable(item.icon);
            holder.tv_main.setText(item.main);
            return convertView;
        }
    }

    private static class ViewHolder {
        TextView tv_title;
        TextView tv_des;
        ImageView iv_icon;
        TextView tv_main;
    }
}
