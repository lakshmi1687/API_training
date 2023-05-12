package api_chaining;
import static io.restassured.RestAssured.*;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class GetUser {
	@Test
	void getUserUsingId(ITestContext context) {
		int id = (Integer) context.getAttribute("user_id");
		
		given()
		 .pathParam("id", id)
		
		.when()
		  .get("https://reqres.in/api/users/{id}")
		.then()
		  .statusCode(200)
		  .log().all();
	}

}
