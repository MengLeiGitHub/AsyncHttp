package com.async.pool;

import java.util.LinkedList;

import com.async.pool.AfterService.AfterServiceManager;
import com.async.pool.BeforeService.BeforeCustomerService;
import com.async.pool.BeforeService.BeforeServiceManager;
import com.async.pool.ConstructionCenter.Captain;
import com.async.pool.ConstructionCenter.TaskWork;
import com.async.pool.Log.Log;
import com.async.pool.RecordCenter.RecordManager;
import com.async.pool.RecordCenter.after.AfterTaskCenter;
import com.async.pool.RecordCenter.after.AfterTaskManager;
import com.async.pool.RecordCenter.before.BeforeTaskCenter;
import com.async.pool.RecordCenter.before.BeforeTaskManager;
import com.async.pool.msg.CustomMessage;
import com.async.pool.msg.DefaultMsgFilter;
import com.async.pool.msg.MsgFilter;
import com.async.pool.msg.ResultObsever;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		run3();

		//System.out.println(Integer.MAX_VALUE+"");

	}


	static	void run1(){
		/**
		 * 开启 备案消息中心
		 */
		BeforeTaskManager.Call().setBeforeTaskHanlder(BeforeTaskCenter.handler());

		AfterTaskManager.Call().setAfterTaskHandler(AfterTaskCenter.Call());


		/**
		 * 开启售后服务
		 */
		AfterServiceManager.Call(2).setAfterTaskHandler(RecordManager.Call());


		/**
		 * 消息过滤器
		 */
		MsgFilter filter=new DefaultMsgFilter();



		/**
		 * 开启售前客服管理
		 */
		BeforeServiceManager beforeServiceManager=	BeforeServiceManager.Call().addMsgFilter(filter);

		beforeServiceManager.registerAfterTaskObsever(AfterTaskManager.Call());

		beforeServiceManager.registerRecordCenterHandlerObsever(RecordManager.Call());

		BeforeCustomerService.call().registerObserver(beforeServiceManager);



		LinkedList<CustomMessage > list=new LinkedList<CustomMessage >();
		for(int i=0;i<20;i++){
			TaskWork taskWork=new TaskWork (i){

				@Override
				public Object run(Object... objects) {
					// TODO Auto-generated method stub
					return "我想草拟"+objects[0] +" isStop="+objects[1];

				}



				@Override
				public int getTaskPriority() {
					// TODO Auto-generated method stub
					return 0;
				}




			};

			CustomMessage  customMessage=new CustomMessage(taskWork);
			customMessage.setObj(new ResultObsever<String>() {

				public void setResult(String result) {
					// TODO Auto-generated method stub
					Log.e(result);
				}
			});
			list.add(customMessage);
		}

		BeforeCustomerService.call().sendMsgs(list);
		/**
		 * 开启  工作队
		 */
		Captain.Call(5);

		list.clear();
		for(int i=0;i<20;i++){

			TaskWork taskWork=new TaskWork (i){
				@Override
				public Object run(Object... objects) {
					// TODO Auto-generated method stub
					return "我想草拟"+objects[0] +" isStop="+objects[1];

				}

				/**
				 * getPriority
				 */



			};

			CustomMessage  customMessage=new CustomMessage(taskWork);
			customMessage.setObj(new ResultObsever<String>() {

				public void setResult(String result) {
					// TODO Auto-generated method stub
					System.out.println(result);
				}
			});
			list.add(customMessage);
		}

		//BeforeCustomerService.call().sendMsgs(list);
		SyncPoolExecutor.newFixedThreadPool(4, 2, null).execute(list);

	}

	static void  run2(){
		SyncPoolExecutor.newFixedThreadPool(5, 2,null).isDebug(false);

		for(int i=0;i<23000000;i++){

			TaskWork t=new TaskWork(i) {

				@Override
				public Object run(Object... objects) {
					// TODO Auto-generated method stub
					//System.out.println("TaskInterface ="	+Thread.currentThread().getClass().getSimpleName());

					return "我是 任务 "+objects[0] +" isStop="+objects[1];

				}


			};

			ResultObsever<String> r=	new ResultObsever<String>() {

				public void setResult(String result) {
					// TODO Auto-generated method stub
					System.out.println(result);
					//System.out.println("ResultObsever ="	+Thread.currentThread().getClass().getSimpleName());

				}
			};
			SyncPoolExecutor.execute(t, r);
		}
	}

	static void  run3(){


		LinkedList<CustomMessage > list=new LinkedList<CustomMessage >();

		long  time1=System.currentTimeMillis();

		for(int i=0;i<1000;i++){

			TaskWork taskWork=new TaskWork (i){

				@Override
				public Object run(Object... objects) {
					// TODO Auto-generated method stub
					return "我想草拟"+objects[0] +" isStop="+objects[1];
				}





			};

			CustomMessage  customMessage=new CustomMessage(taskWork);
			customMessage.setObj(new ResultObsever<String>() {

				public void setResult(String result) {
					// TODO Auto-generated method stub
					System.out.println(result);
				}
			});
			customMessage.setMid(i);
			list.add(customMessage);
		}
		long  time2=System.currentTimeMillis();
		System.out.println(time1-time2);

		//BeforeCustomerService.call().sendMsgs(list);
		SyncPoolExecutor.newFixedThreadPool(5, 1, null).isDebug(true).execute(list);

		long  time3=System.currentTimeMillis();
		System.out.println(time3-time2);
	}

}
