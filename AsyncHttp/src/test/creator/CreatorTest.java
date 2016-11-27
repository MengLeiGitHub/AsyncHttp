package test.creator;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import test.RequestIn1;
import test.RequestIn2;
import test.ResponseIn1;
import test.ResponseIn2;

import com.async.http.AsyncHttp;
import com.async.http.callback.HttpCallBack;
import com.async.http.clientImpl.HttpMethod;
import com.async.http.constant.Constents;
import com.async.http.entity.ResponseBody;
import com.async.http.request2.RequestConfig;
import com.async.http.request2.entity.Header;
import com.async.http.requsetcreator.Creator;
import com.async.http.requsetcreator.MIO;

public class CreatorTest {

	/**
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
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
		          headerlist.add(new Header("Accept-Charset", "utf-8"));
		          headerlist.add(new Header("Accept", "text/html, application/xhtml+xml, image/jxr, */*"));

		          headerlist.add(new Header("Accept-Encoding", "gzip, deflate"));
		        
		          
		          headerlist.add(new Header(Constents.CONTENT_TYPE, Constents.TYPE_FORM_DATA));
		          requestConfig.setHeadList(headerlist);
		          
		          AsyncHttp.instance().setConfig(requestConfig);
		          
		TestApi  t=Creator.creator(TestApi.class);
		
		 t.Create("234",1);
		 
	}

}
