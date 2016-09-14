package test;




import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import com.async.AsyncHttp;
import com.async.AsyncHttp;
import com.async.ConstructionCenter.TaskPriority;
import com.async.callback.DownProgrossCallback;
import com.async.callback.HttpCallBack;
import com.async.clientImpl.HttpMethod;
import com.async.constant.Constents;
import com.async.entity.ResponseBody;
import com.async.handler.TaskHandler;
import com.async.request2.FileRequest;
import com.async.request2.RequestConfig;
import com.async.request2.download;
import com.async.request2.entity.Header;
import com.async.request2.record.RecordEntity;
import com.async.request2.record.RecordManager;
import com.async.utils.LogUtils;
import com.async.utils.Utils;


 

 
public class RandomAccessFileTest  extends  DownProgrossCallback<ResponseBody<RandomAccessFile>>{

	static ArrayList<TaskHandler> tasklist=new ArrayList<TaskHandler>();

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
          requestConfig.setConnectTimeout(5000);
          requestConfig.setSocketTimeout(5000);
          requestConfig.setRequestMethod(HttpMethod.Post);
          ArrayList<Header> headerlist=new ArrayList<Header>();
          headerlist.add(new Header("connection", "Keep-Alive"));
          headerlist.add(new Header("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)"));
          headerlist.add(new Header("Accept-Charset", "utf-8"));
          headerlist.add(new Header(Constents.CONTENT_TYPE, Constents.TYPE_FORM_DATA));
          requestConfig.setHeadList(headerlist);
          LogUtils.setDebug(false);
          
          AsyncHttp.instance().setConfig(requestConfig);

          
          
          
         String url="http://211.149.184.79:8080/we/car/getAllCarMessageForPage.do";
         
         String[] u={
        		 "http://img.wallpapersking.com/d7/2016-9/2016091206311.jpg",
        		 "http://scimg.jb51.net/allimg/160815/103-160Q509544OC.jpg"
        		 ,"http://scimg.jb51.net/allimg/160813/103-160Q3143110P5.jpg",
         		 "http://pic24.nipic.com/20121029/5056611_120019351000_2.jpg",
        	 	 "http://lensbuyersguide.com/gallery/219/2/23_iso100_14mm.jpg" ,
        		 "http://img.taopic.com/uploads/allimg/130711/318756-130G1222R317.jpg"
        		 ,"http://pic14.nipic.com/20110610/7181928_110502231129_2.jpg"
        		 ,"http://pic41.nipic.com/20140509/18696269_121755386187_2.png"
        		 ,"http://pic55.nipic.com/file/20141208/19462408_171130083000_2.jpg"
        		 ,"http://pica.nipic.com/2008-03-11/2008311112935830_2.gif"
        		 ,"http://img.taopic.com/uploads/allimg/120423/107913-12042323220753.jpg"
        		//  ,"http://img5.imgtn.bdimg.com/it/u=484208524,194442631&amp;fm=21&amp;gp=0.jpg"

        		 
         };
       
         
 		for(int i=0;i<u.length;i++){
 			
 		    final int e=i;
 		    String urls=u[i];
 		//    String agername= urls.substring(urls.lastIndexOf("."),urls.length());
  		    String name=urls.substring(urls.lastIndexOf("/")+1,urls.length());
 	   		String filepath="C:\\Users\\admin\\Pictures\\Camera Roll\\"+name;
 			
 	   		download s=	 new download(new RecordEntity(u[i],filepath));
 	   		
  			s.setTaskPriority(TaskPriority.LOWEST.getValue());//设置优先级
  			
			 if(i==5)s.setTaskPriority(TaskPriority.HIGHEST.getValue());
			 else if(i==4) s.setTaskPriority(TaskPriority.LOWEST.getValue());

 			TaskHandler t=   AsyncHttp.instance().download(s, new DownProgrossCallback<ResponseBody<RandomAccessFile>>() {
			
			@Override
			public void download_current(long current, long total) {
				// TODO Auto-generated method stub
				// System.out.println("当前 "+e+"  current=="+current+"    total="+total);
			}
			
			@Override
				public void fail(Exception e1,ResponseBody<RandomAccessFile> response) {
					// TODO Auto-generated method stub
					super.fail(e1,response);
					System.out.println("任务   "+ e+"   ="+ Utils.getNowTime()+"  e1="+e1.getMessage());
								 
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
			
			
		   });
 			tasklist.add(t);
  		
 		    // if(i==0)break;
 		}
 		
 		
 		
 		
 		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
			//	TaskHandler t=tasklist.get(tasklist.size()-2);
			//	t.stop();
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
				
				
				/*String filepath="C:\\Users\\admin\\Pictures\\Camera Roll\\img"+10+".jpg";
	 			
	 			FileRequest resReques=new FileRequest("http://img1.mm131.com/pic/2631/4.jpg");
	 		 
	 			resReques.setFilepath(filepath);
	 	 
	 			resReques.addHead(new Header("user-agent", "AsyHttp/1.0 ml"));

	 			resReques.setRequestMethod(HttpMethod.Get);*/
	  		  
	 		//	AsyncHttp.instance().newRequest2(resReques, new RandomAccessFileTest());
 				
				
				
			LinkedList<download>	li=	RecordManager.Call().getErrorDownloadTasks();
				
				
				for(final download d:li){
					 AsyncHttp.instance().download(d, new DownProgrossCallback<ResponseBody<RandomAccessFile>>() {
							
							@Override
							public void download_current(long current, long total) {
								// TODO Auto-generated method stub
								// System.out.println("当前 "+e+"  current=="+current+"    total="+total);
							}
							
							@Override
								public void fail(Exception e,ResponseBody<RandomAccessFile> response) {
									// TODO Auto-generated method stub
									super.fail(e, response);
									e.printStackTrace();
								}
							@Override
								public void start() {
									// TODO Auto-generated method stub
									super.start();
									System.out.println(" 执行错误  task    startTime="+ Utils.getNowTime() +"  url=\n"+d.getRecordEntity().getUrl());
								}
								
							   @Override
								public void finish() {
									// TODO Auto-generated method stub
									super.finish();
								//	System.out.println("任务    endTime="+ Utils.getNowTime());

								}
							
							
						   });
					
					
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
		}, 1000*60,10000);
 		
 		
     
		
 		
	}
	
	
 
	 

	public void stop() {
		// TODO Auto-generated method stub
		
	}

	public void finish() {
		// TODO Auto-generated method stub
		
	}

	 
	public void fail(Exception e) {
		// TODO Auto-generated method stub
		System.out.println("=======================================");
		e.printStackTrace();
	}



	public void start() {
		// TODO Auto-generated method stub
		
	}



	public void success(ResponseBody<File> result) {
		// TODO Auto-generated method stub
      
        System.out.println("message="+result.getResult().getAbsolutePath());

	}



	@Override
	public void download_current(long current, long total) {
		// TODO Auto-generated method stub
		System.out.println("long current ="+current+" long total="+total);

	}

	 

}
