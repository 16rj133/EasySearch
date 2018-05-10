package com.android.wordsmanagesystem.contact;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;


import com.android.wordsmanagesystem.utils.PinyinUtils;

import java.io.Serializable;

/**
 * @创建者 CSDN_LQR
 * @描述 联系人（好友）信息
 */
public class Contact implements Comparable<Contact>, Serializable {

    //private String mAccount;//账号
    private String mDisplayName;//要显示的名字（没有备注的话就显示昵称）
    //private String mName;//昵称
    //private String mAlias;//备注
    private String mPinyin;//昵称/备注的全拼
    //private Friend mFriend;//你的好友信息
    //private NimUserInfo mUserInfo;//好友自己的信息
    private int mAvatar;//头像地址
//    private List<String> mAccounts;//要查询用户信息的用户账号

    public Contact(String mDisplayName,int mAvatar) {
        this.mDisplayName = mDisplayName;
        this.mAvatar = mAvatar;
        this.mPinyin = PinyinUtils.getPinyin(mDisplayName);
    }
    public int getAvatar() {
        return mAvatar;
    }

    public void setAvatar(int avatar) {
        mAvatar = avatar;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public void setDisplayName(String displayName) {
        mDisplayName = displayName;
    }

    public String getmPinyin() {
        return mPinyin;
    }

    public void setmPinyin(String displayName) {
        mPinyin = PinyinUtils.getPinyin(displayName);
    }

    @Override
    public int compareTo(Contact o) {
        return this.mPinyin.compareTo(o.getmPinyin());
    }
}
