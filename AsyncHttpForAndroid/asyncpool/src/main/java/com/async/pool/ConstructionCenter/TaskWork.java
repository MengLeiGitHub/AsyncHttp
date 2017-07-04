package com.async.pool.ConstructionCenter;

public abstract class TaskWork implements WorkTaskInterface {



	private int index;

	boolean isStop;

	int taskPriority=TaskPriority.NORMAL.getValue();

	boolean isRunning;


	public TaskWork(int index) {

		this.index = index;

	}


	public void stop() {
		// TODO Auto-generated method stub
		isStop = true;
		/*synchronized (this) {
			if(!isRunning){
				
				
				
			}
		}*/

	}

	public void start() {
		// TODO Auto-generated method stub
		isStop = false;
	}

	public void remove() {
		// TODO Auto-generated method stub
		isStop = true;

	}

	public Object startTask() {
		// TODO Auto-generated method stub
		if (isStop){


			return null;
		}


		Object obj = run(index, isStop);

		return obj;
	}





	public int getTaskPriority() {
		return taskPriority;
	}


	public void setTaskPriority(int taskPriority) {
		this.taskPriority = taskPriority;
	}


	public boolean isRunning() {
		// TODO Auto-generated method stub
		return isRunning;
	}

	public void setRunning(boolean isruning) {
		// TODO Auto-generated method stub
		this.isRunning = isruning;
		this.isStop = false;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * 第一个参数表示 当前任务的下标 第二个参数表示 当前任务是否是 取消掉了
	 *
	 * @param objects
	 * @return
	 */

	public abstract Object run(Object... objects);

}
