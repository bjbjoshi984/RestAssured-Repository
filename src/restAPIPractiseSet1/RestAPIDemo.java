package restAPIPractiseSet1;

import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;

public class RestAPIDemo {
	
	public static void main(String main[])
	{

		Response response=RestAssured.get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22");
		int code=response.getStatusCode();
		Assert.assertEquals(200,code);
		
		System.out.println(response.toString());
		System.out.println(response.getTimeIn(TimeUnit.MILLISECONDS));
	}

}
