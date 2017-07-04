package com.async.pool.ConstructionCenter;

/**
 * 具体的任务
 *
 * @author ml
 *
 */

public interface WorkTaskInterface extends TaskLife{

	public Object startTask();

	/**
	 * 设置任务的优先级
	 * @param priority
	 */
	public void setTaskPriority(int priority);

	/**
	 * 获取任务的优先级
	 * @return
	 */
	public int getTaskPriority();

	/**
	 * 任务的进度
	 * @return
	 */
	public boolean isRunning();

	/**
	 * 设置任务的进度
	 * @param isruning
	 */
	public void setRunning(boolean isruning);


}
