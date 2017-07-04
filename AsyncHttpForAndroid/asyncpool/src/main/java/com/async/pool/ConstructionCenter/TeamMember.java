package com.async.pool.ConstructionCenter;

import com.async.pool.BeforeService.Observer;
import com.async.pool.Log.Log;
import com.async.pool.msg.CustomMessage;
import com.async.pool.msg.Result;

/**
 * 任务的具体实施者，将工作完成情况上报到队长哪里
 *
 * @author ml
 *
 */
public class TeamMember implements Runnable {

	private boolean isRuning;
	private int index;

	Observer<Result> observer;

	public TeamMember(int index) {
		this.index = index;
	}

	/**
	 * 任务
	 */
	private CustomMessage customMessage;

	public void setCustomMessage(CustomMessage customMessage) {
		this.customMessage = customMessage;
	}

	public synchronized boolean isRuning() {

		return isRuning;
	}

	public synchronized void setRuning(boolean isRuning) {
		this.isRuning = isRuning;

		if (isRuning) {
			Log.e("队员" + index + " notify");
			notify();
		}
	}

	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			synchronized (this) {
				if (!isRuning()) {

					try {
						Log.e("队员   " + index + " wait");
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {

					if (customMessage.getTask() != null) {
						customMessage.getTask().setRunning(true);
						Object res = customMessage.getTask().startTask();


						observer.Observe(new Result(Integer
								.toString(customMessage.getMid()), res));// 通知队长
					}
					customMessage=null;
					setRuning(false);
				}
			}

		}
	}

	public void registerObserver(Observer<Result> observer) {
		// TODO Auto-generated method stub
		this.observer = observer;

	}
}