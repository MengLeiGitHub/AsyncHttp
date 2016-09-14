package com.async.request2.convert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import com.async.callback.DownProgrossCallback;
import com.async.callback.HttpCallBack;
import com.async.exception.HttpException;
import com.async.request2.BaseHttpRequest;
import com.async.request2.download;
import com.async.request2.record.RecordEntity;

public class RandomAccessFileDataConvert extends
		BaseDataConvert<RandomAccessFile> {

	@Override
	public RandomAccessFile read(BaseHttpRequest<RandomAccessFile> request,
			InputStream input, long len) throws IOException, HttpException {
		// TODO Auto-generated method stub
		RandomAccessFile randomfile = null;
		 

		try {
			RecordEntity recordEntity=((download) request).getRecordEntity();
			long startIndex = ((download) request).getRecordEntity().getStartTag();

			String file = ((download) request).getRecordEntity().getFilePath();
			randomfile = new RandomAccessFile(file, "rw");
			randomfile.seek(startIndex);
			
			int b = 0;
			byte[] buffer = new byte[1024];
			 
			long current=0;
			HttpCallBack call = request.getCallBack();
			while ((b = input.read(buffer)) != -1
					&& !request.isCancledOrInterrupted()) {

				// System.out.println(buffer.length+" "+b);
				// 4.写到输出流(out)中
				randomfile.write(buffer, 0, b);

				if (call instanceof DownProgrossCallback) {
					((DownProgrossCallback) call).download_current(b, len);

				}
				current+=b;
				recordEntity.setCurrent(current);
				
			}
		} catch (IOException e) {
			throw e;
		} finally {
			randomfile.close();
		}

		if (request.isCancledOrInterrupted()) {
			input.close();
			throw new HttpException("the request cancled by user") {
			};
		}

		return randomfile;
	}

}
