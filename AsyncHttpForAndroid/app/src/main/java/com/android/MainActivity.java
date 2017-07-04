package com.android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.async.http.callback.DownProgrossCallback;
import com.async.http.entity.ResponseBody;
import com.async.test.android.R;

import java.io.File;

/*
import com.uyin.apkupdate.ApkUpdateCheck;
import com.uyin.apkupdate.listener.AppVersionCheckCallBack;
*/

//import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements DownProgrossCallback<ResponseBody<File>> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test);

    }

    @Override
    public void download_current(long current, long total) {

    }

    @Override
    public void start() {

    }

    @Override
    public void success(ResponseBody<File> result) {

    }

    @Override
    public void fail(Exception e, ResponseBody<File> request) {

    }
    /*    ProgressDialog  progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressDialog=new ProgressDialog(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ApkUpdateCheck apkUpdateCheck=new ApkUpdateCheck (new AppVersionCheckCallBack() {
                    @Override
                    public String getTitle(Object o) {
                        return "title";
                    }

                    @Override
                    public String getContent(Object o) {
                        return "content";
                    }

                    @Override
                    public String getDownURL(Object o) {
                        return "http://www.apk3.com/uploads/soft/20160511/QQ_422.apk";
                    }

                    @Override
                    public boolean isMustUpdate(Object o) {
                        return true;
                    }

                    @Override
                    public boolean isShowDialog(Object t1) {
                        return true;
                    }

                    @Override
                    public String getResultDesConstants(String str) {
                        return null;
                    }
                },null,new MDefaultNotification(getApplication()));
                apkUpdateCheck.setActivity(MainActivity.this);
                apkUpdateCheck.check(new Object());



            }
        });






        WebView  webView=new WebView(this);

    }
    private  String  getStoragePath(){
        String path= StorageUtils.getCacheDirectory(this).toString();
        File fileDir=new File(path);
        if(!fileDir.exists()){
            try {
                fileDir.mkdirs();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return path;

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    long time1=0;
    @Override
    public void start() {
        time1=System.currentTimeMillis();
        Log.e("tag1","time1="+time1);

    }


    @Override
    public void success(ResponseBody<File> result) {
        long  time2=System.currentTimeMillis();
        Log.e("tag1","time2 -time1="+(time2-time1));


    }

    @Override
    public void fail(Exception e, ResponseBody<File> request) {
            e.printStackTrace();
    }

    @Override
    public void download_current(final long current, final long total) {

                int pro = (int) (current * 100f / total);
               // progressDialog.setProgress(pro);
                Log.e("tag","progresss="+pro);

    }

*/
}
