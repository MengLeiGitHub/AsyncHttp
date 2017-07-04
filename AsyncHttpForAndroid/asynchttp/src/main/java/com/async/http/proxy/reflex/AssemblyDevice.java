package com.async.http.proxy.reflex;

import com.async.http.annotation.param.Param;
import com.async.http.annotation.param.PathParam;
import com.async.http.proxy.entity.AnnotationKey;
import com.async.http.proxy.entity.CreatorBeans;
import com.async.http.proxy.entity.ParamBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析属性
 * 将属性对象 ，url 配置好
 * @author ml
 *
 */
public class AssemblyDevice {
	
	public CreatorBeans   startAssembly(CreatorBeans c,Object[] param){
		CreatorBeans  creatorBeans=new CreatorBeans();
		List<ParamBean>  params=new ArrayList<ParamBean>();
		if(param!=null){
			List<AnnotationKey> keys=c.getKeys();
			String url=c.getUrl();

			for(int i=0;i<param.length;i++){
				AnnotationKey annotationKey=keys.get(i);
				String key_=annotationKey.getKey();
				Object  val=param[i];
				ParamBean paramBean=new ParamBean();
				if(annotationKey.getClas().getName().equals(PathParam.class.getName())){
					url=matching(url,key_,val.toString());
				}else if(annotationKey.getClas().getName().equals(Param.class.getName())){
					paramBean.setKey(key_);
					paramBean.setVal(val);
					params.add(paramBean);
				}
			}
			creatorBeans.setKeys(keys);
			creatorBeans.setList(params);
			creatorBeans.setUrl(url);
			creatorBeans.setMultitierDownload(c.MultitierDownload());
			creatorBeans.setDownload(c.isDownload());

		}else {
			creatorBeans.setUrl(c.getUrl());
		}
		creatorBeans.setMethodClass(c.getMethodClass());
		creatorBeans.setHttpMethod(c.getHttpMethod());
		creatorBeans.setRequestImpl(c.getRequestImpl());
		return creatorBeans;
	}
	
	private String  matching(String url,String key,String val){
		String replace="{"+key+"}";
		return url.replace(replace, val);
	}
	
	

}
