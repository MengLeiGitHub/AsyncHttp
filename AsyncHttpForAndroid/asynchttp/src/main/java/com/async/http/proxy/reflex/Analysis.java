package com.async.http.proxy.reflex;

import com.async.http.annotation.DOWNLOAD;

import com.async.http.client.HttpMethod;
import com.async.http.exception.HttpException;
import com.async.http.proxy.annotation.RequestImpl;
import com.async.http.proxy.constant.RequestMode;
import com.async.http.proxy.entity.AnnotationKey;
import com.async.http.proxy.entity.CreatorBeans;
import com.async.http.proxy.parseType.method.MethodParseTypeFactory;
import com.async.http.proxy.parseType.method.MethodParseTypeInteface;
import com.async.http.proxy.parseType.param.ParamParseTypeFactory;
import com.async.http.proxy.parseType.param.ParamParseTypeInteface;
import com.async.http.request2.BaseRequest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Analysis {
	/**
	 * 解析当前的 method
	 * @param method  当前方法
	 * @param cla   POST.class  or  GET.class  。。。。
	 * @return
	 * @throws HttpException
	 */

	private  CreatorBeans   getAnalysisData(Method method,Class<?> cla) throws HttpException{

		method.setAccessible(true);
		HttpMethod  httpMethod = null;

		//获取url
		MethodParseTypeInteface h=MethodParseTypeFactory.getParseType(cla);
		String url=h.getType(method);

		httpMethod=RequestMode.INSTANCE().getMethods().get(cla.getName());

		List<AnnotationKey> keys=new ArrayList<AnnotationKey>();
		int  index=1;

		Annotation[][] annotations1=method.getParameterAnnotations();
		for (Annotation[] annotations2:annotations1){
			for (Annotation annotation:annotations2){
				boolean   isNoAnnotioned=true;
				Class cl=annotation.annotationType();
				ParamParseTypeInteface paramParseTypeInteface=
						ParamParseTypeFactory.getParseType(cl);
				isNoAnnotioned=paramParseTypeInteface==null;
				if(!isNoAnnotioned){
					String key=paramParseTypeInteface.getType(annotation);
					AnnotationKey  annotationKey=new AnnotationKey();
					annotationKey.setClas(annotation.annotationType());
					annotationKey.setKey(key);
					keys.add(annotationKey);
				}else {
					String methodname=method.getName();
					String methodName=method.toGenericString().split("\\(")[1];
					StringBuilder sb=new StringBuilder();
					sb.append("请在接口的");
					sb.append(methodname);
					sb.append("(");
					sb.append(methodName);
					sb.append("方法的第");
					sb.append(index);
					sb.append("参数加上注解\n例如：@PathParam(\"String\")int id)");
					throw  new  HttpException(sb.toString()){

					};
				}
				index++;
			}

		}

		CreatorBeans  creatorBeans=new  CreatorBeans();
		creatorBeans.setHttpMethod(httpMethod);
		creatorBeans.setUrl(url);
		isDownLoad(cla, method,creatorBeans);
		creatorBeans.setKeys(keys);
		creatorBeans.setMethodClass(cla);
		RequestImpl requestImpl=	method.getAnnotation(RequestImpl.class);
		if(requestImpl!=null){
			Class<? extends BaseRequest>  cls=	 requestImpl.impl();
			creatorBeans.setRequestImpl(cls);
		}

		return creatorBeans;
	}

	private void  isDownLoad(Class<?> cla,Method method,CreatorBeans creatorBeans){
		boolean isDownload = DOWNLOAD.class.getName().equals(cla.getName());
		if(isDownload){
			creatorBeans.setDownload(isDownload);
			DOWNLOAD download=method.getAnnotation(DOWNLOAD.class);
			creatorBeans.setMultitierDownload(download.MultitierDownload());
		}
	}

	public  CreatorBeans    dealWith(Method method) throws HttpException{

		List<Class>  list=RequestMode.INSTANCE().getClasss();
		Class  annoClass=null;
		for(Class cla:list){
			if(method.isAnnotationPresent(cla)){
				annoClass=cla;
				break;
			}
		}
		return getAnalysisData(method,annoClass);
	}




}
