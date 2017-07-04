package com.async.pool.AfterService;

import java.util.LinkedList;

import com.async.pool.Log.Log;
import com.async.pool.RecordCenter.RecordManager;
import com.async.pool.RecordCenter.after.AfterTaskHandler;
import com.async.pool.msg.Result;
import com.async.pool.msg.ResultObsever;

/**
 * 售后服务中心，经理，负责接收，售前客服人员的任务，分发给消息备案
 *
 * @author ml
 *
 */

public class AfterServiceManager {

	/*
     * 工作队列
     */
	private LinkedList<AfterCustomServicer> workQuece = null;

	private Thread thread;

	private static AfterServiceManager afterServiceManager;

	private AfterTaskHandler afterTaskHandler;

	public  static AfterServiceManager Call(int worker_num) {

		if (afterServiceManager == null)
			afterServiceManager = new AfterServiceManager(worker_num);

		return afterServiceManager;
	}

	public AfterServiceManager(int worker_num) {
		workQuece = new LinkedList<AfterCustomServicer>();
		afterTaskHandler = RecordManager.Call();
		creatWorkThead(worker_num);
		startWorkThead();
	}

	public void setAfterTaskHandler(AfterTaskHandler afterTaskHandler) {
		this.afterTaskHandler = afterTaskHandler;
	}

	private void creatWorkThead(int work_thead_num) {
		// TODO Auto-generated method stub

		while (work_thead_num-- > 0) {
			AfterCustomServicer syncPoolWorker = new AfterCustomServicer(
					work_thead_num);
			Thread thread = new Thread(syncPoolWorker);
			workQuece.add(syncPoolWorker);
			thread.start();
		}
	}

	private void startWorkThead() {
		// TODO Auto-generated method stub
		thread = new Thread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					// synchronized (this) {

					synchronized (this) {
						LinkedList<Result> resultQuece=AfterDataManager.Call().getResultQuece();

						if (resultQuece.size() == 0) {

							try {
								Log.e(" 售后经理  wait");

								synchronized (resultQuece) {
									resultQuece.notify();
									resultQuece.wait(10);
								}

								Log.e("----------------------------------------");

								wait(10);

								//resultQuece.wait();



							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						} else {
							Log.e(this.getClass().getSimpleName()
									+ " resultQuece.size()= " + resultQuece.size());
							while (resultQuece.size() != 0) {
								int workSize = workQuece.size();
								for (int worknum = 0; worknum < workSize; worknum++) {

									AfterCustomServicer syncPoolWorker = workQuece
											.get(worknum);
									synchronized (syncPoolWorker) {
										if (!syncPoolWorker.isRuning()
												&& resultQuece.size() > 0) {
											try {

												String id = resultQuece.getFirst()
														.getId();
												ResultObsever resultObsever = afterTaskHandler
														.getObsever(id);

												if (resultObsever != null) {
													Log.e("售后客服经理  干活啦   ");

													syncPoolWorker
															.setResultObsever(resultObsever);
													syncPoolWorker
															.setResult(resultQuece
																	.removeFirst());
													syncPoolWorker.setRuning(true);
													syncPoolWorker.notify();
												}else{


												}



											} catch (Exception e) {
												e.printStackTrace();
											}

										}
									}

								}

							}

						}
					}

				}
			}
		});
		thread.start();
	}

	public Thread getThread() {
		return thread;
	}




}
