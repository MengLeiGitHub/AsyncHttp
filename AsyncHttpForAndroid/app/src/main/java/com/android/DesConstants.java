package com.android;

import android.util.Base64;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;


public class DesConstants {

    // 解密
   public static String DecryptDoNet(String message, String key)
             {
                 try{
                     byte[] bytesrc = Base64.decode(message.getBytes(), Base64.NO_PADDING);
                     Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
                     DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
                     SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
                     SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
                     IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
                     cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
                     byte[] retByte = cipher.doFinal(bytesrc);
                     return new String(retByte);
                 }catch (Exception e){
                     return null;
                 }


   }

   // 加密
   public static String EncryptAsDoNet(String message, String key)
           throws Exception {
       Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
       DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
       SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
       SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
       IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
       cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
       byte[] encryptbyte = cipher.doFinal(message.getBytes());
       return new String(Base64.encode(encryptbyte, Base64.DEFAULT));
   }
   
   public static final String AESKey = "5671-m_=";
    public static final String YAN= "http://www.jiujiumiandan.cn/";

    public static HashMap<String,String> getNetHashMap(Map<String, String> tempMap) {
        try{
            String he = "&";
            int i=0;
            StringBuffer sb=new StringBuffer();
            for (Map.Entry entry : tempMap.entrySet()) {
                if (i != 0) sb.append(he);
                sb.append(entry.getKey() + "=" + ((String) entry.getValue()));
                i++;
            }
            Log.e("tag", sb.toString());
            HashMap hashMap=new HashMap();
            String jiami= DesConstants.EncryptAsDoNet(sb.toString(), DesConstants.AESKey);
            hashMap.put("para",jiami);
            return hashMap;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
    public static String getNetString(Map<String, String> tempMap) {
        try{
            String he = "&";
            int i=0;
            StringBuffer sb=new StringBuffer();
            for (Map.Entry entry : tempMap.entrySet()) {
                if (i != 0) sb.append(he);
                sb.append(entry.getKey() + "=" + ((String) entry.getValue()));
                i++;
            }
            Log.e("tag", sb.toString());
            String jiami= DesConstants.EncryptAsDoNet(sb.toString(), DesConstants.AESKey);
            return jiami;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }


}
