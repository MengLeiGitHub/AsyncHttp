package com.async.http.request2.writer;

import com.async.http.clientImpl.BoundaryBuilder;
import com.async.http.request2.BaseHttpRequest;
import com.async.http.request2.part.BaseParamPart;
import com.async.http.utils.LogUtils;


import java.io.DataOutputStream;
import java.io.OutputStream;


/**
 * Created by admin on 2016-11-08.
 */
public class JSONWriter extends BaseWriter {
    public JSONWriter(BaseHttpRequest<?> baseHttpRequest) {
        super(baseHttpRequest);
    }
    @Override
    public void write(OutputStream out, BoundaryBuilder boundaryBuilder) throws Exception {
        DataOutputStream dataOutputStream = new DataOutputStream(out);
      /*  JSONObject  jsonObject=new JSONObject();
        for (BaseParamPart  baseParamPart:baseHttpRequest.getParamParts()){
            String key= baseParamPart.getKey();
            Object val= baseParamPart.getVal();
            jsonObject.put(key,val);
        }
        String json=jsonObject.toString();
       // String jsons=new String(json.getBytes("utf8"),baseHttpRequest.getDataConverCharset());
        LogUtils.e(json+"   "+baseHttpRequest.getDataConverCharset());

        dataOutputStream.write(json.getBytes(baseHttpRequest.getDataConverCharset()));
        dataOutputStream.flush();
        dataOutputStream.close();*/
    }
}
