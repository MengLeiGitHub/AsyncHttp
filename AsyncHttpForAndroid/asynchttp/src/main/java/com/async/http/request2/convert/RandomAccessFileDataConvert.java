package com.async.http.request2.convert;

import com.alibaba.fastjson.JSON;
import com.async.http.callback.DownProgrossCallback;
import com.async.http.callback.HttpCallBack;
import com.async.http.exception.CancledOrInterruptedExcetion;
import com.async.http.exception.HttpException;
import com.async.http.request2.BaseRequest;
import com.async.http.request2.download;
import com.async.http.request2.record.RecordEntity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;


public class RandomAccessFileDataConvert extends
		BaseDataConvert<File> {

	@Override
	public File read(BaseRequest<File> request,
					 InputStream input, long len) throws IOException, HttpException {
		// TODO Auto-generated method stub
		RandomAccessFile randomfile = null;
		RandomAccessFile randomfileJILu = null;
		String filepath=null;
		try {
			RecordEntity recordEntity = ((download) request).getRecordEntity();
			long startTag = ((download) request).getRecordEntity()
					.getStartTag();

			String file = ((download) request).getRecordEntity().getFilePath();
			filepath=file;
			randomfile = new RandomAccessFile(file, "rw");

			long current = recordEntity.getCurrent();


			randomfile.seek(startTag+current);

			int b = 0;
			byte[] buffer = new byte[1024 * 5];


			HttpCallBack call = request.getCallBack();

			randomfileJILu = new RandomAccessFile(recordEntity.getRecordPath(),
					"rw");
			while ((b = input.read(buffer)) != -1
					&& !request.isCancledOrInterrupted()) {

				// System.out.println(buffer.length+" "+b);
				// 4.写到输出流(out)中
				randomfile.write(buffer, 0, b);
				if (call instanceof DownProgrossCallback) {
					((DownProgrossCallback) call).download_current(b, len);

				}
				current += b;
				recordEntity.setCurrent(current);
				randomfileJILu.seek(0);
				randomfileJILu.write(JSON.toJSON(recordEntity).toString().getBytes(
						"utf-8"));

			}
			if (randomfile != null)
				randomfile.close();
			if (randomfileJILu != null) {
				randomfileJILu.close();
			}
			if((startTag+=current)>=recordEntity.getEndTag()){
				File reFile=new File(recordEntity.getRecordPath());
				if(reFile.exists()){
					reFile.delete();
				}
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (randomfile != null)
				randomfile.close();
			if (randomfileJILu != null) {
				randomfileJILu.close();
			}
		}

		if (request.isCancledOrInterrupted()) {
			input.close();
			throw new CancledOrInterruptedExcetion();
		}

		return new File(filepath);
	}

}
