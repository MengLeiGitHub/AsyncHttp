package com.async.pool.RecordCenter.before;

import java.util.LinkedList;
import java.util.Vector;

import com.async.pool.msg.CustomMessage;


/**
 * 消息的具体管理责任人，负责消息的存档，和  消息的送出。
 *
 * @author ML
 *
 */
public class BeforeTaskManager implements BeforeTaskHanlder{


	private static BeforeTaskManager  beforeTaskManager;

	private  BeforeTaskHanlder  beforeTaskHanlder;

	public  static synchronized  BeforeTaskManager   Call(){
		if(beforeTaskManager==null){
			beforeTaskManager=new BeforeTaskManager();
		}

		return beforeTaskManager;
	}

	public  void  setBeforeTaskHanlder(BeforeTaskHanlder  beforeTaskHanlder){
		this.beforeTaskHanlder=beforeTaskHanlder;
	}




	public void add(CustomMessage msg) {
		// TODO Auto-generated method stub
		beforeTaskHanlder.add(msg);
	}

	public void delete(CustomMessage msg) {
		// TODO Auto-generated method stub
		beforeTaskHanlder.delete(msg);
	}

	public CustomMessage getMsg() {
		// TODO Auto-generated method stub
		return beforeTaskHanlder.getMsg();
	}

	public int BeforeSize() {
		// TODO Auto-generated method stub
		return beforeTaskHanlder.BeforeSize();
	}

	public void add(LinkedList<? extends CustomMessage> msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public Vector<? extends CustomMessage> getALLTask() {
		// TODO Auto-generated method stub
		return beforeTaskHanlder.getALLTask();
	}

	@Override
	public CustomMessage getHightMsg() {
		// TODO Auto-generated method stub
		return beforeTaskHanlder.getHightMsg();
	}




}
