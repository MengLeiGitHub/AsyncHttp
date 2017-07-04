package com.async.test.android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;



import com.android.bean.ResonseEnty;
import com.async.apkupdate.ApkUpdateOnline;
import com.async.apkupdate.dialog.ApkUpdateDialog;
/*import com.async.apkupdate.BsdiffUtils;*/
import com.async.http.android.ProxyCreater;
import com.async.http.android.StringRequestResultCallBack;
import com.async.http.entity.ResponseBody;
import com.async.http.handler.TaskHandler;
import com.async.http.proxy.MIO;
import com.async.http.utils.LogUtils;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by admin on 2017-04-12.
 */

public class PoolTestActivtiy extends Activity {
    GridView gridView;
    List<TestBean> testBeens;
    MyAdpter myAdpter;
    AtomicLong atomicLong = new AtomicLong(1);
    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            progressDialog.hide();
            TestBean testBean = (TestBean) msg.obj;
            testBeens.add(testBean);
            myAdpter.notifyDataSetChanged();

        }
    };
    ProgressDialog progressDialog;
    EditText number;
    WeatherApi weatherApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.setDebug(true);
        //SyncPoolExecutor.newFixedThreadPool(5, 2, null).isDebug(false);

       /* new Thread(){
            @Override
            public void run() {
                super.run();
                String paths=BsdiffUtils.extract(getApplication());
                Log.e("tag",paths);
               final int i= new BsdiffUtils().genDiff(paths,"/sdcard/test/app-debug.apk","/sdcard/test/apk.patch");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.setMessage(i+"");
                        progressDialog.show();
                    }
                });
            }
        }.start();*/


        setContentView(R.layout.test_activity);
        testBeens = new ArrayList<>();
        gridView = (GridView) findViewById(R.id.gridview);
        myAdpter = new MyAdpter();
        gridView.setAdapter(myAdpter);
        progressDialog = new ProgressDialog(this);
        number= (EditText) findViewById(R.id.number);
        number.setText("1");
        number.setFocusable(false);
        weatherApi= ProxyCreater.creator(WeatherApi.class);

        updateVersion();
      //  progressDialog.show();
    }

    public void addTesk(View view) {
        progressDialog.show();
        int num=Integer.parseInt(number.getText().toString());
        List<TaskHandler> taskHandlers=new LinkedList<>();
        for (int i = 0; i < num; i++) {
            final TestBean testBean = new TestBean();
            testBean.setName("" + atomicLong.addAndGet(1));
            testBean.setStart(System.currentTimeMillis());
            String[]  strings={"test"+i,"imgahn"+i};
            String[]  asdas={"asdada"+i,"asdasda"+i};
           weatherApi.getWeather(strings,asdas).ResultMonitor(MIO.IOThread).Observation(new StringRequestResultCallBack<String>(){

                @Override
                public void requestFail(Exception e, ResponseBody<String> request) {

                }

                @Override
                public void requestSuccess(String s) {
                   // testBean.setName(s);
                    testBean.setEnd(System.currentTimeMillis());
                    Message message = new Message();
                    message.obj = testBean;
                    handler.sendMessage(message);
                }

                @Override
                public void requsetFinish() {

                }

                @Override
                public void requsetStart() {
                    testBean.setTaskStart(System.currentTimeMillis());
                }
            });
           /* TaskWork t = new TaskWork(atomicLong.intValue()) {

                @Override
                public Object run(Object... objects) {
                    // TODO Auto-generated method stub
                    TestBean testBean = new TestBean();
                    testBean.setName("" + atomicLong.addAndGet(1));
                    testBean.setStart(System.currentTimeMillis());
                    return testBean;

                }


            };

            ResultObsever<TestBean> r = new ResultObsever<TestBean>() {

                public void setResult(TestBean result) {
                    // TODO Auto-generated method stub
                    result.setEnd(System.currentTimeMillis());
                    Message message = new Message();
                    message.obj = result;
                    handler.sendMessage(message);
                }
            };
            SyncPoolExecutor.execute(t, r);*/
            //taskHandlers.add(taskHandler);
        }
        for (int i=30;i<num;i++){
            /* TaskHandler handler= taskHandlers.get(i);
             handler.stop();*/
        }

    }

    public void clearTesk(View view) {
        testBeens.clear();
        myAdpter.notifyDataSetChanged();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        progressDialog.dismiss();
    }

    class MyAdpter extends BaseAdapter {

        @Override
        public int getCount() {
            return testBeens.size();
        }

        @Override
        public Object getItem(int position) {
            return testBeens.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TestBean testBean = (TestBean) getItem(position);
            TextView textView = new TextView(PoolTestActivtiy.this);
            textView.setText("任务：" + testBean.getName() + "  耗时：" + (testBean.getEnd() - testBean.getStart()) +"  中间等待："+(testBean.getTaskStart()-testBean.getStart()));

            return textView;
        }
    }

    class TestBean {
        private long start;
        private long end;
        private String name;
        private long taskStart;

        public void setTaskStart(long taskStart) {
            this.taskStart = taskStart;
        }

        public long getTaskStart() {
            return taskStart;
        }

        public long getStart() {
            return start;
        }

        public void setStart(long start) {
            this.start = start;
        }

        public long getEnd() {
            return end;
        }

        public void setEnd(long end) {
            this.end = end;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }




    private void updateVersion() {
//        Log.e("tag",VersionUtils.getAppVersionName(this));
        String urll= "http://192.168.2.110:8080/AppUpdate/servlet/AppUpdateServerlet";

        ApkUpdateOnline<ResonseEnty<VersionBean>> userApkUpdateOnline= new ApkUpdateOnline<ResonseEnty<VersionBean>>(this, urll,new UpdateDialog(this),null) {
            @Override
            public String getTitle(ResonseEnty<VersionBean> user) {

                return  "新版本"+ user.getData().getVersion();
            }

            @Override
            public String getContent(ResonseEnty<VersionBean> user) {
                //dialog显示content内容

                return  user.getData().getUpdateLog();
            }

            @Override
            public String getDownURL(ResonseEnty<VersionBean> user) {

                return  user.getData().getAddress();
            }

            @Override
            public String getIncrementDownURL(ResonseEnty<VersionBean> versionBeanResonseEnty) {
                Log.e("tag","versionBeanResonseEnty.getData().getPatchAddress()="+versionBeanResonseEnty.getData().getPatchAddress());
                return versionBeanResonseEnty.getData().getPatchAddress();
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

            @Override
            public String getDownApkVersionName(ResonseEnty<VersionBean> versionBeanResonseEnty) {

                return versionBeanResonseEnty.getData().getVersion();
            }

        };
        ApkUpdateDialog  apkUpdateDialog;
        userApkUpdateOnline.setAckDialog(apkUpdateDialog=new ApkUpdateDialog(this) {

            @Override
            public int getLayout() {
                return com.async.apkupdate.R.layout.app_update_custom_dialog;
            }

            @Override
            public int getCancleViewId() {
                return com.async.apkupdate.R.id.cancel;
            }



            public int getWholeViewId() {
                return com.async.apkupdate.R.id.confirm;
            }

            @Override
            public int getTitleViewId() {
                return com.async.apkupdate.R.id.dialog_title;
            }

            @Override
            public int getContentViewId() {
                return com.async.apkupdate.R.id.dialog_deatail;
            }

            @Override
            public int customeDialogWidth(int fullWidth) {
                return (int) (fullWidth*0.9);
            }

             public int getIncrementId() {
                return 0;
            }


        });
        apkUpdateDialog.setDialogTitle("当前网络是非wifi网络");
        apkUpdateDialog.setContentText(" 确定要下载吗？");
        userApkUpdateOnline.CheckByGet();

       // progressDialog.show();
    }


}
