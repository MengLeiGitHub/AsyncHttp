package com.async.pool.AfterService;

import com.async.pool.Log.Log;
import com.async.pool.msg.Result;
import com.async.pool.msg.ResultObsever;

/**
 * 将任务完成后的情况，反馈给消费者
 *
 * @author ml
 *
 */
public class AfterCustomServicer implements  Runnable{

	private boolean isRuning;
	private int index;



	public AfterCustomServicer(int index) {
		this.index = index;
	}

	/**
	 * 任务
	 */
	private ResultObsever resultObsever;

	/**
	 * 结果
	 */
	private Result  result;


	public void setResultObsever(
			ResultObsever resultObsever) {
		this.resultObsever = resultObsever;
	}
	public void setResult(Result result) {
		this.result = result;
	}

	public synchronized boolean isRuning() {

		return isRuning;
	}

	public synchronized void setRuning(boolean isRuning) {
		this.isRuning = isRuning;

		if (isRuning) {
			Log.e(" 售后客服 " + index + " notify");

			notify();
		}
	}

	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			synchronized (this) {
				if (!isRuning()) {

					try {
						Log.e( "售后客服 "+ index + " wait");

						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					Log.e( "售后客服 "+ index + " run");
					setRuning(false);
					resultObsever.setResult(result.getObj());
				   	resultObsever=null;
					result=null;
				}
			}

		}
	}


}
