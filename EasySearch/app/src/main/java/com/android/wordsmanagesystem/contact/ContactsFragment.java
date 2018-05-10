package com.android.wordsmanagesystem.contact;

import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.wordsmanagesystem.BaseFragment;
import com.lqr.adapter.LQRAdapterForRecyclerView;
import com.lqr.adapter.*;
import com.lqr.recyclerview.LQRRecyclerView;
import com.android.wordsmanagesystem.R;
import com.android.wordsmanagesystem.view.QuickIndexBar;

import java.util.ArrayList;
import java.util.List;

import com.android.wordsmanagesystem.utils.SortUtils;

/**
 * @创建者 CSDN_LQR
 * @描述 通讯录
 */
public class ContactsFragment extends BaseFragment {

    private List<Contact> mContacts = new ArrayList<>();
    private LQRAdapterForRecyclerView<Contact> mAdapter;
    private int i;
    private LQRRecyclerView mRvContacts;
    private QuickIndexBar mQuickIndexBar;
    private TextView mTvLetter;
   /* @InjectView(R.id.rvContacts)
    LQRRecyclerView mRvContacts;
    @InjectView(R.id.quickIndexBar)
    QuickIndexBar mQuickIndexBar;
    @InjectView(R.id.tvLetter)
    TextView mTvLetter;*/

    //列表首尾布局
    View mHeaderView;
    //TextView mFooterTv;

