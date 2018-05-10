package com.android.wordsmanagesystem.base;

import android.content.Context;
import android.graphics.Color;
import android.provider.MediaStore;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.wordsmanagesystem.MainActivity;
import com.android.wordsmanagesystem.R;
/**
 * Created by jackie on 2017/3/18 15:31.
 * QQ : 971060378
 * Used as : xxx
 */
public class BaseAndroidFragmentAdapter extends BaseAdapter {

    private final Context mContext;
    private final String[] mDatas;
    private final int[] mImages;

    public BaseAndroidFragmentAdapter(Context context, String[] datas, int[] images) {
        this.mContext = context;
        this.mDatas = datas;
        this.mImages = images;
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
        View layout = View.inflate(mContext,R.layout.listview_layout, null);
        TextView tvName = layout.findViewById(R.id.tvName);
        tvName.setText(mDatas[position]);
        ImageView ivThumb = layout.findViewById(R.id.ivThumb);
        ivThumb.setImageResource(mImages[0]);
        return layout;

    }

}
