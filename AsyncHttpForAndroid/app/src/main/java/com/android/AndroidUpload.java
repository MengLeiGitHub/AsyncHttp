package com.android;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.AsyncHttpForAndroid.AsyncHttpUtils;
import com.AsyncHttpForAndroid.CProxyRequester;
import com.AsyncHttpForAndroid.ProxyCreater;
import com.AsyncHttpForAndroid.StringRequestResultCallBack;
import com.AsyncHttpForAndroid.UploadRequestResultCallBack;

/*
import com.async.apkupdate.ApkUpdateOnline;
*/

import com.android.bean.DownloadTest;
import com.android.bean.IPBean;
import com.android.bean.LoginApi;
import com.android.bean.UploadResultBean;
import com.android.bean.UploadTest;
import com.android.bean.User;
import com.async.http.callback.DownProgrossCallback;
import com.async.http.client.HttpMethod;
import com.async.http.entity.ResponseBody;
import com.async.http.proxy.Creator;
import com.async.http.proxy.MIO;
import com.async.test.android.R;



import java.io.File;
import java.util.ArrayList;

/*import com.async.http.android.AsyncHttpUtils;
import com.async.http.android.ProxyCreater;
import com.async.http.android.StringRequestResultCallBack;
import com.async.http.android.UploadRequestResultCallBack;*/
/*
import com.uyin.apkupdate.ApkUpdateCheck;
import com.uyin.apkupdate.ApkUpdateOnline;
import com.uyin.apkupdate.listener.AppVersionCheckCallBack;
*/

/**
 * Created by admin on 2016-11-05.
 */
public class AndroidUpload extends Activity implements View.OnClickListener{
    ProgressBar  xiazai_pr,progressBar_shangchuan;
    TextView xiazai_text,shangchuan_text,String_text,json_text;
    private int    requestTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      //  com.async.pool.Log.Log.setDebug(true);

        setContentView(R.layout.activity_upload);
        this.findViewById(R.id.xiazai).setOnClickListener(this);

        this.findViewById(R.id.Stringrequest).setOnClickListener(this);
        this.findViewById(R.id.jsongrequest).setOnClickListener(this);

        this.findViewById(R.id.shangchuan).setOnClickListener(this);
        xiazai_pr= (ProgressBar) findViewById(R.id.xiazai_pr);
        xiazai_pr.setMax(100);
        progressBar_shangchuan= (ProgressBar) findViewById(R.id.progressBar_shangchuan);
        progressBar_shangchuan.setMax(100);
        xiazai_text= (TextView) findViewById(R.id.xiazai_text);
        shangchuan_text= (TextView) findViewById(R.id.shangchuan_text);
        String_text= (TextView) findViewById(R.id.string_text);
        json_text= (TextView) findViewById(R.id.json_text);
        this.findViewById(R.id.versioncheck).setOnClickListener(this);

         Handler handler=new Handler(Looper.getMainLooper()){

         };



          /*  PathBeanTest  t=   ProxyCreater.creator(PathBeanTest.class);
            String jsno="{'out_trade_no':'20161226012651663000',\"paypasswd\":\"MTIzNDU2\",\"paytype\":\"dadapay\",\"serviceid\":86963,\"servicetype\":\"parkingpay\"}";
            t.testss("20161226012651663000","MTIzNDU2",
                    "dadapay",86963,"parkingpay").ResultMonitor(MIO.MainThread).Observation(new StringRequestResultCallBack<ResonseEnty>() {

                @Override
                public void requestFail(Exception e, ResponseBody<String> request) {
                    e.printStackTrace();
                }

                @Override
                public void requestSuccess(ResonseEnty resonseEnty) {
                 //   Log.e("tag",resonseEnty.getData().toString());
                   // Toast.makeText(getApplication(),"asd"+resonseEnty.getData().toString(),Toast.LENGTH_LONG).show();
                    Log.e("tag",resonseEnty.getData().toString());
                }

                @Override
                public void requsetFinish() {

                }

                @Override
                public void requsetStart() {

                }
            });*/

