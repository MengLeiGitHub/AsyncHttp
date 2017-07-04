package com.async.http.request2.writer;

import com.async.http.client.BoundaryBuilder;
import com.async.http.request2.BaseRequest;
import com.async.http.request2.part.BaseParamPart;
import com.async.http.utils.LogUtils;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.OutputStream;


/**
 * Created by admin on 2016-11-08.
 */
public class JSONWriter extends BaseWriter {
    public JSONWriter(BaseRequest<?> baseRequest) {
        super(baseRequest);
    }
    @Override
    public void write(OutputStream out, BoundaryBuilder boundaryBuilder) throws Exception {
        DataOutputStream dataOutputStream = new DataOutputStream(out);
        JSONObject  jsonObject=new JSONObject();
        for (BaseParamPart  baseParamPart: baseRequest.getParamParts()){
            String key= baseParamPart.getKey();
            Object val= baseParamPart.getVal();
            jsonObject.put(key,val);
        }
        String json=jsonObject.toString();
       // String jsons=new String(json.getBytes("utf8"),baseRequest.getDataConverCharset());
        LogUtils.e(json+"   "+ baseRequest.getDataConverCharset());

        dataOutputStream.write(json.getBytes(baseRequest.getDataConverCharset()));
        dataOutputStream.flush();
        dataOutputStream.close();
    }
}
