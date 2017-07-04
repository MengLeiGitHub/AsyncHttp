package com.async.pool.BeforeService;

import java.util.LinkedList;

import com.async.pool.RecordCenter.RecordCenterHandler;
import com.async.pool.RecordCenter.after.AfterTaskHandler;
import com.async.pool.msg.CustomMessage;
import com.async.pool.msg.MsgFilter;

/**
 * 客户服务中心领导，处理Customer报上来的消息 ，过滤消息
 * 过滤后，将有效的消息发送到task管理员，进行备案 
 * @author ml
 *
 */
public class BeforeServiceManager implements Observer<CustomMessage> {
	private    int msgNums;

	MsgFilter  msgFilter;

	RecordCenterHandler  recordCenterHandler;

	private static BeforeServiceManager  beforeServiceManager;

	public  static synchronized BeforeServiceManager   Call(){
		if(beforeServiceManager==null){
			beforeServiceManager=new BeforeServiceManager();
		}

		return beforeServiceManager;
	}

	public  BeforeServiceManager  addMsgFilter(MsgFilter  msgFilter){
		this.msgFilter=msgFilter;
		return this;
	}


	public BeforeServiceManager registerAfterTaskObsever(AfterTaskHandler afterTaskHandler) {
		return  this;
	}

	public BeforeServiceManager  registerRecordCenterHandlerObsever(RecordCenterHandler recordCenterHandler) {
		this.recordCenterHandler = recordCenterHandler;
		return this;
	}


	/**
	 * 观察任务 ，有消息 会自动调该方法
	 */
	public void Observe(CustomMessage msg) {
		// TODO Auto-generated method stub
		msg=msgFilter(msg);//消息过滤
		noticeAfterManager();//通知以后会有一条 售后服务，进行备案
		sendMsgToMsgManager(msg);//将消息发送到管理员 进行备案
	}


	private void noticeAfterManager() {
		// TODO Auto-generated method stub

	}

	private CustomMessage msgFilter(CustomMessage customMessage) {
		// TODO Auto-generated method stub
		if(msgFilter!=null){
			customMessage=	msgFilter.isQualified(customMessage);
		}
		return customMessage;

	}

	private void sendMsgToMsgManager(CustomMessage customMessage) {
		// TODO Auto-generated method stub
		recordCenterHandler.add(customMessage);
	}

	public void Observe(LinkedList<CustomMessage> listmsg) {
		// TODO Auto-generated method stub
		for(CustomMessage customMessage:listmsg){
			Observe(customMessage);
		}
	}


}
