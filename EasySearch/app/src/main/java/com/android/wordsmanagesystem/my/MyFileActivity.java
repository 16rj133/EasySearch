package com.android.wordsmanagesystem.my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.android.wordsmanagesystem.R;
/**
 * Created by 杨婷 on 2018/4/8.
 */

public class MyFileActivity extends Activity {
    private LinearLayout sharefile;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_file);
        sharefile = (LinearLayout) findViewById(R.id.share_file);
        sharefile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyFileActivity.this,ShareFileActivity.class);
                startActivity(intent);
            }
        });
    }
}
