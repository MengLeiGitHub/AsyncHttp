package com.async.test.android.NOkeyTest;

import com.async.http.proxy.entity.ParamBean;
import com.async.http.proxy.request.ProxyBaseHttpRequest;
import com.async.http.request2.convert.BaseDataConvert;
import com.async.http.request2.convert.StringDataConvert;
import com.async.http.request2.writer.BaseWriter;

import java.util.List;

/**
 * Created by admin on 2017-04-27.
 */

public class NOkeyRequest extends ProxyBaseHttpRequest<String> {
    public NOkeyRequest(String url, String charcode) {
        super(url, charcode);
    }

    @Override
    public void addParams(List<ParamBean> paramBeans) {
                    for (ParamBean paramBean:paramBeans){
                        NokeyParamPart nokeyParamPart=new NokeyParamPart(paramBean.getVal());
                        addParam(nokeyParamPart);
                    }
    }

    @Override
    public BaseDataConvert<String> getConvert() {
        return new StringDataConvert();
    }

    @Override
    public BaseWriter getWriter() {
        return  new NOkeyWriter(this);
    }
}
