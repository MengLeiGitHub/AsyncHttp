package com.async.http.request2.writer;

import com.async.http.clientImpl.BoundaryBuilder;
import com.async.http.request2.BaseHttpRequest;
import com.async.http.request2.part.BaseParamPart;

import java.io.OutputStream;

/**
 * Created by admin on 2016-11-08.
 */
public class OneByOneWriter extends BaseWriter {
    public OneByOneWriter(BaseHttpRequest<?> baseHttpRequest) {
        super(baseHttpRequest);
    }

    @Override
    public void write(OutputStream out, BoundaryBuilder boundaryBuilder) throws  Exception {

         for (BaseParamPart<?> baseParamPart : baseHttpRequest.getParamParts()) {
            baseParamPart.setBoundaryStart(boundaryBuilder.getStart_tag());
            baseParamPart.setBoundaryBuilder(boundaryBuilder);
            baseParamPart.createHead();
            baseParamPart.write(out, baseHttpRequest);
        }
        if (baseHttpRequest.getParamParts() != null && baseHttpRequest.getParamParts().size() != 0) {
            out.write(boundaryBuilder.getEnd_tag().getBytes());
            out.flush();
        }
    }
}
