package com.android.wordsmanagesystem.konwledge;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.android.wordsmanagesystem.BaseFragment;
import com.android.wordsmanagesystem.R;
import com.android.wordsmanagesystem.SearchActivity;

/**
 * Created by 杨婷 on 2018/4/7.
 */

public class KnowledgeFragment extends BaseFragment {
    private Button buttonEnter;
    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.knowledge_enter, null);
        buttonEnter = (Button)view.findViewById(R.id.enter_knowledge);
        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, KnowledgeActivity.class));
            }
        });
        return view;
    }
}
