package test;


 import com.async.http.Interceptor2.RequestInterceptorActionInterface;
import com.async.http.request2.BaseHttpRequest;


public class RequestIn1  implements  RequestInterceptorActionInterface{

	public <T> BaseHttpRequest<T> interceptorAction(BaseHttpRequest<T> baserequest )
			throws Exception {
		// TODO Auto-generated method stub
	
		//System.out.println(this.getClass().getSimpleName()+"   interceptorAction  11111111111" );

		
		return baserequest;
	}
	
}
