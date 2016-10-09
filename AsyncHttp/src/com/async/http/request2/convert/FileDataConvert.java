package com.async.http.request2.convert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import com.async.http.callback.DownProgrossCallback;
import com.async.http.callback.HttpCallBack;
import com.async.http.exception.CancledOrInterruptedExcetion;
import com.async.http.exception.HttpException;
import com.async.http.request2.BaseHttpRequest;
import com.async.http.request2.FileRequest;
import com.async.http.request2.record.RecordEntity;

public class FileDataConvert extends BaseDataConvert<File> {

	@Override
	public File read(BaseHttpRequest<File> request, InputStream input, long len)
			throws IOException, HttpException {
		// TODO Auto-generated method stub
		File file = new File(((FileRequest) request).getFilepath());
		RandomAccessFile out =null;
		try{
			
		
		if (!file.exists()) {
			if (!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			file.createNewFile();
		}
		long start=0;
		out = new RandomAccessFile(file,"rw");
		if(request instanceof FileRequest){
			RecordEntity recordEntity=((FileRequest) request).getRecordEntity();
			if(recordEntity!=null)
			start=recordEntity.getStartTag();
			out.seek(start);
		}
		
		long length =start;
		len+=start;
		int b = 0;
		byte[] buffer = new byte[1024];
		HttpCallBack call = request.getCallBack();

		while ((b = input.read(buffer)) != -1
				&& !request.isCancledOrInterrupted()) {

			// System.out.println(buffer.length+" "+b);
			// 4.写到输出流(out)中
			out.write(buffer, 0, b);
			length += b;

			if (call instanceof DownProgrossCallback)
				((DownProgrossCallback) call).download_current(length, len);

		}
 			out.close();
 		}catch(Exception e){
			throw new HttpException(e.getMessage()) {
			};
		}finally{
			if(out!=null)
				out.close();

		}
		
		
		if (request.isCancledOrInterrupted()) {
			throw new CancledOrInterruptedExcetion();
		}

		return file;
	}
	
}
