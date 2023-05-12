package api_chaining;

import static io.restassured.RestAssured.given;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUser {
	@Test
	void update_user(ITestContext context) {
		
Faker faker = new Faker();
		
		JSONObject jo = new JSONObject();
		
		jo.put("name", faker.name().fullName());
		jo.put("email", faker.internet().emailAddress());
		jo.put("status", "active");
		
		int id = (Integer) context.getAttribute("user_id");
		
		given()
		           .contentType("application/json")
		           .pathParam("id", id)
		           .body(jo.toString())
		
		.when()
		  .put("https://reqres.in/api/users/{id}")
		  
		 .then()
		 .statusCode(200)
		 .log().all();
		 
	}

}
