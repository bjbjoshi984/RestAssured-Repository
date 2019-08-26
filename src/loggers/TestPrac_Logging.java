package loggers;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestPrac_Logging {
	
	/* We can log headers,body,cookies or we can log all things */
	//@Test
	public void testLogging()
	{
		//given().get("https://jsonplaceholder.typicode.com/photos/1").then().log().headers();
		//given().get("https://jsonplaceholder.typicode.com/photos/1").then().log().body();
		//given().get("https://jsonplaceholder.typicode.com/photos/1").then().log().cookies();
		given().get("https://jsonplaceholder.typicode.com/photos/1").then().log().all();
		
	}
	
	/* Logging if error is coming */
	//@Test
	public void testLoggingIfError()
	{
	    /* Wrong URl */
		given().get("https://jsonplaceholder.typicode.com/photos/1hnjkbj").then().log().ifError();
	}
	
	
	/* Conditional logging*/
	@Test
	public void testLoggingConditional()
	{
	    /* Wrong URl */
		given().get("https://jsonplaceholder.typicode.com/photos/1").then().log().ifStatusCodeIsEqualTo(200);
	}

}
