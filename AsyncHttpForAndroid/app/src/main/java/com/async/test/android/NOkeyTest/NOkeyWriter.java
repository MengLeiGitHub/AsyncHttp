package com.async.test.android.NOkeyTest;

import com.alibaba.fastjson.JSON;
import com.async.http.client.BoundaryBuilder;
import com.async.http.request2.BaseRequest;
import com.async.http.request2.part.BaseParamPart;
import com.async.http.request2.writer.BaseWriter;
import com.async.http.utils.LogUtils;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.OutputStream;

/**
 * Created by admin on 2017-04-27.
 */

public class NOkeyWriter extends BaseWriter {
    BaseRequest<?> baseRequest;
    public NOkeyWriter(BaseRequest<?> baseRequest) {
        super(baseRequest);
        this.baseRequest=baseRequest;
    }

    @Override
    public void write(OutputStream out, BoundaryBuilder boundaryBuilder) throws Exception {
        DataOutputStream dataOutputStream = new DataOutputStream(out);
        StringBuilder stringBuilder=new StringBuilder();

        for (BaseParamPart baseParamPart: baseRequest.getParamParts()){
            String   param=JSON.toJSONString(baseParamPart.getVal());
            stringBuilder.append(param);
        }

        LogUtils.e(stringBuilder.toString()+"   "+ baseRequest.getDataConverCharset());

        dataOutputStream.write(stringBuilder.toString().getBytes(baseRequest.getDataConverCharset()));
        dataOutputStream.flush();
        dataOutputStream.close();
    }
}
