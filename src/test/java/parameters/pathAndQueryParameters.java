package parameters;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

public class pathAndQueryParameters {
	
	//https://reqres.in/api/users?page=2&id=5
	@Test
	void testQueryAndPathParams() {
		given()
		.pathParam("mypath", "users")
		.queryParam("page", 2)
		.queryParam("id", 5)
		
		.when()
		.get("https://reqres.in/api/{mypath}")
			
		.then()
		.statusCode(200)
		.log().all();
		
	}

}
