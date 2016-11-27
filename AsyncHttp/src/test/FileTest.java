package test;




import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.async.http.AsyncHttp;
import com.async.http.callback.DownProgrossCallback;
import com.async.http.callback.HttpCallBack;
import com.async.http.clientImpl.HttpMethod;
import com.async.http.constant.Charsets;
import com.async.http.constant.Constents;
import com.async.http.entity.ResponseBody;
import com.async.http.handler.TaskHandler;
import com.async.http.request2.FileRequest;
import com.async.http.request2.RequestConfig;
import com.async.http.request2.StringRequest;
import com.async.http.request2.download;
import com.async.http.request2.entity.Header;
import com.async.http.request2.part.StringParamPart;
import com.async.http.request2.record.RecordEntity;
import com.async.http.utils.LogUtils;
import com.async.http.utils.Utils;


 

 
public class FileTest  extends  DownProgrossCallback<ResponseBody<File>>{

	static ArrayList<TaskHandler> tasklist=new ArrayList<TaskHandler>();
	private  int e;
	
	public FileTest(int i) {
		// TODO Auto-generated constructor stub
		this.e=i;
	}





	public static void main(String[] string){
		
          AsyncHttp.instance() .
			addRequestInterceptor(new RequestIn1())
			.addRequestInterceptor(new RequestIn2())
			.addResponseInterceptor(new ResponseIn1())
			.addResponseInterceptor(new ResponseIn2()) ;
          
          AsyncHttp.instance().addRequestInterceptor(new RequestIn1())
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
          headerlist.add(new Header(Constents.CONTENT_TYPE, Constents.TYPE_FORM_DATA));
          requestConfig.setHeadList(headerlist);
          
          LogUtils.setDebug(false);
          AsyncHttp.instance().setConfig(requestConfig);

         new FileTest(0). downApk();
          
          if(true)return;
         String url="http://211.149.184.79:8080/we/car/getAllCarMessageForPage.do";
         
         
         String[] u={
        		 
        		 "http://img.wallpapersking.com/d7/2016-9/2016091206311.jpg",
        		 "http://scimg.jb51.net/allimg/160815/103-160Q509544OC.jpg"
        		 ,"http://scimg.jb51.net/allimg/160813/103-160Q3143110P5.jpg",
         		 "http://pic24.nipic.com/20121029/5056611_120019351000_2.jpg"
        		 ,"http://img.taopic.com/uploads/allimg/130711/318756-130G1222R317.jpg"
        		 ,"http://pic14.nipic.com/20110610/7181928_110502231129_2.jpg"
        		 ,"http://pic41.nipic.com/20140509/18696269_121755386187_2.png"
        		 ,"http://pic55.nipic.com/file/20141208/19462408_171130083000_2.jpg"
        	,	 "http://lensbuyersguide.com/gallery/219/2/23_iso100_14mm.jpg"
         		 ,"http://pica.nipic.com/2008-03-11/2008311112935830_2.gif"
        		 ,"http://img.taopic.com/uploads/allimg/120423/107913-12042323220753.jpg"
        		 ,"http://img5.imgtn.bdimg.com/it/u=484208524,194442631&amp;fm=21&amp;gp=0.jpg",
        		 "http://pic51.nipic.com/file/20141022/19779658_171157758000_2.jpg",	
        		 "http://pic51.nipic.com/file/20141027/11284670_094822707000_2.jpg",
        		 "http://pic.4j4j.cn/upload/pic/20130815/31e652fe2d.jpg",
        		 "http://pic7.nipic.com/20100609/5136651_124423001651_2.jpg",
        		 "http://a.hiphotos.baidu.com/image/pic/item/e7cd7b899e510fb3dfed5079dd33c895d0430c63.jpg"
        		// "http://sw.bos.baidu.com/sw-search-sp/software/3e9a64e9e4d1b/BaiduMusic_10.0.5.0_setup.exe"
         };
         
         long time1=System.currentTimeMillis();
      //   System.out.println("================== ======================time1="+time1);
 		for(int i=0;i<u.length;i++){
 			
 		    
 	   		String filepath="C:\\Users\\admin\\Pictures\\Camera Roll\\img"+i+".jpg";
 			
 			FileRequest resReques=new FileRequest(u[i]);
 		//	resReques.addParam(new FileParamPart(key, val));
 			resReques.setFilepath(filepath);
 		/*
 			resReques.addParam(new StringParamPart("page", "1","utf-8","application/x-javascript"));
 			
 			resReques.addParam(new StringParamPart("size", "2","utf-8","application/x-javascript"));
*/
 			//  Content-Type: application/x-javascript
 			
 			resReques.setIndex(i);
 		//	resReques.addHead(new Header("accept", "*/*"));
 			/*resReques.addHead(new Header("connection", "Keep-Alive"));
 			resReques.addHead(new Header("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)"));
 			resReques.addHead(new Header("Accept-Charset", "utf-8"));
 			resReques.addHead(new Header(Constents.CONTENT_TYPE, Constents.TYPE_STREAM));
  			*/
 			resReques.addHead(new Header("user-agent", "AsyHttp/1.0 ml"));

 			resReques.setRequestMethod(HttpMethod.Get);
 			resReques.setRetry(true);
 		//	System.out.println("==================start=======================");

 			//tasklist.add();
 			AsyncHttp.instance().download(resReques, new FileTest(i));
 			
 		//	System.out.println("==================end=======================");
   		}
 		
 		  String urls="http://211.149.184.79:8080/we/car/getAllCarMessageForPage.do";
	        // url="https://www.baidu.com/s?wd=java%20%20connection%20%20header&rsv_spt=1&rsv_iqid=0xeedc5e52000538bb&issp=1&f=3&rsv_bp=1&rsv_idx=2&ie=utf-8&rqlang=cn&tn=80035161_1_dg&rsv_enter=0&oq=java%20future&rsv_t=d401YWWNzGRFKoYJLxl5GAPzWANQ6pWrQw3%2Bbe2Pc34RTlRwcrZNfN7924ZBbvsDtHsItQ&inputT=11349&rsv_pq=e202081a0010d865&rsv_sug3=237&rsv_sug1=137&rsv_sug7=100&prefixsug=java%20%20connection%20%20header&rsp=0&rsv_sug4=12270";
	         
	 		for(int i=0;i<100;i++){
	 			StringRequest resReques=new StringRequest(urls, Charsets.UTF_8);
	 			resReques.addParam(new StringParamPart("page", "1"));
	 			resReques.addParam(new StringParamPart("size", "2"));
	 			resReques.addParam(new StringParamPart("index", "index"+1));
	 			resReques.setRequestMethod(HttpMethod.Post);
	 			AsyncHttp.instance().stringRequest(resReques, new StringTest());
	 		}
	     
		
 		
 		
  	//	System.out.println(System.currentTimeMillis()-time1);
 		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				if(true)return;
				
				TaskHandler t=tasklist.get(tasklist.size()-2);
				t.stop();
				/*synchronized (t) {
					t.stop();	
					try {
						t.wait();
						t.notify();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}*/
				
				
				String filepath="C:\\Users\\admin\\Pictures\\Camera Roll\\jiujiumiandan-user.apk";
	 			
	 			FileRequest resReques=new FileRequest("http://www.jiujiumiandan.cn/jiujiumiandan-user.apk");
	 			
 	 			resReques.setFilepath(filepath);
	 	 
	 			resReques.addHead(new Header("user-agent", "AsyHttp/1.0 ml"));

	 			resReques.setRequestMethod(HttpMethod.Get);
	  		  
	 			AsyncHttp.instance().newRequest2(resReques, new FileTest(0));
 				
				
			}
		}, 1000*10);
 		
 		
     
		
 		
	}
	
	
 
	 public  void  downApk(){
		 String filepath="C:\\Users\\admin\\Pictures\\Camera Roll\\apic24088.jpg";
			
			FileRequest resReques=new FileRequest("http://pic1.sc.chinaz.com/files/pic/pic9/201611/apic24088.jpg");
			
			resReques.setFilepath(filepath);
	 
			resReques.addHead(new Header("user-agent", "AsyHttp/1.0 ml"));

			resReques.setRequestMethod(HttpMethod.Get);
		  
			AsyncHttp.instance().newRequest2(resReques, new FileTest(0));
	 }

	public void stop() {
		// TODO Auto-generated method stub
		
	}

	 
	 @Override
	public void fail(Exception e,ResponseBody<File> responseBody) {
		// TODO Auto-generated method stub
	     
		e.printStackTrace();
	}



	@Override
	public void start() {
		// TODO Auto-generated method stub
		super.start();
		System.out.println("任务   "+ e+"  startTime="+ Utils.getNowTime());
	}
	
   @Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		System.out.println("任务   "+ e+"  endTime="+ Utils.getNowTime());

	}


	public void success(ResponseBody<File> result) {
		// TODO Auto-generated method stub
      
        System.out.println("message="+result.getResult().getAbsolutePath());

	}



	@Override
	public void download_current(long current, long total) {
		// TODO Auto-generated method stub
	 	// if(e==0)
		// TODO Auto-generated method stub
				int progress=(int)(current*100f/total);
				System.out.println("progress="+progress);
 		//	 System.out.println("e="+e+"  long current ="+current+" long total="+total);
  
	}

	 

}
