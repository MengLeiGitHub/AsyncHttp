# AsyncHttp

1.基于AsyncPool 线程池实现的 http请求，支持 post和 get 两中请求，实现请求任务和结果异步获取，版本1.0

   框架初始化：1.
       
		 
	  请求的一些基本参数，在框架初始化的时候设置。
          
          RequestConfig requestConfig=new RequestConfig();
          requestConfig.setConnectTimeout(10000);
          requestConfig.setSocketTimeout(30000);
          requestConfig.setRequestMethod(HttpMethod.Post);
          ArrayList<Header> headerlist=new ArrayList<Header>();
          headerlist.add(new Header("connection", "Keep-Alive"));
          headerlist.add(new Header("user-agent", "AsyncHttp 1.0"));
          headerlist.add(new Header("Accept-Charset", "utf-8"));
          headerlist.add(new Header(Constents.CONTENT_TYPE, Constents.TYPE_FORM_DATA));
          requestConfig.setHeadList(headerlist);
          
          
          AsyncHttp.instance().addRequestInterceptor(new RequestIn1())
			.addRequestInterceptor(new RequestIn2())
			.addResponseInterceptor(new ResponseIn1())
			.addResponseInterceptor(new ResponseIn2()).setConfig(requestConfig);
	
	  ResponseIn1是结果过滤器，可以自定义，只需要  实现  ResponseInterceptorActionInterface<ResponseBody> 接口，就可以

		RequestIn1 自定义请求处理拦截器，需要实现  RequestInterceptorActionInterface  接口

	 请求拦截器和过滤器可以在框架初始化添加，也可以在创建请求的时候，添加到请求中
		

         功能（1）、文件的下载 （ 必须要 GET请求，否则会报错，还未做进一步优化）
          
          
         String url="http://211.149.184.79:8080/we/car/getAllCarMessageForPage.do";
         
         String[] u={
        		 "http://scimg.jb51.net/allimg/160815/103-160Q509544OC.jpg"
        		 ,"http://scimg.jb51.net/allimg/160813/103-160Q3143110P5.jpg"
        		 ,"http://pic24.nipic.com/20121029/5056611_120019351000_2.jpg"
        		 ,"http://img.taopic.com/uploads/allimg/130711/318756-130G1222R317.jpg"
        		 ,"http://pic14.nipic.com/20110610/7181928_110502231129_2.jpg"
        		 ,"http://pic41.nipic.com/20140509/18696269_121755386187_2.png"
        		 ,"http://pic55.nipic.com/file/20141208/19462408_171130083000_2.jpg"
        		 ,"http://pica.nipic.com/2008-03-11/2008311112935830_2.gif"
        		 ,"http://img.taopic.com/uploads/allimg/120423/107913-12042323220753.jpg"
        		 ,"http://img5.imgtn.bdimg.com/it/u=484208524,194442631&amp;fm=21&amp;gp=0.jpg"

        		 
         };
         
         
         
 		for(int i=0;i<u.length;i++){
 			
 		    	//必须设置存储路径
 	   		String filepath="C:\\Users\\admin\\Pictures\\Camera Roll\\img"+i+".jpg";
 			
 			FileRequest resReques=new FileRequest(u[i]);
 		 
 			resReques.setFilepath(filepath);
 			resReques.setIndex(i);
 		  
 			resReques.addHead(new Header("user-agent", "AsyHttp/1.0 ml"));

 			resReques.setRequestMethod(HttpMethod.Get);
  		  
 			TaskHandler taskhandler= AsyncHttp.instance().newRequest2(resReques, new FileTest());
			//可以调用 taskhandler.stop()方法取消任务
  			
 			//FileTest 是 继承了 DownProgrossCallback<ResponseBody<T>>的 回掉接口，实现进度的监控，和结果的返回
 		}





	（2）上传 文件 

 		        String url="http://192.168.1.33:8080/StrutsDemo2/upload.action";
         
         		
 		 
 			 		
 			StringRequest resReques=new StringRequest(url, Charsets.UTF_8);
 		 	resReques.addParam(new FileParamPart("upload", new File("C:\\Users\\admin\\Pictures\\Camera Roll\			\img10.jpg"),Constents.TYPE_IMAGE));
 			//resReques.addParam(new FileParamPart("upload", new File("C:\\Users\\admin\\Pictures\\Camera Roll\			\ds.txt"),Constents.TYPE_TEXT));

 			resReques.setRequestMethod(HttpMethod.Post);
 			AsyncHttp.instance().newRequest2(resReques, new uploadTest());

		        //uploadTest是 继承了   UploadProgrossCallback<ResponseBody<T>>的 回掉接口，实现进度的监控，和结果的返回

		(3) 普通网络请求

		   String url="http://211.149.184.79:8080/we/car/getAllCarMessageForPage.do";
       
          
 		
 			StringRequest resReques=new StringRequest(url, Charsets.UTF_8);
 			resReques.addParam(new StringParamPart("page", "1"));
 			resReques.addParam(new StringParamPart("size", "2"));
 			resReques.addParam(new StringParamPart("index", "index"+1));
 		
  		  
 			AsyncHttp.instance().newRequest2(resReques, new StringTest());
		// StringTest  	 实现接口 HttpCallBack<ResponseBody<T>>


	###########################################################################################
   		 
		框架正在进一步的优化中，敬请请期待