package test;


import com.async.Interceptor.RequestInterceptorActionInterface;
import com.async.builder.RequestBuilder;


public class RequestIn2  implements  RequestInterceptorActionInterface{

 	public RequestBuilder interceptorAction(RequestBuilder builder)
			throws Exception {
		// TODO Auto-generated method stub
	
		System.out.println(this.getClass().getSimpleName()+"   interceptorAction  "+builder.getConenctionParams());

		
		return builder;
	}
	
}
