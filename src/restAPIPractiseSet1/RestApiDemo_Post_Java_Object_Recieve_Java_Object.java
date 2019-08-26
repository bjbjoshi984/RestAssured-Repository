package restAPIPractise;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import base.JasonRecieveData;
import base.POJOInputClass;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestApiDemo_Post_Java_Object_Recieve_Java_Object {
	@Test
	public void postMethod() throws IOException
	{
		int randomID= new Random().nextInt(1000);
		POJOInputClass info=new POJOInputClass(randomID,"Title"+randomID,"Author"+randomID);
		
		JasonRecieveData jasonRecieveData=new JasonRecieveData();
		Gson gson=new GsonBuilder().create();
		
		RestAssured.baseURI="http://localhost:3000/";
		
		Response response=given()
		        .header("Content-Type","application/json")
		        .body(info)
		        .when()
		        .post("/posts")
		        .then()
		        .statusCode(201)
		        .and()
		        .log().all()
		        .and()
		        .extract().response();
		
		jasonRecieveData=gson.fromJson(response.prettyPrint(),JasonRecieveData.class);
		
		Assert.assertEquals(jasonRecieveData.getId(),info.getId());
		Assert.assertEquals(jasonRecieveData.getTitle(),info.getTitle());
		Assert.assertEquals(jasonRecieveData.getAuthor(),info.getAuthor());
	}
}
 