     /*   MySelftApi mySelftApi= ProxyCreater.creator(MySelftApi.class);
        mySelftApi.update("nickname","",null,"postino","wwww","1").ResultMonitor(MIO.IOThread).Observation(new StringRequestResultCallBack<ResonseEnty<TestBeans>>() {
            @Override
            public void requestFail(Exception e, ResponseBody<String> request) {
                    e.printStackTrace();
            }

            @Override
            public void requestSuccess(ResonseEnty<TestBeans> resonseEnty) {
                    //    Log.e("tag","===="+resonseEnty.getData().toString());
            }

            @Override
            public void requsetFinish() {

            }

            @Override
            public void requsetStart() {

            }
        });*/





       /* String results="{\"errcode\":10000,\"status\":0,\"msg\":\"您还未登录哦\",\"data\":null}";
        StringRequestResultCallBack requestResultCallBack=   new StringRequestResultCallBack<com.async.test.android.bean.ResonseEnty<TestBeans>>() {
            @Override
            public void requestFail(Exception e, ResponseBody<String> request) {

            }

            @Override
            public void requestSuccess(com.async.test.android.bean.ResonseEnty<TestBeans> testBeansResonseEnty) {

            }

            @Override
            public void requsetFinish() {

            }

            @Override
            public void requsetStart() {

            }
        };
        ResponseBody<String> request=new ResponseBody<>();
        request.setResult(results);
        requestResultCallBack.requestSuccess(null,request);
*/

     //   new Gson().fromJson(results,)

        Log.e("tag","");


    //    updateVersion();







         /*   t.getAll().ResultMonitor(MIO.MainThread).Observation(new StringRequestResultCallBack<ResonseEnty>() {
                @Override
                public void requestFail(Exception e, ResponseBody<String> request) {
                    Log.e("tag",e.getMessage());
                    e.printStackTrace();
                }
                    //ResonseEnty<ListBean<Province<CityListBean>>>
                @Override
                public void requestSuccess(ResonseEnty factoryTestBean) {
                  //  Log.e("tag",factoryTestBean.getMsg()+"  "+factoryTestBean.getData().getList().size());
                    Log.e("tag",""+new Gson().toJson(factoryTestBean));
                }

                @Override
                public void requsetFinish() {

                }

                @Override
                public void requsetStart() {

                }
            });

            */
           /* new Thread(){

                @Override
                public void run() {
                    super.run();
                    try {
                        new NetWork().login("https://120.26.106.136:8443/rest/common/user/login.do");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();*/



    }
/*
    private void updateVersion() {
        String urll="rest/common/"+ "1.0"+"/checkVersion.do";
        ApkUpdateOnline<ResonseEnty<VersionBean>> userApkUpdateOnline= new ApkUpdateOnline<ResonseEnty<VersionBean>>(this, urll) {
            @Override
            public String getTitle(ResonseEnty<VersionBean> user) {

                return  "新版本"+user.getData().getVersion();
            }

            @Override
            public String getContent(ResonseEnty<VersionBean> user) {
                //dialog显示content内容

                return user.getData().getUpdateLog();
            }

            @Override
            public String getDownURL(ResonseEnty<VersionBean> user) {

                return  user.getData().getAddress();
            }

            @Override
            public boolean isMustUpdate(ResonseEnty<VersionBean> user) {
                //是否必须更新，如果为true 关闭dialog app则会关闭
                return user.getData().isIsForce();
            }

            @Override
            public boolean isShowDialog(ResonseEnty<VersionBean> t1) {
                //是否显示dialog
                return t1.isSuccess();
            }

            @Override
            public String getResultDesConstants(String str) {
                //这个是对于网络数据加密的，如果数据没加密无需操作该方法
                return null;
            }

        };

        //  HashMap map=new HashMap<>();
       */
