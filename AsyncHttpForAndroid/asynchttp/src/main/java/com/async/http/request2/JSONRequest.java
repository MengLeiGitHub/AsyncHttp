package com.async.http.request2;

import com.async.http.request2.writer.BaseWriter;
import com.async.http.request2.writer.JSONWriter;

/**
 * Created by admin on 2016-11-08.
 */
public class JSONRequest  extends StringRequest {


    public JSONRequest(String url) {
        super(url);
    }

    public JSONRequest(String url, String charCncode) {
        super(url, charCncode);
    }

    @Override
    public BaseWriter getWriter() {
        return new JSONWriter(this);
    }
}
