package com.async.pool.RecordCenter.after;

import java.util.HashMap;

import com.async.pool.msg.ResultObsever;


/**
 * 消息中心，管理问题
 *
 * @author ml
 *
 */
public class AfterTaskCenter implements AfterTaskHandler{
	private HashMap<String, ResultObsever<?>> response=null;

	private  static AfterTaskCenter  afterTaskCenter;

	public  synchronized  static  AfterTaskCenter Call(){

		if(afterTaskCenter==null)afterTaskCenter=new AfterTaskCenter();

		return afterTaskCenter;
	}

	public AfterTaskCenter(){
		response=new HashMap<String, ResultObsever<?>>();

	}

	public void add(String id,ResultObsever<?> msg) {
		// TODO Auto-generated method stub
		response.put(id, msg);
	}

	public void delete(String mid) {
		// TODO Auto-generated method stub
		getObsever(mid);
	}

	public ResultObsever<?> getObsever(String mid) {
		// TODO Auto-generated method stub
		if(!response.containsKey(mid)){
			System.out.println("!response.containsKey(mid) ==true");
			return null;
		}
	//	ResultObsever<?> re=	response.get(mid);
		return response.remove(mid);
	}

	public int AfterSize() {
		// TODO Auto-generated method stub
		return response.size();
	}





}
