package test;



import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import com.async.AsyncHttp;
import com.async.ClientBuilder;
import com.async.MHttpClient;
import com.async.callback.HttpCallBack;
import com.async.constant.RequestType;
import com.async.entity.URLConfig;
import com.async.handler.RequestHandler;


 

 
public class MainActivity  implements HttpCallBack<String>{

 

	public static void main(String[] string){
		
          AsyncHttp.instance() .
			addRequestInterceptor(new RequestIn1())
			.addRequestInterceptor(new RequestIn2())
			.addResponseInterceptor(new ResponseIn1())
			.addResponseInterceptor(new ResponseIn2());
          
          
          
         String url="http://211.149.184.79:8080/we/car/getAllCarMessageForPage.do";
        // url="https://www.baidu.com/s?wd=java%20%20connection%20%20header&rsv_spt=1&rsv_iqid=0xeedc5e52000538bb&issp=1&f=3&rsv_bp=1&rsv_idx=2&ie=utf-8&rqlang=cn&tn=80035161_1_dg&rsv_enter=0&oq=java%20future&rsv_t=d401YWWNzGRFKoYJLxl5GAPzWANQ6pWrQw3%2Bbe2Pc34RTlRwcrZNfN7924ZBbvsDtHsItQ&inputT=11349&rsv_pq=e202081a0010d865&rsv_sug3=237&rsv_sug1=137&rsv_sug7=100&prefixsug=java%20%20connection%20%20header&rsp=0&rsv_sug4=12270";
         
 		for(int i=0;i<100;i++){
 			
 		    URLConfig config=new URLConfig();
 	 		config.setRequestType(RequestType.GET);
 	 		config.setUrl(url);
 	 		
 	 		HashMap<String, Object> h=new HashMap<String, Object>();
  	 		h.put("page", "1");
 	 		h.put("size", "2");
 	 		h.put("Index", "index"+i);
 	  		config.setMap(h);
 	   		
 			RequestHandler requstManager=new MHttpClient()
 					.newRequest(config, new MainActivity());
 		
 		
 			
 		}
     
		
 		
	}
	
	
 
	public void current(long current, long total) {
		// TODO Auto-generated method stub
		
	}

	public void stop() {
		// TODO Auto-generated method stub
		
	}

	public void finish() {
		// TODO Auto-generated method stub
		
	}

	public void success(String result) {
		// TODO Auto-generated method stub
        System.out.println("message="+result);
	}
	public void fail(Exception e) {
		// TODO Auto-generated method stub
		e.printStackTrace();
	}



	public void start() {
		// TODO Auto-generated method stub
		
	}

	 

}
