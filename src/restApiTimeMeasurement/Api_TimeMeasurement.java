package restApiTimeMeasurement;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;

public class Api_TimeMeasurement {
	
	
	
	@Test
	public void testResponseTime()
	{
		long t=given().get("https://jsonplaceholder.typicode.com/photos").time();
		System.out.println("Time ms"+t);
		
	}
	
	@Test
	public void testResponseInTimeUnit()
	{
		long t=given().get("https://jsonplaceholder.typicode.com/photos").timeIn(TimeUnit.MILLISECONDS);
		System.out.println("Time ms"+t);
		
	}
	
	@Test
	public void testResponseTimeAssertion()
	{
		given().get("https://jsonplaceholder.typicode.com/photos").then().time(lessThan(500L));
		System.out.println("Your Api is taking is more than 500 sec to bring the results");
		
	}
	
	
	
	
	
	

}
