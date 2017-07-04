package com.android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;



import com.async.test.android.R;

import java.util.ArrayList;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //SyncPoolExecutor.newFixedThreadPool(5, 2, null).isDebug(false);

        setContentView(R.layout.test_activity);
        testBeens = new ArrayList<>();
        gridView = (GridView) findViewById(R.id.gridview);
        myAdpter = new MyAdpter();
        gridView.setAdapter(myAdpter);
        progressDialog=new ProgressDialog(this);

    }

    public void addTesk(View view) {
        progressDialog.show();
        /*TaskWork t = new TaskWork(atomicLong.intValue()) {

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
            textView.setText("任务：" + testBean.getName() + "  耗时：" + (testBean.getEnd() - testBean.getStart()));

            return textView;
        }
    }

    class TestBean {
        private long start;
        private long end;
        private String name;

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


}
