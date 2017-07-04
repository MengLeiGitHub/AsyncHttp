package com.async.pool.msg;

import com.async.pool.ConstructionCenter.TaskWork;

/**
 * 用户的消息
 * @author m
 * @param <T>
 *
 */
public class CustomMessage   {
	/*
	 * 任务id
	 */
	private int mid;
	/**
	 * 任务
	 */
	private TaskWork   task;

	public  CustomMessage(TaskWork  task){
		this.task=task;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public TaskWork  getTask() {

		return task;
	}

	public void setTask(TaskWork  task) {
		this.task = task;
	}

	private Object obj;
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public Object getObj() {
		return obj;
	}

}
