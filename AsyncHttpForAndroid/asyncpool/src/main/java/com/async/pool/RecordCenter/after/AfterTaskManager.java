package com.async.pool.RecordCenter.after;

import com.async.pool.msg.ResultObsever;

/**
 * 消息中心，管理者
 *
 * @author ML
 *
 */
public class AfterTaskManager implements AfterTaskHandler{

	private static AfterTaskManager afterTaskManager;

	AfterTaskHandler afterTaskHandler;


	public synchronized static AfterTaskManager Call() {

		if (afterTaskManager == null)
			afterTaskManager = new AfterTaskManager();

		return afterTaskManager;
	}

	public void setAfterTaskHandler(AfterTaskHandler afterTaskHandler) {
		this.afterTaskHandler = afterTaskHandler;
	}

	public AfterTaskManager(){

		afterTaskHandler=AfterTaskCenter.Call();
	}


	public void add(String id, ResultObsever<?> msg) {
		// TODO Auto-generated method stub
		afterTaskHandler.add(id, msg);
	}


	public void delete(String mid) {
		// TODO Auto-generated method stub
		afterTaskHandler.delete(mid);
	}


	public ResultObsever<?> getObsever(String mid) {
		// TODO Auto-generated method stub
		return afterTaskHandler.getObsever(mid);
	}


	public int AfterSize() {
		// TODO Auto-generated method stub
		return afterTaskHandler.AfterSize();
	}



}
