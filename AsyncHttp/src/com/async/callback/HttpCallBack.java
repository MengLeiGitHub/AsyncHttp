package com.async.callback;

public interface HttpCallBack<T> {
	/**
	 * ��ȡ������������
	 * @param total
	 */
	public void start();
	public void current(long current,long total);
	public void finish();
	public void success(T result);
	public void fail(Exception e);
	
}
