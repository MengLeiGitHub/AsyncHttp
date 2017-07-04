package com.async.pool.ConstructionCenter;

public interface TaskInterface {
	/**
	 * 第一个参数表示 当前任务的下标   第二个参数表示  当前任务是否是 取消掉了
	 * @param objects
	 * @return
	 */

	public Object run(Object ...objects);

	public  void   registerObsever(TaskLife tasklife);


}
