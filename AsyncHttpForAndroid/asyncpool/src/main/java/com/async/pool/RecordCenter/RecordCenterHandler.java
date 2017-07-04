package com.async.pool.RecordCenter;

import com.async.pool.RecordCenter.after.AfterTaskHandler;
import com.async.pool.RecordCenter.before.BeforeTaskHanlder;

public interface RecordCenterHandler  extends BeforeTaskHanlder,AfterTaskHandler{

	public void  sortUtils();
	
}
