package com.async.http.request2.part;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import com.async.http.clientImpl.BoundaryBuilder;
import com.async.http.constant.Constents;
import com.async.http.request2.BaseHttpRequest;
import com.async.http.utils.LogUtils;

public abstract class BaseParamPart<T> {
	protected String CHAR_DECODE = Constents.ENCODE_DEFAULT;
	protected String key;
	protected T val;
	protected byte[] head;

	private String Enter = "\r\n";
	protected String ParamType = Constents.TYPE_TEXT;

	private String boundaryStart;

	private BoundaryBuilder boundaryBuilder;

	public void setBoundaryStart(String boundaryStart) {
		this.boundaryStart = boundaryStart;
	}

	public BaseParamPart(String key, T val) {
		this.key = key;
		this.val = val;
	}

	public BaseParamPart(String key, T val, String charCode) {
		this.key = key;
		this.val = val;
		this.CHAR_DECODE = charCode;
	}

	public void setBoundaryBuilder(BoundaryBuilder boundaryBuilder) {
		this.boundaryBuilder = boundaryBuilder;
	}

	public BoundaryBuilder getBoundaryBuilder() {
		return boundaryBuilder;
	}

	public BaseParamPart(String key, T val, String charCode, String ParamType) {
		this.key = key;
		this.val = val;
		this.CHAR_DECODE = charCode;
		this.ParamType = ParamType;
	}

	public void createHead() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			out.write(boundaryStart.getBytes(CHAR_DECODE));
			out.write(Enter.getBytes(CHAR_DECODE));

			out.write(getContenType());
			out.write(Enter.getBytes(CHAR_DECODE));

			out.write(getDisposition());
			out.write(Enter.getBytes(CHAR_DECODE));

			out.toByteArray();
			head = out.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void write(OutputStream output, BaseHttpRequest<?> reqest)
			throws IOException {
		LogUtils.e(new String(head).toString());
		output.write(Enter.getBytes(CHAR_DECODE));

		output.write(head);

		output.write(Enter.getBytes(CHAR_DECODE));
		// output.write(Enter.getBytes(CHAR_DECODE));
		writeContent(output, reqest);
		output.write(Enter.getBytes(CHAR_DECODE));

	}

	public abstract long getContentLength();

	public abstract void writeContent(OutputStream output,
			BaseHttpRequest<?> reqest) throws IOException;

	public abstract byte[] getDisposition() throws UnsupportedEncodingException;

	public abstract byte[] getContenType() throws UnsupportedEncodingException;

	public byte[] getBytes() {
		return (key + "=" + val).getBytes();
	}

}
