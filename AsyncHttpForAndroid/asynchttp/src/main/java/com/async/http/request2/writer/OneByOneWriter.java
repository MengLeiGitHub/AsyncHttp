package com.async.http.request2.writer;

import com.async.http.client.BoundaryBuilder;
import com.async.http.request2.BaseRequest;
import com.async.http.request2.part.BaseParamPart;

import java.io.OutputStream;

/**
 * Created by admin on 2016-11-08.
 */
public class OneByOneWriter extends BaseWriter {
    public OneByOneWriter(BaseRequest<?> baseRequest) {
        super(baseRequest);
    }

    @Override
    public void write(OutputStream out, BoundaryBuilder boundaryBuilder) throws  Exception {

         for (BaseParamPart<?> baseParamPart : baseRequest.getParamParts()) {
            baseParamPart.setBoundaryStart(boundaryBuilder.getStart_tag());
            baseParamPart.setBoundaryBuilder(boundaryBuilder);
            baseParamPart.createHead();
            baseParamPart.write(out, baseRequest);
        }
        if (baseRequest.getParamParts() != null && baseRequest.getParamParts().size() != 0) {
            out.write(boundaryBuilder.getEnd_tag().getBytes());
            out.flush();
        }
    }
}
