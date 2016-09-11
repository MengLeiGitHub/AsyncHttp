package com.async.exception;

public abstract class HttpException extends Exception {
	
	

    public HttpException() {}

    public HttpException(String name) {
        super(name);
    }

    public HttpException(String name, Throwable cause) {
        super(name, cause);
    }

    public HttpException(Throwable cause) {
        super(cause);
    }

    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	StringBuilder sb=new StringBuilder();
    	sb.append("--------------------");
    	sb.append(""+getClass().getName());
    	sb.append("--------------------");
    	sb.append("\n");
    	sb.append(getMessage());
    	sb.append("\n");
    	sb.append("--------------------");
    	sb.append("end");
    	sb.append("--------------------");
    	
    	return  sb.toString();
    }

}
