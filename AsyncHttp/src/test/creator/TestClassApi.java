package test.creator;

import com.async.http.annotation.GET;
import com.async.http.annotation.POST;
import com.async.http.annotation.param.Path;
import com.async.http.requsetcreator.RunA;

public class TestClassApi {
	
	@POST("123/456/789")
	public RunA  Create(){
		
		return new RunA()
		;
	}

	

	@GET("http://192.168.1.2:8080/test/{id1}/{id2}/text.action")
	public RunA  Create(@Path("id")String  ma,@Path("id2")int id){
		
		return new RunA()
		;
	}

	@POST("123/456/789")
	public RunA  Create(int  ma){
		
		return new RunA()
		;
	}
	
}