    //联系人列表最上条目
    LinearLayout mLlNewFriend;
    LinearLayout mLlGroupCheat;
    LinearLayout mLlTag;
    LinearLayout mLlOffical;
   /* private View mVNewFriendUnread;
    private View mVGroupCheatUnread;*/


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_directory, null);
        mRvContacts = view.findViewById(R.id.rvContacts);
        mQuickIndexBar = view.findViewById(R.id.quickIndexBar);
        mTvLetter = view.findViewById(R.id.tvLetter);
        mQuickIndexBar.setListener(new QuickIndexBar.OnLetterUpdateListener() {
            @Override
            public void onLetterUpdate(String letter) {
                //显示字母提示
                showLetter(letter);

                //滑动对对应字母条目处
                if ("↑".equalsIgnoreCase(letter)) {
                    mRvContacts.moveToPosition(0);
                } else if ("☆".equalsIgnoreCase(letter)) {
                    mRvContacts.moveToPosition(0);
                } else {
                    //找出第一个对应字母的位置后，滑动到指定位置
                    for (i = 0; i < mContacts.size(); i++) {
                        Contact contact = mContacts.get(i);
                        String c = contact.getmPinyin().charAt(0) + "";
                        if (c.equalsIgnoreCase(letter)) {
                            mRvContacts.moveToPosition(i);
                            break;
                        }
                    }
                }
            }
        });
        initHeaderViewAndFooterView();
        return view;
    }

    @Override
    public void initData() {

        mContacts.clear();
        Contact contact = new Contact("子新",R.drawable.person1);
        Contact contact2 = new Contact("小鱼",R.drawable.person2);
        Contact contact3 = new Contact("灵瑶",R.drawable.person3);
        Contact contact4 = new Contact("静茹",R.drawable.person4);
        Contact contact5 = new Contact("诗涵",R.drawable.person5);
        Contact contact6 = new Contact("歆薇",R.drawable.person6);
        Contact contact7 = new Contact("婉如",R.drawable.person7);
        Contact contact8 = new Contact("怡蓝",R.drawable.person8);
        Contact contact9 = new Contact("霏凡",R.drawable.person9);
        mContacts.add(contact);
        mContacts.add(contact4);
        mContacts.add(contact2);
        mContacts.add(contact3);
        mContacts.add(contact5);
        mContacts.add(contact6);
        mContacts.add(contact7);
        mContacts.add(contact8);
        mContacts.add(contact9);
        setDataAndUpdateView();
    }

    private void initHeaderViewAndFooterView() {
        mHeaderView = View.inflate(getActivity(), R.layout.header_contacts_rv, null);

        mLlNewFriend = (LinearLayout) mHeaderView.findViewById(R.id.llNewFriend);
        mLlGroupCheat = (LinearLayout) mHeaderView.findViewById(R.id.llGroupCheat);
        mLlTag = (LinearLayout) mHeaderView.findViewById(R.id.llTag);
        mLlOffical = (LinearLayout) mHeaderView.findViewById(R.id.llOffical);

        /*mVNewFriendUnread = mHeaderView.findViewById(R.id.vNewFriendUnread);
        mVGroupCheatUnread = mHeaderView.findViewById(R.id.vGroupCheatUnread);*/
    }

    private void setDataAndUpdateView() {
            //整理排序
            SortUtils.sortContacts(mContacts);
            setAdapter();
    }


    private void setAdapter() {
        mAdapter = new LQRAdapterForRecyclerView<Contact>(getActivity(), R.layout.item_contact_cv, mContacts) {
            @Override
            public void convert(LQRViewHolderForRecyclerView helper, final Contact item, int position) {
                helper.setText(R.id.tvName, item.getDisplayName());
                    helper.setImageResource(R.id.ivHeader, item.getAvatar());
                String str = "";
                //得到当前字母
                String currentLetter = item.getmPinyin().charAt(0) + "";

                if (position == 0) {
                    str = currentLetter;
                } else {
                    //得到上一个字母
                    String preLetter = mContacts.get(position - 1).getDisplayName().charAt(0) + "";
                    //如果和上一个字母的首字母不同则显示字母栏
                    if (!preLetter.equals(currentLetter+"")) {
                        str = currentLetter;
                    }

                    int nextIndex = position + 1;
                    if (nextIndex < mContacts.size() - 1) {
                        //得到下一个字母
                        String nextLetter = mContacts.get(nextIndex).getmPinyin().charAt(0) + "";
                        //如果和下一个字母的首字母不同则隐藏下划线
                        if (!nextLetter.equals(currentLetter+"")) {
                            helper.setViewVisibility(R.id.vLine, View.INVISIBLE);
                        } else {
                            helper.setViewVisibility(R.id.vLine, View.VISIBLE);
                        }
                    } else {
                        helper.setViewVisibility(R.id.vLine, View.INVISIBLE);
                    }
                }
                if (position == mContacts.size() - 1) {
                    helper.setViewVisibility(R.id.vLine, View.GONE);
                }


                //根据str是否为空决定字母栏是否显示
                if (TextUtils.isEmpty(str)) {
                    helper.setViewVisibility(R.id.tvIndex, View.GONE);
                } else {
                    helper.setViewVisibility(R.id.tvIndex, View.VISIBLE);
                    helper.setText(R.id.tvIndex, currentLetter);
                }

                /*//条目点击跳转好友信息查看界面
                helper.getView(R.id.root).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), UserInfoActivity.class);
                        intent.putExtra("account", item.getAccount());
                        startActivity(intent);
                        //清空该好友的消息未读数
                        NimRecentContactSDK.clearUnreadCount(item.getAccount(), SessionTypeEnum.P2P);
                    }
                });*/

            }
        };
        //加入头部
        mAdapter.addHeaderView(mHeaderView);
        //加入脚部
        //mAdapter.addFooterView(mFooterTv);
        //设置适配器
        if (mRvContacts != null)
            mRvContacts.setAdapter(mAdapter.getHeaderAndFooterAdapter());
    }
/*
    *
     * 显示所触摸到的字母
     *
     * @param letter*/

    protected void showLetter(String letter) {
        mTvLetter.setVisibility(View.VISIBLE);// 设置为可见
        mTvLetter.setText(letter);


       /* UIUtils.getMainThreadHandler().removeCallbacksAndMessages(null);
        UIUtils.postTaskDelay(new Runnable() {
            @Override
            public void run() {
                mTvLetter.setVisibility(View.GONE);
            }
        }, 500);*/
    }

   /* *
     * 是否显示快速导航条
     *
     * @param show*/

    public void showQuickIndexBar(boolean show) {
        mQuickIndexBar.setVisibility(show ? View.VISIBLE : View.GONE);
        mQuickIndexBar.invalidate();
    }
}
