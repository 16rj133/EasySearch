package com.android.wordsmanagesystem.bean;

import java.io.Serializable;

/**
 * Created by 杨婷 on 2018/4/27.
 */

public class File implements Serializable{
    private static final long serialVersionUID = 1L;
    public int id;
    public String title;
    public String tag;
    public String type;
    public String content;
    public String url;
}
