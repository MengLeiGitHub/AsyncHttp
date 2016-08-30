package com.async.callback;

public interface HttpCallBack<T> {
	/**
	 * 获取网络数据总量
	 * @param total
	 */
	public void start();
	public void current(long current,long total);
	public void finish();
	public void success(T result);
	public void fail(Exception e);
	
}
