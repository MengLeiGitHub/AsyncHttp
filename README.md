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
 compile 'com.ml.asynchttp:asynchttp:1.0.0'
 
 ```



####��ʼ��


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

*  ResponseIn1�ǽ���������������Զ��壬ֻ��Ҫ  ʵ��  ResponseInterceptorActionInterface<ResponseBody> �ӿ�

*      RequestIn1 �Զ�������������������Ҫʵ��  RequestInterceptorActionInterface  �ӿ�

 ######��ʾ �� �����������͹����������ڿ�ܳ�ʼ����ӣ�Ҳ�����ڴ��������ʱ����ӵ�������
 
 
 
 #####��ͨ�������� �������ȼ�֮�֣�
   * �����ȼ�

```java
     String urls="http://211.149.184.79:8080/we/car/getAllCarMessageForPage.do";
     StringRequest resReques=new StringRequest(urls, Charsets.UTF_8);
     resReques.addParam(new StringParamPart("page", "1"));
     resReques.addParam(new StringParamPart("size", "2"));
     resReques.addParam(new StringParamPart("index", "index"+1));
     AsyncHttp.instance().stringRequest(resReques, new StringTest());
        
```
  * �����ȼ�

```java
    String url="http://211.149.184.79:8080/we/car/getAllCarMessageForPage.do";
    StringRequest resReques=new StringRequest(url, Charsets.UTF_8);
    resReques.addParam(new StringParamPart("page", "1"));
    resReques.addParam(new StringParamPart("size", "2"));
    resReques.addParam(new StringParamPart("index", "index"+1));
    AsyncHttp.instance().newRequest2(resReques, new StringTest());
```
 
#####�ļ�������  

```java
        String url="http://scimg.jb51.net/allimg/160815/103-160Q509544OC.jpg";
 
       //�������ô洢·��
        String filepath="C:\\Users\\admin\\Pictures\\Camera Roll\\img"+i+".jpg";

        FileRequest resReques=new FileRequest(url);

        resReques.setFilepath(filepath);

        resReques.addHead(new Header("user-agent", "AsyHttp/1.0 ml"));

        resReques.setRequestMethod(HttpMethod.Get);

        TaskHandler taskhandler= AsyncHttp.instance().newRequest2(resReques, new FileTest());
        //���Ե��� taskhandler.stop()����ȡ������

        //FileTest �� �̳��� DownProgrossCallback<ResponseBody<T>>�� �ص��ӿڣ�ʵ�ֽ��ȵļ�أ��ͽ���ķ���
    }
     
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


   String url="http://192.168.1.33:8080/StrutsDemo2/upload.action";
   StringRequest resReques=new StringRequest(url, Charsets.UTF_8);
   resReques.addParam(new FileParamPart("upload", new File("C:\\Users\\admin\\Pictures\\Camera Roll\           \img10.jpg"),Constents.TYPE_IMAGE));
   //resReques.addParam(new FileParamPart("upload", new File("C:\\Users\\admin\\Pictures\\Camera Roll\         \ds.txt"),Constents.TYPE_TEXT));

   resReques.setRequestMethod(HttpMethod.Post);
   AsyncHttp.instance().newRequest2(resReques, new uploadTest());
   //uploadTest�� �̳���   UploadProgrossCallback<ResponseBody<T>>�� �ص��ӿڣ�ʵ�ֽ��ȵļ�أ��ͽ���ķ���

 

```









##�����ⷴ��
��ʹ�������κ����⣬��ӭ�������ң�������������ϵ��ʽ���ҽ���

* �ʼ�:menglei0207@sina.cn
* QQȺ: 366802936
* github:https://github.com/MengLeiGitHub/)

##����������Դ


* [miniorm-for-android](https://github.com/MengLeiGitHub/miniOrm-for-android) 
* [AsyncPool](https://github.com/MengLeiGitHub/AsyncPool)
