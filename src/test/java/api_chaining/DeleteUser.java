package api_chaining;
import static io.restassured.RestAssured.given;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUser {
	@Test
	void deleteUser(ITestContext context) {
		int id = (Integer) context.getAttribute("user_id"); 
		
		given()
		   .pathParam("id", id)
		
		.when()
		
		  .delete("https://reqres.in/api/users/{id}")
		
		.then()
		   .statusCode(204)
		   .log().all();
	}

}
