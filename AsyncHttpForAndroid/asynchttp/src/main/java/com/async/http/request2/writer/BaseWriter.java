package com.async.http.request2.writer;

import com.async.http.client.BoundaryBuilder;
import com.async.http.request2.BaseRequest;

import java.io.OutputStream;

/**
 * Created by ml on 2016-11-08.
 */
public abstract class BaseWriter {
    BaseRequest<?> baseRequest;

    public BaseWriter(BaseRequest<?> baseRequest) {
        this.baseRequest = baseRequest;
    }

    public abstract void write(OutputStream out, BoundaryBuilder boundaryBuilder) throws  Exception;


}
