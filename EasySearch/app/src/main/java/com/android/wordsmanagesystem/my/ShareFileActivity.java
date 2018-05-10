package com.android.wordsmanagesystem.my;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.android.wordsmanagesystem.R;
import com.android.wordsmanagesystem.adapter.ShareAdapter;

/**
 * Created by 杨婷 on 2018/4/8.
 */

public class ShareFileActivity extends Activity {
    private ListView lvShare;
    private String mDatas[] = {"金融","网贷","阅读","科技","医疗","答疑","设计","教育"};
    private String mDates[] = {"2018-03-01 16:00"};
    private ShareAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_file);
        lvShare = (ListView)findViewById(R.id.lv_share);
        adapter = new ShareAdapter(ShareFileActivity.this,mDatas,mDates);
        lvShare.setAdapter(adapter);
    }
}
