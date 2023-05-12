package authentication;
import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

public class NoAuth implements Tokens {
	@Test
	void noAuth() {
		
		given()
		
		.get(noAuthURL)
		
		.then()
		.statusCode(200)
		.log().all();
	}

}
