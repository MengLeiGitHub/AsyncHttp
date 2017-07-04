package com.async.pool.ConstructionCenter;

public enum TaskPriority {

	HIGHEST(1), NORMAL(2), LOWEST(3);
	int taskPriority;

	TaskPriority(int taskPriority) {
		this.taskPriority = taskPriority;

	}
	
	public int  getValue(){
		return taskPriority;
	}
	
}
