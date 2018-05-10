package com.android.wordsmanagesystem.activity;

/**
 * Created by 杨婷 on 2018/3/13.
 */

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.wordsmanagesystem.LoginActivity;
import com.android.wordsmanagesystem.R;
import com.android.wordsmanagesystem.utils.FileUtils;
import com.othershe.dutil.DUtil;
import com.othershe.dutil.callback.UploadCallback;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class SpeakActivity extends Activity {
    private OkHttpClient okHttpClient;
    private File tempFile;
    private TextView title;
    private ImageView search;
    private Button choose;
    private Button publish;
    private TextView result;
    private String path;
    private LinearLayout ll_image_choose;
    private TextView addappendix;
    private EditText etSpeaking;
    private ImageView image_choose;
    private static final int FILE_SELECT_CODE = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_speaking);
        title = (TextView)findViewById(R.id.tm);
        search = (ImageView)findViewById(R.id.searchauto);
        etSpeaking = (EditText) findViewById(R.id.et_speaking);
        ll_image_choose = (LinearLayout)findViewById(R.id.ll_image_choose);
        image_choose = (ImageView) findViewById(R.id.image_choose);
        addappendix= (TextView)findViewById(R.id.add_appendix);
        title.setText("写日志");
        search.setVisibility(View.INVISIBLE);
        //choose = (Button)findViewById(R.id.choose);
        choose = (Button)findViewById(R.id.comeout);
        result=(TextView) findViewById(R.id.result);
        image_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });
        /*addappendix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });*/
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(SpeakActivity.this, "进入上传！", Toast.LENGTH_SHORT).show();
                //uploadFile();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            int result = enterSpeak();
                            //login()为向php服务器提交请求的函数，返回数据类型为int
                            if (result == 1) {
                                Log.e("log_tag", "发表成功！");
                                //Toast toast=null;
                                Looper.prepare();
                                Toast.makeText(SpeakActivity.this, "发表成功", Toast.LENGTH_SHORT).show();
                                //Looper.loop();
                            }else if (result == -1) {
                                Log.e("log_tag", "发表失败！");
                                //Toast toast=null;
                                Looper.prepare();
                                Toast.makeText(SpeakActivity.this, "发表失败", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }).start();
                /*SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date curDate = new Date(System.currentTimeMillis());
                String date = formatter.format(curDate);
                String text = etSpeaking.getText().toString();
                String imageAddress = result.getText().toString();*/

                //返回另一个activity
            }
        });
    }
    //打开文件选择器
    private void showFileChooser() {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult( Intent.createChooser(intent, "Select a File to Upload"), FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Please install a File Manager.",  Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    path = FileUtils.getPath(this, uri);
                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    image_choose.setImageBitmap(bitmap);
                    result.setText(path);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    /* 上传文件至Server，uploadUrl：接收文件的处理页面 */
    private void uploadFile()//将图片发送到服务器
    {
        final String url = "http://172.27.70.12:8081/upload.php";
        //File file = new File( Environment.getExternalStorageDirectory(), "haha.jpg");
        File file = new File(path);
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        File file2 = new File( Environment.getExternalStorageDirectory(), "4p3.png");
        RequestBody fileBody2 = RequestBody.create(MediaType.parse("application/octet-stream"), file2);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image1", "xy.jpg", fileBody)
                .addFormDataPart("image2", "yyw.png", fileBody2)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        final okhttp3.OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = httpBuilder
                //设置超时
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("aa", "uploadMultiFile() e=" + e);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("bb", "uploadMultiFile() response=" + response.body().string());
            }
        });
    }
    private int enterSpeak() throws IOException {
        int returnResult=0;
        /*获取用户名和密码*/
        String user_id = "123";
        String content = etSpeaking.getText().toString();
        String image = result.getText().toString();
        int like = 0;
        int commend = 0;
        int delivery = 0;
        String urlstr=SpeakActivity.this.getString(R.string.insertSpeak);
        //建立网络连接
        URL url = new URL(urlstr);
        HttpURLConnection http= (HttpURLConnection) url.openConnection();
        //往网页写入POST数据，和网页POST方法类似，参数间用‘&’连接
        String params="uid="+user_id+'&'+"content="+content+'&'+"image="+image+'&'+"like="+like+'&'+"commend="+commend+'&'+"delivery="+delivery;
        http.setDoOutput(true);
        http.setRequestMethod("POST");
        OutputStream out=http.getOutputStream();
        out.write(params.getBytes());//post提交参数
        out.flush();
        out.close();

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
            returnResult=jsonObject.getInt("status");//获取JSON数据中status字段值
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("log_tag", "the Error parsing data "+e.toString());
        }
        return returnResult;
    }
}

