package com.async.http.request2.record;

import java.util.LinkedList;

import com.async.http.request2.download;

public interface RecordManagerHandler {

	public RecordEntity getErrorTask();
	
	public LinkedList<RecordEntity>  getErrorTasks();
	
	public LinkedList<download>      getErrorDownloadTasks();
	
	public void   addErrorTask(RecordEntity recordEntity);
	
	public void   removeErrorTasks(RecordEntity recordEntity);
	
	
	public LinkedList<download>      getErrorDownloadTasksFromDisk();

	
	
}
