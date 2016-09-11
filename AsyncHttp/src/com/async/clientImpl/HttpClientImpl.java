package com.async.clientImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import javax.net.ssl.HttpsURLConnection;
import com.async.AsyncHttpClient;
import com.async.Log.Log;
import com.async.constant.Constents;
import com.async.entity.ResponseBody;
import com.async.request2.BaseHttpRequest;
import com.async.request2.entity.Header;
import com.async.request2.part.BaseParamPart;

public class HttpClientImpl implements AsyncHttpClient {

	private BoundaryBuilder boundaryBuilder;

	public <T> ResponseBody<T> request(BaseHttpRequest<T> request,
			ResponseBody<T> responseBody) throws Exception {
		OutputStream outputStream = null;
		InputStream inputStream = null;
		boundaryBuilder = new BoundaryBuilder();

		try {

			// ��д �������󲿷�

			// 0.������url

			URL realUrl = new URL(request.getUrl());
			// �򿪺�URL֮�������
			HttpURLConnection conn = (HttpURLConnection) realUrl
					.openConnection();

			conn.setReadTimeout(request.getSocketTimeout());	
			
			conn.setConnectTimeout(request.getConnectTimeout());
			
			
			// 1.�������������ͷ����Ϣ���������������棬assaent ...��
			ArrayList<Header> headlist = request.getHeaders();
			
			for (Header head : headlist) {
				if (Constents.CONTENT_TYPE.equals(head.getKey())){
					conn.addRequestProperty(head.getKey(),
							head.getVal() + Constents.BOUNDARY_PARAM
									+ boundaryBuilder.getBoundary());
				}
				else {
					conn.addRequestProperty(head.getKey(), head.getVal());
				}
			}

			// 2.�ж��Ƿ���ssl��if��ishave������ssl

			if (request.isHaveSSL()) {
				// ����ssl
				HttpsURLConnection httpsURLConnection;
				httpsURLConnection = (HttpsURLConnection) realUrl
						.openConnection();
				httpsURLConnection.setDefaultSSLSocketFactory(null);
				// δ�����

			}

			conn.setRequestMethod(request.getRequestMethod().getMethodName());
			System.out.println(request.getRequestMethod().getMethodName());

			// ����POST�������������������
			conn.setDoOutput(true);
			conn.setDoInput(true);

			responseBody.setRequestParamLength(request.getTotalParamLength());

			// 3.���������������ͨ��urlconnection outputStream ���� ���� ��
			/**
			 * 1.���ܻ��кܶ��������ͣ����� �� list ���� ѭ�� output 2.BasePart<T> (chrild string
			 * file ������������)
			 */
			
			if(request.getRequestMethod()==HttpMethod.Post || request.getRequestMethod()==HttpMethod.Put ||request.getRequestMethod()==HttpMethod.Patch )
			 {
					outputStream = conn.getOutputStream();

					write(outputStream, request);
		
					conn.getResponseCode();
			 }
			// 4.����response

			responseBody.setContentLength(conn.getContentLengthLong());

			// 5.��ȡ������ ���������ʹ��������ݣ�1.stringConvert 2.fileConvert 3.....��
			/**
			 * ���ص���������ֻ����һ�� 1��baseConvert(child: 1.string 2. file .....)
			 * 
			 */
			inputStream = conn.getInputStream();
			// BaseDataConvert<T> convert=request.getConvert();
			// T result= convert.convert(request, inputStream ,
			// conn.getContentLengthLong() );

			T result = read(request, inputStream, conn.getContentLengthLong());

			responseBody.setResult(result);

			return responseBody;

		} catch (Exception e) {

			throw e;

		} finally {
			try {
				if (outputStream != null)

					outputStream.close();

				if (inputStream != null) {

					inputStream.close();

				}

			} catch (IOException e) {
				throw e;

			}
		}

	}

	public void write(OutputStream out, BaseHttpRequest<?> req)
			throws Exception {
		// TODO Auto-generated method stub

		for (BaseParamPart<?> baseParamPart : req.getParamParts()) {

			baseParamPart.setBoundaryStart(boundaryBuilder.getStart_tag());
			baseParamPart.setBoundaryBuilder(boundaryBuilder);
			baseParamPart.createHead();

			baseParamPart.write(out,req);
		}
		if (req.getParamParts() != null && req.getParamParts().size() != 0) {
			out.write(boundaryBuilder.getEnd_tag().getBytes());
			out.flush();
		}
		// out.close();

	}

	public <T> T read(BaseHttpRequest<T> request, InputStream input, long len)
			throws Exception {
		// TODO Auto-generated method stub

		// BaseHttpRequest<T> request,InputStream input,long len,String charset
		return request.getConvert().convert(request, input, len);
	}

	
	
	
	
	
}
