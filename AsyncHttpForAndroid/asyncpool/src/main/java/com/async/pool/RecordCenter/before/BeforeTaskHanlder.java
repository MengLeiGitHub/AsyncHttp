package com.async.pool.RecordCenter.before;

import java.util.LinkedList;
import java.util.Vector;

import com.async.pool.msg.CustomMessage;

public interface BeforeTaskHanlder {

	public  void  add(CustomMessage msg);
	public  void  add(LinkedList<? extends CustomMessage> msg);

	
	public  void  delete(CustomMessage msg);

	public  CustomMessage  getMsg();

	public  CustomMessage  getHightMsg();

	
	public  int   BeforeSize();
	
	public   Vector<? extends CustomMessage>   getALLTask();
	
	
}
