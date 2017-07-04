package com.android;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/**
 * Created by admin on 2016-12-22.
 */

public class NetWork {
        public String login( String requestURL) throws Exception {
            String result="";
            try{
                URL url=new URL(requestURL);
                HttpsURLConnection urlConn = (HttpsURLConnection)url.openConnection();
                urlConn.setDoInput(true);//设置输入流采用字节流
                urlConn.setDoOutput(true);//设置输出流采用字节流
                urlConn.setRequestMethod("POST");
                urlConn.setUseCaches(false);//设置缓存

                Log.e("result",urlConn.getOutputStream()+"");


                TrustManager[] tm = { new MyX509TrustManager() };
                SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
                sslContext.init(null, tm, new java.security.SecureRandom());
                // 从上述SSLContext对象中得到SSLSocketFactory对象
                SSLSocketFactory ssf = sslContext.getSocketFactory();
                urlConn.setSSLSocketFactory(ssf);

                // 设置请求的超时时间
                urlConn.setReadTimeout(5000);
                urlConn.setConnectTimeout(5000);
                urlConn.setRequestProperty("Content-Type",
                        "application/json;charset=UTF-8");//设置methed参数
                urlConn.setRequestProperty("Charset", "utf-8");
                urlConn.setRequestProperty("version", "1.0");
                urlConn.setRequestProperty("tokenId", "8FA24C888B39405FB46499C62E48A504");
                urlConn.setRequestProperty("token", "675E020A70424BDCBC8DBD6A8D81FBDA");
                urlConn.setRequestProperty("appType", "2");
                urlConn.setRequestProperty("deviceId", "1231232342342341");
                urlConn.setRequestProperty("ostype", "1");

/*
        baserequest.addHead(new Header("version", "1.0"));
                baserequest.addHead(new Header("tokenId", "8FA24C888B39405FB46499C62E48A504"));
                baserequest.addHead(new Header("token", "675E020A70424BDCBC8DBD6A8D81FBDA"));
                //35C43D51E8C844B69B4AF149A82B40E7
                baserequest.addHead(new Header("appType", "2"));
                baserequest.addHead(new Header("ostype", "1"));
                baserequest.addHead(new Header("deviceId", "1231232342342341"));
 */

                urlConn.connect();//链接既往服务器发送消息
                PrintWriter pw=new PrintWriter(urlConn.getOutputStream());
                StringBuffer buffer=new StringBuffer();
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("username","15093201628");
                jsonObject.put("password","e565f08d058ebb4a1c99907a9860a93b");

               /* for(Map.Entry<String, String> entry : map.entrySet()){
                    buffer.append(URLEncoder.encode(entry.getKey(), "utf-8") + "=" + URLEncoder.encode(entry.getValue(), "utf-8") + "&");
                }*/
                pw.print(jsonObject.toString().getBytes("UTF-8"));
                pw.flush();

                InputStream inputStream=urlConn.getInputStream();
                int httpCode=urlConn.getResponseCode();

                byte[] bufferBytes = new byte[1];
                    BufferedReader bf=new BufferedReader(new InputStreamReader(inputStream, Charset.forName("utf-8")));
                    //定义String类型用于储存单行数据
                    String line=null;
                    //创建StringBuffer对象用于存储所有数据
                    StringBuffer sb=new StringBuffer();
                    while((line=bf.readLine())!=null){

                        sb.append(line);
                    }
                    result=sb.toString();
                Log.e("result",result+"");

                pw.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
        public String doGet(Map<String,String> map,String requestUrl){
            String result="";
            try{
                StringBuffer buffer=new StringBuffer("?");
                if(map!=null)
                    for(Map.Entry<String,String> mapItem:map.entrySet() ){
                        buffer.append(URLEncoder.encode(mapItem.getKey(), "utf-8")+"="+URLEncoder.encode(mapItem.getValue(), "utf-8")+"&");
                    }
                URL url=new URL(requestUrl+buffer.toString());
                HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                conn.setDoOutput(false);
                conn.setDoInput(true);
                conn.setRequestMethod("GET");
                conn.setUseCaches(false);
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置methed参数
                conn.setRequestProperty("Charset", "utf-8");
                conn.connect();//链接既往服务器发送消息
                InputStream inputStream=conn.getInputStream();
                int httpCode=conn.getResponseCode();
                if(httpCode==200){
                    byte[] bufferBytes = new byte[1];
                    BufferedReader bf=new BufferedReader(new InputStreamReader(inputStream, Charset.forName("utf-8")));
                    //定义String类型用于储存单行数据
                    String line=null;
                    //创建StringBuffer对象用于存储所有数据
                    StringBuffer sb=new StringBuffer();
                    while((line=bf.readLine())!=null){
                        sb.append(line);
                    }
                    Log.e("tag","result="+result);
                    return sb.toString();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
        public String doPost(Map<String,String> map,String requestURL){
            String result="";
            try{
                URL url=new URL(requestURL);
                HttpURLConnection urlConn=(HttpURLConnection)url.openConnection();
                urlConn.setDoInput(true);//设置输入流采用字节流
                urlConn.setDoOutput(true);//设置输出流采用字节流
                urlConn.setRequestMethod("POST");
                urlConn.setUseCaches(false);//设置缓存
                // 设置请求的超时时间
                urlConn.setReadTimeout(5000);
                urlConn.setConnectTimeout(5000);
                urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置methed参数
                urlConn.setRequestProperty("Charset", "utf-8");
                urlConn.connect();//链接既往服务器发送消息
                PrintWriter pw=new PrintWriter(urlConn.getOutputStream());
                StringBuffer buffer=new StringBuffer();
                if(map!=null)
                    for(Map.Entry<String, String> entry : map.entrySet()){
                        buffer.append(URLEncoder.encode(entry.getKey(), "utf-8") + "=" + URLEncoder.encode(entry.getValue(), "utf-8") + "&");
                    }
                pw.print(buffer);
                pw.flush();
                pw.close();
                InputStream inputStream=urlConn.getInputStream();
                int httpCode=urlConn.getResponseCode();
                if(httpCode==200){
                    byte[] bufferBytes = new byte[1];
                    BufferedReader bf=new BufferedReader(new InputStreamReader(inputStream, Charset.forName("utf-8")));
                    //定义String类型用于储存单行数据
                    String line=null;
                    //创建StringBuffer对象用于存储所有数据
                    StringBuffer sb=new StringBuffer();
                    while((line=bf.readLine())!=null){
                        sb.append(line);
                    }
                    result=sb.toString();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
    }
