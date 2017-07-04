package com.android;

import java.io.Serializable;

public class ResonseEnty<T>  implements Serializable{
	private int status;
	private String msg;
	private T data;
	private int errcode;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	public  boolean  isSuccess(){
		return status==1;
	}
}
