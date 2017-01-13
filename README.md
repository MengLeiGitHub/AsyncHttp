##AsyncHttp
����AsyncPool �̳߳�ʵ�ֵ� http����֧�� post�� get��delete�ȶ�������ʵ����������ͽ���첽��ȡ

,����������

##AsyncHttp����Щ���ܣ�

* ������������
* �ļ����أ�֧�ֶϵ����أ�
    *  ���߳�����
    *  ���߳�����
*  �Զ���ͷ��������Ϣ
*  �������ȼ�����ֹ�����޷�Ӧ
*  �ļ��ϴ�


##ʹ�÷���

####��ν��룿
 
 ```java
    compile 'com.ml.asynchttp:asynchttp-android:1.1.1'
 
 ```



####��ʼ��


 ```java
    
        AsyncHttpUtils.init(new RequestInterceptorActionInterface() {
            @Override
            public <T> BaseHttpRequest<T> interceptorAction(BaseHttpRequest<T> baserequest) throws Exception {
                TelephonyManager tm = (TelephonyManager) BaseApplication.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
              

                return baserequest;
            }
        }, new  ResponseInterceptorActionInterface(){
		);
        LogUtils.setDebug(true);
 
 ```


*  ����1�Զ�������������������Ҫʵ��  RequestInterceptorActionInterface  �ӿ�
*  ����2�ǽ���������������Զ��壬ֻ��Ҫ  ʵ��  ResponseInterceptorActionInterface<ResponseBody> �ӿ�


 ######��ʾ �� �����������͹����������ڿ�ܳ�ʼ����ӣ�Ҳ�����ڴ��������ʱ����ӵ�������
 
 
 
 #####��ͨ�������� 
 

```java
  ##### ����д��
	String url=Contents.baseURL+"user/"+ machineid +"/getDutyList.do";
        Log.e("PaiBanActivity",url);
        AsyncHttpUtils.json(url, HttpMethod.Post, new HashMap<String, String>(), new JsonRequestLoadingCallback<PaiBanBean>(this) {
            @Override
            public void requestFail(Exception e, ResponseBody<String> request) {
                e.printStackTrace();
                showToast("�������,������");
            }

            @Override
            public void requsetFinish() {

            }

            @Override
            public void requsetStart() {

            }

            @Override
            public void requestSuccess(PaiBanBean paiBanBean) {
                Log.e("PaiBanActivity",paiBanBean.toString() );
                int status = paiBanBean.getStatus();
                if (status==1) {
                    setPaiBan(paiBanBean);
                }else {
                    showToast("��ȡ����ʧ��,������");
                }
            }
        });
		
		
		##### ����д��
		
		###### ����һ:������صĽӿ�
		
		public interface PathBeanTest {

        @JSONPOST("http://{ip}.26.106.136:8080/rest/common/user/login.do")
        public CProxyRequester test(@PathParam("ip")String ip, @Param("username")String username, @Param("password")String passwork );


        @GET("http://{ip}.26.106.136:8080/rest/common/allFactorys.do")
        public CProxyRequester getAll(@PathParam("ip")String ip);

		}
		*���У�����ע������У�JSONPOST  POST  GET  DELETE  PUT  ....�� ��JSONPOST��POST�޼����ϲ��ֻ��Ϊ�˿�����Ա��ʶ����
		*��������ע�⣬�������֣�PathParam,Param ,PathParam��ʾƥ�䷽��ע���ϵ�URLռλ����Param������ľ������ ��ע�⣺
		 ���������  {ip}  ������@PathParam("ip")String ip  ����Ҫ��Ӧ  ����,����ʧ��
		
		

	###### �����:ͨ������ʵ�����ӿ�
	    PathBeanTest  t=   ProxyCreater.creator(PathBeanTest.class);
		
	###### ������:���ý�������̼߳���
	    t.getAll("120").ResultMonitor(MIO.MainThread).Observation(new StringRequestResultCallBack<FactoryTestBean>() {
                @Override
                public void requestFail(Exception e, ResponseBody<String> request) {

                }

                @Override
                public void requestSuccess(FactoryTestBean factoryTestBean) {
                    Log.e("tag",factoryTestBean.getMsg()+"  "+factoryTestBean.getData().getList().size());
                }

                @Override
                public void requsetFinish() {

                }

                @Override
                public void requsetStart() {

                }
            });


		######MIO.MainThread��ʾ���̣߳�MIO.IOThread  ��ʾIO�߳�
	
	
        
```

 
#####�ļ�������  

