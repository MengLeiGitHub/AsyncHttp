package com.async.pool.RecordCenter.after;


import com.async.pool.msg.ResultObsever;

public interface AfterTaskHandler   {
	public  void  add(String id, ResultObsever<?> msg);
 	
	public  void  delete(String mid);

	public  ResultObsever<?>  getObsever(String mid);

	public  int   AfterSize();
}
