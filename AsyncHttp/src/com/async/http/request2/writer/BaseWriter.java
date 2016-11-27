package com.async.http.request2.writer;

import com.async.http.clientImpl.BoundaryBuilder;
import com.async.http.request2.BaseHttpRequest;

import java.io.OutputStream;

/**
 * Created by ml on 2016-11-08.
 */
public abstract class BaseWriter {
    BaseHttpRequest<?> baseHttpRequest;

    public BaseWriter(BaseHttpRequest<?> baseHttpRequest) {
        this.baseHttpRequest = baseHttpRequest;
    }

    public abstract void write(OutputStream out, BoundaryBuilder boundaryBuilder) throws  Exception;


}
