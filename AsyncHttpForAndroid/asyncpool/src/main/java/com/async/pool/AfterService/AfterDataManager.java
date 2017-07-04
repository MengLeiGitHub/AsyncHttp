package com.async.pool.AfterService;

import java.util.LinkedList;

import com.async.pool.msg.Result;

public class AfterDataManager {

	private LinkedList<Result> resultQuece = null;

	private static AfterDataManager afterDataManager;


	public  static AfterDataManager Call(   ) {

		if (afterDataManager == null)
			afterDataManager = new AfterDataManager();

		return afterDataManager;
	}

	public   AfterDataManager(){
		resultQuece = new LinkedList<Result>();
	}
	public  void notice(Result result) {
		// TODO Auto-generated method stub
		if(result!=null&&result.getObj()!=null)//防止 任务取消之后，添加到 完成队列中
			resultQuece.add(result);

	}
	public LinkedList<Result> getResultQuece(){
		return resultQuece;
	}

	public void  remove(Result result){
		resultQuece.remove(result);
	}



}
