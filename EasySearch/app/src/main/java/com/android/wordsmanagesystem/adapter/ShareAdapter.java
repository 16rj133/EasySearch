package com.android.wordsmanagesystem.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.wordsmanagesystem.R;

/**
 * Created by 杨婷 on 2018/4/8.
 */

public class ShareAdapter  extends BaseAdapter {
    private final Context mContext;
    private final String[] mDatas;
    private final String[] mDates;

    public ShareAdapter(Context context, String[] datas,String[] dates) {
        this.mContext = context;
        this.mDatas = datas;
        this.mDates = dates;
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
        View layout = View.inflate(mContext, R.layout.item_file_share, null);
        TextView fileTitle = layout.findViewById(R.id.file_title);
        TextView fileTime = layout.findViewById(R.id.file_time);
        fileTitle.setText(mDatas[position]);
        fileTime.setText(mDates[0]);
        return layout;

    }
}
