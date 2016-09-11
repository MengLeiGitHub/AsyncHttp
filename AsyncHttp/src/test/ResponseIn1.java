package test;


import com.async.Interceptor2.ResponseInterceptorActionInterface;

 import com.async.entity.ResponseBody;


public   class ResponseIn1  implements ResponseInterceptorActionInterface<ResponseBody> {

	public ResponseBody  interceptorAction(ResponseBody  t) throws Exception {
		// TODO Auto-generated method stub
	
		//System.out.println(this.getClass().getSimpleName()+"   ResponseIn1 1111111 ");

		
		return t;
	}
 
	
 	 
	
	

}
