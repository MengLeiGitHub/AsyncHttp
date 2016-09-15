package test;




import java.io.File;
import java.util.ArrayList;

import com.async.http.AsyncHttp;
import com.async.http.callback.HttpCallBack;
import com.async.http.callback.UploadProgrossCallback;
import com.async.http.clientImpl.HttpMethod;
import com.async.http.constant.Charsets;
import com.async.http.constant.Constents;
import com.async.http.entity.ResponseBody;
import com.async.http.request2.RequestConfig;
import com.async.http.request2.StringRequest;
import com.async.http.request2.entity.Header;
import com.async.http.request2.part.FileParamPart;
import com.async.http.request2.part.StringParamPart;


 

 
public class uploadTest  extends  UploadProgrossCallback<ResponseBody<String>>{

 

	public static void main(String[] string){
		
          AsyncHttp.instance() .
			addRequestInterceptor(new RequestIn1())
			.addRequestInterceptor(new RequestIn2())
			.addResponseInterceptor(new ResponseIn1())
			.addResponseInterceptor(new ResponseIn2());
          
          RequestConfig requestConfig=new RequestConfig();
          requestConfig.setConnectTimeout(10000);
          requestConfig.setSocketTimeout(30000);
          requestConfig.setRequestMethod(HttpMethod.Post);
          ArrayList<Header> headerlist=new ArrayList<Header>();
          headerlist.add(new Header("connection", "Keep-Alive"));
          headerlist.add(new Header("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)"));
          headerlist.add(new Header("Accept-Charset", "ISO-8859-1"));
          headerlist.add(new Header(Constents.CONTENT_TYPE, Constents.TYPE_FORM_DATA));
          requestConfig.setHeadList(headerlist);
          
          AsyncHttp.instance().setConfig(requestConfig);
          
          
         String url="http://192.168.1.33:8080/StrutsDemo2/upload.action";
        // url="https://www.baidu.com/s?wd=java%20%20connection%20%20header&rsv_spt=1&rsv_iqid=0xeedc5e52000538bb&issp=1&f=3&rsv_bp=1&rsv_idx=2&ie=utf-8&rqlang=cn&tn=80035161_1_dg&rsv_enter=0&oq=java%20future&rsv_t=d401YWWNzGRFKoYJLxl5GAPzWANQ6pWrQw3%2Bbe2Pc34RTlRwcrZNfN7924ZBbvsDtHsItQ&inputT=11349&rsv_pq=e202081a0010d865&rsv_sug3=237&rsv_sug1=137&rsv_sug7=100&prefixsug=java%20%20connection%20%20header&rsp=0&rsv_sug4=12270";
         
 		for(int i=0;i<1;i++){
 			 		
 			StringRequest resReques=new StringRequest(url, Charsets.UTF_8);
 		//	resReques.addParam(new StringParamPart("uploadContentType", "jpg"));
 		//	resReques.addParam(new StringParamPart("uploadFileName", "xxx"));
 			resReques.addParam(new FileParamPart("upload", new File("C:\\Users\\admin\\Pictures\\Camera Roll\\img10.jpg"),Constents.TYPE_IMAGE));
 			//resReques.addParam(new FileParamPart("upload", new File("C:\\Users\\admin\\Pictures\\Camera Roll\\ds.txt"),Constents.TYPE_TEXT));

 			resReques.setRequestMethod(HttpMethod.Post);
 			AsyncHttp.instance().newRequest2(resReques, new uploadTest());
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
	public void fail(Exception e,ResponseBody<String> response) {
		// TODO Auto-generated method stub
		e.printStackTrace();
	}



	public void start() {
		// TODO Auto-generated method stub
		
	}



	public void success(ResponseBody<String> result) {
		// TODO Auto-generated method stub
        System.out.println("message "+result.getIndex()+"="+result.getResult());

	}



	@Override
	public void upload_current(long current, long total) {
		// TODO Auto-generated method stub
		
		System.out.println("long current="+current+" long total="+total);

	}

	 

}