/* map.put("username","15093201628");
        map.put("password","e565f08d058ebb4a1c99907a9860a93b");*//*

       userApkUpdateOnline.CheckByGet();
    }
*/


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.xiazai:
               /* AsyncHttpUtils.download("http://120.26.106.136:8080/web/factory/attendance/3/viewInOnline.do?month=2017-03", "/sdcard/test/", "file.xls", new DownProgrossCallback<ResponseBody<File>>() {
                    @Override
                    public void download_current(long current, long total) {
                        Log.e("tag", "current=" + current + " total=" + total);
                        int pr = (int) (current * 100.0 / total);
                        xiazai_pr.setProgress(pr);
                    }

                    @Override
                    public void start() {
                        Toast.makeText(getApplication(), "开始下载", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void finish() {
                        Toast.makeText(getApplication(), "完成", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void success(ResponseBody<File> result) {
                        Toast.makeText(getApplication(), "成功下载" + result.getResult().getPath(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void fail(Exception e, ResponseBody<File> request) {
                        e.printStackTrace();
                        Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });*/

                DownloadTest downloadTest=ProxyCreater.creator(DownloadTest.class);

                downloadTest.down("/sdcard/test/game.xls",3).ResultMonitor(MIO.MainThread).Observation(new DownProgrossCallback<ResponseBody<File>>() {
                    @Override
                    public void download_current(long current, long total) {
                      //  Log.e("tag", "current=" + current + " total=" + total);
                        int pr = (int) (current * 100.0 / total);
                        xiazai_pr.setProgress(pr);
                    }

                    @Override
                    public void start() {
                        Toast.makeText(getApplication(), "开始下载", Toast.LENGTH_SHORT).show();
                        xiazai_pr.setMax(100);
                    }

                    @Override
                    public void finish() {
                        Toast.makeText(getApplication(), "完成", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void success(ResponseBody<File> result) {
                        Log.e("tag",result.getResult().getAbsolutePath());
                    }

                    @Override
                    public void fail(Exception e, ResponseBody request) {
                            e.printStackTrace();
                    }
                });

                break;
            case R.id.shangchuan:
                Toast.makeText(getApplicationContext(),"shangchuan", Toast.LENGTH_LONG).show();
                UploadTest uploadTest= ProxyCreater.creator(UploadTest.class);
                uploadTest.upload("/sdcard/test/file.jpg").ResultMonitor(MIO.MainThread).Observation(new UploadRequestResultCallBack<UploadResultBean>(){

                    @Override
                    public void upload_current(long current, long currentFileTotal, long total) {
                        int pr = (int) (current * 100.0 / total);
                        Log.e("tag", "current=" + current + " currentFileTotal= " + currentFileTotal + "  total=" + total);
                        progressBar_shangchuan.setProgress(pr);
                    }

                    @Override
                    public void requestFail(Exception e, ResponseBody<String> request) {
                            e.printStackTrace();
                    }

                    @Override
                    public void requestSuccess(UploadResultBean uploadResultBean) {
                        Log.e("tag",uploadResultBean.getMsg());
                      //  Toast.makeText(getApplicationContext(),uploadResultBean.getData().getName(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void requsetFinish() {

                    }

                    @Override
                    public void requsetStart() {
                        Toast.makeText(getApplicationContext(),"开始", Toast.LENGTH_LONG).show();

                    }
                });




/*



                 AsyncHttpUtils.upload("http://192.168.1.33:7878", "/sdcard/test/1.apk", "file", new UploadRequestResultCallBack<com.async.test.android.bean.ResonseEnty>() {

                     @Override
                     public void upload_current(long current, long currentFileTotal, long total) {
                         int pr = (int) (current * 100.0 / total);
                         Log.e("tag", "current=" + current + " currentFileTotal= " + currentFileTotal + "  total=" + total);
                         progressBar_shangchuan.setProgress(pr);
                     }

                     @Override
                     public void requestFail(Exception e, ResponseBody<String> request) {

                     }

                     @Override
                     public void requestSuccess(com.async.test.android.bean.ResonseEnty resonseEnty) {
                        Log.e("tag","result="+new Gson().toJson(resonseEnty));
                     }

                     @Override
                     public void requsetFinish() {

                     }

                     @Override
                     public void requsetStart() {

                     }
                 });
*/


                break;
            case R.id.Stringrequest:
                    AsyncHttpUtils.string("http://ip.taobao.com/service/getIpInfo.php?ip=63.223.108.42", HttpMethod.Get, null, new StringRequestResultCallBack<IPBean>() {
                        @Override
                        public void requestFail(Exception e, ResponseBody<String> request) {
                            e.printStackTrace();
                        }

                        @Override
                        public void requestSuccess(IPBean ipBean) {
                            Toast.makeText(AndroidUpload.this, ipBean.getData().getIp(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void requsetFinish() {

                        }

                        @Override
                        public void requsetStart() {

                        }
                    });
                break;
            case R.id.jsongrequest:
             /*   //username":"15093201628","password":"e565f08d058ebb4a1c99907a9860a93b"
                HashMap<String ,String> map=new HashMap<>();

                HashMap<String, String> hashmap=new HashMap<>();
                hashmap.put("factoryId", "15");
                hashmap.put("type","2");
                hashmap.put("machineBrand", "啊是的你");
                hashmap.put("machineModel","123");
                hashmap.put("uniqueId","15");
                hashmap.put("manufacturerPhone","15093201628");
                hashmap.put("productionTime","2010-1-1");
                hashmap.put("info","");
                hashmap.put("note","");
                hashmap.put("rate","1000");
                hashmap.put("machinePic","http://www.u-yin.cn/a.png");



                AsyncHttpUtils.json("http://120.26.106.136:8080/rest/factory/machine/add.do", HttpMethod.Post, hashmap, new StringRequestResultCallBack<User>() {


                    @Override
                    public void requestFail(Exception e, ResponseBody<String> request) {
                        e.printStackTrace();
                    }

                    @Override
                    public void requestSuccess(User provinc) {
                        Toast.makeText(AndroidUpload.this, provinc.getMsg(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void requsetFinish() {

                    }

                    @Override
                    public void requsetStart() {

                    }
                });*/
                LoginApi loginApi= ProxyCreater.creator(LoginApi.class);
                loginApi.Login("15093201628","e565f08d058ebb4a1c99907a9860a93b").ResultMonitor(MIO.MainThread).Observation(new StringRequestResultCallBack<User>(){

                    @Override
                    public void requestFail(Exception e, ResponseBody<String> request) {
                        e.printStackTrace();
                    }

                    @Override
                    public void requestSuccess(User user) {

                    }

                    @Override
                    public void requsetFinish() {

                    }

                    @Override
                    public void requsetStart() {

                    }
                });



                break;
            case R.id.versioncheck:
               /* ApkUpdateOnline<User> userApkUpdateOnline= new ApkUpdateOnline<User>(this, "http://120.26.106.136:8080/rest/common/user/login.do") {
                    @Override
                    public String getTitle(User user) {
                        return user.getData().getUsername();
                    }

                    @Override
                    public String getContent(User user) {
                        return user.getData().getUsername();
                    }

                    @Override
                    public String getDownURL(User user) {
                        return user.getData().getAvatar();
                    }

                    @Override
                    public boolean isMustUpdate(User user) {
                        return false;
                    }

                    @Override
                    public boolean isShowDialog(User t1) {
                        return true;
                    }

                    @Override
                    public String getResultDesConstants(String str) {
                        return null;
                    }
                };
                HashMap   map=new HashMap<>();
                map.put("username","15093201628");
                map.put("password","e565f08d058ebb4a1c99907a9860a93b");
               // userApkUpdateOnline.setResultBean(User.class);

                userApkUpdateOnline.CheckByJSONPost(map);

                ApkUpdateCheck apkUpdateCheck=new ApkUpdateCheck(new AppVersionCheckCallBack<User>(){

                    @Override
                    public String getTitle(User user) {
                        return null;
                    }

                    @Override
                    public String getContent(User user) {
                        return null;
                    }

                    @Override
                    public String getDownURL(User user) {
                        return user.getMsg();
                    }

                    @Override
                    public boolean isMustUpdate(User user) {
                        return false;
                    }

                    @Override
                    public boolean isShowDialog(User t1) {
                        return false;
                    }

                    @Override
                    public String getResultDesConstants(String str) {
                        return null;
                    }
                });
                User user=new User();
                user.setMsg("下载地址");
                apkUpdateCheck.check(user);
*/
                break;
        }


    }





}
