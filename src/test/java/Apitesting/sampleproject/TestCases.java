package Apitesting.sampleproject;

import org.hamcrest.core.IsEqual;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

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
		ExtentReportManager.createReport();
		
	}
	
//	GET
	@Test(description="Fetching all user details.", testName="testFetchUsers")
	public void testFetchUsers(){
		ExtentReportManager.test = ExtentReportManager.getTest();
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI", "https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API Call", "GET");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource url", "api/users?page=2");
		ExtentReportManager.test.log(LogStatus.INFO, "Field Comparison", "page");
		RestAssured.baseURI = "https://reqres.in";
		given().when().get("api/users?page=2").then().spec(res).assertThat().body("page", IsEqual.equalTo(2));
		
	}
	
//	GET
	@SuppressWarnings("unchecked")
	@Test(description="Fetching single user details.", testName="testFetchUser")
	public void testFetchUser(){
		ExtentReportManager.test = ExtentReportManager.getTest();
//		ExtentReportManager.test = ExtentReportManager.reports.startTest("testFetchUser", "Fetching single user details.");
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI", "https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API Call", "GET");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource url", "api/users/2");
		ExtentReportManager.test.log(LogStatus.INFO, "Field Comparison", "data");
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
	@Test(description="User not found.", testName="testUserNotFound")
	public void testUserNotFound(){
		ExtentReportManager.test = ExtentReportManager.getTest();
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI", "https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API Call", "GET");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource url", "api/users/23");
		ExtentReportManager.test.log(LogStatus.INFO, "Field Comparison", "data");
		ResponseSpecification resp;
		resp = RestAssured.expect();
		resp.statusLine("HTTP/1.1 404 Not Found");
		resp.contentType(ContentType.JSON);
		
		RestAssured.baseURI = "https://reqres.in";
		given().when().get("api/users/23").then().spec(resp).assertThat().body("data", IsEqual.equalTo(null));
		
	}
	
//	GET
	@Test(description="Fetch list of resouces.", testName="testFetchListResource")
	public void testFetchListResource(){
		ExtentReportManager.test = ExtentReportManager.getTest();
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI", "https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API Call", "GET");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource url", "api/unknown");
		ExtentReportManager.test.log(LogStatus.INFO, "Field Comparison", "page");
		RestAssured.baseURI = "https://reqres.in";
		given().when().get("api/unknown").then().spec(res).assertThat().body("page", IsEqual.equalTo(1));
		
	}
	
//	GET
	@Test(description="Fetch single resouce.", testName="testFetchResource")
	public void testFetchResource(){
		ExtentReportManager.test = ExtentReportManager.getTest();
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI", "https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API Call", "GET");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource url", "api/unknown/2");
		ExtentReportManager.test.log(LogStatus.INFO, "Field Comparison", "data.id");
		RestAssured.baseURI = "https://reqres.in";
		given().when().get("api/unknown/2").then().spec(res).assertThat().body("data.id", IsEqual.equalTo(2));
		
	}
	
//	GET
	@Test(description="Resource not found.", testName="testFetchResourceNotFound")
	public void testFetchResourceNotFound(){
		ExtentReportManager.test = ExtentReportManager.getTest();
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI", "https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API Call", "GET");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource url", "api/unknown/23");
		ExtentReportManager.test.log(LogStatus.INFO, "Field Comparison", "page");
		ResponseSpecification resp;
		resp = RestAssured.expect();
		resp.statusLine("HTTP/1.1 404 Not Found");
		resp.contentType(ContentType.JSON);
		
		RestAssured.baseURI = "https://reqres.in";
		given().when().get("api/unknown/23").then().spec(resp).assertThat().body("page", IsEqual.equalTo(null));
		
	}
	
	
//	GET
	@Test(description="Fetch Delayed Response.", testName="testDelayedResponse")
	public void testDelayedResponse(){
		ExtentReportManager.test = ExtentReportManager.getTest();
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI", "https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API Call", "GET");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource url", "api/users?delay=3");
		ExtentReportManager.test.log(LogStatus.INFO, "Field Comparison", "page");
		RestAssured.baseURI = "https://reqres.in";
		given().when().get("api/users?delay=3").then().spec(res).assertThat().body("page", IsEqual.equalTo(1));
		
	}
	
	
