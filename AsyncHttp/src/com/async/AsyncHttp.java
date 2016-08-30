package com.async;

 
import com.async.SyncPoolExecutor;
import com.async.Interceptor.RequestInterceptor2;
import com.async.Interceptor.RequestInterceptorActionInterface;
import com.async.Interceptor.ResponseInterceptor2;
import com.async.Interceptor.ResponseInterceptorActionInterface;


public class AsyncHttp {
	
	RequestInterceptor2 requestInterceptor;
	ResponseInterceptor2 responseInterceptor;
	static	AsyncHttp  asyncHttp;
	 public  static     AsyncHttp instance(){
		 if(asyncHttp==null){
			 asyncHttp=new AsyncHttp();
			 init();
		 }
		 return asyncHttp;
	 }
	
	 public static  void  init(){
		 
 		 SyncPoolExecutor.newFixedThreadPool(4, 2, null).isDebug(false);
  	 }
		public AsyncHttp addRequestInterceptor(RequestInterceptorActionInterface requestInterceptor) {
			if (this.requestInterceptor == null)
				this.requestInterceptor =new RequestInterceptor2(requestInterceptor); 
	 
			else
			this.requestInterceptor = new RequestInterceptor2(requestInterceptor).setRequestInterceptor2(this.requestInterceptor);
	 
			
			return this;
		}

		public AsyncHttp addResponseInterceptor(
				ResponseInterceptorActionInterface responseInterceptor) {
		 
			
			if (this.responseInterceptor == null)
				this.responseInterceptor =new ResponseInterceptor2(responseInterceptor); 
	 
			else
			this.responseInterceptor = new ResponseInterceptor2(responseInterceptor).setResponseInterceptor2(this.responseInterceptor);
 			
 			
			return this;
		}
	 
		
		public RequestInterceptor2 getRequestInterceptor() {
			return requestInterceptor;
		}
		
		public ResponseInterceptor2 getResponseInterceptor() {
			return responseInterceptor;
		}
	 
}
