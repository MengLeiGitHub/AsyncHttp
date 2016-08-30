package test;


import com.async.Interceptor.ResponseInterceptorActionInterface;
import com.async.builder.ResponseBuilder;


public class ResponseIn1  implements ResponseInterceptorActionInterface {
 
	
 	public ResponseBuilder interceptorAction(ResponseBuilder builder)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println(this.getClass().getSimpleName()+"   interceptorAction  "+builder.response().getResult());
		
		
		return  builder;
	}
	
	

}