```java
		д��һ��
		
        String url="http://scimg.jb51.net/allimg/160815/103-160Q509544OC.jpg";
 
       //�������ô洢·��
        String filepath="C:\\Users\\admin\\Pictures\\Camera Roll\\img"+i+".jpg";

        FileRequest resReques=new FileRequest(url);

        resReques.setFilepath(filepath);

        resReques.addHead(new Header("user-agent", "AsyHttp/1.0 ml"));

        resReques.setRequestMethod(HttpMethod.Get);

        TaskHandler taskhandler= AsyncHttp.instance().download(resReques, new FileTest());
        //���Ե��� taskhandler.stop()����ȡ������

        //FileTest �� �̳��� DownProgrossCallback<ResponseBody<T>>�� �ص��ӿڣ�ʵ�ֽ��ȵļ�أ��ͽ���ķ���
    }
	
	
	д������
	
	AsyncHttpUtils.download("http://pic1.sc.chinaz.com/files/pic/pic9/201611/apic24088.jpg", "/sdcard/test/", "file.jpg", new DownProgrossCallback<ResponseBody<File>>() {
                    @Override
                    public void download_current(long current, long total) {
                        Log.e("tag", "current=" + current + " total=" + total);
                        int pr = (int) (current * 100.0 / total);
                        xiazai_pr.setProgress(pr);
                    }

                    @Override
                    public void start() {
                        Toast.makeText(getApplication(), "��ʼ����", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void finish() {
                        Toast.makeText(getApplication(), "���", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void success(ResponseBody<File> result) {
                        Toast.makeText(getApplication(), "�ɹ�����" + result.getResult().getPath(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void fail(Exception e, ResponseBody<File> request) {
                        e.printStackTrace();
                        Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
	
	д������
	
			public interface DownloadTest {
				@DOWNLOAD("http://pic6.nipic.com/20100426/1687102_082357914928_2.jpg")
				public CProxyRequester<File> down(@Param String path);
			}

		    DownloadTest downloadTest=ProxyCreater.creator(DownloadTest.class);
			 downloadTest.down("/sdcard/test/game.apk").ResultMonitor(MIO.MainThread).Observation(new DownProgrossCallback<ResponseBody<File>>() {
                    @Override
                    public void download_current(long current, long total) {
                      //  Log.e("tag", "current=" + current + " total=" + total);
                        int pr = (int) (current * 100.0 / total);
                        xiazai_pr.setProgress(pr);
                    }

                    @Override
                    public void start() {
                        xiazai_pr.setMax(100);
                    }

                    @Override
                    public void finish() {

                    }

                    @Override
                    public void success(ResponseBody<File> result) {
                        Log.e("tag",result.getResult().getAbsolutePath());
                    }

                    @Override
                    public void fail(Exception e, ResponseBody request) {

                    }
                });

     
```





###### ���߳������ļ�

```java
 ���߳������ļ���
	    
        String urls="http://img.taopic.com/uploads/allimg/130711/318756-130G1222R317.jpg";
        String name=urls.substring(urls.lastIndexOf("/")+1,urls.length());
        String filepath="C:\\Users\\admin\\Pictures\\Camera Roll\\"+name;

        download s=  new download(new RecordEntity(urls,filepath));

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


```


######�ϴ� �ļ� (���߳�)