//	POST
	@Test(description="Create a new User", testName="testCreateUser")
	public void testCreateUser(){
		ExtentReportManager.test = ExtentReportManager.getTest();
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI", "https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API Call", "POST");
		ExtentReportManager.test.log(LogStatus.INFO, "Payload", "{name: \"Shivam\", job: \"QA\"}");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource url", "api/users");
		ExtentReportManager.test.log(LogStatus.INFO, "Field Comparison", "name");
		RestAssured.baseURI = "https://reqres.in";
		HashMap<String,String> params = new HashMap<>();
		params.put("name", "Shivam");
		params.put("job", "QA");
		given().when().contentType("application/json").body(params).post("api/users").then().assertThat().body("name", IsEqual.equalTo("Shivam"));
		
	}
	
	
//	POST
	@Test(description="User register successful", testName="testRegisterUserSuccessful")
	public void testRegisterUserSuccessful(){
		ExtentReportManager.test = ExtentReportManager.getTest();
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI", "https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API Call", "POST");
		ExtentReportManager.test.log(LogStatus.INFO, "Payload", "{email: \"eve.holt@reqres.in\", password: \"123456\"}");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource url", "api/register");
		ExtentReportManager.test.log(LogStatus.INFO, "Field Comparison", "id");
		RestAssured.baseURI = "https://reqres.in";
		HashMap<String,String> params = new HashMap<>();
		params.put("email", "eve.holt@reqres.in");
		params.put("password", "123456");
		given().when().contentType("application/json").body(params).post("api/register").then().assertThat().body("id", IsEqual.equalTo(4));
		
	}
	
	
//	POST
	@Test(description="User register unsuccessful", testName="testRegisterUserUnsuccessful")
	public void testRegisterUserUnsuccessful(){
		ExtentReportManager.test = ExtentReportManager.getTest();
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI", "https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API Call", "POST");
		ExtentReportManager.test.log(LogStatus.INFO, "Payload", "{email: \"eve.holt@reqres.in\"}");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource url", "api/register");
		ExtentReportManager.test.log(LogStatus.INFO, "Field Comparison", "error");
		RestAssured.baseURI = "https://reqres.in";
		HashMap<String,String> params = new HashMap<>();
		params.put("email", "eve.holt@reqres.in");
		given().when().contentType("application/json").body(params).post("api/register").then().assertThat().body("error", IsEqual.equalTo("Missing password"));
		
	}
	
	
//	POST
	@Test(description="User login successful", testName="testLoginSuccessful")
	public void testLoginSuccessful(){
		ExtentReportManager.test = ExtentReportManager.getTest();
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI", "https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API Call", "POST");
		ExtentReportManager.test.log(LogStatus.INFO, "Payload", "{email: \"eve.holt@reqres.in\", password: \"123456\"}");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource url", "api/login");
		ExtentReportManager.test.log(LogStatus.INFO, "Field Comparison", "token");
		RestAssured.baseURI = "https://reqres.in";
		HashMap<String,String> params = new HashMap<>();
		params.put("email", "eve.holt@reqres.in");
		params.put("password", "123456");
		given().when().contentType("application/json").body(params).post("api/login").then().assertThat().body("token", IsEqual.equalTo("QpwL5tke4Pnpja7X4"));
		
	}
	
	
//	POST
	@Test(description="User login unsuccessful", testName="testLoginUnSuccessful")
	public void testLoginUnSuccessful(){
		ExtentReportManager.test = ExtentReportManager.getTest();
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI", "https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API Call", "POST");
		ExtentReportManager.test.log(LogStatus.INFO, "Payload", "{email: \"eve.holt@reqres.in\"}");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource url", "api/login");
		ExtentReportManager.test.log(LogStatus.INFO, "Field Comparison", "error");
		RestAssured.baseURI = "https://reqres.in";
		HashMap<String,String> params = new HashMap<>();
		params.put("email", "eve.holt@reqres.in");
		given().when().contentType("application/json").body(params).post("api/login").then().assertThat().body("error", IsEqual.equalTo("Missing password"));
		
	}
	
	@AfterClass
	public void closeReport() {
		ExtentReportManager.reports.flush();
		
	}
	
}



