package com.async.http.requsetcreator.reflex;

import java.util.ArrayList;
import java.util.List;

import com.async.http.annotation.param.Param;
import com.async.http.annotation.param.Path;
import com.async.http.requsetcreator.entity.AnnotationKey;
import com.async.http.requsetcreator.entity.CreatorBeans;
import com.async.http.requsetcreator.entity.MultiPart;
import com.async.http.requsetcreator.entity.ParamBean;

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
			List<MultiPart>  multiParts=new ArrayList<MultiPart>();

			for(int i=0;i<param.length;i++){
				AnnotationKey annotationKey=keys.get(i);
				String key_=annotationKey.getKey();
				Object  val=param[i];
				ParamBean paramBean=new ParamBean();
				if(annotationKey.getClas().getName().equals(Path.class.getName())){
					url=matching(url,key_,val.toString());
				}else if(annotationKey.getClas().getName().equals(Param.class.getName())){
					paramBean.setKey(key_);
					paramBean.setVal(val);
					params.add(paramBean);
				}
				creatorBeans.setMethodClass(annotationKey.getClas());
				
			}
			creatorBeans.setHttpMethod(c.getHttpMethod());
			creatorBeans.setKeys(keys);
			creatorBeans.setList(params);
			creatorBeans.setUrl(url);
		}
		return creatorBeans;
	}
	
	private String  matching(String url,String key,String val){
		String replace="{"+key+"}";
		int index=url.indexOf(replace);
		return url.replace(replace, val);
	}
	
	

}
