package com.async.http.request2.convert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.async.http.exception.CancledOrInterruptedExcetion;
import com.async.http.exception.HttpException;
import com.async.http.request2.BaseHttpRequest;

public class StringDataConvert extends BaseDataConvert<String> {

	@Override
	public String read(BaseHttpRequest<String> request, InputStream input,
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
				// ���½�����

				request.getCallBack().current(readLength, len);
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
