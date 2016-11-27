package com.async.http.requsetcreator.reflex;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;


import com.async.http.clientImpl.HttpMethod;
import com.async.http.exception.HttpException;
import com.async.http.requsetcreator.constant.RequestMode;
import com.async.http.requsetcreator.entity.AnnotationKey;
import com.async.http.requsetcreator.entity.CreatorBeans;
import com.async.http.requsetcreator.parseType.method.MethodParseTypeFactory;
import com.async.http.requsetcreator.parseType.method.MethodParseTypeInteface;
import com.async.http.requsetcreator.parseType.param.ParamParseTypeFactory;
import com.async.http.requsetcreator.parseType.param.ParamParseTypeInteface;

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
    	Class<?>[] paramClass= method.getParameterTypes();
    	
    	
    	Annotation[][] a=	method.getParameterAnnotations();
    
    	for (int i = 0; i < a.length; i++) {
    		Annotation[] a1=a[i];
    		for (int j = 0; j < a1.length; j++) {
				System.out.println("======"+a1[j].getClass().getName());
			}
    		
    		
		}
     
    	

    	Parameter[] ps=method.getParameters();
		List<Class> paramList=RequestMode.INSTANCE().getParamClass();

		   Type[] t=	method.getParameterTypes();
	    	
	    	for (int i = 0; i < t.length; i++) {
				System.out.println("----"+t.getClass().getName());

				if(t.getClass().isAnnotationPresent(paramList.get(i))){
				}
			}
		
		    TypeVariable<Method> typeVariable[]= method.getTypeParameters();
		    	
		    for (int i = 0; i < typeVariable.length; i++) {
		    	
		    	System.out.println("--==--"+typeVariable[i].getName());
			}
		    
		
		List<AnnotationKey> keys=new ArrayList<AnnotationKey>();
		int  index=1;
		for(Parameter cl:ps){
			boolean   isNoAnnotioned=true;
			for (int i = 0; i < paramList.size(); i++) {
				boolean isflag=cl.isAnnotationPresent(paramList.get(i));
				if(isflag){
					ParamParseTypeInteface paramParseTypeInteface=
					ParamParseTypeFactory.getParseType(paramList.get(i));
					String key=paramParseTypeInteface.getType(cl);
					AnnotationKey  annotationKey=new AnnotationKey();
					annotationKey.setClas(paramList.get(i));
					annotationKey.setKey(key);
					keys.add(annotationKey);
					isNoAnnotioned=false;
				}
			}
			if(isNoAnnotioned){
				String methodname=method.getName();
				String methodName=method.toGenericString().split("\\(")[1];
				StringBuilder sb=new StringBuilder();
				sb.append("请在接口的");
				sb.append(methodname);
				sb.append("(");
				sb.append(methodName);
				sb.append("方法的第");
				sb.append(index);
				sb.append("参数加上注解\n例如：@Path(\"String\")int id)");
				throw  new  HttpException(sb.toString()){
					
				};
				
			}
				
					
				
			index++;
		}
		
    	
    	CreatorBeans  creatorBeans=new  CreatorBeans();	
    	creatorBeans.setHttpMethod(httpMethod);
    	creatorBeans.setUrl(url);
    	creatorBeans.setKeys(keys);
		
		return creatorBeans;
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
