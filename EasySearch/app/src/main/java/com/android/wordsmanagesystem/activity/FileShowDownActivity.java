package com.android.wordsmanagesystem.activity;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.wordsmanagesystem.R;
import com.android.wordsmanagesystem.base.WebActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 杨婷 on 2018/3/6.
 */

public class FileShowDownActivity extends Activity {
    private Button buttonDownload;
    private Button buttonLook;
    private OkHttpClient okHttpClient;
    private TextView textView;
    private ProgressBar progressBar;
    private File tempFile;
    private File targetFile;
    private String path;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_show);
        buttonDownload = (Button)findViewById(R.id.buttonDownload);
        buttonLook = (Button)findViewById(R.id.buttonLook);
        buttonLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onlineLook();
            }
        });
        buttonDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                download();
            }
        });
    }
    public void onlineLook()
    {
        /*Intent intent = new Intent(FileShowDownActivity.this, WebActivity.class);
        intent.putExtra("url",FileShowDownActivity.this.getString(R.string.fileLookIp));
        startActivity(intent);*/
        Uri uri = Uri.parse(FileShowDownActivity.this.getString(R.string.fileLookIp));
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
    public void download()
    {
        startDownloadClick();
    }
    //点击按钮开始下载文件
    public void startDownloadClick() {
        startActivity(new Intent(this, SingleTaskActivity.class));
    }
}
