package com.android.wordsmanagesystem.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.wordsmanagesystem.R;

/**
 * Created by 杨婷 on 2018/4/8.
 */

public class HistoryAdapter extends BaseAdapter {
    private final Context mContext;
    private final String[] mDatas;

    public HistoryAdapter(Context context, String[] datas) {
        this.mContext = context;
        this.mDatas = datas;
    }
    @Override
    public int getCount() {
        return mDatas.length;
    }

    @Override
    public Object getItem(int position) {
        return mDatas [position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View layout = View.inflate(mContext, R.layout.item_history, null);
        TextView tvHistory = layout.findViewById(R.id.tv_history);
        tvHistory.setText(mDatas[position]);
        return layout;

    }
}
