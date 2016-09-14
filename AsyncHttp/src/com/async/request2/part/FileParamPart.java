package com.async.request2.part;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import com.async.callback.HttpCallBack;
import com.async.callback.UploadProgrossCallback;
import com.async.constant.Constents;
import com.async.entity.ResponseBody;
import com.async.request2.BaseHttpRequest;

public class FileParamPart extends BaseParamPart<File> {

	private String fileType;

	public FileParamPart(String key, File val) {
		super(key, val);
		// TODO Auto-generated constructor stub
	}

	public FileParamPart(String key, File val, String fileType) {
		super(key, val);
		// TODO Auto-generated constructor stub
		this.fileType = fileType;

	}

	@Override
	public long getContentLength() {
		// TODO Auto-generated method stub

		return val.length();
	}

	@Override
	public void writeContent(OutputStream output, BaseHttpRequest<?> reqest)
			throws IOException {
		// TODO Auto-generated method stub
		// 上传文件流
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(val);
			byte buffer[] = new byte[1024];
			int b = 0;
			int len = 0;
			long total = reqest.getTotalParamLength();
			HttpCallBack httpCallBack = reqest.getCallBack();
			UploadProgrossCallback uploadProgrossCallback = null;
			if (httpCallBack instanceof UploadProgrossCallback)
				uploadProgrossCallback = ((UploadProgrossCallback) httpCallBack);

			while ((b = fileInputStream.read(buffer)) != -1) {
				output.write(buffer, 0, b);
				len += b;
				if (uploadProgrossCallback != null)
					((UploadProgrossCallback) httpCallBack).upload_current(len,
							total);

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fileInputStream != null)
				fileInputStream.close();

		}

	}

	@Override
	public byte[] getDisposition() throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("Content-Disposition:form-data;name=\"" + key
				+ "\";filename=");
		sb.append(val.getName());

		return sb.toString().getBytes(CHAR_DECODE);
	}

	@Override
	public byte[] getContenType() throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("Content-Type:" + Constents.TYPE_IMAGE + ";");

		return sb.toString().getBytes(CHAR_DECODE);
	}

	public String getFileType() {

		return fileType;
	}

}
