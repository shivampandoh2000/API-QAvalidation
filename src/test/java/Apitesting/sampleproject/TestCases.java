package Apitesting.sampleproject;

import org.hamcrest.core.IsEqual;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;

import java.util.HashMap;

public class TestCases {

	ResponseSpecification res;
	
	@BeforeClass
	public void setSpecification() {
		res = RestAssured.expect();
		res.statusLine("HTTP/1.1 200 OK");
		res.contentType(ContentType.JSON);
	}
	
//	GET
	@Test
	public void testFetchUsers(){
		RestAssured.baseURI = "https://reqres.in";
		given().when().get("api/users?page=2").then().spec(res).assertThat().body("page", IsEqual.equalTo(2));
		
	}
	
//	GET
	@SuppressWarnings("unchecked")
	@Test
	public void testFetchUser(){
		RestAssured.baseURI = "https://reqres.in";
		JSONObject data = new JSONObject();
		data.put("id", 2);
		data.put("email", "janet.weaver@reqres.in");
		data.put("first_name", "Janet");
		data.put("last_name", "Weaver");
		data.put("avatar", "https://reqres.in/img/faces/2-image.jpg");
		given().when().get("api/users/2").then().spec(res).assertThat().body("data", IsEqual.equalTo(data));
		
	}
	
//	GET
	@Test
	public void testUserNotFound(){
		ResponseSpecification resp;
		resp = RestAssured.expect();
		resp.statusLine("HTTP/1.1 404 Not Found");
		resp.contentType(ContentType.JSON);
		
		RestAssured.baseURI = "https://reqres.in";
		given().when().get("api/users/23").then().spec(resp).assertThat().body("data", IsEqual.equalTo(null));
		
	}
	
//	GET
	@Test
	public void testFetchListResource(){
		RestAssured.baseURI = "https://reqres.in";
		given().when().get("api/unknown").then().spec(res).assertThat().body("page", IsEqual.equalTo(1));
		
	}
	
//	GET
	@Test
	public void testFetchResource(){
		RestAssured.baseURI = "https://reqres.in";
		given().when().get("api/unknown/2").then().spec(res).assertThat().body("data.id", IsEqual.equalTo(2));
		
	}
	
//	GET
	@Test
	public void testFetchResourceNotFound(){
		ResponseSpecification resp;
		resp = RestAssured.expect();
		resp.statusLine("HTTP/1.1 404 Not Found");
		resp.contentType(ContentType.JSON);
		
		RestAssured.baseURI = "https://reqres.in";
		given().when().get("api/unknown/23").then().spec(resp).assertThat().body("page", IsEqual.equalTo(null));
		
	}
	
	
//	GET
	@Test
	public void testDelayedResponse(){
		RestAssured.baseURI = "https://reqres.in";
		given().when().get("api/users?delay=3").then().spec(res).assertThat().body("page", IsEqual.equalTo(1));
		
	}
	
	
//	POST
	@Test
	public void testCreateUser(){
		RestAssured.baseURI = "https://reqres.in";
		HashMap<String,String> params = new HashMap<>();
		params.put("name", "Shivam");
		params.put("job", "QA");
		given().when().contentType("application/json").body(params).post("api/users").then().assertThat().body("name", IsEqual.equalTo("Shivam"));
		
	}
	
	
//	POST
	@Test
	public void testRegisterUserSuccessful(){
		RestAssured.baseURI = "https://reqres.in";
		HashMap<String,String> params = new HashMap<>();
		params.put("email", "eve.holt@reqres.in");
		params.put("password", "123456");
		given().when().contentType("application/json").body(params).post("api/register").then().assertThat().body("id", IsEqual.equalTo(4));
		
	}
	
	
//	POST
	@Test
	public void testRegisterUserUnsuccessful(){
		RestAssured.baseURI = "https://reqres.in";
		HashMap<String,String> params = new HashMap<>();
		params.put("email", "eve.holt@reqres.in");
		given().when().contentType("application/json").body(params).post("api/register").then().assertThat().body("error", IsEqual.equalTo("Missing password"));
		
	}
	
	
//	POST
	@Test
	public void testLoginSuccessful(){
		RestAssured.baseURI = "https://reqres.in";
		HashMap<String,String> params = new HashMap<>();
		params.put("email", "eve.holt@reqres.in");
		params.put("password", "123456");
		given().when().contentType("application/json").body(params).post("api/login").then().assertThat().body("token", IsEqual.equalTo("QpwL5tke4Pnpja7X4"));
		
	}
	
	
//	POST
	@Test
	public void testLoginUnSuccessful(){
		RestAssured.baseURI = "https://reqres.in";
		HashMap<String,String> params = new HashMap<>();
		params.put("email", "eve.holt@reqres.in");
		given().when().contentType("application/json").body(params).post("api/login").then().assertThat().body("error", IsEqual.equalTo("Missing password"));
		
	}
	
}



