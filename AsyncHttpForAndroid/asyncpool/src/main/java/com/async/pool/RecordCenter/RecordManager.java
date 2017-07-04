package com.async.pool.RecordCenter;

import java.util.LinkedList;
import java.util.Vector;

import com.async.pool.RecordCenter.after.AfterTaskHandler;
import com.async.pool.RecordCenter.after.AfterTaskManager;
import com.async.pool.RecordCenter.before.BeforeTaskHanlder;
import com.async.pool.RecordCenter.before.BeforeTaskManager;
import com.async.pool.msg.CustomMessage;
import com.async.pool.msg.ResultObsever;


/**
 * 备案中心  领导
 *
 * @author ml
 *
 */

public class RecordManager  implements RecordCenterHandler {

	private static RecordManager recordManager;

	AfterTaskHandler afterTaskHandler;
	BeforeTaskHanlder beforeTaskHanlder;
	public  static RecordManager Call() {

		if (recordManager == null)
			recordManager = new RecordManager();

		return recordManager;
	}

	public RecordManager(){
		afterTaskHandler=AfterTaskManager.Call();
		beforeTaskHanlder=BeforeTaskManager.Call();
	}

	public void add(CustomMessage msg) {
		// TODO Auto-generated method stub
		beforeTaskHanlder.add(msg);
		afterTaskHandler.add(msg.getMid()+"", (ResultObsever<?>)msg.getObj());
	}

	public void add(LinkedList<? extends CustomMessage> msg) {
		// TODO Auto-generated method stub
		for(CustomMessage c:msg){
			add(c);
		}
	}

	public void delete(CustomMessage msg) {
		// TODO Auto-generated method stub
		beforeTaskHanlder.delete(msg);
		afterTaskHandler.delete(Integer.toString(msg.getMid()));
	}

	public CustomMessage getMsg() {
		// TODO Auto-generated method stub
		return beforeTaskHanlder.getMsg();
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
	public int BeforeSize() {
		// TODO Auto-generated method stub
		return beforeTaskHanlder.BeforeSize();
	}

	@Override
	public void sortUtils() {
		// TODO Auto-generated method stub
		Vector<CustomMessage> list=getALLTask();
		if(list.size()!=0){
			sortUtils.quicksort(list,0,list.size()-1);
		}
	}

	@Override
	public Vector<CustomMessage> getALLTask() {
		// TODO Auto-generated method stub
		return   (Vector<CustomMessage>) beforeTaskHanlder.getALLTask();
	}

	@Override
	public CustomMessage getHightMsg() {
		// TODO Auto-generated method stub
		return beforeTaskHanlder.getHightMsg();
	}
}
