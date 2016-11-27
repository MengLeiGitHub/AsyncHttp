package test.creator;

import com.async.http.annotation.GET;
import com.async.http.annotation.POST;
import com.async.http.annotation.param.Param;
import com.async.http.annotation.param.Path;
import com.async.http.requsetcreator.RunA;

public interface TestApi {
	
	@POST("123/456/789")
	public RunA  Create();

	

	@GET("http://www.haoservice.com/")
	public RunA  Create(@Path("id")String  ma,@Param("id2")int id);
	

	@POST("123/456/789")
	public RunA  Create(int  ma);
	
	
}
