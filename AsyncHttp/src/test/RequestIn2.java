package test;


 import com.async.Interceptor2.RequestInterceptorActionInterface;
import com.async.request2.BaseHttpRequest;


public class RequestIn2  implements  RequestInterceptorActionInterface{

 	public <T> BaseHttpRequest<T> interceptorAction(BaseHttpRequest<T> baserequest )
			throws Exception {
		// TODO Auto-generated method stub
	
		 System.out.println(this.getClass().getSimpleName()+"   interceptorAction 2222222222222 ");

		
		return baserequest;
	}
	
}
