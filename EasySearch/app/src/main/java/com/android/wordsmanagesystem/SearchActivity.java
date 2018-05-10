package com.android.wordsmanagesystem;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.wordsmanagesystem.adapter.HistoryAdapter;
import com.android.wordsmanagesystem.bean.File;
import com.android.wordsmanagesystem.utils.KeyBoardUtils;

/**
 * Created by 杨婷 on 2018/2/7.
 */
import com.android.wordsmanagesystem.utils.SearchAdapter;

import com.android.wordsmanagesystem.view.CustomDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends Activity implements TextWatcher
{
    private EditText etsearch;
    private TextView searchcancel;
    private CustomDialog mDialogWaiting;
    private ImageView searchstart;
    private ListView lvhistory;
    private HistoryAdapter historyAdapter;
    private ArrayList<File> fileArrayList;
    private String[] str = {"基金投资的流程？", "速记有什么秘诀吗？怎样快速的记住有用信息", "快速入门金融学需要掌握的知识有哪些"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_konwlege);
        etsearch = (EditText) findViewById(R.id.search);
        searchcancel = (TextView) findViewById(R.id.search_cancel);
        searchstart = (ImageView) findViewById(R.id.search_start);
        lvhistory = (ListView) findViewById(R.id.lv_history);
        historyAdapter = new HistoryAdapter(SearchActivity.this,str);
        lvhistory.setAdapter(historyAdapter);
        etsearch.addTextChangedListener(this);
        //监听键盘回车或搜索
        etsearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    if (TextUtils.isEmpty(etsearch.getText().toString().trim())) {
                        KeyBoardUtils.closeKeybord(etsearch, SearchActivity.this);
                        Toast.makeText(SearchActivity.this,"请输入关键词", Toast.LENGTH_LONG).show();
                    } else {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    int result = doSearch();
                                    //login()为向php服务器提交请求的函数，返回数据类型为int
                                    if (result == 1) {
                                        Log.e("log_tag", "搜索成功！");
                                        //Toast toast=null;
                                        Looper.prepare();
                                        Toast.makeText(SearchActivity.this, "搜索成功！", Toast.LENGTH_SHORT).show();
                                        if(fileArrayList.size()==0)
                                        {
                                            Toast.makeText(SearchActivity.this, "搜索成功长度为0", Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            Toast.makeText(SearchActivity.this,"搜索成功长度有的",Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(SearchActivity.this,ConlusionActivity.class);
                                            intent.putExtra("resultList",(Serializable) fileArrayList);//将结果数组传到结果Activity中展示
                                            startActivity(intent);//这部分完全成功
                                        }

                                    } else{
                                        Log.e("log_tag", "搜索错误！");
                                        //Toast toast=null;
                                        Looper.prepare();
                                        Toast.makeText(SearchActivity.this, "搜索到零条结果，请重新输入！", Toast.LENGTH_SHORT).show();
                                        Looper.loop();
                                    }
                                } catch (IOException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        }).start();
                    }
                    return true;
                }
                return false;
            }
        });
        searchstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            int result = doSearch();
                            //login()为向php服务器提交请求的函数，返回数据类型为int
                            if (result == 1) {
                                Log.e("log_tag", "搜索成功！");
                                //Toast toast=null;
                                Looper.prepare();
                                Toast.makeText(SearchActivity.this, "搜索成功！", Toast.LENGTH_SHORT).show();
                                if(fileArrayList.size()==0)
                                {
                                    Toast.makeText(SearchActivity.this, "搜索成功长度为0", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(SearchActivity.this,"搜索成功长度有的",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SearchActivity.this,ConlusionActivity.class);
                                    intent.putExtra("resultList",(Serializable) fileArrayList);//将结果数组传到结果Activity中展示
                                    startActivity(intent);//这部分完全成功
                                }
                            } else{
                                Log.e("log_tag", "搜索错误！");
                                //Toast toast=null;
                                Looper.prepare();
                                Toast.makeText(SearchActivity.this, "搜索到零条结果，请重新输入！", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }).start();
            }
        });
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
        // TODO Auto-generated method stub
    }
    //监听里面有文字后可以做的改变
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (TextUtils.isEmpty(etsearch.getText().toString().trim())) {
            /*searchlayout.setVisibility(View.GONE);*/
        } else {
            /*searchlayout.setVisibility(View.VISIBLE);
            mTvMsg.setText(s);*/
        }
    }
    @Override
    public void afterTextChanged(Editable s) {

    }
    private int doSearch() throws IOException{
        int returnResult=0;
        /*获取用户名和密码*/
        String input_search = etsearch.getText().toString();
        if(input_search==null||input_search.length()<=0){
            Looper.prepare();
            Toast.makeText(SearchActivity.this,"请输入关键词", Toast.LENGTH_LONG).show();
            Looper.loop();
            return 0;

        }
        String urlstr=SearchActivity.this.getString(R.string.fileSearch);
        //建立网络连接
        URL url = new URL(urlstr);
        HttpURLConnection http= (HttpURLConnection) url.openConnection();//这部分没错
        //往网页写入POST数据，和网页POST方法类似，参数间用‘&’连接
        String params="search="+input_search;
        http.setDoOutput(true);
        http.setRequestMethod("POST");
        OutputStream out=http.getOutputStream();
        out.write(params.getBytes());//post提交参数
        out.flush();
        out.close();
        //Toast.makeText(SearchActivity.this,"这部分没错", Toast.LENGTH_LONG).show();
        //读取网页返回的数据
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(http.getInputStream()));//获得输入流
        String line="";
        StringBuilder sb=new StringBuilder();//建立输入缓冲区
        while (null!=(line=bufferedReader.readLine())){//结束会读入一个null值
            sb.append(line);//写缓冲区
        }
        String result= sb.toString();//返回结果
        try {
        /*获取服务器返回的JSON数据*/
            JSONObject jsonObject= new JSONObject(result);
            returnResult=jsonObject.getInt("status");//获取JSON数据中status字段值,由此可知是否成功搜索到关键词
            if(returnResult==0)
            {
                Toast.makeText(SearchActivity.this,"搜索到0条结果，请重新查询",Toast.LENGTH_LONG);
                return 0;
            }
            else if(returnResult==1)
            {
                JSONArray jsonArray = jsonObject.getJSONArray("result");
                fileArrayList = new ArrayList<File>();
                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    File file = new File();
                    file.id = i;
                    file.title = jsonObject1.getString("title");
                    file.tag = jsonObject1.getString("tag");
                    file.type = jsonObject1.getString("type");
                    file.content = jsonObject1.getString("content");
                    file.url = "http://baidu.com";
                    fileArrayList.add(file);
                }
            }
        } catch (Exception e) {
            Log.e("log_tag", "the Error parsing data "+e.toString());
        }
        return returnResult;
    }
    /**
     * 显示等待提示框
     */
    public Dialog showWaitingDialog(String tip) {
        hideWaitingDialog();
        View view = View.inflate(this, R.layout.dialog_waiting, null);
        if (!TextUtils.isEmpty(tip))
            ((TextView) view.findViewById(R.id.tvTip)).setText(tip);
        mDialogWaiting = new CustomDialog(this, view, R.style.dialog);
        mDialogWaiting.show();
        mDialogWaiting.setCancelable(false);
        return mDialogWaiting;
    }

    /**
     * 隐藏等待提示框
     */
    public void hideWaitingDialog() {
        if (mDialogWaiting != null) {
            mDialogWaiting.dismiss();
            mDialogWaiting = null;
        }
    }
}

