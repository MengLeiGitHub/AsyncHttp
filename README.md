##AsyncHttp
基于AsyncPool 线程池实现的 http请求，支持 post和 get、delete等多种请求，实现请求任务和结果异步获取

,简化网络请求

##AsyncHttp有哪些功能？

* 基本网络请求
* 文件下载（支持断点下载）
    *  单线程下载
    *  多线程下载
*  自定义头部请求信息
*  含有优先级，防止请求无反应
*  文件上传


##使用方法

####如何接入？
 
 ```java
    compile 'com.ml.asynchttp:asynchttp-android:1.1.1'
 
 ```



####初始化


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


*  参数1自定义请求处理拦截器，需要实现  RequestInterceptorActionInterface  接口
*  参数2是结果过滤器，可以自定义，只需要  实现  ResponseInterceptorActionInterface<ResponseBody> 接口


 ######提示 ： 请求拦截器和过滤器可以在框架初始化添加，也可以在创建请求的时候，添加到请求中
 
 
 
 #####普通网络请求 
 

```java
  ##### 常规写法
	String url=Contents.baseURL+"user/"+ machineid +"/getDutyList.do";
        Log.e("PaiBanActivity",url);
        AsyncHttpUtils.json(url, HttpMethod.Post, new HashMap<String, String>(), new JsonRequestLoadingCallback<PaiBanBean>(this) {
            @Override
            public void requestFail(Exception e, ResponseBody<String> request) {
                e.printStackTrace();
                showToast("网络出错,请重试");
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
                    showToast("获取数据失败,请重试");
                }
            }
        });
		
		
		##### 个性写法
		
		###### 步骤一:定义相关的接口
		
		public interface PathBeanTest {

        @JSONPOST("http://{ip}.26.106.136:8080/rest/common/user/login.do")
        public CProxyRequester test(@PathParam("ip")String ip, @Param("username")String username, @Param("password")String passwork );


        @GET("http://{ip}.26.106.136:8080/rest/common/allFactorys.do")
        public CProxyRequester getAll(@PathParam("ip")String ip);

		}
		*其中，方法注解包含有：JSONPOST  POST  GET  DELETE  PUT  ....等 ，JSONPOST和POST无技术上差别，只是为了开发人员辨识清晰
		*方法参数注解，包含两种，PathParam,Param ,PathParam表示匹配方法注解上的URL占位符，Param是请求的具体参数 ，注意：
		 例如上面的  {ip}  方法中@PathParam("ip")String ip  名称要对应  否则,请求失败
		
		

	###### 步骤二:通过代理实例化接口
	    PathBeanTest  t=   ProxyCreater.creator(PathBeanTest.class);
		
	###### 步骤三:设置结果处理线程级别
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


		######MIO.MainThread表示主线程，MIO.IOThread  表示IO线程
	
	
        
```

 
#####文件的下载  

```java
		写法一：
		
        String url="http://scimg.jb51.net/allimg/160815/103-160Q509544OC.jpg";
 
       //必须设置存储路径
        String filepath="C:\\Users\\admin\\Pictures\\Camera Roll\\img"+i+".jpg";

        FileRequest resReques=new FileRequest(url);

        resReques.setFilepath(filepath);

        resReques.addHead(new Header("user-agent", "AsyHttp/1.0 ml"));

        resReques.setRequestMethod(HttpMethod.Get);

        TaskHandler taskhandler= AsyncHttp.instance().download(resReques, new FileTest());
        //可以调用 taskhandler.stop()方法取消任务

        //FileTest 是 继承了 DownProgrossCallback<ResponseBody<T>>的 回掉接口，实现进度的监控，和结果的返回
    }
	
	
	写法二：
	
	AsyncHttpUtils.download("http://pic1.sc.chinaz.com/files/pic/pic9/201611/apic24088.jpg", "/sdcard/test/", "file.jpg", new DownProgrossCallback<ResponseBody<File>>() {
                    @Override
                    public void download_current(long current, long total) {
                        Log.e("tag", "current=" + current + " total=" + total);
                        int pr = (int) (current * 100.0 / total);
                        xiazai_pr.setProgress(pr);
                    }

                    @Override
                    public void start() {
                        Toast.makeText(getApplication(), "开始下载", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void finish() {
                        Toast.makeText(getApplication(), "完成", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void success(ResponseBody<File> result) {
                        Toast.makeText(getApplication(), "成功下载" + result.getResult().getPath(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void fail(Exception e, ResponseBody<File> request) {
                        e.printStackTrace();
                        Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
	
	写法三：
	
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





###### 多线程下载文件

```java
 多线程下载文件：
	    
        String urls="http://img.taopic.com/uploads/allimg/130711/318756-130G1222R317.jpg";
        String name=urls.substring(urls.lastIndexOf("/")+1,urls.length());
        String filepath="C:\\Users\\admin\\Pictures\\Camera Roll\\"+name;

        download s=  new download(new RecordEntity(urls,filepath));

        s.setTaskPriority(TaskPriority.LOWEST.getValue());//设置优先级


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


```


######上传 文件 (单线程)



```java
   #####一般写法1
	
   String url="http://192.168.1.33:8080/StrutsDemo2/upload.action";
   UploadRequest resReques=new UploadRequest(url);
   resReques.addParam(new FileParamPart("upload", new File("C:\\Users\\admin\\Pictures\\Camera Roll\\img10.jpg"),Constents.TYPE_IMAGE));
  
   resReques.setRequestMethod(HttpMethod.Post);
   AsyncHttp.instance().newRequest2(resReques, new uploadTest());
   //uploadTest是 继承了   UploadProgrossCallback<ResponseBody<T>>的 回掉接口，实现进度的监控，和结果的返回

   #####一般写法2
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
                         Toast.makeText(getApplication(), "开始上传", Toast.LENGTH_SHORT).show();
                     }

                     @Override
                     public void finish() {
                         Toast.makeText(getApplication(), "完成", Toast.LENGTH_SHORT).show();
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
   
   #####个性写法
   
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


##android 代码混淆

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
 # 使用Gson时需要配置Gson的解析对象及变量都不混淆。不然Gson会找不到变量。
 # 将下面替换成自己的实体类
 -keep class com.example.bean.** { *; }


</code></pre>






##有问题反馈
在使用中有任何问题，欢迎反馈给我，可以用以下联系方式跟我交流

* 邮件:menglei0207@sina.cn
* QQ群: 366802936
* github:https://github.com/MengLeiGitHub/)

##作者其他开源


* [miniorm-for-android](https://github.com/MengLeiGitHub/miniOrm-for-android) 
* [AsyncPool](https://github.com/MengLeiGitHub/AsyncPool)
