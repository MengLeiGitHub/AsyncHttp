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
 compile 'com.ml.asynchttp:asynchttp:1.0.0'
 
 ```



####初始化


 ```java
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
 
 ```

*  ResponseIn1是结果过滤器，可以自定义，只需要  实现  ResponseInterceptorActionInterface<ResponseBody> 接口

*      RequestIn1 自定义请求处理拦截器，需要实现  RequestInterceptorActionInterface  接口

 ######提示 ： 请求拦截器和过滤器可以在框架初始化添加，也可以在创建请求的时候，添加到请求中
 
 
 
 #####普通网络请求 （有优先级之分）
   * 高优先级

```java
     String urls="http://211.149.184.79:8080/we/car/getAllCarMessageForPage.do";
     StringRequest resReques=new StringRequest(urls, Charsets.UTF_8);
     resReques.addParam(new StringParamPart("page", "1"));
     resReques.addParam(new StringParamPart("size", "2"));
     resReques.addParam(new StringParamPart("index", "index"+1));
     AsyncHttp.instance().stringRequest(resReques, new StringTest());
        
```
  * 低优先级

```java
    String url="http://211.149.184.79:8080/we/car/getAllCarMessageForPage.do";
    StringRequest resReques=new StringRequest(url, Charsets.UTF_8);
    resReques.addParam(new StringParamPart("page", "1"));
    resReques.addParam(new StringParamPart("size", "2"));
    resReques.addParam(new StringParamPart("index", "index"+1));
    AsyncHttp.instance().newRequest2(resReques, new StringTest());
```
 
#####文件的下载  

```java
        String url="http://scimg.jb51.net/allimg/160815/103-160Q509544OC.jpg";
 
       //必须设置存储路径
        String filepath="C:\\Users\\admin\\Pictures\\Camera Roll\\img"+i+".jpg";

        FileRequest resReques=new FileRequest(url);

        resReques.setFilepath(filepath);

        resReques.addHead(new Header("user-agent", "AsyHttp/1.0 ml"));

        resReques.setRequestMethod(HttpMethod.Get);

        TaskHandler taskhandler= AsyncHttp.instance().newRequest2(resReques, new FileTest());
        //可以调用 taskhandler.stop()方法取消任务

        //FileTest 是 继承了 DownProgrossCallback<ResponseBody<T>>的 回掉接口，实现进度的监控，和结果的返回
    }
     
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


   String url="http://192.168.1.33:8080/StrutsDemo2/upload.action";
   StringRequest resReques=new StringRequest(url, Charsets.UTF_8);
   resReques.addParam(new FileParamPart("upload", new File("C:\\Users\\admin\\Pictures\\Camera Roll\           \img10.jpg"),Constents.TYPE_IMAGE));
   //resReques.addParam(new FileParamPart("upload", new File("C:\\Users\\admin\\Pictures\\Camera Roll\         \ds.txt"),Constents.TYPE_TEXT));

   resReques.setRequestMethod(HttpMethod.Post);
   AsyncHttp.instance().newRequest2(resReques, new uploadTest());
   //uploadTest是 继承了   UploadProgrossCallback<ResponseBody<T>>的 回掉接口，实现进度的监控，和结果的返回

 

```









##有问题反馈
在使用中有任何问题，欢迎反馈给我，可以用以下联系方式跟我交流

* 邮件:menglei0207@sina.cn
* QQ群: 366802936
* github:https://github.com/MengLeiGitHub/)

##作者其他开源


* [miniorm-for-android](https://github.com/MengLeiGitHub/miniOrm-for-android) 
* [AsyncPool](https://github.com/MengLeiGitHub/AsyncPool)
