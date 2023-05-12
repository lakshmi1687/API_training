package api_chaining;

import org.json.JSONObject;
import static io.restassured.RestAssured.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;



public class CreateUser {
	
	@Test
	void createUser(ITestContext context) {
		
		Faker faker = new Faker();
		
		JSONObject jo = new JSONObject();
		
		jo.put("name", faker.name().fullName());
		jo.put("email", faker.internet().emailAddress());
		jo.put("status", "inactive");
		
		int id=given()
		           .contentType("application/json")
		           .body(jo.toString())
		
		.when()
		  .post("https://reqres.in/api/users")
		  .jsonPath().getInt("id");
		System.out.println("Generated id is" + id);
		  
		  context.setAttribute("user_id", id);
		
	}

}
