package com.async.pool.ConstructionCenter;

import java.util.LinkedList;

import com.async.pool.AfterService.AfterDataManager;
import com.async.pool.BeforeService.Observer;
import com.async.pool.Log.Log;
import com.async.pool.RecordCenter.RecordCenterHandler;
import com.async.pool.RecordCenter.RecordManager;
import com.async.pool.msg.CustomMessage;
import com.async.pool.msg.Result;



/**
 * 队长，负责的队员的准备工作的落实，和任务的具体派发，
 * 任务完成的情况，交付备案中心
 *
 * @author ml
 *
 */
public class Captain implements Observer<Result> {

	private Thread  handler;
	private RecordCenterHandler recordCenterHandler;
	private int   DEFAULT_WORKER_NUM=2;

	private int   DEFAILT_HIGHT_NUMS=1;


	/*
	 * 工作队列
	 */
	private LinkedList<TeamMember> workQuece = null;

	public	static Captain  captain;

	public  synchronized static  Captain  Call(int memberNums){
		if(captain==null)captain=new Captain(memberNums);
		return captain;
	}
	public  synchronized static  Captain  Call(int memberNums,int higherNums){
		if(captain==null)captain=new Captain(memberNums);

		return captain;
	}

	public  Captain(int work_thead_num){
		recordCenterHandler=RecordManager.Call();
		workQuece=new LinkedList<TeamMember>();

		creatWorkThead(work_thead_num);
		startWorkThead();
	}

	public  Captain(int work_thead_lowest_num,int higherNums){
		if(work_thead_lowest_num<=0){
			work_thead_lowest_num=1;
		}
		if(higherNums<=0){
			higherNums=1;
		}
		DEFAILT_HIGHT_NUMS=higherNums;

		recordCenterHandler=RecordManager.Call();
		workQuece=new LinkedList<TeamMember>();

		creatWorkThead(work_thead_lowest_num+higherNums);

		startWorkThead();
	}




	private void creatWorkThead(int work_thead_num) {
		// TODO Auto-generated method stub
		if(DEFAULT_WORKER_NUM>work_thead_num)work_thead_num=DEFAULT_WORKER_NUM;

		while (work_thead_num-- > 0) {
			TeamMember teamworker = new TeamMember(work_thead_num);
			teamworker.registerObserver(this);
			Thread thread = new Thread(teamworker);
			workQuece.add(teamworker);
			thread.start();
		}
	}







	private void startWorkThead() {
		// TODO Auto-generated method stub
		handler = new Thread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					// synchronized (this) {
					if (recordCenterHandler.BeforeSize() == 0) {
						synchronized (this) {
							try {
								Log.e(" 施工队 队长  等待 ");

								wait(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					} else {
						while (recordCenterHandler.BeforeSize() != 0) {

							int workSize = workQuece.size();
							//执行 低 等级的任务
							for (int worknum = 0; worknum < workSize; worknum++) {

								TeamMember teamworker = workQuece
										.get(worknum);

								synchronized (teamworker) {
									if (!teamworker.isRuning()
											&& recordCenterHandler.BeforeSize() > 0) {

										if(worknum<DEFAILT_HIGHT_NUMS){
											//如果工作线程的数量  》3  将保证至少有两个线程专门执行 string 的请求   ，否则  下载任务  执行的时间过长的话
											//将会有  stringreques 的假死的 情况
											////如果工作线程的数量  < 3   至少保证有一个线程执行String 的请求

											CustomMessage custommsg =recordCenterHandler.getHightMsg();

											if(custommsg!=null){
												teamworker.setCustomMessage(custommsg);

												teamworker.setRuning(true);

												teamworker.notify();

											}

											continue;

										}else{

											//优先 执行 高级任务  ，高级任务全部执行完之后，才会执行  低级任务
											//但是程序会 保证 有一个程序 一直执行  高级任务

											CustomMessage custommsg =recordCenterHandler.getHightMsg();

											if(custommsg!=null){


											} else{
												custommsg=recordCenterHandler.getMsg();
											}


											teamworker.setCustomMessage(custommsg);

											teamworker.setRuning(true);

											teamworker.notify();



										}
									}

								}

							}














						}
					}
				}

			}
			// }

		});
		handler.start();
	}


	public synchronized void Observe(Result result) {
		// TODO Auto-generated method stub
		synchronized ( AfterDataManager.Call().getResultQuece()) {
			AfterDataManager.Call().getResultQuece().add(result);
			//AfterDataManager.Call().getResultQuece().wait();
			AfterDataManager.Call().getResultQuece().notify();
			/* try {
				    AfterDataManager.Call().getResultQuece().add(result);
 					AfterDataManager.Call().getResultQuece().wait();
					AfterDataManager.Call().getResultQuece().notify();
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/

		}




	}


	public synchronized void Observe(LinkedList<Result> listmsg) {
		// TODO Auto-generated method stub

	}




}
