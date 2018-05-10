package com.android.wordsmanagesystem.my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.wordsmanagesystem.MainActivity;
import com.android.wordsmanagesystem.R;
import com.android.wordsmanagesystem.activity.SpeakActivity;

/**
 * Created by 杨婷 on 2018/4/8.
 */

public class MyDailyActivity extends Activity{
    private ImageView jia;
    private ImageView me;
    private ScrollView sayingScroll;
    private int scrllViewWidth = 0, scrollViewMiddle = 0;
    private View views[];
    private LayoutInflater inflater;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saying);
        jia = (ImageView)findViewById(R.id.write);
        me = (ImageView)findViewById(R.id.me);
        sayingScroll = (ScrollView)findViewById(R.id.saying_scrolllview);
        inflater= LayoutInflater.from(this);
        showToolsView();
        jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyDailyActivity.this, SpeakActivity.class);
                startActivity(intent);
            }
        });
        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyDailyActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    /**
     * 动态生成显示items中的textview
     */
    private void showToolsView() {
        LinearLayout sayingLayout=(LinearLayout) findViewById(R.id.saying);
        views=new View[15];

        for (int i = 0; i < 15; i++) {
            View view=inflater.inflate(R.layout.listview_speaking, null);//重点学习
            view.setId(i);
            LinearLayout giveLike = (LinearLayout) view.findViewById(R.id.giveLike);
            giveLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView tvLike = view.findViewById(R.id.tvLike);
                    int like = Integer.parseInt(tvLike.getText().toString())+1;
                    tvLike.setText(String.valueOf(like));
                }
            });
            //view.setOnClickListener(toolsItemListener);
            //TextView textView=(TextView) view.findViewById(R.id.text);
            //textView.setText(toolsList[i]);
            sayingLayout.addView(view);
            //toolsTextViews[i]=textView;
            views[i]=view;
        }
        //changeTextColor(0);
    }
    /**
     * 改变栏目位置
     *
     * @param clickPosition
     */
    private void changeTextLocation(int clickPosition) {

        int x = (views[clickPosition].getTop() - getScrollViewMiddle() + (getViewheight(views[clickPosition]) / 2));
        sayingScroll.smoothScrollTo(0, x);
    }

    /**
     * 返回scrollview的中间位置
     *
     * @return
     */
    private int getScrollViewMiddle() {
        if (scrollViewMiddle == 0)
            scrollViewMiddle = getScrollViewheight() / 2;
        return scrollViewMiddle;
    }

    /**
     * 返回ScrollView的宽度
     *
     * @return
     */
    private int getScrollViewheight() {
        if (scrllViewWidth == 0)
            scrllViewWidth = sayingScroll.getBottom() - sayingScroll.getTop();
        return scrllViewWidth;
    }

    /**
     * 返回view的宽度
     *
     * @param view
     * @return
     */
    private int getViewheight(View view) {
        return view.getBottom() - view.getTop();
    }
}