```java
   #####һ��д��1
	
   String url="http://192.168.1.33:8080/StrutsDemo2/upload.action";
   UploadRequest resReques=new UploadRequest(url);
   resReques.addParam(new FileParamPart("upload", new File("C:\\Users\\admin\\Pictures\\Camera Roll\\img10.jpg"),Constents.TYPE_IMAGE));
  
   resReques.setRequestMethod(HttpMethod.Post);
   AsyncHttp.instance().newRequest2(resReques, new uploadTest());
   //uploadTest�� �̳���   UploadProgrossCallback<ResponseBody<T>>�� �ص��ӿڣ�ʵ�ֽ��ȵļ�أ��ͽ���ķ���

   #####һ��д��2
   <pre><code>
   AsyncHttpUtils.upload("http://120.26.106.136:8080/rest/common/user/uploadAvatar.do", "/sdcard/test/test.jpg", "file", new UploadProgrossCallback<ResponseBody<String>>() {
                     @Override
                     public void upload_current(long current, long currentFileTotal, long total) {
                         int pr = (int) (current * 100.0 / total);
                         Log.e("tag", "current=" + current + " currentFileTotal= " + currentFileTotal + "  total=" + total);
                         progressBar_shangchuan.setProgress(pr);
                     }


                     @Override
                     public void start() {
                         Toast.makeText(getApplication(), "��ʼ�ϴ�", Toast.LENGTH_SHORT).show();
                     }

                     @Override
                     public void finish() {
                         Toast.makeText(getApplication(), "���", Toast.LENGTH_SHORT).show();
                     }

                     @Override
                     public void success(ResponseBody<String> result) {
                         Log.e("tag", result.getResult());

                     }

                     @Override
                     public void fail(Exception e, ResponseBody<String> request) {
                         Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_SHORT).show();
                         e.printStackTrace();
                         Log.e("tag", request.getResult());

                     }
                 });
				 </code></pre>
   
   #####����д��
   
   <pre><code>
	public interface UploadTest {

	@UPLOAD("http://120.26.106.136:8080/rest/common/user/uploadFile.do")
	public CProxyRequester<UploadResultBean> upload(@Param("file") String filepath);
	}
 
	UploadTest uploadTest= ProxyCreater.creator(UploadTest.class);
                uploadTest.upload("/sdcard/test/file.jpg").ResultMonitor(MIO.MainThread).Observation(new UploadRequestResultCallBack<UploadResultBean>(){

                    @Override
                    public void upload_current(long current, long currentFileTotal, long total) {
                        int pr = (int) (current * 100.0 / total);
                        Log.e("tag", "current=" + current + " currentFileTotal= " + currentFileTotal + "  total=" + total);
                        progressBar_shangchuan.setProgress(pr);
                    }

                    @Override
                    public void requestFail(Exception e, ResponseBody<String> request) {
                            e.printStackTrace();
                    }

                    @Override
                    public void requestSuccess(UploadResultBean uploadResultBean) {
                        Log.e("tag",uploadResultBean.getMsg());
                        Toast.makeText(getApplicationContext(),uploadResultBean.getData().getName(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void requsetFinish() {

                    }

                    @Override
                    public void requsetStart() {

                    }
                });

</code></pre>


##android �������

<pre><code>

-keep class com.async.**
-keepclassmembers class com.async.** { *; }
-keep enum com.async.**
-keepclassmembers enum com.async.** { *; }
-keep interface com.async.**
-keepclassmembers interface com.async.** { *; }

 # Gson
 -keepattributes Signature
 -keepattributes *Annotation*
 -keep class sun.misc.Unsafe { *; }
 -keep class com.google.gson.stream.** { *; }
 # ʹ��Gsonʱ��Ҫ����Gson�Ľ������󼰱���������������ȻGson���Ҳ���������
 # �������滻���Լ���ʵ����
 -keep class com.example.bean.** { *; }


</code></pre>






##�����ⷴ��
��ʹ�������κ����⣬��ӭ�������ң�������������ϵ��ʽ���ҽ���

* �ʼ�:menglei0207@sina.cn
* QQȺ: 366802936
* github:https://github.com/MengLeiGitHub/)

##����������Դ


* [miniorm-for-android](https://github.com/MengLeiGitHub/miniOrm-for-android) 
* [AsyncPool](https://github.com/MengLeiGitHub/AsyncPool)
