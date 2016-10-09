package test;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSON;
import com.async.http.request2.record.RecordEntity;
import com.async.http.utils.UrlEncodeUtils;

public class nnn {

	void test() throws Exception
    {
          try
          {
            int a = 1/0;
          }catch(Exception e){
            throw e;
          }
          finally
          {
            System.out.println("finally");
          }
    }
     
    public static void main(String[] args)
    {
        try
        {
        	String ss = "http://pic14.nipic.com/20110610/7181928_110502231s129_2.jpg";   

            System.out.println(UrlEncodeUtils.encodeUrl(ss));   
            System.out.println(ss.replaceAll("12z[(]ÐÞ¶©¹ý£©.doc", ""));  
        	
 	   		String filepath="C:\\Users\\admin\\Pictures\\Camera Roll\\00\\result.txt";
             RecordEntity recordEntity=new RecordEntity();
             recordEntity.setUrl(ss);
             recordEntity.setStartTag(0);
             recordEntity.setEndTag(20000);
            long start= System.currentTimeMillis();
            File  file=   new File(filepath);
            
          ///   System.out.println(ss.lastIndexOf("/"));

            
            String re=file.getParent()+File.separator+ss.substring(ss.lastIndexOf("/"), ss.lastIndexOf("."));
            
         //   System.out.println("ee="+new File(re).getParentFile().length());
            
            /*
               if(!new File(re).exists()){
            	new File(re).mkdirs();
            }
            System.out.println(re);

           String  f = re+File.separator+recordEntity.getStartTag()+"_"+recordEntity.getEndTag();
           
           
           
          RandomAccessFile randomAccessFile=new RandomAccessFile(f, "rw");

             int len=0;
            for (int i = 0; i < 10000; i++) {
            	len+=i;
            	recordEntity.setCurrent(i);
            	randomAccessFile.seek(0);
             	randomAccessFile.write(JSON.toJSONString(recordEntity).getBytes("utf-8"));
			}
            System.out.println("end-start="+(System.currentTimeMillis()-start));
            
            */
            
            
            
            
      //      new nnn().test();
            long time1=System.currentTimeMillis();
           
            URL url = new URL("http://blog.csdn.net/lmj623565791/article/details/23272657");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			long contentlength = con.getContentLength();
			
			 System.out.println(System.currentTimeMillis()-time1);
        }
        catch (Exception e)
        {e.printStackTrace();
            System.out.println("Do you see me ?");
        } 
    }

}
