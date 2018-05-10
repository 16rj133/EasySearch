package com.android.wordsmanagesystem.my;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.wordsmanagesystem.BaseFragment;
import com.android.wordsmanagesystem.LoginActivity;
import com.android.wordsmanagesystem.MainActivity;
import com.android.wordsmanagesystem.R;
import com.android.wordsmanagesystem.activity.MyInfoActivity;
import com.lqr.optionitemview.OptionItemView;

/**
 * Created by 杨婷 on 2018/2/25.
 */

public class MyFragment extends BaseFragment {
    private ImageView ivHeader;
    private LinearLayout myInfo;
    private TextView tvName;
    private TextView tvAccount;
    private ImageView ivQRCordCard;
    private OptionItemView mygroup;
    private OptionItemView myfriend;
    private OptionItemView mymessage;
    private OptionItemView mydays;
    private OptionItemView myfile;
    private OptionItemView usage;
    private OptionItemView oivSetting;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_me,null);
        ivHeader = (ImageView)view.findViewById(R.id.ivHeader);
        ivQRCordCard = (ImageView)view.findViewById(R.id.ivQRCordCard);
        myInfo = (LinearLayout)view.findViewById(R.id.llMyInfo);
        tvName = (TextView)view.findViewById(R.id.tvName);
        tvAccount = (TextView)view.findViewById(R.id.tvAccount);
        mygroup = (OptionItemView)view.findViewById(R.id.mygroup);
        myfriend = (OptionItemView)view.findViewById(R.id.myfriend);
        mymessage = (OptionItemView)view.findViewById(R.id.mymessage);
        mydays = (OptionItemView)view.findViewById(R.id.mydays);
        myfile = (OptionItemView)view.findViewById(R.id.myfile);
        usage = (OptionItemView)view.findViewById(R.id.usage);
        oivSetting = (OptionItemView)view.findViewById(R.id.oivSetting);

        //设置我的信息点击事件
        myInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MyInfoActivity.class);
                startActivity(intent);
            }
        });
        //设置我的日志点击事件
        mydays.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MyDailyActivity.class);
                startActivity(intent);
            }
        });
        //设置我的文件点击事件
        myfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MyFileActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
