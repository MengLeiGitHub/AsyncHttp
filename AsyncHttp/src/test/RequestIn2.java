package test;


 import com.async.http.Interceptor2.RequestInterceptorActionInterface;
import com.async.http.request2.BaseHttpRequest;
import com.async.http.utils.LogUtils;


public class RequestIn2  implements  RequestInterceptorActionInterface{

 	public <T> BaseHttpRequest<T> interceptorAction(BaseHttpRequest<T> baserequest )
			throws Exception {
		// TODO Auto-generated method stub
	
		 LogUtils.e(this.getClass().getSimpleName()+"   interceptorAction 2222222222222 ");

		
		return baserequest;
	}
	
}
