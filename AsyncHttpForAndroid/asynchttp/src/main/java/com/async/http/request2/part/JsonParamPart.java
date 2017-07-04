package com.async.http.request2.part;

import com.async.http.request2.BaseRequest;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by admin on 2017-03-03.
 */

public class JsonParamPart extends BaseParamPart<Object> {
    public JsonParamPart(String key, Object val) {
        super(key, val);
    }

    @Override
    public byte[] getBytes() {
        return (key + ":" + val.toString()).getBytes();
    }

    @Override
    public long getContentLength() {
        return 0;
    }
    @Override
    public void writeContent(OutputStream output,BaseRequest<?> reqest)throws IOException {
        // TODO Auto-generated method stub
        output.write((val.toString()).getBytes(CHAR_DECODE));
    }

    @Override
    public byte[] getDisposition() throws UnsupportedEncodingException {
        // TODO Auto-generated method stub
        StringBuilder sb=new StringBuilder();
        sb.append("Content-Disposition:form-data;name=\""+key+"\";");



        return sb.toString().getBytes(CHAR_DECODE);
    }
    @Override
    public byte[] getContenType()throws UnsupportedEncodingException {
        // TODO Auto-generated method stub
        StringBuilder sb=new StringBuilder();
        sb.append("Content-Type:" +ParamType+";charset=");
        sb.append(CHAR_DECODE);
        return sb.toString().getBytes(CHAR_DECODE);

    }
}
