package test;


import com.async.Interceptor.ResponseInterceptorActionInterface;
import com.async.builder.ResponseBuilder;


public class ResponseIn2   implements ResponseInterceptorActionInterface {

 
	
 	public ResponseBuilder interceptorAction(ResponseBuilder builder)
			throws Exception {
		// TODO Auto-generated method stub
		/*String msg=new String(builder.response().getResult().getBytes("utf-8"),"GBK");
		builder.setResult(msg);*/
		System.out.println(this.getClass().getSimpleName()+"   interceptorAction  "+builder.response().getResult());

		
		return builder;
	}
	
	

}
