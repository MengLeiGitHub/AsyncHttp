# AsyncHttp

1.����AsyncPool �̳߳�ʵ�ֵ� http����֧�� post�� get ��������ʵ����������ͽ���첽��ȡ���汾1.0

   ��ܳ�ʼ����1.
       
		 
	  �����һЩ�����������ڿ�ܳ�ʼ����ʱ�����á�
          
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
	
		ResponseIn1�ǽ���������������Զ��壬ֻ��Ҫ  ʵ��  ResponseInterceptorActionInterface<ResponseBody> �ӿڣ��Ϳ���

		RequestIn1 �Զ�������������������Ҫʵ��  RequestInterceptorActionInterface  �ӿ�

		�����������͹����������ڿ�ܳ�ʼ����ӣ�Ҳ�����ڴ��������ʱ����ӵ�������
		

        ���ܣ�1�����ļ������� �����߳� ����Ҫ GET���󣬷���ᱨ����δ����һ���Ż���
          
          
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
 			
 		    	//�������ô洢·��
 	   		String filepath="C:\\Users\\admin\\Pictures\\Camera Roll\\img"+i+".jpg";
 			
 			FileRequest resReques=new FileRequest(u[i]);
 		 
 			resReques.setFilepath(filepath);
 			resReques.setIndex(i);
 		  
 			resReques.addHead(new Header("user-agent", "AsyHttp/1.0 ml"));

 			resReques.setRequestMethod(HttpMethod.Get);
  		  
 			TaskHandler taskhandler= AsyncHttp.instance().newRequest2(resReques, new FileTest());
			//���Ե��� taskhandler.stop()����ȡ������
  			
 			//FileTest �� �̳��� DownProgrossCallback<ResponseBody<T>>�� �ص��ӿڣ�ʵ�ֽ��ȵļ�أ��ͽ���ķ���
 		}


		    ���߳������ļ���
			String urls="http://img.taopic.com/uploads/allimg/130711/318756-130G1222R317.jpg";
		    String name=urls.substring(urls.lastIndexOf("/")+1,urls.length());
 	   		String filepath="C:\\Users\\admin\\Pictures\\Camera Roll\\"+name;
 			
 	   		download s=	 new download(new RecordEntity(urls,filepath));
 	   		
  			s.setTaskPriority(TaskPriority.LOWEST.getValue());//�������ȼ�
  		 

 			TaskHandler t=   AsyncHttp.instance().download(s, new DownProgrossCallback<ResponseBody<RandomAccessFile>>() {
			
			@Override
			public void download_current(long current, long total) {
				// TODO Auto-generated method stub
				// System.out.println("��ǰ "+e+"  current=="+current+"    total="+total);
			}
			
			@Override
				public void fail(Exception e1,ResponseBody<RandomAccessFile> response) {
					// TODO Auto-generated method stub
					super.fail(e1,response);
					System.out.println("����   "+ e+"   ="+ Utils.getNowTime()+"  e1="+e1.getMessage());
								 
				}
			@Override
				public void start() {
					// TODO Auto-generated method stub
					super.start();
					System.out.println("����   "+ e+"  startTime="+ Utils.getNowTime());
				}
				
			   @Override
				public void finish() {
					// TODO Auto-generated method stub
					super.finish();
					System.out.println("����   "+ e+"  endTime="+ Utils.getNowTime());

				}
			
			
		   });


	��2���ϴ� �ļ� 

 		        String url="http://192.168.1.33:8080/StrutsDemo2/upload.action";
         
         		
 		 
 			 		
 			StringRequest resReques=new StringRequest(url, Charsets.UTF_8);
 		 	resReques.addParam(new FileParamPart("upload", new File("C:\\Users\\admin\\Pictures\\Camera Roll\			\img10.jpg"),Constents.TYPE_IMAGE));
 			//resReques.addParam(new FileParamPart("upload", new File("C:\\Users\\admin\\Pictures\\Camera Roll\			\ds.txt"),Constents.TYPE_TEXT));

 			resReques.setRequestMethod(HttpMethod.Post);
 			AsyncHttp.instance().newRequest2(resReques, new uploadTest());

		        //uploadTest�� �̳���   UploadProgrossCallback<ResponseBody<T>>�� �ص��ӿڣ�ʵ�ֽ��ȵļ�أ��ͽ���ķ���

		(3) ��ͨ�������� �������ȼ�֮�֣�

			1.�����ȼ�
			
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
			
			2.�����ȼ�
			
		   String url="http://211.149.184.79:8080/we/car/getAllCarMessageForPage.do";
       
          
 		
 			StringRequest resReques=new StringRequest(url, Charsets.UTF_8);
 			resReques.addParam(new StringParamPart("page", "1"));
 			resReques.addParam(new StringParamPart("size", "2"));
 			resReques.addParam(new StringParamPart("index", "index"+1));
 		
  		  
 			AsyncHttp.instance().newRequest2(resReques, new StringTest());
			
			
			
		// StringTest  	 ʵ�ֽӿ� HttpCallBack<ResponseBody<T>>

		
		
		
		
		

	###########################################################################################
   		 
		������ڽ�һ�����Ż��У��������ڴ