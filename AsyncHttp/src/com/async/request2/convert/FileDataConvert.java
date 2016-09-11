package com.async.request2.convert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.async.callback.DownProgrossCallback;
import com.async.callback.HttpCallBack;
import com.async.exception.HttpException;
import com.async.request2.BaseHttpRequest;
import com.async.request2.FileRequest;

public class FileDataConvert extends BaseDataConvert<File> {

	@Override
	public File read(BaseHttpRequest<File> request, InputStream input, long len)
			throws IOException, HttpException {
		// TODO Auto-generated method stub
		File file = new File(((FileRequest) request).getFilepath());
		if (!file.exists()) {
			if (!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			file.createNewFile();
		}
		FileOutputStream out = new FileOutputStream(file);
		int length=0;
		int b = 0;
		byte[] buffer = new byte[1024];
		HttpCallBack call=request.getCallBack();
		while ((b = input.read(buffer)) != -1 && !request.isCancledOrInterrupted()) {
			
 			//System.out.println(buffer.length+" "+b);
  			// 4.写到输出流(out)中
 			out.write(buffer, 0, b);
 			length+=b;
 			
 			if(call instanceof DownProgrossCallback)
 			     ((DownProgrossCallback) call).download_current(length, len);
 			  
		}
		
		out.flush();
		out.close();
		
		if(request.isCancledOrInterrupted()){
			throw new HttpException("the request cancled by user") {
			};
		}
		
 		return file;
	}

}
