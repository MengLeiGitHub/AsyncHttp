package com.async.http.request2.convert;

import com.async.http.exception.CancledOrInterruptedExcetion;
import com.async.http.exception.HttpException;
import com.async.http.request2.BaseRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StringDataConvert extends BaseDataConvert<String> {

	@Override
	public String read(BaseRequest<String> request, InputStream input,
					   long len) throws IOException,HttpException {
		// TODO Auto-generated method stub

		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		try {
			byte[] buff = new byte[1024];
			int readLength = 0;
			int l = 0;
			while (!request.isCancledOrInterrupted()
					&& (l = input.read(buff)) > 0) {
				arrayOutputStream.write(buff, 0, l);
				readLength += l;
				if (request.isCancledOrInterrupted()) {
					input.close();
					throw new CancledOrInterruptedExcetion();
				}
			}
			return arrayOutputStream.toString(request.getDataConverCharset());
		} finally {
			arrayOutputStream.close();
		}

	}

}
