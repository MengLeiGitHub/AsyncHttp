package com.async.http.client.impl;

import com.async.http.client.AsyncHttpClient;
import com.async.http.client.BoundaryBuilder;
import com.async.http.client.HttpMethod;
import com.async.http.entity.ResponseBody;
import com.async.http.exception.HttpException;
import com.async.http.request2.BaseRequest;
import com.async.http.request2.conn.BaseConn;
import com.async.http.request2.download;
import com.async.http.request2.writer.BaseWriter;
import com.async.http.utils.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class HttpClientImpl implements AsyncHttpClient {

    private BoundaryBuilder boundaryBuilder;

    public <T> ResponseBody<T> request(BaseRequest<T> request,
                                       ResponseBody<T> responseBody) throws Exception {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        boundaryBuilder = new BoundaryBuilder();
        BaseConn<?> conns=null;

        try {
            // 改写 网络请求部分
            // 0.先连接url
            String url = request.getUrl();
            if (StringUtils.isNull(url)) {
                throw new HttpException("Request url can not be  empty") {
                };
            }
                if(!url.startsWith("http")||!url.startsWith("https")){
                    String baseUrl=request.getBaseUrl();
                    if(!url.startsWith("http")) {
                        if (StringUtils.isNull(request.getBaseUrl())) {
                            if (!url.startsWith("http") || !url.startsWith("https")) {
                                throw new HttpException("Request url  is not  vaild ,please check url format\nerror url:" + request.getUrl()
                                +" example : \n  AsyncHttp.instance().addRequestInterceptor(new RequestInterceptorActionInterface() {\n" +
                                        "\n" +
                                        "\t\t\t@Override\n" +
                                        "\t\t\tpublic <T> BaseRequest<T> interceptorAction(\n" +
                                        "\t\t\t\t\tBaseRequest<T> baserequest) throws Exception {\n" +
                                        "\t\t\t\t// TODO Auto-generated method stub\n" +
                                        "\t\t\t\t\n" +
                                        "\t\t\t\t\n" +
                                        "\t\t\t baserequest.addHead(new Header(\"version\", \"1.0\"));\n" +
                                        "             baserequest.addHead(new Header(\"tokenId\", \"8FA24C888B39405FB46499C62E48A504\"));\n" +
                                        "             baserequest.addHead(new Header(\"token\", \"3FC14C472A5D471681EDEB7043FF2D0D\"));\n" +
                                        "             //35C43D51E8C844B69B4AF149A82B40E7\n" +
                                        "             baserequest.addHead(new Header(\"appType\", \"2\"));\n" +
                                        "             baserequest.addHead(new Header(\"ostype\", \"1\"));\n" +
                                        "             baserequest.addHead(new Header(\"deviceId\", \"1231232342342341\"));\n" +
                                        "             baserequest.setBaseUrl(\"http://120.26.106.136:8080\");\n" +
                                        "             //baserequest.setBaseUrl(\"http://192.168.1.118:80/uin\");\n" +
                                        "             return baserequest;\n" +
                                        "             \n" +
                                        "\t\t\t}\n" +
                                        "        \n" +
                                        "     });") {
                                };
                            }
                        }
                        url=baseUrl+url;
                    }



                }
            //url=UrlEncodeUtils.encodeUrl(url);
            conns=    request.getConn();
            conns.setBoundaryBuilder(boundaryBuilder);
            conns.openConnection();

            if (request instanceof download)
                responseBody.setRecordEntity(((download) request).getRecordEntity());

            responseBody.setRequestParamLength(request.getTotalParamLength());

            // 3.发送请求参数，（通过urlconnection outputStream 发送 数据 ）
            /**
             * 1.可能会有很多数据类型，所以 有 list 数组 循环 output 2.BasePart<T> (chrild string
             * file 。。。。。。)
             */
            request.getCallBack().start();

            if (request.getRequestMethod() == HttpMethod.Post
                    || request.getRequestMethod() == HttpMethod.Put
                    || request.getRequestMethod() == HttpMethod.Patch) {
                outputStream = conns.getOutputStram();

                write(outputStream, request);

                int code = conns.getResponseCode();
                if (code >= 300 && code < 600) {
                    HttpException httpException = new HttpException("Server returned HTTP response code: " + code + " for URL:" +url) {
                    };
                    responseBody.setException(httpException);

                    throw httpException;
                }

            }
            // 4.设置response

			/*
			 * 判断下载的文件是否已经存在如果存在，并且大小和网络文件大小一样，则直接返回
			 */


            responseBody.setContentLength(conns.getContentLength());

            // 5.获取输入流 （根据类型处理流数据：1.stringConvert 2.fileConvert 3.....）
            /**
             * 返回的数据类型只能有一种 1。baseConvert(child: 1.string 2. file .....)
             *
             */
            inputStream = conns.getInputStream();



            T result = read(request, inputStream, conns.getContentLength());

            responseBody.setResult(result);

            return responseBody;

        } catch (Exception e) {

            throw e;

        } finally {
            try {
                if (outputStream != null)

                    outputStream.close();

               if (inputStream != null) {

                    inputStream.close();

                }

            } catch (IOException e) {

                throw e;

            }
        }

    }

    public void write(OutputStream out, BaseRequest<?> req)
            throws Exception {
        // TODO Auto-generated method stub
        BaseWriter  writer= req.getWriter();
        writer.write(out,boundaryBuilder);
    }

    public <T> T read(BaseRequest<T> request, InputStream input, long len)
            throws Exception {
        // TODO Auto-generated method stub

        // BaseRequest<T> request,InputStream input,long len,String charset

        return request.getConvert().convert(request, input, len);
    }

}
